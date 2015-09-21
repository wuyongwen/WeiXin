package com.chn.wx.listener.impl.service.end.message;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.chn.common.StringTemplate;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.impl.service.end.ServiceTest;

public class VideoMessageAdaptorTest extends ServiceTest {

    StringTemplate stp = StringTemplate.compile(
            "<xml>                                                   " + 
            "<ToUserName><![CDATA[${toUser}]]></ToUserName>             " + 
            "<FromUserName><![CDATA[${fromUser}]]></FromUserName>       " + 
            "<CreateTime>${createTime}</CreateTime>                     " + 
            "<MsgType><![CDATA[video]]></MsgType>                    " + 
            "<MediaId><![CDATA[${media_id}]]></MediaId>                 " + 
            "<ThumbMediaId><![CDATA[${thumb_media_id}]]></ThumbMediaId> " + 
            "<MsgId>${msgId}</MsgId>                         " + 
            "</xml>");
    String mediaId = "mediaid";
    String thumbMediaId = "thumbmediaid";
    
    @Test
    public void test() throws Exception {
        
        MockVideoMessageService service = this.preparToTest(MockVideoMessageService.class);
        Map<String, Object> params = new HashMap<>();
        params.put("toUser", toUserName);
        params.put("fromUser", fromUserName);
        params.put("createTime", createTime);
        params.put("thumb_media_id", thumbMediaId);
        params.put("media_id", mediaId);
        params.put("msgId", msgId);
        
        Context context = this.doPostCtxt(stp.replace(params));
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Assert.assertEquals(toUserName, service.ToUserName);
        Assert.assertEquals(fromUserName, service.FromUserName);
        Assert.assertEquals(createTime, service.CreateTime);
        Assert.assertEquals(thumbMediaId, service.ThumbMediaId);
        Assert.assertEquals(mediaId, service.MediaId);
        Assert.assertEquals(msgId, service.MsgId);
        Mockito.verify(service).doService(context);
    }

    public static class MockVideoMessageService extends VideoMessageAdaptor {

        @Override
        public String doService(Context context) throws Exception {
            throw new RuntimeException();
        }

    }
    
}
