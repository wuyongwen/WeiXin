/**
 * WeiXin
 * @title package-info.java
 * @package com.chn.wx.invocation
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月17日-上午10:11:36
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.invocation;

interface WeiXinURL {
    
    String GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${appId}&secret=${appSecret}";
    String SEND_SERVICE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=${accessToken}";
    
    String POST_FILE = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=${accessToken}&type=${type}";
    String GET_FILE = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=${accessToken}&media_id=${mediaId}";

    String CREATE_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=${accessToken}";
    String QUERY_GROUPS = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=${accessToken}";
    String QUERY_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=${accessToken}";
    String MODIFY_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=${accessToken}";
    String MODIFY_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=${accessToken}";
    
    String REMARK_USER = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=${accessToken}";
    String QUERY_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=${accessToken}&openid=${openid}&lang=zh_CN";
    String QUERY_FOLLOWER = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=${accessToken}&next_openid=${next_openid}";
    
    String CREATE_BUTTONS = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=${accessToken}";
    String QUERY_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=${accessToken}";
    String DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=${accessToken}";

    String SHORT_URL = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=${accessToken}";
    
    String GET_PLATFORM_TOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
    
    String PLATFORM_GET_PRE_AUTHCODE = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=${component_access_token}";
    
}