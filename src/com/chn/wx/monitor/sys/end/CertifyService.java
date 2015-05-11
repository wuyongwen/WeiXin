/**
 * WeiXin
 * @title CertifyService.java
 * @package com.chn.wx.core
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月15日-下午6:16:07
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.monitor.sys.end;

import org.apache.log4j.Logger;

import com.chn.common.SHA1;
import com.chn.common.StringUtils;
import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.App;
import com.chn.wx.dto.Context;
import com.chn.wx.monitor.Service;
import com.chn.wx.monitor.sys.route.MethodRouter;

/**
 * @class CertifyService
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@Node(value="GET", parents=MethodRouter.class)
public class CertifyService implements Service {

    private static Logger log = Logger.getLogger(CertifyService.class);
    
    @Param
    private String signature;
    @Param
    private String timestamp;
    @Param
    private String nonce;
    @Param
    private String echostr;

    /* 
     * 加密/校验流程如下：
     * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @Override
    public String doService(Context context) throws Exception {
        
        if(StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp) ||
                StringUtils.isEmpty(nonce) || StringUtils.isEmpty(echostr))
            return "微信接口，禁止访问！";
        String calcSignature = SHA1.getSHA1(App.Info.token, timestamp, nonce);
        log.info("对比签名：["+calcSignature+"]["+signature+"]");
        return calcSignature.equals(signature) ? echostr : null;
    }

}
