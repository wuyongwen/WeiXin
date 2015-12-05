package com.chn.common;

public class Lang {

    public static Class<?> forName(String type) {
        
        try {
            return Class.forName(type);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("类型" + type + "未找到", e);
        }
    }
    
}
