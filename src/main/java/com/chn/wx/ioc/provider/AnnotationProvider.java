package com.chn.wx.ioc.provider;

import java.lang.annotation.Annotation;

import com.chn.common.Scans;
import com.chn.wx.ioc.core.BeanFactory;

public class AnnotationProvider<A extends Annotation> implements IocProvider {

    private BeanFactory factory;
    
    public AnnotationProvider(String packagestr, Class<A> anno) {
        
        factory = new BeanFactory();
        String[] packages = packagestr.split("\\|");
        for(String each : packages) 
            for(Class<?> clazz : Scans.getClasses(each)) 
                if(clazz.getAnnotation(anno) != null)
                    factory.regist(clazz.getName(), clazz);
    }
    
    @Override
    public <T> T getObject(String name) throws Exception {
        
        return factory.get(name);
    }

}
