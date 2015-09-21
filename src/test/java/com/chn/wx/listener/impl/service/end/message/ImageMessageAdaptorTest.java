package com.chn.wx.listener.impl.service.end.message;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.chn.common.StringTemplate;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.impl.service.end.ServiceTest;

public class ImageMessageAdaptorTest extends ServiceTest {

    StringTemplate stp = StringTemplate.compile(
            "<xml>                                            " + 
            "<ToUserName><![CDATA[${toUser}]]></ToUserName>      " + 
            "<FromUserName><![CDATA[${fromUser}]]></FromUserName>" + 
            "<CreateTime>${createTime}</CreateTime>              " + 
            "<MsgType><![CDATA[image]]></MsgType>             " + 
            "<PicUrl><![CDATA[${picUrl}]]></PicUrl>       " + 
            "<MediaId><![CDATA[${media_id}]]></MediaId>          " + 
            "<MsgId>${msgId}</MsgId>                  " + 
            "</xml>");
    String toUserName = "toUserName";
    String fromUserName = "fromUserName";
    String createTime = "" + System.currentTimeMillis();
    String picUrl = "http://picurl.com";
    String mediaId = "mediaid";

    String expectReturn = "expectString";
    
    @Test
    public void testRaw() throws Exception {
        
        MockImageMessageService service = this.preparToTest(MockImageMessageService.class);
        
        Map<String, Object> params = new HashMap<>();
        params.put("toUser", toUserName);
        params.put("fromUser", fromUserName);
        params.put("createTime", createTime);
        params.put("picUrl", picUrl);
        params.put("media_id", mediaId);
        Context context = this.doPostCtxt(stp.replace(params));
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Assert.assertEquals(toUserName, service.ToUserName);
        Assert.assertEquals(fromUserName, service.FromUserName);
        Assert.assertEquals(createTime, service.CreateTime);
        Assert.assertEquals(picUrl, service.PicUrl);
        Assert.assertEquals(mediaId, service.MediaId);
    }

    public static class MockImageMessageService extends ImageMessageAdaptor {

        @Override
        public String doService(Context context) throws Exception {
            return super.doService(context);
        }
        
    }
    
}
