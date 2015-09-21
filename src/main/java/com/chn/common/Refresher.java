package com.chn.common;

/**
 * 自动刷新工具类
 * @author lzxz1234
 * @version v1.0
 */
public abstract class Refresher<T> {

    protected T current;
    
    public abstract T refresh();
    
    public abstract boolean isExpired();
    
    public T get() {
        
        if(isExpired()) 
            current = refresh();
        return current;
    }
    
}
