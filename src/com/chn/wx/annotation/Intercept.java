package com.chn.wx.annotation;

import com.chn.wx.monitor.ServiceInterceptor;

public @interface Intercept {

	public Class<? extends ServiceInterceptor>[] value();
	
}
