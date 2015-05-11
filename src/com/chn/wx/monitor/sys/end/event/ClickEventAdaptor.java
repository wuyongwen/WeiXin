/**
 * WeiXin
 * @title ClickEventAdaptor.java
 * @package com.chn.wx.listener.end.event
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午6:31:10
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.monitor.sys.end.event;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.monitor.Service;
import com.chn.wx.monitor.sys.route.EventRouter;

/**
 * @class ClickEventAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=EventRouter.class, value="CLICK")
public class ClickEventAdaptor implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String ToUserName;
    @Param protected String FromUserName;
    @Param protected String CreateTime;
    @Param protected String EventKey; //事件KEY值，与自定义菜单接口中KEY值对应
    
    @Override
    public String doService(Context context) throws Exception {
        
        log.debug(String.format("收到来自 %s 的点击事件 %s", FromUserName, EventKey));
        return null;
    }

}
