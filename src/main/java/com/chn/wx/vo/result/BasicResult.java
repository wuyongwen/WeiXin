/**
 * WeiXin
 * @title BasicResult.java
 * @package com.chn.wx.vo.result
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月23日-下午6:22:24
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.vo.result;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class BasicResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class BasicResult implements Serializable {
    
    private static final long serialVersionUID = 3379612749505864582L;
    
    @JSONField(name="errcode") private String errcode;
    @JSONField(name="errmsg") private String errmsg;
    
    public String getErrcode() {
        return errcode;
    }
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    
}
