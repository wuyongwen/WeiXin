/**
 * WeiXin
 * @title LinkMessageService.java
 * @package com.chn.wx.listener.end.message
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午6:17:41
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.listener.end.message;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ServiceTree;
import com.chn.wx.listener.route.RawMessageRouter;

/**
 * @class LinkMessageService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parent=RawMessageRouter.class, value="link")
public class LinkMessageService implements Service {

    private static Logger log = Logger.getLogger(TextMessageService.class);
    
    @Param private String ToUserName;   //开发者微信号
    @Param private String FromUserName; //发送方帐号（一个OpenID）
    @Param private String CreateTime;   //消息创建时间 （整型）
    @Param private String MsgId;        //消息id，64位整型
    @Param private String Title;        //消息标题
    @Param private String Description;  //消息描述
    @Param private String Url;          //消息链接
    
    @Override
    public String doService(ServiceTree tree, Context context) {

        log.info("收到链接信息：" + Title);
        return null;
    }

}
