/**
 * WeiXin
 * @title TextMessageAdaptor.java
 * @package com.chn.wx.Service
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月16日-下午1:06:17
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.listener.impl.service.end.message;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.chn.common.Exec;
import com.chn.common.StringUtils;
import com.chn.turing.api.TuringRobotApi;
import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.api.PlatFormManager;
import com.chn.wx.api.PlatFormManagerImp;
import com.chn.wx.api.ServiceMessageSender;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.impl.process.AsyncThreadMode;
import com.chn.wx.listener.impl.service.route.RawMessageRouter;
import com.chn.wx.template.PassiveMessage;
import com.chn.wx.vo.result.PlatFormGetAuthInfoResult;

/**
 * @class TextMessageAdaptor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(parents=RawMessageRouter.class, value="text")
public class TextMessageAdaptor implements Service {

    protected Logger log = Logger.getLogger(this.getClass());
    protected static final String AUTO_TEST_TEXTMSG_RETURN = "TESTCOMPONENT_MSG_TYPE_TEXT";
    protected static final String AUTO_TEST_TEXTMSG_RETURN_BACK = "TESTCOMPONENT_MSG_TYPE_TEXT_callback";
    @Param protected String ToUserName;   //开发者微信号
    @Param protected String FromUserName; //发送方帐号（一个OpenID）
    @Param protected String CreateTime;   //消息创建时间 （整型）
    @Param protected String MsgId;        //消息id，64位整型
    @Param protected String Content;      //消息id，64位整型
    
    @Override
    public String doService(Context context) throws Exception {
    	// 全网发布测试
    	if(!StringUtils.isEmpty(ToUserName) && AUTO_TEST_USERNAME.equals(ToUserName)
    			&& AUTO_TEST_TEXTMSG_RETURN.equals(Content)){
    		return PassiveMessage.wrapText(FromUserName, ToUserName, AUTO_TEST_TEXTMSG_RETURN_BACK);
    	}else if(!StringUtils.isEmpty(ToUserName) && AUTO_TEST_USERNAME.equals(ToUserName)
    			&& Content.startsWith("QUERY_AUTH_CODE")){
    		final String query_auth = Content.split(":")[1];
    		 Exec.submit(new Callable<String>() {
    	            @Override
    	            public String call() throws Exception {
    	                try {
    	                	PlatFormManager mgr = new PlatFormManagerImp();
    	                	PlatFormGetAuthInfoResult result = mgr.getAuthInfo(query_auth);
    	                	String token = result.getAuthorizationInfo().getAuthorizerAccessToken();
    	                	ServiceMessageSender.sendTestText(FromUserName, query_auth+"_from_api", token);
    	                } catch (Exception e) {
    	                    log.error("任务处理出错", e);
    	                }
    	                return null;
    	            }
    	        });
    		return DEFAULT_RETURN;
    	}
        log.debug(String.format("收到来自 %s 的文本信息 %s", FromUserName, Content));
        String content = PassiveMessage.wrapText(FromUserName, ToUserName, TuringRobotApi.talking(Content, FromUserName).getTextInfo());
        return content;
    }
}
