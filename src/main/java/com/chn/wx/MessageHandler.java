package com.chn.wx;

import com.chn.common.Lang;
import com.chn.wx.annotation.Node;
import com.chn.wx.dto.App;
import com.chn.wx.dto.Context;
import com.chn.wx.ioc.core.BeanFactory;
import com.chn.wx.ioc.core.FactoryBean;
import com.chn.wx.ioc.provider.AnnotationProvider;
import com.chn.wx.ioc.provider.JsonIocProvider;
import com.chn.wx.listener.ThreadsMode;

/**
 * @class MessageHandler
 * @author lzxz1234
 * @description
 * @version v1.0
 */
public class MessageHandler {

    private ThreadsMode proxy;
    private BeanFactory factory;
    
    public MessageHandler() throws Exception {
        
        factory = new BeanFactory();
        factory.regist("beanFactory", factory);
        new AnnotationProvider<Node>("com.chn.wx.listener", Node.class).registTo(factory);
        new AnnotationProvider<Node>(App.getConfig("weixin.service.package"), Node.class).registTo(factory);
        new JsonIocProvider(new String(Lang.loadFromClassPath("/weixin.js"))).registTo(factory);
        FactoryBean<ThreadsMode> factoryBean = factory.get("root");
        proxy = factoryBean.get();
    }

    public String process(Context context) throws Exception {
        context.setAttribute("threadsMode", proxy);
        return proxy.process(context);
    }

}
