package com.chn.wx.listener.impl.service.route;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ServiceAgent;

@Node(value = "platform", parents = {RawMessageRouter.class})
public class PlatFormEventRouter implements Service {

    protected Logger log = Logger.getLogger(PlatFormEventRouter.class);
    
    @Param private ServiceAgent serviceAgent;
    @Param private String InfoType;
    
    @Override
    public String doService(Context context) throws Exception {

        log.debug(String.format("第三方平台服务根据类型 [%s] 作路由", InfoType));
        return serviceAgent.routeToNext(InfoType, context);
    }

}
