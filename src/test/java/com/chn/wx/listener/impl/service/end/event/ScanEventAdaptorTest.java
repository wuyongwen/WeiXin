package com.chn.wx.listener.impl.service.end.event;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.chn.common.StringTemplate;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.impl.service.end.ServiceTest;

public class ScanEventAdaptorTest extends ServiceTest {

    StringTemplate template = StringTemplate.compile(
            "<xml>                                            " + 
            "<ToUserName><![CDATA[${toUser}]]></ToUserName>      " + 
            "<FromUserName><![CDATA[${fromUser}]]></FromUserName>" + 
            "<CreateTime>${createtime}</CreateTime>               " + 
            "<MsgType><![CDATA[event]]></MsgType>             " + 
            "<Event><![CDATA[SCAN]]></Event>                  " + 
            "<EventKey><![CDATA[${eventkey}]]></EventKey>     " + 
            "<Ticket><![CDATA[${ticket}]]></Ticket>              " + 
            "</xml>");
    String eventKey = randomString();
    String ticket = randomString();
    
    @Test
    public void test() throws Exception {
        
        Map<String, Object> params = new HashMap<>();
        params.put("toUser", toUserName);
        params.put("fromUser", fromUserName);
        params.put("createtime", createTime);
        params.put("eventkey", eventKey);
        params.put("ticket", ticket);
        Context context = this.doPostCtxt(template.replace(params));
        
        MockScanEventService service = this.preparToTest(MockScanEventService.class);
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Assert.assertEquals(toUserName, service.ToUserName);
        Assert.assertEquals(fromUserName, service.FromUserName);
        Assert.assertEquals(createTime, service.CreateTime);
        Assert.assertEquals(eventKey, service.EventKey);
        Assert.assertEquals(ticket, service.Ticket);
        Mockito.verify(service).doService(context);
    }

    public static class MockScanEventService extends ScanEventAdaptor {
        
    }
}
