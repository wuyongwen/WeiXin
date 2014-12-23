/**
 * WeiXin
 * @title LocationMessage.java
 * @package com.chn.wx.vo
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月16日-上午10:36:16
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.vo.message;

/**
 * @class LocationMessage
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class LocationMessage extends Message {

    private String Location_X;//地理位置维度
    private String Location_Y;//地理位置经度
    private String Scale;     //地图缩放大小
    private String Label;     //地理位置信息
    
    public String getLocation_X() {
        return Location_X;
    }
    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }
    public String getLocation_Y() {
        return Location_Y;
    }
    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }
    public String getScale() {
        return Scale;
    }
    public void setScale(String scale) {
        Scale = scale;
    }
    public String getLabel() {
        return Label;
    }
    public void setLabel(String label) {
        Label = label;
    }
    
}
