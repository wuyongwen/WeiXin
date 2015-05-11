/**
 * WeiXin
 * @title EncryptRouter.java
 * @package com.chn.wx.listener
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午5:27:30
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.monitor.sys.route;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.monitor.Service;
import com.chn.wx.monitor.ServiceProxy;

/**
 * @class EncryptRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(value = "POST", parents = MethodRouter.class)
public class EncryptRouter implements Service {

    private Logger log = Logger.getLogger(EncryptRouter.class);
    
    @Param(value="encrypt_type", defaultValue="raw") 
    private String encrypt_type; //加密类型
    @Param private ServiceProxy serviceHolder;
    
    @Override
    public String doService(Context context) throws Exception {
        
        log.info(String.format("根据加密类型[%s]做路由。", encrypt_type));
        return serviceHolder.routeToNext(this.encrypt_type, context);
    }

}
