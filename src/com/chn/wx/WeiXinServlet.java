/**
 * WeiXin
 * @title WeiXinServlet.java
 * @package com.chn.wx
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月15日-下午5:29:56
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.StringUtils;

import com.chn.common.HttpUtils;
import com.chn.common.IOUtils;
import com.chn.wx.dto.Context;

/**
 * @class WeiXinServlet
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class WeiXinServlet extends HttpServlet {

    private static final long serialVersionUID = 1785566409069051059L;
    
    private Logger log = Logger.getLogger(WeiXinServlet.class);
    private MessageHandler handler = new MessageHandler();
    
    @Override
    public void destroy() {
        
        this.handler.destroy();
    }

    @Override
    public void init() throws ServletException {
        
        this.handler.init();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Context context = new Context(HttpUtils.decodeParams(req));
        if(req.getMethod().equalsIgnoreCase("POST"))
            context.setAttribute("xmlContent", HttpUtils.read(req));
        
        OutputStream os = resp.getOutputStream();
        try {
            String responseString = this.handler.process(context);
            os.write(StringUtils.getBytesUtf8(responseString));
        } catch (Exception e) {
            log.error("消息处理失败", e);
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

}
