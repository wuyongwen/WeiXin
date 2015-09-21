package com.chn.wx.api;

import static com.chn.common.StringTemplate.compile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.chn.common.HttpUtils;
import com.chn.common.StringTemplate;

public class UrlTransformer {

    private static final StringTemplate urlTransformUrl = compile(WeiXinURL.SHORT_URL);

    /**
     * @param longUrl 需要转换的长链接，支持http://、https://、weixin://wxpay 格式的url 
     * @return 长地址转短地址
     */
    public static String long2short(String longUrl) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = urlTransformUrl.replace(params);
        try {
            return HttpUtils.post(urlLocation, "action=long2short&long_url="+URLEncoder.encode(longUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("No Avaliable");
        }
    }
}
