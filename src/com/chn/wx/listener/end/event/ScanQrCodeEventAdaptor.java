/**
 * WeiXin
 * @title ScanQrCodeEventAdaptor.java
 * @package com.chn.wx.listener.end.event
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午6:29:25
 * @version V1.0
 * All Right Reserved
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
 * @class ScanQrCodeEventAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parent=EventRouter.class, value="SCAN")
public class ScanQrCodeEventAdaptor implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String ToUserName;
    @Param protected String FromUserName;
    @Param protected String CreateTime;
    @Param protected String EventKey;//事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
    @Param protected String Ticket;//二维码的ticket，可用来换取二维码图片
    
    @Override
    public String doService(ServiceTree tree, Context context) throws Exception {
        
        log.debug(String.format("收到来自 %s 的二维码事件 %s-%s", FromUserName, 
                                EventKey, Ticket));
        return null;
    }

}
