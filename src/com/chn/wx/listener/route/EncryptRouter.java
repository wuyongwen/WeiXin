/**
 * WeiXin
 * @title EncryptRouter.java
 * @package com.chn.wx.listener
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午5:27:30
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.listener.route;

import java.io.IOException;

import com.chn.wx.WeiXinServlet;
import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ServiceTree;
import com.chn.wx.util.HttpUtils;

/**
 * @class EncryptRouter
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(value = "POST", parent = WeiXinServlet.class)
public class EncryptRouter implements Service {

    @Param(value="encrypt_type", defaultValue="raw") 
    private String encrypt_type; //加密类型
    
    @Override
    public String doService(ServiceTree tree, Context context) {
        
        try {
            String xmlContent = HttpUtils.read(context.getRequest());
            context.addAttribute("xmlContent", xmlContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tree.route(context, this.encrypt_type).doService(tree, context);
    }

}
