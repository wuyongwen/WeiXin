package com.chn.wx.listener;

public interface ServiceInterceptor {

	/**
	 * Service 实例构造并注入完成时执行
	 * @return 返回 null 时不影响后续执行，任何非 null 返回会截断后续逻辑直接返回客户端
	 */
	public String preConstruct();
	
	/**
	 * 执行 Service.doService() 前执行
	 * @return 返回 null 时不影响后续执行，任何非 null 返回会截断后续逻辑直接返回客户端
	 */
	public String preService();
	
	/**
	 * 执行 Service.doService() 后执行
	 * @return 返回 null 时不影响后续执行，任何非 null 返回会截断后续逻辑直接返回客户端
	 */
	public String postService();
	
}
