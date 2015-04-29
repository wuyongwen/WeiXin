/**
 * WeiXin
 * @title ViewEventAdaptor.java
 * @package com.chn.wx.listener.end.event
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午6:31:40
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.listener.end.event;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ServiceTree;
import com.chn.wx.listener.route.EventRouter;

/**
 * @class ViewEventAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parent=EventRouter.class, value="VIEW")
public class ViewEventAdaptor implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String ToUserName;
    @Param protected String FromUserName;
    @Param protected String CreateTime;
    @Param protected String EventKey;//事件KEY值，设置的跳转URL
    
    @Override
    public String doService(ServiceTree tree, Context context) throws Exception {

        log.debug(String.format("收到 %s 的点击事件，跳转到 %s", FromUserName, EventKey));
        return null;
    }

}
