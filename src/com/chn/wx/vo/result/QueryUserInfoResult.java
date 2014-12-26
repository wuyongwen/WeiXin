/**
 * WeiXin
 * @title QueryUserInfoResult.java
 * @package com.chn.wx.vo.result
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月26日-上午9:17:26
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class QueryUserInfoResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class QueryUserInfoResult {

    @JSONField(name="errcode") private String errcode;
    @JSONField(name="errmsg") private String errmsg;
    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    @JSONField(name="subscribe") private Integer subscribe;
    /**
     * 用户的标识，对当前公众号唯一
     */
    @JSONField(name="openid") private String openId;
    /**
     * 用户的昵称
     */
    @JSONField(name="nickname") private String nickName;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    @JSONField(name="sex") private Integer sex;
    /**
     * 用户的语言，简体中文为zh_CN
     */
    @JSONField(name="language") private String language;
    /**
     * 用户所在城市
     */
    @JSONField(name="city") private String city;
    /**
     * 用户所在省份
     */
    @JSONField(name="province") private String province;
    /**
     * 用户所在国家
     */
    @JSONField(name="country") private String country;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，
     * 0代表640*640正方形头像），用户没有头像时该项为空
     */
    @JSONField(name="headimgurl") private String headImgUrl;
    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    @JSONField(name="subscribe_time") private Integer subscribeTime;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
     */
    @JSONField(name="unionid") private String unionId;
    
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
    public Integer getSubscribe() {
        return subscribe;
    }
    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }
    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getHeadImgUrl() {
        return headImgUrl;
    }
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
    public Integer getSubscribeTime() {
        return subscribeTime;
    }
    public void setSubscribeTime(Integer subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
    public String getUnionId() {
        return unionId;
    }
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
    
}
