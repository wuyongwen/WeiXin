package com.chn.wx.ioc.provider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.chn.common.Json;
import com.chn.wx.ioc.core.BeanFactory;

public class JsonIocProvider implements IocProvider {

    private Logger log = Logger.getLogger(JsonIocProvider.class);
    private Map<String, Object> ioc;
    
    public JsonIocProvider(String src) {
        
        try {
            ioc = Json.parse(new StringReader(src));
        } catch (IOException e) {
            log.error("Json 串加载失败", e);
        }
    }
    
    public JsonIocProvider(byte[] src) {
        
        try {
            ioc = Json.parse(new InputStreamReader(new ByteArrayInputStream(src), "UTF-8"));
        } catch (IOException e) {
            log.error("Json 串加载失败", e);
        }
    }
    
    @Override
    public void registTo(BeanFactory factory) {
        
        Iterator<Entry<String, Object>> it = ioc.entrySet().iterator();
        while(it.hasNext()) {
            Entry<String, Object> entry = it.next();
            factory.regist(entry.getKey(), entry.getValue());
        }
    }
    
}
