/**
 * WeiXin
 * @title VideoMessageAdaptor.java
 * @package com.chn.wx.listener
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午6:11:19
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.listener.end.message;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.core.Service;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.route.RawMessageRouter;

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
        return null;
    }

}
