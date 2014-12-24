/**
 * WeiXin
 * @title UserManager.java
 * @package com.chn.wx.invocation
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月24日-上午9:42:25
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.invocation;

import static com.chn.common.StringTemplate.compile;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.chn.common.StringTemplate;
import com.chn.wx.template.GroupMessage;
import com.chn.wx.util.HttpUtils;
import com.chn.wx.vo.result.BasicResult;
import com.chn.wx.vo.result.CreateGroupResult;
import com.chn.wx.vo.result.QueryGroupsResult;
import com.chn.wx.vo.result.QueryUserGroupResult;

/**
 * @class UserManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class GroupManager {

    private static StringTemplate createGroupUrl = compile(WeiXinURL.CREATE_GROUP);
    private static StringTemplate queryGroupsUrl = compile(WeiXinURL.QUERY_GROUPS);
    private static StringTemplate queryUserGroupUrl = compile(WeiXinURL.QUERY_USER_GROUP);
    private static StringTemplate modifyGroupUrl = compile(WeiXinURL.MODIFY_GROUP);
    private static StringTemplate modifyUserGroupUrl = compile(WeiXinURL.MODIFY_USER_GROUP);
    
    /**
     * @description 创建分组<br>
     * 一个公众账号，最多支持创建100个分组
     * @param groupName
     * @return 
    */
    public static CreateGroupResult createGroup(String groupName) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = createGroupUrl.replace(params);
        try {
            String respString = HttpUtils.post(urlLocation, GroupMessage.wrapCreateGroup(groupName));
            return JSON.parseObject(respString, CreateGroupResult.class);
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        }
    }
    
    /**
     * @description 查询所有分组
     * @return 
    */
    public static QueryGroupsResult queryGroups() {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = queryGroupsUrl.replace(params);
        try {
            String respString = HttpUtils.get(urlLocation);
            return JSON.parseObject(respString, QueryGroupsResult.class);
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        }
    }
    
    /**
     * @description 通过用户的OpenID查询其所在的GroupID
     * @param userId
     * @return 
    */
    public static QueryUserGroupResult queryUserGroup(String userId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = queryUserGroupUrl.replace(params);
        try {
            String respString = HttpUtils.post(urlLocation, GroupMessage.wrapQueryUserGroup(userId));
            return JSON.parseObject(respString, QueryUserGroupResult.class);
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        }
    }
    
    /**
     * @description 修改分组名
     * @param groupId 分组id，由微信分配
     * @param groupName 分组名字（30个字符以内）
     * @return 
    */
    public static BasicResult modifyGroup(String groupId, String groupName) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = modifyGroupUrl.replace(params);
        try {
            String respString = HttpUtils.post(urlLocation, GroupMessage.wrapModifyGroup(groupId, groupName));
            return JSON.parseObject(respString, BasicResult.class);
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        }
    }
    
    /**
     * @description 移动用户分组
     * @param userId 用户唯一标识符
     * @param toGroupId 分组id
     * @return 
    */
    public static BasicResult modfiyUserGroup(String userId, String toGroupId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = modifyUserGroupUrl.replace(params);
        try {
            String respString = HttpUtils.post(urlLocation, GroupMessage.wrapModifyUserGroup(userId, toGroupId));
            return JSON.parseObject(respString, BasicResult.class);
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        }
    }
    
}
