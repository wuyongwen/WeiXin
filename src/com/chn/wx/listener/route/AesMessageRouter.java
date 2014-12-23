/**
 * WeiXin
 * @title AESService.java
 * @package com.chn.wx.listener
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午5:32:55
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.listener.route;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.invocation.TokenAccessor;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ServiceTree;
import com.chn.wx.template.PassiveMessage;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * @class AESService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(value = "raw", parent = EncryptRouter.class)
public class AesMessageRouter implements Service {

    @Param private String msg_signature;//消息体签名
    @Param private String timestamp;
    @Param private String nonce;
    @Param private String xmlContent;
    
    private WXBizMsgCrypt msgCrypt;
    
    @Override
    public String doService(ServiceTree tree, Context context) {

        try {
            //初始化加密解密组件
            String accessToken = TokenAccessor.getAccessToken();
            msgCrypt = new WXBizMsgCrypt(accessToken, Context.info.getAesKey(), Context.info.getId());
            
            //解密，并将解密后信息放入上下文
            String encryptText = this.getEncyptText(xmlContent);
            xmlContent = msgCrypt.decryptMsg(msg_signature, timestamp, nonce, encryptText);
            context.setAttribute("xmlContent", xmlContent);
            
            //调用下一环节
            String result = tree.route(context, EncryptRouter.class, "raw").doService(tree, context);
            
            //加密并组 XML 返回
            String Encrypt = msgCrypt.encryptMsg(result, timestamp, nonce);
            //FromUserName(用户OpenID) 会在后续结点被解析并放入，不能通过 @Param 直接注入
            return PassiveMessage.wrapAES((String)context.getAttribute("FromUserName"), Encrypt);
        } catch (Exception e) {
            throw new RuntimeException("编码/解码错误！", e);
        }
    }
    
    /**
     * <xml>
     *     <ToUserName><![CDATA[gh_10f6c3c3ac5a]]></ToUserName>
     *     <Encrypt><![CDATA[hQM/NS0ujPGbF+/8yVe61=]]></Encrypt>
     * </xml>
     * @throws DocumentException 
    */
    private String getEncyptText(String xmlContent) throws DocumentException {
        
        SAXReader reader = new SAXReader();
        Document document = reader.read(xmlContent);
        return document.selectSingleNode("/xml/Encrypt").getText();
    }
    
}
