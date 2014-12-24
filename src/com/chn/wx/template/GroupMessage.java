/**
 * WeiXin
 * @title UserMessage.java
 * @package com.chn.wx.tplate
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月23日-下午5:37:18
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.template;

import java.util.HashMap;
import java.util.Map;

import com.chn.common.StringTemplate;

/**
 * @class UserMessage
 * @author lzxz1234
 * @description 用户管理
 * @version v1.0
 */
public class GroupMessage {

    private static StringTemplate T_CREATE_GROUP = StringTemplate.compile(
            "{\"group\":{\"name\":\"${name}\"}}"
            );
    private static StringTemplate T_SEARCH_GROUP = StringTemplate.compile(
            "{\"openid\":\"${openid}\"}"
            );
    private static StringTemplate T_MODIFY_GROUP = StringTemplate.compile(
            "{\"group\":{\"id\":${id},\"name\":\"${name}\"}}"
            );
    private static StringTemplate T_MODIFY_USERGROUP = StringTemplate.compile(
            "{\"openid\":\"${openid}\",\"to_groupid\":${to_groupid}}"
            );
    
    /**
     * @param groupName 分组名字（30个字符以内）
    */
    public static String wrapCreateGroup(String groupName) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("name", groupName);
        return T_CREATE_GROUP.replace(params);
    }
    
    /**
     * @param openid 用户的OpenID
    */
    public static String wrapQueryUserGroup(String openid) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("openid", openid);
        return T_SEARCH_GROUP.replace(params);
    }
    
    /**
     * @param groupId 分组id，由微信分配
     * @param groupName 分组名字（30个字符以内）
    */
    public static String wrapModifyGroup(String groupId, String groupName) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("id", groupId);
        params.put("name", groupName);
        return T_MODIFY_GROUP.replace(params);
    }
    
    /**
     * @param openid 用户唯一标识符
     * @param toGroupid 分组id
    */
    public static String wrapModifyUserGroup(String openid, String toGroupid) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("openid", openid);
        params.put("to_groupid", toGroupid);
        return T_MODIFY_USERGROUP.replace(params);
    }
    
}
