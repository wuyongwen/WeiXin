/**
 * WeiXin
 * @title ServiceFactory.java
 * @package com.chn.wx.listener
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月19日-上午9:19:12
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.listener;

import com.chn.wx.dto.Context;

/**
 * @class ServiceFactory
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class ServiceFactory {

    public Service buildService(Context context, Class<? extends Service> clazz) {
        
        try {
            Service result = clazz.newInstance();
            context.injectField(result);
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
}
