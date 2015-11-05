/**
 * WeiXin
 * @title AccessTokenResult.java
 * @package com.chn.wx.vo
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-下午2:10:38
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class AccessTokenResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class AccessTokenResult extends BasicResult {

    private static final long serialVersionUID = -8150125867084144128L;
    
    @JSONField(name="access_token") private String accessToken;
    @JSONField(name="expires_in") private Integer expiresIn;
    
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public Integer getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
    
}
