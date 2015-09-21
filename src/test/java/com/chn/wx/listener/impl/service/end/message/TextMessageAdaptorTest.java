package com.chn.wx.listener.impl.service.end.message;

import junit.framework.Assert;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.junit.Test;
import org.mockito.Mockito;

import com.chn.wx.dto.App;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.impl.service.end.ServiceTest;
import com.qq.weixin.mp.aes.SHA1;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class TextMessageAdaptorTest extends ServiceTest {

    String tpl = "<xml>                                         " + 
                 "<ToUserName><![CDATA[%s]]></ToUserName>       " + 
                 "<FromUserName><![CDATA[%s]]></FromUserName>   " + 
                 "<CreateTime>%s</CreateTime>                   " + 
                 "<MsgType><![CDATA[text]]></MsgType>           " + 
                 "<Content><![CDATA[%s]]></Content>             " + 
                 "<MsgId>%s</MsgId>                             " + 
                 "</xml>";
    String content = randomString();
    String nonce = randomString();
    String postXml = String.format(tpl, toUserName, fromUserName, createTime, content, msgId);
    
    String expectReturn = randomString();
    
    @Test
    public void testRaw() throws Exception {
        
        MockTextMessageService service = preparToTest(MockTextMessageService.class);
        
        Context context = doPostCtxt(postXml);
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        
        String realReturn = handler.process(context);
        Assert.assertEquals(expectReturn, realReturn);
        Assert.assertEquals(toUserName, service.ToUserName);
        Assert.assertEquals(fromUserName, service.FromUserName);
        Assert.assertEquals(createTime, service.CreateTime);
        Assert.assertEquals(content, service.Content);
        Assert.assertEquals(msgId, service.MsgId);
        Mockito.verify(service).doService(context);
    }
    
    @Test
    public void testAes() throws Exception {
        
        WXBizMsgCrypt msgCrypt = new WXBizMsgCrypt(App.Info.token, App.Info.aesKey, App.Info.id);
        MockTextMessageService service = preparToTest(MockTextMessageService.class);
        String xmlFormat = "<xml><ToUserName><![CDATA[%s]]></ToUserName><Encrypt><![CDATA[%s]]></Encrypt></xml>";
        
        String afterEncrpt = msgCrypt.encryptMsg(postXml, createTime, nonce);
        
        Document document = DocumentHelper.parseText(afterEncrpt);
        String encrypt = document.selectSingleNode("//xml/Encrypt").getText();
        
        String toSendXML = String.format(xmlFormat, toUserName, encrypt);
        String signature = SHA1.getSHA1(App.Info.token, createTime, nonce, encrypt);
        String params = String.format("encrypt_type=aes&timestamp=%s&nonce=%s&msg_signature=%s", createTime, nonce, signature);
        Context context = doPostCtxt(params, toSendXML);
        
        Mockito.when(service.doService(context)).thenReturn(expectReturn);
        handler.process(context);
        Assert.assertEquals(toUserName, service.ToUserName);
        Assert.assertEquals(fromUserName, service.FromUserName);
        Assert.assertEquals(createTime, service.CreateTime);
        Assert.assertEquals(content, service.Content);
        Assert.assertEquals(msgId, service.MsgId);
        Mockito.verify(service).doService(context);
    }

    public static class MockTextMessageService extends TextMessageAdaptor {

        @Override
        public String doService(Context context) throws Exception {
            return super.doService(context);
        }
        
    }
    
}
