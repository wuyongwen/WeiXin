/**
 * WeiXin
 * @title MessageService.java
 * @package com.chn.wx.core
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月15日-下午6:27:04
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.listener.route;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ServiceTree;

/**
 * @class MessageService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(value = "raw", parent = EncryptRouter.class)
public final class RawMessageRouter implements Service {

    protected Logger log = Logger.getLogger(RawMessageRouter.class);
    
    @Param private String xmlContent;
    
    @Override
    public String doService(ServiceTree tree, Context context) {
        
        try {
            
            Document document = DocumentHelper.parseText(xmlContent);
            Element root = document.getRootElement();
            for(Iterator<?> it = root.elementIterator(); it.hasNext();) {
                Element ele = (Element)it.next();
                context.addAttribute(ele.getName(), ele.getText());
            }
            String routeKey = context.getAttribute(String.class, "MsgType");
            log.debug(String.format("根据消息类型[%s]作路由", routeKey));
            return tree.route(context, routeKey).doService(tree, context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
