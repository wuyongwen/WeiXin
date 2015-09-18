package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatFormGetAuthorizerInfoResult {

    @JSONField(name="authorizer_info") private String authorizerInfo;
    @JSONField(name="head_img") private String headImg;
    @JSONField(name="service_type_info") private String serviceTypeInfo;
    @JSONField(name="verify_type_info") private String verifyTypeInfo;
    @JSONField(name="user_name") private String userName;
    @JSONField(name="alias") private String alias;
    @JSONField(name="qrcode_url") private String qrcodeUrl;
    @JSONField(name="authorization_info") private String authorizationInfo;
    @JSONField(name="appid") private String appId;
    @JSONField(name="func_info") private FuncInfo[] funcInfo;
    
    /**
     * @return 授权方昵称
     */
    public String getAuthorizerInfo() {
        return authorizerInfo;
    }
    public void setAuthorizerInfo(String authorizerInfo) {
        this.authorizerInfo = authorizerInfo;
    }
    /**
     * @return 授权方头像
     */
    public String getHeadImg() {
        return headImg;
    }
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    /**
     * @return 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
     */
    public String getServiceTypeInfo() {
        return serviceTypeInfo;
    }
    public void setServiceTypeInfo(String serviceTypeInfo) {
        this.serviceTypeInfo = serviceTypeInfo;
    }
    /**
     * @return 授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微
     * 博认证，3代表已资质认证通过但还未通过名称认证，4代表已资质认证通过、还未通过名称认证，
     * 但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
     */
    public String getVerifyTypeInfo() {
        return verifyTypeInfo;
    }
    public void setVerifyTypeInfo(String verifyTypeInfo) {
        this.verifyTypeInfo = verifyTypeInfo;
    }
    /**
     * @return 授权方公众号的原始ID
     */
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return 授权方公众号所设置的微信号，可能为空
     */
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    /**
     * @return 二维码图片的URL，开发者最好自行也进行保存
     */
    public String getQrcodeUrl() {
        return qrcodeUrl;
    }
    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }
    /**
     * @return 授权信息
     */
    public String getAuthorizationInfo() {
        return authorizationInfo;
    }
    public void setAuthorizationInfo(String authorizationInfo) {
        this.authorizationInfo = authorizationInfo;
    }
    /**
     * @return 授权方appid
     */
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public FuncInfo[] getFuncInfo() {
        return funcInfo;
    }
    public void setFuncInfo(FuncInfo[] funcInfo) {
        this.funcInfo = funcInfo;
    }

    public static class FuncInfo {
            
            @JSONField(name="funcscope_category") private FuncScopCategory funcscopCategory;
    
            public FuncScopCategory getFuncscopCategory() {
                return funcscopCategory;
            }
            public void setFuncscopCategory(FuncScopCategory funcscopCategory) {
                this.funcscopCategory = funcscopCategory;
            }
            
        }
    
    public static class FuncScopCategory {
        
        @JSONField(name="id") private Integer id;

        /**
         * 1  消息与菜单权限集<br>
         * 2  用户管理权限集<br>
         * 3  帐号管理权限集<br>
         * 4  网页授权权限集<br>
         * 5  微信小店权限集<br>
         * 6  多客服权限集<br>
         * 7  业务通知权限集<br>
         * 8  微信卡券权限集<br>
         * 9  微信扫一扫权限集<br>
         * 10 微信连WIFI权限集<br>
         * 11 素材管理权限集<br>
         * 12 摇一摇周边权限集<br>
         * 13 微信门店权限集
         */
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
    }

}
