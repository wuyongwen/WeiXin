/**
 * WeiXin
 * @title package-info.java
 * @package com.chn.wx.invocation
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-上午10:11:36
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.invocation;

interface WeiXinURL {
    
    String GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${appId}&secret=${appSecret}";
    String SEND_SERVICE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=${accessToken}";
    String POST_FILE = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=${accessToken}&type=${type}";
    String GET_FILE = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=${accessToken}&media_id=${mediaId}";

}