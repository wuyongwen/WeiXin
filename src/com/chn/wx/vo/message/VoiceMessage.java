/**
 * WeiXin
 * @title VoiceMessage.java
 * @package com.chn.wx.vo
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月16日-上午10:33:05
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.vo.message;

/**
 * @class VoiceMessage
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class VoiceMessage extends Message {

    private String Format;  //语音格式，如amr，speex等
    private String MediaId; //语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
    
    private String voiceLocation;

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getVoiceLocation() {
        return voiceLocation;
    }

    public void setVoiceLocation(String voiceLocation) {
        this.voiceLocation = voiceLocation;
    }
    
    
}
