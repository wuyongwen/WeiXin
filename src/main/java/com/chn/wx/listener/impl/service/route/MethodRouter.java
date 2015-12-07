package com.chn.wx.listener.impl.service.route;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ThreadsMode;

/**
 * @class MethodRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(value = "root", parents = Service.class)
public final class MethodRouter implements Service {

    private Logger log = Logger.getLogger(MethodRouter.class);
    
    @Param("method")
    private String method;
    @Param private ThreadsMode threadsMode;
    
    @Override
    public String doService(Context context) throws Exception {
        
    	log.debug(String.format("根据请求方法[%s]做路由。", method));
        return threadsMode.routeToNext(this.getClass(), this.method, context);
    }

}
