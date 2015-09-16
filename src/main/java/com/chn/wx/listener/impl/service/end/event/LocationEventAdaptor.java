/**
 * WeiXin
 * @title LocationEventAdaptor.java
 * @package com.chn.wx.listener.end.event
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午6:30:06
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.listener.impl.service.end.event;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.impl.service.route.EventRouter;

/**
 * @class LocationEventAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=EventRouter.class, value="LOCATION")
public class LocationEventAdaptor implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    
    @Param protected String ToUserName;
    @Param protected String FromUserName;
    @Param protected String CreateTime;
    @Param protected String Latitude;//地理位置纬度
    @Param protected String Longitude;//地理位置经度
    @Param protected String Precision;//地理位置精度
    
    @Override
    public String doService(Context context) throws Exception {

        log.debug(String.format("收到来自 %s 的坐标信息 %s-%s(%s)", FromUserName, 
                                Latitude, Longitude, Precision));
        return null;
    }

}
