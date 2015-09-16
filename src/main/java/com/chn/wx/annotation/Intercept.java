package com.chn.wx.annotation;

import com.chn.wx.listener.ServiceInterceptor;

public @interface Intercept {

	public Class<? extends ServiceInterceptor>[] value();
	
}
