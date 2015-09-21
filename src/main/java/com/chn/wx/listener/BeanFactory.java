package com.chn.wx.listener;

import com.chn.wx.dto.Context;

/**
 * Bean 工厂，预留接口
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
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
