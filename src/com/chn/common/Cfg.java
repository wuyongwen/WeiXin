/**
 * WeiXin
 * @title Cfg.java
 * @package com.chn.common
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2015年4月27日-下午9:01:46
 * @version V1.0
 * Copyright (c) 2015 ChineseAll.com All Right Reserved
 */
package com.chn.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.io.IOUtils;


/**
 * @class Cfg
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class Cfg {

    private Properties prop;
    
    /**
     * @description 每次调用都会引起文件加载，文件编码格式应为 UTF-8
     * @param path classpath，如果是classes目录下的config.properties那么传参应为 '/config.properties'
     * @return 
     */
    public static Cfg getCfg(String path) {
        
        return new Cfg(path);
    }
    
    private Cfg(String path) {
        
        InputStream is = this.getClass().getResourceAsStream(path);
        if (is != null) {
            try {
                Properties props = new Properties();
                props.load(new InputStreamReader(is, "UTF-8"));
                this.prop = props;
            } catch (IOException e) {
                throw new IllegalArgumentException("Config file[classpath: " + path + "] can't be loaded!", e);
            } finally {
                IOUtils.closeQuietly(is);
            }
        } else {
            throw new IllegalArgumentException("Config file[classpath: " + path + "] is not found!");
        }
    }
    
    public String get(String key) {
        
        return prop.getProperty(key);
    }
    
    public String get(String key, String defaultValue) {
        
        return prop.getProperty(key, defaultValue);
    }
    
}
