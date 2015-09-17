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

import java.util.LinkedHashSet;
import java.util.Set;

import com.chn.common.Cfg;
import com.chn.common.Exec;
import com.chn.common.Scans;
import com.chn.common.StringUtils;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.ThreadsMode;
import com.chn.wx.listener.ThreadsMode.ClassProvider;
import com.chn.wx.listener.impl.process.AsyncThreadMode;
import com.chn.wx.listener.impl.process.SyncThreadMode;

/**
 * @class MessageHandler
 * @author lzxz1234
 * @description
 * @version v1.0
 */
public class MessageHandler {

    private ThreadsMode proxy;
    
	public void init() {

		this.init(Cfg.getCfg("/weixin.properties"));
	}
	
	public void init(Cfg cfg) {
	    
	    String packages = cfg.get("weixin.service.package");
        String executeMode = cfg.get("weixin.service.async");
        String innerexecSize = cfg.get("weixin.service.innerexec.size");

        Exec.init(Integer.parseInt(innerexecSize));
        proxy = "TRUE".equalsIgnoreCase(executeMode) ? new AsyncThreadMode() 
                                                     : new SyncThreadMode();
        
        proxy.loadClass(new PackageClassProvider("com.chn.wx.listener.impl.service"),
                        new PackageClassProvider(packages));
	}

	public ThreadsMode getThreadsMode() {
	    
	    return proxy;
	}
	
	public String process(Context context) {

		return proxy.process(context);
	}

	public void destroy() {
	    
	    Exec.destroy();
	}

	public static class PackageClassProvider implements ClassProvider {

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
