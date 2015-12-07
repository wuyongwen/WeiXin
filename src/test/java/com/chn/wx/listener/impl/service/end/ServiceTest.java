package com.chn.wx.listener.impl.service.end;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

import com.chn.common.Lang;
import com.chn.common.StringUtils;
import com.chn.wx.annotation.Node;
import com.chn.wx.dto.App;
import com.chn.wx.dto.Context;
import com.chn.wx.ioc.core.BeanFactory;
import com.chn.wx.ioc.core.FactoryBean;
import com.chn.wx.ioc.provider.AnnotationProvider;
import com.chn.wx.ioc.provider.JsonIocProvider;
import com.chn.wx.listener.ThreadsMode;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.crypto.*", "org.apache.log4j.*"})
public abstract class ServiceTest {

    protected ThreadsMode handler;
    
    protected String toUserName = randomString();
    protected String fromUserName = randomString();
    protected String msgId = randomString();
    protected String createTime = Long.toString(System.currentTimeMillis());
    protected String expectReturn = randomString();
    
    protected String randomString() {
        
        return UUID.randomUUID().toString();
    }
    
    protected Context doGetCtxt(String params) {
        
        return doTestCtxt("GET", params, null);
    }
    
    protected Context doPostCtxt(String xmlContent) {
        
        return doTestCtxt("POST", null, xmlContent);
    }
    
    protected Context doPostCtxt(String params, String xmlContent) {
        
        return doTestCtxt("POST", params, xmlContent);
    }
    
    private Context doTestCtxt(String method, String params, String xmlContent) {
        
        Context context = new Context(decodeParams(params));
        context.setAttribute("method", method.toUpperCase());
        context.setAttribute("xmlContent", xmlContent);
        context.setAttribute("threadsMode", handler);
        return context;
    }
    
    private Map<String, Object> decodeParams(String paramstr) {
        
        Map<String, Object> params = new HashMap<>();
        if(!StringUtils.isEmpty(paramstr)) {
            String[] pair = paramstr.split("&");
            for(String each : pair) {
                String[] keyValue = each.split("=");
                if(keyValue.length == 2)
                    params.put(keyValue[0], keyValue[1]);
            }
        }
        return params;
    }
    
    public <T> T preparToTest(Class<T> clazz) throws Exception {
        
        T instance = Mockito.mock(clazz);
        BeanFactory factory = new BeanFactory();
        factory.regist("beanFactory", factory);
        new AnnotationProvider<Node>("com.chn.wx.listener", Node.class).registTo(factory);
        new AnnotationProvider<Node>(App.getConfig("weixin.service.package"), Node.class).registTo(factory);
        new JsonIocProvider(new String(Lang.loadFromClassPath("/weixin.js"))).registTo(factory);
        
        factory.replace(clazz.getSuperclass().getName(), instance);
        FactoryBean<ThreadsMode> factoryBean = factory.get("root");
        handler = factoryBean.get();
        return instance;
    }
    
}
