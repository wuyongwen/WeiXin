package com.chn.wx.ioc.provider;

public interface IocProvider {

    public <T> T getObject(String name) throws Exception;
    
}
