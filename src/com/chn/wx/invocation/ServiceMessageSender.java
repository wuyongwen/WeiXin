/**
 * WeiXin
 * @title ServiceMessageSender.java
 * @package com.chn.wx.invocation
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月18日-上午9:26:05
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.invocation;

import static com.chn.common.StringTemplate.compile;

import java.util.HashMap;
import java.util.Map;

import com.chn.common.StringTemplate;
import com.chn.wx.template.ServiceMessage;
import com.chn.wx.util.HttpUtils;

/**
 * @class ServiceMessageSender
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class ServiceMessageSender {

    private static final StringTemplate sendServiceUrl = compile(WeiXinURL.SEND_SERVICE);
    
    /**
     * @param touser 普通用户openid
     * @param content 文本消息内容
    */
    public static void sendText(String touser, String content) {
        
        send(ServiceMessage.wrapText(touser, content));
    }
    
    /**
     * @param touser 普通用户openid
     * @param media_id 发送的语音的媒体ID
    */
    public static void sendVoice(String touser, String media_id) {
        
        send(ServiceMessage.wrapVoice(touser, media_id));
    }
    
    /**
     * @param touser 普通用户openid
     * @param media_id 发送的视频的媒体ID
     * @param thumb_media_id 缩略图的媒体ID
    */
    public static void sendVideo(String touser, String media_id, String thumb_media_id) {
        
        send(ServiceMessage.wrapVideo(touser, media_id, thumb_media_id));
    }
    
    /**
     * @param touser 普通用户openid
     * @param media_id 发送的视频的媒体ID
     * @param thumb_media_id 缩略图的媒体ID
     * @param title 视频消息的标题
     * @param description 视频消息的描述
    */
    public static void sendVideo(String touser, String media_id, 
            String thumb_media_id, String title, String description) {
        
        send(ServiceMessage.wrapVideo(touser, media_id, thumb_media_id, title, description));
    }
    
    /**
     * @param touser 普通用户openid
     * @param musicurl 音乐链接 
     * @param hqmusicurl 高品质音乐链接，wifi环境优先使用该链接播放音乐
     * @param thumb_media_id 缩略图的媒体ID
    */
    public static void sendMusic(String touser, String musicurl, 
            String hqmusicurl, String thumb_media_id) {
        
        send(ServiceMessage.wrapMusic(touser, musicurl, hqmusicurl, thumb_media_id));
    }
    
    /**
     * @param touser 普通用户openid
     * @param title 音乐标题
     * @param description 音乐描述
     * @param musicurl 音乐链接 
     * @param hqmusicurl 高品质音乐链接，wifi环境优先使用该链接播放音乐
     * @param thumb_media_id 缩略图的媒体ID
    */
    public static void sendMusic(String touser, String title, String description,
            String musicurl, String hqmusicurl, String thumb_media_id) {
        
        send(ServiceMessage.wrapMusic(null, title, description, musicurl, hqmusicurl, thumb_media_id));
    }
    
    private static void send(String xml) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = sendServiceUrl.replace(params);
        try {
            HttpUtils.post(urlLocation, xml);
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        }
    }
    
}
