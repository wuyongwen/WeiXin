package com.chn.wx.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chn.common.Assert;
import com.chn.wx.dto.Context;

public class ServiceHolder {

	private Logger log = Logger.getLogger(ServiceHolder.class);
	
	private Class<? extends Service> realService;
	private Map<String, ServiceHolder> nexts = new HashMap<>();
	
	public String doService(Context context) throws Exception {
		
		context.setAttribute("serviceHolder", this);
		return this.buildService(context).doService(context);
	}
	
	public String routeToNext(String identify, Context context) throws Exception {
		
		if(identify == null) return null;
		ServiceHolder holder = this.nexts.get(identify);
		Assert.notNull(holder, String.format("类[%s]无[%s]子节点！", 
							   realService.getSimpleName(), 
							   identify));
		return holder.doService(context);
	}
	
	public Service buildService(Context context) throws Exception {
		
		Service result = realService.newInstance();
		context.injectField(result);
		return result;
	}
	
	public void setRealServiceClass(Class<? extends Service> serviceClass) {
		
		this.realService = serviceClass;
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
