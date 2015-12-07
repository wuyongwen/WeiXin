package com.chn.wx.ioc;

import com.chn.common.Lang;
import com.chn.wx.annotation.Node;
import com.chn.wx.dto.App;
import com.chn.wx.ioc.core.BeanFactory;
import com.chn.wx.ioc.provider.AnnotationProvider;
import com.chn.wx.ioc.provider.JsonIocProvider;


public class Ioc {

    private BeanFactory factory;
    
    public Ioc() {
        
        factory = new BeanFactory();
        factory.regist("beanFactory", factory);
        new AnnotationProvider<Node>("com.chn.wx.listener", Node.class).registTo(factory);
        new AnnotationProvider<Node>(App.getConfig("weixin.service.package"), Node.class).registTo(factory);
        new JsonIocProvider(new String(Lang.loadFromClassPath("/weixin.js"))).registTo(factory);
    }
    
}
