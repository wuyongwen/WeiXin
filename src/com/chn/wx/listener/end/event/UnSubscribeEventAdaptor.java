/**
 * WeiXin
 * @title UnSubscribeEventAdaptor.java
 * @package com.chn.wx.listener.end.event
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午6:27:41
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.listener.end.event;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.core.Service;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.route.EventRouter;

/**
 * @class UnSubscribeEventAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
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
        return null;
    }

}
