package com.chn.wx.listener.impl.service.end;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.chn.common.StringUtils;
import com.chn.wx.MessageHandler;
import com.chn.wx.MessageHandler.PackageClassProvider;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.ThreadsMode.ClassProvider;
import com.chn.wx.listener.impl.beanfactory.BeanFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BeanFactory.class}) 
public abstract class ServiceTest {

    protected static MessageHandler handler = new MessageHandler();
    static {
        
        try {
            handler.init();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    protected Context doGetCtxt(String params) {
        
        return doTestCtxt("GET", params, null);
    }
    
    protected Context doPostCtxt(String xmlContent) {
        
        return doTestCtxt("POST", null, xmlContent);
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
    
    public void reRegistAndLoad(final Class<?>... classes) {
        
        handler.getThreadsMode().loadClass(new PackageClassProvider("com.chn.wx.listener.impl.service"), 
                new ClassProvider() {
                    @Override
                    public Set<Class<?>> getClasses() {
                        Set<Class<?>> result = new HashSet<Class<?>>();
                        for(Class<?> clazz : classes) result.add(clazz);
                        return result;
                    }
        });
    }
    
    public <T> void registReturn(Class<T> clazz, T result) throws Exception {
        
        PowerMockito.whenNew(clazz.getConstructor()).withNoArguments().thenReturn(result);
    }
}
