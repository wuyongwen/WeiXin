/**
 * WeiXin
 * @title InvokeUtils.java
 * @package com.chn.common
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月16日-下午4:53:36
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.common;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @class InvokeUtils
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class InvokeUtils {

    /** 
     * 从对象 obj 中查找 field 字段，并取值 ,查不到时递归查找父类，均不存在时抛出异常
     * @param obj 待取值所在对象
     * @param field 待取字段
     * @return obj 中 field 字段值
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static final Object getFieldValue(Object obj, String field) throws 
    NoSuchFieldException {
        
        return getFieldValue(obj.getClass(), obj, field);
    }
    
    /** 
     * 按 clazz 查找对象 obj 中的字段 field 并取值，查不到抛出 NoSuchFieldException
     * @param clazz 要求 obj 必需为 clazz 的实例
     * @param obj 待取值所在对象
     * @param field 待取字段
     * @return obj 中 field 字段值
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static final Object getFieldValue(Class<?> clazz, Object obj, String field) 
    throws NoSuchFieldException {
        
        try {
            return FieldUtils.traverseField(clazz, field).get(obj);
        } catch (IllegalAccessException e) {
            // Not Accessable
        }
        throw new RuntimeException("This Exception Should Never Occured，If You " +
                "See This, Please Check InvokeUtils.class");
    }
    
    /** 
     * 从对象 obj 中查找 field 字段，并设值 value ,查不到时递归查找父类，均不存在时抛出异常
     * @param obj 待填值对象
     * @param field 待填字段
     * @return value 待填内容
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static final void setFieldValue(Object obj, String field, Object value) 
    throws NoSuchFieldException {
        
        Class<?> clazz = obj.getClass();
        setFieldValue(clazz, obj, field, value);
    }
    
    /** 
     * 按类 clazz 查找字段并设值，查不到抛出 NoSuchFieldException 
     * @param clazz 要求 obj 必需为 clazz 的实例
     * @param obj 待填值对象
     * @param field 待填字段
     * @return value 待填内容
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static final void setFieldValue(Class<?> clazz, Object obj, String field, 
        Object value) throws NoSuchFieldException {
        
        try {
            FieldUtils.traverseField(clazz, field).set(obj, value);
        } catch (IllegalAccessException e) {
            // Not Accessable
        }
    }
    
    /** 
     * 使用参数 param 调用 clazz 中的以 methodName 命名的静态方法 
     * @param clazz
     * @param methodName
     * @param param
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static final <T> T invokeStatic(Class<?> clazz, String methodName, int param) throws Exception {
        
        Method method = clazz.getMethod(methodName, int.class);
        if(!method.isAccessible()) {
            method.setAccessible(true);
        }
        if(!Modifier.isStatic(method.getModifiers())) {
            throw new RuntimeException("非静态方法！！");
        }
        return (T) method.invoke(null, param);
    }
    
}
