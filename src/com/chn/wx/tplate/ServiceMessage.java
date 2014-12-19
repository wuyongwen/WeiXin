/**
 * WeiXin
 * @title ServiceMessage.java
 * @package com.chn.wx.format
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月18日-上午10:03:36
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.tplate;

import java.util.HashMap;
import java.util.Map;

import com.chn.common.StringTemplate;

/**
 * @class ServiceMessage
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class ServiceMessage {

    private static StringTemplate T_TEXT = StringTemplate.compile(
              "{"
            + "    \"touser\":\"${touser}\","
            + "    \"msgtype\":\"text\","
            + "    \"text\":"
            + "    {"
            + "         \"content\":\"${content}\""
            + "    }"
            + "}"
            );
    private static StringTemplate T_VOICE = StringTemplate.compile(
              "{"
            + "    \"touser\":\"${touser}\","
            + "    \"msgtype\":\"voice\","
            + "    \"voice\":"
            + "    {"
            + "      \"media_id\":\"${media_id}\""
            + "    }"
            + "}"
            );
    private static StringTemplate T_VIDEO = StringTemplate.compile(
              "{"
            + "    \"touser\":\"${touser}\","
            + "    \"msgtype\":\"video\","
            + "    \"video\":"
            + "    {"
            + "      \"media_id\":\"${media_id}\","
            + "      \"thumb_media_id\":\"${thumb_media_id}\","
            + "      \"title\":\"${title}\","
            + "      \"description\":\"${description}\""
            + "    }"
            + "}"
            );
    private static StringTemplate T_MUSIC = StringTemplate.compile(
              "{"
            + "    \"touser\":\"${touser}\","
            + "    \"msgtype\":\"music\","
            + "    \"music\":"
            + "    {"
            + "      \"title\":\"${title}\","
            + "      \"description\":\"${description}\","
            + "      \"musicurl\":\"${musicurl}\","
            + "      \"hqmusicurl\":\"${hqmusicurl}\","
            + "      \"thumb_media_id\":\"${thumb_media_id}\""
            + "    }"
            + "}"
            );
    
    /**
     * @param touser 普通用户openid
     * @param content 文本消息内容
    */
    public static String wrapText(String touser, String content) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("touser", touser);
        params.put("content", content);
        return T_TEXT.replace(params);
    }
    
    /**
     * @param touser 普通用户openid
     * @param media_id 发送的语音的媒体ID
    */
    public static String wrapVoice(String touser, String media_id) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("touser", touser);
        params.put("media_id", media_id);
        return T_VOICE.replace(params);
    }
    
    /**
     * @param touser 普通用户openid
     * @param media_id 发送的视频的媒体ID
     * @param thumb_media_id 缩略图的媒体ID
    */
    public static String wrapVideo(String touser, String media_id, String thumb_media_id) {
        
        return wrapVideo(touser, thumb_media_id, thumb_media_id, "", "");
    }
    
    /**
     * @param touser 普通用户openid
     * @param media_id 发送的视频的媒体ID
     * @param thumb_media_id 缩略图的媒体ID
     * @param title 视频消息的标题
     * @param description 视频消息的描述
    */
    public static String wrapVideo(String touser, String media_id, 
            String thumb_media_id, String title, String description) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("touser", touser);
        params.put("media_id", media_id);
        params.put("thumb_media_id", thumb_media_id);
        params.put("title", title);
        params.put("description", description);
        return T_VIDEO.replace(params);
    }
    
    /**
     * @param touser 普通用户openid
     * @param musicurl 音乐链接 
     * @param hqmusicurl 高品质音乐链接，wifi环境优先使用该链接播放音乐
     * @param thumb_media_id 缩略图的媒体ID
    */
    public static String wrapMusic(String touser, String musicurl, 
            String hqmusicurl, String thumb_media_id) {
        
        return wrapMusic(touser, "", "", musicurl, hqmusicurl, thumb_media_id);
    }
    
    /**
     * @param touser 普通用户openid
     * @param title 音乐标题
     * @param description 音乐描述
     * @param musicurl 音乐链接 
     * @param hqmusicurl 高品质音乐链接，wifi环境优先使用该链接播放音乐
     * @param thumb_media_id 缩略图的媒体ID
    */
    public static String wrapMusic(String touser, String title, String description,
            String musicurl, String hqmusicurl, String thumb_media_id) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("touser", touser);
        params.put("title", title);
        params.put("description", description);
        params.put("musicurl", musicurl);
        params.put("hqmusicurl", hqmusicurl);
        params.put("thumb_media_id", thumb_media_id);
        return T_MUSIC.replace(params);
    }
}
