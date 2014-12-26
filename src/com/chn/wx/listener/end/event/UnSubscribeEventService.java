/**
 * WeiXin
 * @title UnSubscribeEventService.java
 * @package com.chn.wx.listener.end.event
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午6:27:41
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.listener.end.event;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ServiceTree;
import com.chn.wx.listener.route.EventRouter;

/**
 * @class UnSubscribeEventService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parent=EventRouter.class, value="unsubscribe")
public class UnSubscribeEventService implements Service {

    @Param private String ToUserName;
    @Param private String FromUserName;
    @Param private String CreateTime;
    
    @Override
    public String doService(ServiceTree tree, Context context) {

        return null;
    }

}
