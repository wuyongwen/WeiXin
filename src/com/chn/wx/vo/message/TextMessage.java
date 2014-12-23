/**
 * WeiXin
 * @title TextMessage.java
 * @package com.chn.wx.vo
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月16日-上午10:30:32
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.vo.message;

/**
 * @class TextMessage
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class TextMessage extends Message {

    private String Content; //消息id，64位整型

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
    
}
