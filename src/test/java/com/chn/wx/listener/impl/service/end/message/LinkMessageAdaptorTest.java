package com.chn.wx.listener.impl.service.end.message;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.chn.common.StringTemplate;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.impl.service.end.ServiceTest;

public class LinkMessageAdaptorTest extends ServiceTest {

    StringTemplate stp = StringTemplate.compile(
            "<xml>                                                   " +
            "<ToUserName><![CDATA[${toUser}]]></ToUserName>             " +
            "<FromUserName><![CDATA[${fromUser}]]></FromUserName>       " +
            "<CreateTime>${createTime}</CreateTime>                     " +
            "<MsgType><![CDATA[link]]></MsgType>                     " +
            "<Title><![CDATA[${title}]]></Title>             " +
            "<Description><![CDATA[${description}]]></Description> " +
            "<Url><![CDATA[${url}]]></Url>                              " +
            "<MsgId>${msgId}</MsgId>                         " +
            "</xml> ");
    String title = randomString();
    String description = randomString();
    String url = randomString();
    
    @Test
    public void test() throws Exception {
        
        Map<String, Object> params = new HashMap<>();
        params.put("toUser", toUserName);
        params.put("fromUser", fromUserName);
        params.put("createTime", createTime);
        params.put("msgId", msgId);
        params.put("title", title);
        params.put("description", description);
        params.put("url", url);
        MockLinkService service = this.preparToTest(MockLinkService.class);
        Context context = this.doPostCtxt(stp.replace(params));
        
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Assert.assertEquals(toUserName, service.ToUserName);
        Assert.assertEquals(fromUserName, service.FromUserName);
        Assert.assertEquals(createTime, service.CreateTime);
        Assert.assertEquals(msgId, service.MsgId);
        Assert.assertEquals(title, service.Title);
        Assert.assertEquals(description, service.Description);
        Assert.assertEquals(url, service.Url);
        Mockito.verify(service).doService(context);
    }

    public static class MockLinkService extends LinkMessageAdaptor {
        
    }
    
}
