package com.chn.wx.listener.impl.service.route;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ThreadsMode;

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

    @Override
    public String doService(Context context) throws Exception {

        log.debug(String.format("根据事件[%s]做路由", Event));
        return threadsMode.routeToNext(this.getClass(), this.Event, context);
    }
    
}
