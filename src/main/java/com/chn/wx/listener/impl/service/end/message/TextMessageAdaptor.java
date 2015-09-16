/**
 * WeiXin
 * @title TextMessageAdaptor.java
 * @package com.chn.wx.Service
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月16日-下午1:06:17
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.listener.impl.service.end.message;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.impl.service.route.RawMessageRouter;

/**
 * @class TextMessageAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=RawMessageRouter.class, value="text")
public class TextMessageAdaptor implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String ToUserName;   //开发者微信号
    @Param protected String FromUserName; //发送方帐号（一个OpenID）
    @Param protected String CreateTime;   //消息创建时间 （整型）
    @Param protected String MsgId;        //消息id，64位整型
    @Param protected String Content;      //消息id，64位整型
    
    @Override
    public String doService(Context context) throws Exception {
        
        log.debug(String.format("收到来自 %s 的文本信息 %s", FromUserName, Content));
        return null;
    }

}
