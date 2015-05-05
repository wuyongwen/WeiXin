package com.chn.wx.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chn.common.Assert;
import com.chn.wx.annotation.Intercept;
import com.chn.wx.dto.Context;

public class ServiceHolder {

	private Logger log = Logger.getLogger(ServiceHolder.class);
	
	private Class<? extends Service> realService;
	private Map<String, ServiceHolder> nexts = new HashMap<>();
	private ServiceInterceptor[] interceptList = new ServiceInterceptor[0];
	
	public String doService(Context context) throws Exception {
		
		String result = null;
		context.setAttribute("serviceHolder", this);
		
		for(ServiceInterceptor intercept : interceptList) 
			if((result = intercept.preConstruct()) != null)
				return result;
		
		Service serviceInstance = realService.newInstance();
		context.injectField(serviceInstance);
		
		for(ServiceInterceptor intercept : interceptList) 
			if((result = intercept.preService()) != null)
				return result;
		
		String serviceResult = serviceInstance.doService(context);
		
		for(ServiceInterceptor intercept : interceptList) 
			if((result = intercept.postService()) != null)
				return result;
		
		return serviceResult;
	}
	
	public String routeToNext(String identify, Context context) throws Exception {
		
		if(identify == null) return null;
		ServiceHolder holder = this.nexts.get(identify);
		Assert.notNull(holder, String.format("类[%s]无[%s]子节点！", 
							   realService.getSimpleName(), 
							   identify));
		return holder.doService(context);
	}
	
	public void setRealServiceClass(Class<? extends Service> serviceClass) {
		
		this.realService = serviceClass;
		Intercept interceptAnno = serviceClass.getAnnotation(Intercept.class);
		if(interceptAnno != null) {
			this.interceptList = new ServiceInterceptor[interceptAnno.value().length];
			for(int i = 0; i < interceptAnno.value().length; i ++) {
				try {
					this.interceptList[i] = interceptAnno.value()[i].newInstance();
				} catch (Exception e) {
					log.error("不存在无参构造方法", e);
					System.exit(0);
				}
			}
		}
	}
	
	public Class<? extends Service> getRealService() {
		
		return this.realService;
	}
	
	public ServiceHolder registNext(String key, Class<? extends Service> serviceClass) {
		
		ServiceHolder holder = new ServiceHolder();
		holder.setRealServiceClass(serviceClass);
		this.nexts.put(key, holder);
		log.info(String.format("类[%s]的后续结点[%s]被标记为[%s]", 
								realService.getSimpleName(), 
								key, 
								serviceClass.getSimpleName()));
		return holder;
	}
	
}
