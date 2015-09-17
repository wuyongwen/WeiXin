package com.chn.wx.listener;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chn.common.Assert;
import com.chn.wx.annotation.Intercept;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.impl.beanfactory.BeanFactory;

public class ServiceAgent {

	private Logger log = Logger.getLogger(ServiceAgent.class);
	
	private Class<? extends Service> realService;
	private Map<String, ServiceAgent> nexts = new HashMap<>();
	private ServiceInterceptor[] interceptList = new ServiceInterceptor[0];
	
	public String doService(Context context) throws Exception {
		
		String result = null;
		context.setAttribute("serviceAgent", this);
		
		for(ServiceInterceptor intercept : interceptList) 
			if((result = intercept.preConstruct()) != null)
				return result;
		
		Service serviceInstance = BeanFactory.getInstance(realService, context);
		
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
		ServiceAgent holder = this.nexts.get(identify);
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
	
	public ServiceAgent registNext(String key, Class<? extends Service> serviceClass) {
		
		ServiceAgent agent = new ServiceAgent();
		agent.setRealServiceClass(serviceClass);
		this.registNext(key, agent);
		return agent;
	}
	
	public void registNext(String key, ServiceAgent agent) {
	    
	    this.nexts.put(key, agent);
	}
	
}
