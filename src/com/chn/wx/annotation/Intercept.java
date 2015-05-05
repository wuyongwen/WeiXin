package com.chn.wx.annotation;

import com.chn.wx.core.ServiceInterceptor;

public @interface Intercept {

	public Class<? extends ServiceInterceptor>[] value();
	
}
