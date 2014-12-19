/**
 * WeiXin
 * @title Param.java
 * @package com.chn.wx.annotation
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月15日-下午6:13:12
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @class Param
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {
    
  public String value() default "";

  public boolean required() default true;

  public String defaultValue() default "";
  
}
