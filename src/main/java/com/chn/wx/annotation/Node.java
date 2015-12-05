package com.chn.wx.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.chn.wx.listener.Service;

/**
 * @class Node
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface Node {

    public String value();
    
    public Class<? extends Service>[] parents();
    
}
