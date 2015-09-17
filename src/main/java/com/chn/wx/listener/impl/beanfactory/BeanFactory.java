package com.chn.wx.listener.impl.beanfactory;

import com.chn.wx.dto.Context;

public class BeanFactory {

    public static <T> T getInstance(Class<T> clazz, Context context) throws Exception {
        
        T result = getInstance(clazz);
        context.injectField(result);
        return result;
    }
    
    public static <T> T getInstance(Class<T> clazz) throws Exception {
        
        return clazz.newInstance();
    }
}
