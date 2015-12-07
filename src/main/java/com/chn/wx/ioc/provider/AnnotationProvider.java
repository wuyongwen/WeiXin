package com.chn.wx.ioc.provider;

import java.lang.annotation.Annotation;

import com.chn.common.Scans;
import com.chn.common.StringUtils;
import com.chn.wx.ioc.core.BeanFactory;

public class AnnotationProvider<A extends Annotation> implements IocProvider {

    private String[] packages;
    private Class<A> anno;
    
    public AnnotationProvider(String packages, Class<A> anno) {
        
        this.packages = packages.split("\\|");
        this.anno = anno;
    }

    @Override
    public void registTo(BeanFactory factory) {
        
        for(String each : packages) 
            if(!StringUtils.isEmpty(each))
                for(Class<?> clazz : Scans.getClasses(each)) 
                    if(clazz.getAnnotation(anno) != null)
                        factory.regist(clazz.getName(), clazz);
    }

}
