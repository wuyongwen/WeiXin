package com.chn.wx.api;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.chn.common.Assert;
import com.chn.common.HttpUtils;
import com.chn.common.StringTemplate;
import com.chn.wx.vo.result.UploadFileResult;

/**
 * @class MaterialManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class MaterialManager {

    private static final StringTemplate postFileUrl = StringTemplate.compile(WeiXinURL.POST_FILE);
    private static final StringTemplate getFileUrl = StringTemplate.compile(WeiXinURL.GET_FILE);
    
    public static enum Type {
        image, //1M，支持JPG格式 
        voice, //2M，播放长度不超过60s，支持AMR\MP3格式
        video, //10MB，支持MP4格式
        thumb; //64KB，支持JPG格式
    }
    
    /**
     * downloadFile
     * @param mediaId
     * @return 返回文件内容
     */
    public static byte[] downloadTemp(String mediaId) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        params.put("mediaId", mediaId);
        String urlLocation = getFileUrl.replace(params);
        
        return HttpUtils.download(urlLocation);
    }
    
    /**
     * 文件上传，媒体文件在后台保存时间为3天，即3天后media_id失效。 
     * @param type
     * @param fileContent
     * @return 返回 media_id
    */
    public static String uploadTemp(Type type, byte[] fileContent) {
        
        UploadFileResult result = null;
        Map<String, Object> params = new HashMap<>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        params.put("type", type.toString());
        String urlLocation = postFileUrl.replace(params);
        
        String respJson = HttpUtils.post(urlLocation, fileContent);
        result = JSON.parseObject(respJson, UploadFileResult.class);
        Assert.notNull(result.getMediaId(), "文件上传失败[%s][%s]", result.getErrcode(), result.getErrmsg());
        return result.getMediaId();
    }
    
}
