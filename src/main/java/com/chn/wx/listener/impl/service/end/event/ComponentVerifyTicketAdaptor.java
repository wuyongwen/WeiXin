package com.chn.wx.listener.impl.service.end.event;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.impl.service.route.EventRouter;
import com.chn.wx.listener.impl.service.route.PlatFormEventRouter;

@Node(parents={PlatFormEventRouter.class, EventRouter.class}, value="component_verify_ticket")
public class ComponentVerifyTicketAdaptor implements Service {

    protected Logger log = Logger.getLogger(ComponentVerifyTicketAdaptor.class);
    
    @Param protected String AppId;
    @Param protected String CreateTime;
    @Param protected String ComponentVerifyTicket;
    
    @Override
    public String doService(Context context) throws Exception {
        
        log.debug(String.format("收到刷新授权Ticket请求，新Ticket: %s", ComponentVerifyTicket));
        return DEFAULT_RETURN;
    }

}
