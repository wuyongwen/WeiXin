package com.chn.wx.listener.impl.service.end.message;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.chn.common.StringTemplate;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.impl.service.end.ServiceTest;

public class VoiceMessageAdaptorTest extends ServiceTest {

    StringTemplate stp = StringTemplate.compile(
            "<xml>                                            " + 
            "<ToUserName><![CDATA[${toUser}]]></ToUserName>      " + 
            "<FromUserName><![CDATA[${fromUser}]]></FromUserName>" + 
            "<CreateTime>${createTime}</CreateTime>              " + 
            "<MsgType><![CDATA[voice]]></MsgType>             " + 
            "<MediaId><![CDATA[${media_id}]]></MediaId>          " + 
            "<Format><![CDATA[${Format}]]></Format>              " + 
            "<MsgId>${msgId}</MsgId>                  " + 
            "</xml>");
    String toUserName = "toUserName";
    String fromUserName = "fromUserName";
    String createTime = "" + System.currentTimeMillis();
    String format = "speex";
    String mediaId = "mediaid";
    String msgId = UUID.randomUUID().toString();
    
    String expectReturn = "expectString";
    
    @Test
    public void test() throws Exception {
        
        MockVoiceMessageService service = this.preparToTest(MockVoiceMessageService.class);
        Map<String, Object> params = new HashMap<>();
        params.put("toUser", toUserName);
        params.put("fromUser", fromUserName);
        params.put("createTime", createTime);
        params.put("Format", format);
        params.put("media_id", mediaId);
        params.put("msgId", msgId);
        
        Context context = this.doPostCtxt(stp.replace(params));
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Assert.assertEquals(toUserName, service.ToUserName);
        Assert.assertEquals(fromUserName, service.FromUserName);
        Assert.assertEquals(createTime, service.CreateTime);
        Assert.assertEquals(format, service.Format);
        Assert.assertEquals(mediaId, service.MediaId);
        Assert.assertEquals(msgId, service.MsgId);
    }

    public static class MockVoiceMessageService extends VoiceMessageAdaptor {
        
    }
    
}
