/**
 * WeiXin
 * @title Service.java
 * @package com.chn.wx.logic
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月16日-上午9:38:16
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.monitor;

import com.chn.wx.dto.Context;

/**
 * @class Service
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public interface Service {

    /**
     * @description service
     * @param req
     * @param resp
     * @return 
    */
    public String doService(Context context) throws Exception;
    
}
