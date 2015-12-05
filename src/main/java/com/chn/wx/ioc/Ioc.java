package com.chn.wx.ioc;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.chn.common.Json;

public class Ioc {

    private BeanFactory factory;
    
    @SuppressWarnings("unchecked")
    public Ioc(Reader is) throws IOException {
        
        factory = new BeanFactory();
        Map<String, Object> ioc = (Map<String, Object>) Json.parse(is);
        Iterator<Entry<String, Object>> it = ioc.entrySet().iterator();
        while(it.hasNext()) {
            Entry<String, Object> entry = it.next();
            factory.regist(entry.getKey(), entry.getValue());
        }
    }
    
    public <T> T getObject(String name) throws Exception {
        
        return factory.get(name);
    }
}
