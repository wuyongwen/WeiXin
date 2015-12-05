package com.chn.wx.ioc;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class BeanFactory {

    private Map<String, FactoryBean<?>> map = new HashMap<>();
    
    public <T> T get(String name) throws Exception {
        
        return (T) map.get(name).get();
    }
    
    public void regist(String name, Object obj) {
        
        FactoryBean<?> target = from(obj);
        this.map.put(name, target);
    }
    
    protected <T> FactoryBean<T> from(Object obj) {
        
        if(obj instanceof Map) 
            return fromMap((Map<?, ?>) obj);
        return new PrimitiveTypeFactoryBean<T>(this, (T) obj);
    }
    
    private <T> ProtoTypeFactoryBean<T> fromMap(Map<?, ?> params) {
        
        if(params == null || params.size() == 0)
            throw new IllegalArgumentException("配置项错误");
        ProtoTypeFactoryBean<T> result = new ProtoTypeFactoryBean<T>(this);
        result.setType(params.get("type"));
        result.setArgs(params.get("args"));
        result.setFields(params.get("fields"));
        return result;
    }

}
