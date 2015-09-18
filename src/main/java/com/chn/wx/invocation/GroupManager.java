package com.chn.wx.invocation;

import static com.chn.common.StringTemplate.compile;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.chn.common.HttpUtils;
import com.chn.common.StringTemplate;
import com.chn.wx.template.GroupMessage;
import com.chn.wx.vo.result.BasicResult;
import com.chn.wx.vo.result.CreateGroupResult;
import com.chn.wx.vo.result.QueryGroupsResult;
import com.chn.wx.vo.result.QueryUserGroupResult;

/**
 * @class UserManager
 * @author lzxz1234
 * 
 * @version v1.0
 */
public class GroupManager {

    private static StringTemplate createGroupUrl = compile(WeiXinURL.CREATE_GROUP);
    private static StringTemplate queryGroupsUrl = compile(WeiXinURL.QUERY_GROUPS);
    private static StringTemplate queryUserGroupUrl = compile(WeiXinURL.QUERY_USER_GROUP);
    private static StringTemplate modifyGroupUrl = compile(WeiXinURL.MODIFY_GROUP);
    private static StringTemplate modifyUserGroupUrl = compile(WeiXinURL.MODIFY_USER_GROUP);
    
    /**
     * 创建分组<br>
     * 一个公众账号，最多支持创建100个分组
     * @param groupName
     * @return 
    */
    public static CreateGroupResult createGroup(String groupName) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = createGroupUrl.replace(params);
        String postString = GroupMessage.wrapCreateGroup(groupName);
        return postAndDecode(urlLocation, postString, CreateGroupResult.class);
    }
    
    /**
     * 查询所有分组
     * @return 
    */
    public static QueryGroupsResult queryGroups() {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = queryGroupsUrl.replace(params);
        String respString = HttpUtils.get(urlLocation);
        return JSON.parseObject(respString, QueryGroupsResult.class);
    }
    
    /**
     * 通过用户的OpenID查询其所在的GroupID
     * @param userId
     * @return 
    */
    public static QueryUserGroupResult queryUserGroup(String userId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = queryUserGroupUrl.replace(params);
        String postString = GroupMessage.wrapQueryUserGroup(userId);
        return postAndDecode(urlLocation, postString, QueryUserGroupResult.class);
    }
    
    /**
     * 修改分组名
     * @param groupId 分组id，由微信分配
     * @param groupName 分组名字（30个字符以内）
     * @return 
    */
    public static BasicResult modifyGroup(String groupId, String groupName) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = modifyGroupUrl.replace(params);
        String postString = GroupMessage.wrapModifyGroup(groupId, groupName);
        return postAndDecode(urlLocation, postString, BasicResult.class);
    }
    
    /**
     * 移动用户分组
     * @param userId 用户唯一标识符
     * @param toGroupId 分组id
     * @return 
    */
    public static BasicResult modfiyUserGroup(String userId, String toGroupId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = modifyUserGroupUrl.replace(params);
        String postString = GroupMessage.wrapModifyUserGroup(userId, toGroupId);
        return postAndDecode(urlLocation, postString, BasicResult.class);
    }
    
    private static <T> T postAndDecode(String urlLocation, String postString, Class<T> target) {
        
        String respString = HttpUtils.post(urlLocation, postString);
        return JSON.parseObject(respString, target);
    }
    
}
