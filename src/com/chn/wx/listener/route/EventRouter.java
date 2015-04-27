/**
 * WeiXin
 * @title EventRouter.java
 * @package com.chn.wx.listener.route
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午6:20:36
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.listener.route;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ServiceTree;

/**
 * @class EventRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parent=RawMessageRouter.class, value="event")
public class EventRouter implements Service {

    private Logger log = Logger.getLogger(EventRouter.class);
    @Param private String Event;

    @Override
    public String doService(ServiceTree tree, Context context) {

        log.info("根据Event:"+Event+"做路由");
        return tree.route(context, Event).doService(tree, context);
    }
    
}
