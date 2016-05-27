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
import com.chn.wx.listener.ThreadsMode;

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
    @Param private ThreadsMode threadsMode;
    
    @Override
    public String doService(Context context) throws Exception {
        
        Document document = DocumentHelper.parseText(xmlContent);
        Element root = document.getRootElement();
        for(Iterator<?> it = root.elementIterator(); it.hasNext();) {
            Element ele = (Element)it.next();
            context.addAttribute(ele.getName(), ele.getText());
        }
        log.info("=============消息解密完成=============");
        log.info(context.toString());
        log.info("==================================");
        String appId = context.getAttribute(String.class, "AppId");
        if(appId != null) { //此时为第三方服务平台服务自身的消息
            log.debug("路由到第三方平台服务自身");
            return threadsMode.routeToNext(this.getClass(), "platform", context);
        } else {
            String routeKey = context.getAttribute(String.class, "MsgType");
            log.debug(String.format("根据消息类型[%s]作路由", routeKey));
            return threadsMode.routeToNext(this.getClass(), routeKey, context);
        }
    }

}
