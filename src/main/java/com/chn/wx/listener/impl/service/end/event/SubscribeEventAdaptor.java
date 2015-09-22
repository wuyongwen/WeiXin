package com.chn.wx.listener.impl.service.end.event;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.impl.service.route.EventRouter;

/**
 * @class SubscribeEventAdaptor
 * @author lzxz1234
 * @description 订阅事件可能有多个来源，非扫描二维码来的订阅时相关字段不可用
 * @version v1.0
 */
@Node(parents=EventRouter.class, value="subscribe")
public class SubscribeEventAdaptor implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String ToUserName;
    @Param protected String FromUserName;
    @Param protected String CreateTime;
    @Param protected String EventKey;//事件KEY值，qrscene_为前缀，后面为二维码的参数值
    @Param protected String Ticket;//二维码的ticket，可用来换取二维码图片
    
    @Override
    public String doService(Context context) throws Exception {

        log.debug(String.format("收到 %s 的订阅事件", FromUserName));
        return DEFAULT_RETURN;
    }

}
