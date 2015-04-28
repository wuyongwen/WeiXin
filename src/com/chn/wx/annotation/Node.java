/**
 * WeiXin
 * @title Node.java
 * @package com.chn.wx.annotation
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月15日-下午6:03:16
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
    
    public Class<?> parent();
    
}
