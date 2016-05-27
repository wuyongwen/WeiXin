package com.chn.wx.listener.impl.service.route;

import org.apache.log4j.Logger;

import com.chn.common.StringUtils;
import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ThreadsMode;
import com.chn.wx.template.PassiveMessage;

/**
 * @class EventRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=RawMessageRouter.class, value="event")
public final class EventRouter implements Service {

    private Logger log = Logger.getLogger(EventRouter.class);
    @Param private String Event;
    @Param private ThreadsMode threadsMode;
    @Param private String ToUserName;
    @Override
    public String doService(Context context) throws Exception {
    	// 全网发布测试
    	// 1、模拟粉丝触发专用测试公众号的事件，并推送事件消息到专用测试公众号，第三方平台方开发者需要提取推送XML信息中的event值，并在5秒内立即返回按照下述要求组装的文本消息给粉丝。
    	if(!StringUtils.isEmpty(ToUserName) && AUTO_TEST_USERNAME.equals(ToUserName)){
    		String fromUserName = (String) context.getAttribute("FromUserName");
    		return PassiveMessage.wrapText(fromUserName, ToUserName, Event+"from_callback");
    	}
        log.debug(String.format("根据事件[%s]做路由", Event));
        return threadsMode.routeToNext(this.getClass(), this.Event, context);
    }
    
}
