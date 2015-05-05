/**
 * WeiXin
 * @title MessageHandler.java
 * @package com.chn.wx
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2015年4月29日-下午10:17:34
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.chn.common.Cfg;
import com.chn.common.Scans;
import com.chn.common.StringUtils;
import com.chn.wx.annotation.Node;
import com.chn.wx.core.Service;
import com.chn.wx.core.ServiceHolder;
import com.chn.wx.dto.Context;

/**
 * @class MessageHandler
 * @author lzxz1234
 * @description
 * @version v1.0
 */
public class MessageHandler {

	private ServiceHolder root = new ServiceHolder();

	public void init() {

		Cfg cfg = Cfg.getCfg("/weixin.properties");
		String packages = cfg.get("weixin.service.package");

		this.loadClass(new PackageClassProvider("com.chn.wx.listener"),
		               new PackageClassProvider(packages));
	}

	public String process(Context context) throws Exception {

		return root.doService(context);
	}

	public void destroy() {

	}

	public void loadClass(ClassProvider... providers) {

		LinkedHashSet<Class<?>> allClass = new LinkedHashSet<>();
		for (ClassProvider provider : providers)
			allClass.addAll(provider.getClasses());
		this.loopload(this.filtService(allClass), root);
	}
	
	private synchronized void loopload(Map<Node, 
			                           Class<? extends Service>> allNodes, 
			                           ServiceHolder holder) {

		for (Entry<Node, Class<? extends Service>> entry : allNodes.entrySet()) {
			Node node = entry.getKey();
			Class<? extends Service> serviceClass = entry.getValue();
			if (root.getRealService() == null) {
				// TODO: 为什么 parent 不能为 null?
				if ("root".equals(node.value())) {
					root.setRealServiceClass(serviceClass);
					loopload(allNodes, holder);
				}
			} else {
				if (root.getRealService().equals(node.parents())) {
					loopload(allNodes, root.registNext(node.value(), serviceClass));
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Map<Node, Class<? extends Service>> filtService(Set<Class<?>> allClasses) {

		Map<Node, Class<? extends Service>> result = new HashMap<>();
		for (Class<?> clazz : allClasses)
			if (clazz.isAnnotationPresent(Node.class))
				result.put(clazz.getAnnotation(Node.class),
						(Class<? extends Service>) clazz);
		return result;
	}

	public static interface ClassProvider {

		public Set<Class<?>> getClasses();
	}

	private static class PackageClassProvider implements ClassProvider {

		private String packages;

		public PackageClassProvider(String packages) {
			this.packages = packages;
		}

		@Override
		public Set<Class<?>> getClasses() {

			Set<Class<?>> result = new LinkedHashSet<>();
			if (StringUtils.isEmpty(packages))
				return result;
			for (String pkg : packages.split("\\|"))
				if (!StringUtils.isEmpty(pkg))
					result.addAll(Scans.getClasses(pkg));
			return result;
		}

	}
}
