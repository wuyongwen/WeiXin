package com.chn.wx.listener.impl.service.end;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.chn.common.StringUtils;
import com.chn.wx.MessageHandler;
import com.chn.wx.MessageHandler.PackageClassProvider;
import com.chn.wx.dto.Context;
import com.chn.wx.ioc.Ioc;
import com.chn.wx.listener.BeanFactory;
import com.chn.wx.listener.impl.process.AsyncThreadMode;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BeanFactory.class}) 
@PowerMockIgnore({"javax.crypto.*", "org.apache.log4j.*"})
public abstract class ServiceTest {

    protected Ioc ioc;
    protected MessageHandler handler;
    {
        
        try {
            InputStream is = Ioc.class.getResourceAsStream("/weixin.js");
            Ioc ioc = new Ioc(new InputStreamReader(is));
            handler = ioc.getObject("root");
            PowerMockito.mockStatic(BeanFactory.class, new Answer<Object>() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    return invocation.callRealMethod();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
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
        handler.setThreadsMode(new AsyncThreadMode(new PackageClassProvider("com.chn.wx.listener.impl.service|" + clazz.getName())));
        PowerMockito.when(BeanFactory.getInstance(clazz)).thenReturn(instance);
        return instance;
    }
    
}
