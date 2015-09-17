package com.chn.wx.listener.impl.beanfactory;

import com.chn.wx.dto.Context;
import com.chn.wx.listener.impl.service.end.CertifyServiceTest.MockCertifyService;

public class BeanFactory {

    public static <T> T getInstance(Class<T> clazz, Context context) throws Exception {
        
        T result = clazz.newInstance();
        if(clazz.equals(MockCertifyService.class))
            result = (T) new MockCertifyService();
        context.injectField(result);
        return result;
    }

}
