/**
 * WeiXin
 * @title LocationMessageAdaptor.java
 * @package com.chn.wx.listener
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午6:12:58
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.monitor.sys.end.message;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.monitor.Service;
import com.chn.wx.monitor.sys.route.RawMessageRouter;

/**
 * @class LocationMessageAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=RawMessageRouter.class, value="location")
public class LocationMessageAdaptor implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String ToUserName;   //开发者微信号
    @Param protected String FromUserName; //发送方帐号（一个OpenID）
    @Param protected String CreateTime;   //消息创建时间 （整型）
    @Param protected String MsgId;        //消息id，64位整型
    @Param protected String Location_X;   //地理位置维度
    @Param protected String Location_Y;   //地理位置经度
    @Param protected String Scale;        //地图缩放大小
    @Param protected String Label;        //地理位置信息

    @Override
    public String doService(Context context) throws Exception {

        log.debug(String.format("收到来自 %s 的位置信息 %s-%s", FromUserName, 
                                Location_X, Location_Y));
        return null;
    }
    
    
    
}
