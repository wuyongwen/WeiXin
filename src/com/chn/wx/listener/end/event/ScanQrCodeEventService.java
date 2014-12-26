/**
 * WeiXin
 * @title ScanQrCodeEventService.java
 * @package com.chn.wx.listener.end.event
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午6:29:25
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
 * @class ScanQrCodeEventService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parent=EventRouter.class, value="SCAN")
public class ScanQrCodeEventService implements Service {

    @Param private String ToUserName;
    @Param private String FromUserName;
    @Param private String CreateTime;
    @Param private String EventKey;//事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
    @Param private String Ticket;//二维码的ticket，可用来换取二维码图片
    
    @Override
    public String doService(ServiceTree tree, Context context) {
        return null;
    }

}
