/**
 * WeiXin
 * @title FileManager.java
 * @package com.chn.wx.invocation
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月18日-下午3:40:55
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.invocation;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.chn.common.Assert;
import com.chn.common.HttpUtils;
import com.chn.common.StringTemplate;
import com.chn.wx.vo.result.UploadFileResult;

/**
 * @class FileManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class FileManager {

    private static final StringTemplate postFileUrl = StringTemplate.compile(WeiXinURL.POST_FILE);
    private static final StringTemplate getFileUrl = StringTemplate.compile(WeiXinURL.GET_FILE);
    
    public static enum Type {
        image, voice, video, thumb;
    }
    
    /**
     * @description downloadFile
     * @param mediaId
     * @return 返回可访问的 http 地址
     */
    public static byte[] downloadFile(String mediaId) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        params.put("mediaId", mediaId);
        String urlLocation = getFileUrl.replace(params);
        
        return HttpUtils.download(urlLocation);
    }
    
    /**
     * @description 文件上传
     * @param type
     * @param fileContent
     * @return 返回 media_id
    */
    public static String postFile(Type type, byte[] fileContent) {
        
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
