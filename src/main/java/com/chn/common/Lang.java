package com.chn.common;

import java.io.InputStream;

public class Lang {

    public static Class<?> forName(String type) {
        
        try {
            return Class.forName(type);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("类型" + type + "未找到", e);
        }
    }
    
    public static byte[] loadFromClassPath(String path) {
        
        try {
            InputStream is = Lang.class.getResourceAsStream(path);
            return IOUtils.toByteArray(is);
        } catch (Exception e) {
            throw new RuntimeException("从 classpath 加载文件 " + path + "失败", e);
        }
    }
    
}
