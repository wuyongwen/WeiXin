/**
 * WeiXin
 * @title UserMessage.java
 * @package com.chn.wx.template
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月25日-下午4:03:34
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.template;

import static com.chn.common.StringTemplate.compileFromClassPath;

import java.util.HashMap;
import java.util.Map;

import com.chn.common.StringTemplate;

/**
 * @class UserMessage
 * @author lzxz1234
 * @description 用户管理消息模板
 * @version v1.0
 */
public class UserMessage {

    private static StringTemplate T_REMARK = compileFromClassPath("/com/chn/wx/template/tpl/user-remark.json");
    
    /**
     * @param userId 用户标识
     * @param remark 新的备注名，长度必须小于30字符
     */
    public static String wrapRemarkUser(String userId, String remark) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("openid", userId);
        params.put("remark", remark);
        return T_REMARK.replace(params);
    }
    
}
