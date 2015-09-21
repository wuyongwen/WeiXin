package com.chn.wx.listener.impl.service.end.message;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.chn.common.StringTemplate;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.impl.service.end.ServiceTest;

public class LocationMessageAdaptorTest extends ServiceTest {

    StringTemplate stp = StringTemplate.compile(
            "<xml>                                             " + 
            "<ToUserName><![CDATA[${toUser}]]></ToUserName>       " + 
            "<FromUserName><![CDATA[${fromUser}]]></FromUserName> " + 
            "<CreateTime>${createTime}</CreateTime>               " + 
            "<MsgType><![CDATA[location]]></MsgType>           " + 
            "<Location_X>${x}</Location_X>                " + 
            "<Location_Y>${y}</Location_Y>               " + 
            "<Scale>${scale}</Scale>                                 " + 
            "<Label><![CDATA[${label}]]></Label>               " + 
            "<MsgId>${msgId}</MsgId>                   " + 
            "</xml>");
    String x = randomString();
    String y = randomString();
    String scale = randomString();
    String label = randomString();
    
    @Test
    public void test() throws Exception {
        
        Map<String, Object> params = new HashMap<>();
        params.put("toUser", toUserName);
        params.put("fromUser", fromUserName);
        params.put("createTime", createTime);
        params.put("x", x);
        params.put("y", y);
        params.put("scale", scale);
        params.put("label", label);
        params.put("msgId", msgId);
        Context context = this.doPostCtxt(stp.replace(params));
        
        MockLocationService service = this.preparToTest(MockLocationService.class);
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Assert.assertEquals(toUserName, service.ToUserName);
        Assert.assertEquals(fromUserName, service.FromUserName);
        Assert.assertEquals(createTime, service.CreateTime);
        Assert.assertEquals(msgId, service.MsgId);
        Assert.assertEquals(x, service.Location_X);
        Assert.assertEquals(y, service.Location_Y);
        Assert.assertEquals(scale, service.Scale);
        Assert.assertEquals(label, service.Label);
        Mockito.verify(service).doService(context);
    }

    public static class MockLocationService extends LocationMessageAdaptor {
        
    }
    
}
