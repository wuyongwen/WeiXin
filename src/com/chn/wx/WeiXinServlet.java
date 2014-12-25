/**
 * WeiXin
 * @title WeiXinServlet.java
 * @package com.chn.wx
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月15日-下午5:29:56
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.chn.common.Assert;
import com.chn.common.Scans;
import com.chn.common.StringUtils;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.ServiceTree;
import com.chn.wx.listener.ServiceTree.ClassProvider;

/**
 * @class WeiXinServlet
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class WeiXinServlet extends HttpServlet implements Service {

    private static final long serialVersionUID = 1785566409069051059L;
    
    private ServiceTree tree;

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        
        tree = new ServiceTree(new ClassProvider() {
            @Override
            public Set<Class<?>> getClasses() {
                String packages = WeiXinServlet.this.getInitParameter("package");
                Set<Class<?>> result = new HashSet<>();
                for(String pkg : packages.split("\\|"))
                    if(!StringUtils.isEmpty(pkg))
                        result.addAll(Scans.getClasses(pkg));
                return result;
            }
        });
        String appId = getInitParameter("appid");
        String appSecret = getInitParameter("secret");
        Assert.notNull(appId, "web.xml未配置appId");
        Assert.notNull(appSecret, "web.xml未配置secret");
        Context.setAppInfo(appId, appSecret);
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Context context = new Context(req, resp);
        String responseString = this.doService(tree, context);
        if(responseString != null) {
            OutputStream os = null;
            try {
                os = resp.getOutputStream();
                os.write(responseString.getBytes(Charset.forName("UTF-8")));
            } finally {
                IOUtils.closeQuietly(os);
            }
        }
    }

    @Override
    public String doService(ServiceTree tree, Context context) {

        Service service = tree.route(context, context.getRequest().getMethod());
        return service.doService(tree, context);
    }

    
}
