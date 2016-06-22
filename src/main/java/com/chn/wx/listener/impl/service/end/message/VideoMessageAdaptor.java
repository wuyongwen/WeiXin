package com.chn.wx.listener.impl.service.end.message;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.impl.service.route.RawMessageRouter;
import com.chn.wx.template.PassiveMessage;

/**
 * @class VideoMessageAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=RawMessageRouter.class, value="video")
public class VideoMessageAdaptor implements Service{

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String ToUserName;   //开发者微信号
    @Param protected String FromUserName; //发送方帐号（一个OpenID）
    @Param protected String CreateTime;   //消息创建时间 （整型）
    @Param protected String MsgId;        //消息id，64位整型
    @Param protected String MediaId;      //视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
    @Param protected String ThumbMediaId; //视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
    
    @Override
    public String doService(Context context) throws Exception {

        log.debug(String.format("收到来自 %s 的视频信息 %s", FromUserName, MediaId));
        //return PassiveMessage.wrapVideo(FromUserName, ToUserName, MediaId);
        return DEFAULT_RETURN;
    }

}
