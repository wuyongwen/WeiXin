/**
 * WeiXin
 * @title Button.java
 * @package com.chn.wx.vo
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月30日-上午9:14:51
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class Button
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class Button {

    public static enum Type {
        click, //点击推事件
        view, //跳转URL
        scancode_push, //扫码推事件
        scancode_waitmsg, //扫码推事件且弹出“消息接收中”提示框
        pic_sysphoto, //弹出系统拍照发图
        pic_photo_or_album, //弹出拍照或者相册发图
        pic_weixin, //弹出微信相册发图器
        location_select;//弹出地理位置选择器
    }
    
    @JSONField(name="type") private String type;
    @JSONField(name="name") private String name;
    @JSONField(name="key") private String key;
    @JSONField(name="url") private String url;
    @JSONField(name="sub_button") private Button[] subButton;
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setType(Type type) {
        this.type = type.toString();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Button[] getSubButton() {
        return subButton;
    }
    public void setSubButton(Button[] subButton) {
        this.subButton = subButton;
    }
    
}
