/**
 * WeiXin
 * @title MethodRouter.java
 * @package com.chn.wx.listener.route
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2015年4月29日-下午10:30:57
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.listener.impl.service.route;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ServiceAgent;

/**
 * @class MethodRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(value = "root", parents = Service.class)
public class MethodRouter implements Service {

    private Logger log = Logger.getLogger(MethodRouter.class);
    
    @Param("method")
    private String method;
    @Param private ServiceAgent serviceAgent;
    
    @Override
    public String doService(Context context) throws Exception {
        
    	log.info(String.format("根据请求方法[%s]做路由。", method));
        return serviceAgent.routeToNext(this.method, context);
    }

}
