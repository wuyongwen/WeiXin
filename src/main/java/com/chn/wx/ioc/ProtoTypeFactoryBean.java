package com.chn.wx.ioc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chn.common.InvokeUtils;
import com.chn.common.MethodUtils;
import com.chn.common.StringUtils;

public class ProtoTypeFactoryBean<T> extends FactoryBean<T> {

    public ProtoTypeFactoryBean(BeanFactory context) {
        super(context);
    }

    private Class<?> type;
    private FactoryBean<?>[] args;
    private Map<String, FactoryBean<?>> fields;
    
    @Override
    @SuppressWarnings("unchecked")
    public T get() throws Exception{

        Object result;
        if(args == null)
            result = type.newInstance();
        else {
            Class<?>[] argTypes = new Class<?>[args.length];
            Object[] argObjects = new Object[args.length];
            for(int i = 0; i < args.length; i ++) {
                argTypes[i] = args[i].getType();
                argObjects[i] = args[i].get();
            }
            result = MethodUtils.getConstructor(type, argTypes).newInstance(argObjects);
        }
        
        if(fields != null) {
            Iterator<Entry<String, FactoryBean<?>>> it = fields.entrySet().iterator();
            while(it.hasNext()) {
                Entry<String, FactoryBean<?>> entry = it.next();
                InvokeUtils.setFieldValue(result, entry.getKey(), entry.getValue().get());
            }
        }
        return (T) result;
    }

    @Override
    public Class<?> getType() {
        
        return type;
    }
    
    public void setType(Object type) {
        
        if(!(type instanceof String))
            throw new IllegalArgumentException("type 不允许非字符串类型");
        String typeStr = (String) type;
        if(StringUtils.isEmpty(typeStr))
            throw new IllegalArgumentException("类型不允许为空");
        try {
            this.type = Class.forName(typeStr);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("配置类未找到", e);
        }
    }
    
    public void setArgs(Object params) {
        
        if(params == null) return;
        if(params instanceof Collection) {
            List<?> paramList = (List<?>) params;
            args = new FactoryBean[paramList.size()];
            for(int i = 0; i < paramList.size(); i ++) 
                args[i] = context.from(paramList.get(i));
        } else {
            args = new FactoryBean[1];
            args[0] = context.from(params);
        }
    }
    
    public void setFields(Object params) {
        
        if(params == null) return;
        if(!(params instanceof Map))
            throw new IllegalArgumentException("fields 不允许非 map 类型");
        Map<?, ?> paramMap = (Map<?, ?>) params;
        fields = new HashMap<String, FactoryBean<?>>();
        Iterator<?> it = paramMap.entrySet().iterator();
        while(it.hasNext()) {
            Entry<?, ?> entry = (Entry<?, ?>) it.next();
            fields.put((String) entry.getKey(), context.from(entry.getValue()));
        }
    }

}
