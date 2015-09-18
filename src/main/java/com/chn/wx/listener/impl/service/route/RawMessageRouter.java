/**
 * WeiXin
 * @title MessageService.java
 * @package com.chn.wx.core
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月15日-下午6:27:04
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.listener.impl.service.route;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ServiceAgent;

/**
 * @class MessageService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(value = "raw", parents = {EncryptRouter.class, AesMessageRouter.class})
public final class RawMessageRouter implements Service {

    protected Logger log = Logger.getLogger(RawMessageRouter.class);
    
    @Param private String xmlContent;
    @Param private ServiceAgent serviceAgent;
    
    @Override
    public String doService(Context context) throws Exception {
        
        Document document = DocumentHelper.parseText(xmlContent);
        Element root = document.getRootElement();
        for(Iterator<?> it = root.elementIterator(); it.hasNext();) {
            Element ele = (Element)it.next();
            context.addAttribute(ele.getName(), ele.getText());
        }
        
        String appId = context.getAttribute(String.class, "AppId");
        if(appId != null) { //此时为第三方服务平台服务自身的消息
            log.debug("路由到第三方平台服务自身");
            return serviceAgent.routeToNext("platform", context);
        } else {
            String routeKey = context.getAttribute(String.class, "MsgType");
            log.debug(String.format("根据消息类型[%s]作路由", routeKey));
            return serviceAgent.routeToNext(routeKey, context);
        }
    }

}
