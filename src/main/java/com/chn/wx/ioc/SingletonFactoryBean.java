package com.chn.wx.ioc;

import com.chn.wx.ioc.core.BeanFactory;
import com.chn.wx.ioc.core.ProtoTypeFactoryBean;

public class SingletonFactoryBean<T> extends ProtoTypeFactoryBean<T> {

    private T cached;
    
    public SingletonFactoryBean(BeanFactory context) {
        
        super(context);
    }

    @Override
    public T get() throws Exception {
        
        if(cached == null) {
            synchronized(this) {
                if(cached == null)
                    cached = super.get();
            }
        }
        return cached;
    }
    
}
