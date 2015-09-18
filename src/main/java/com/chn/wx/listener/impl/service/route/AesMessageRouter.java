/**
 * WeiXin
 * @title AESService.java
 * @package com.chn.wx.listener
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午5:32:55
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.listener.impl.service.route;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.App;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ServiceAgent;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * @class AESService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(value = "aes", parents = EncryptRouter.class)
public class AesMessageRouter implements Service {

    @Param private String msg_signature;//消息体签名
    @Param private String timestamp;
    @Param private String nonce;
    @Param private String xmlContent;
    
    @Param private ServiceAgent serviceAgent;
    
    private WXBizMsgCrypt msgCrypt;
    
    @Override
    public String doService(Context context) throws Exception {

        msgCrypt = new WXBizMsgCrypt(App.Info.token, App.Info.aesKey, App.Info.id);
        
        //解密，并将解密后信息放入上下文
        xmlContent = msgCrypt.decryptMsg(msg_signature, timestamp, nonce, xmlContent);
        context.setAttribute("xmlContent", xmlContent);
        
        //调用下一环节
        String result = serviceAgent.routeToNext("raw", context);
        
        //加密并组 XML 返回
        return msgCrypt.encryptMsg(result, timestamp, nonce);
    }
    
}
