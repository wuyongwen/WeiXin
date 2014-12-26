/**
 * WeiXin
 * @title HttpUtils.java
 * @package com.chn.wx.util
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月16日-上午10:21:41
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

/**
 * @class HttpUtils
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class HttpUtils {

    private static final int TIME_OUT = 3000;
    
    public static String read(HttpServletRequest req) throws IOException {
        
        InputStream is = req.getInputStream();
        try {
            return IOUtils.toString(is, req.getCharacterEncoding());
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
    
    /**
     * @description 解析请求参数，单值请求参数 map.get() 直接返回值，多值参数返回数组
     * @param req
     * @return 
    */
    public static Map<String, Object> decodeParams(HttpServletRequest req) {
        
        Map<String, Object> result = new HashMap<>();
        Map<String, String[]> originalParams = req.getParameterMap();
        
        Iterator<Map.Entry<String, String[]>> it = originalParams.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, String[]> entry = it.next();
            String key = entry.getKey();
            String[] value = entry.getValue();
            result.put(key, value.length == 0 ? "" : (value.length == 1 ? value[0] : value));
        }
        return result;
    }
    
    public static String get(String urlLocation) {
        
        
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            URL url = new URL(urlLocation);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(TIME_OUT);
            conn.connect();
            
            is = conn.getInputStream();
            return IOUtils.toString(is);
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.close(conn);
        }
    }
    
    public static String post(String urlLocation, String content) {
        
        
        HttpURLConnection conn = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            URL url = new URL(urlLocation);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(TIME_OUT);
            conn.connect();
            
            os = conn.getOutputStream();
            os.write(content.getBytes("UTF-8"));
            IOUtils.closeQuietly(os);
            
            is = conn.getInputStream();
            return IOUtils.toString(is);
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.close(conn);
        }
    }

    public static String post(String urlLocation, byte[] fileContent) {
        
        
        HttpURLConnection conn = null;
        OutputStream os = null;
        InputStream is = null;
        try {
            URL url = new URL(urlLocation);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=3c74f240-7d35-41d0-96b0-268d02cff7e3");
            
            os=new BufferedOutputStream(conn.getOutputStream());
            os.write("--3c74f240-7d35-41d0-96b0-268d02cff7e3\r\n".getBytes());
            os.write("Content-Disposition: form-data; name=\"media\"\r\n".getBytes());
            os.write("Content-Type: application/octet-stream\r\n\r\n".getBytes());
            os.write(fileContent);
            os.write("\r\n".getBytes());
            os.write(("--3c74f240-7d35-41d0-96b0-268d02cff7e3--\r\n").getBytes());
            IOUtils.closeQuietly(os);
            
            is = conn.getInputStream();
            return IOUtils.toString(is);
        } catch (Exception e) {
            throw new RuntimeException("请求错误！", e);
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.close(conn);
        }
    }

}
