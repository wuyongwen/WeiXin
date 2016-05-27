package com.chn.wx.listener.impl.service.end.event;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.impl.service.route.EventRouter;

/**
 * 取消关注公众号
 */
@Node(parents=EventRouter.class, value="unsubscribe")
public class UnSubscribeEventAdaptor implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String ToUserName;
    @Param protected String FromUserName;
    @Param protected String CreateTime;
    
    @Override
    public String doService(Context context) throws Exception {

        log.debug(String.format("收到 %s 的退订事件", FromUserName));
        return DEFAULT_RETURN;
    }

}
