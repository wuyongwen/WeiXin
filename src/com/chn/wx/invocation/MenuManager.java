/**
 * WeiXin
 * @title MenuManager.java
 * @package com.chn.wx.invocation
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月30日-上午10:16:00
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.invocation;

import static com.chn.common.StringTemplate.compile;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.chn.common.StringTemplate;
import com.chn.wx.template.MenuMessage;
import com.chn.wx.util.HttpUtils;
import com.chn.wx.vo.Button;
import com.chn.wx.vo.result.BasicResult;
import com.chn.wx.vo.result.QueryMenuResult;

/**
 * @class MenuManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class MenuManager {

    private static StringTemplate createButtons = compile(WeiXinURL.CREATE_BUTTONS);
    private static StringTemplate queryMenu = compile(WeiXinURL.QUERY_MENU);
    
    /**
     * @description 自定义菜单创建接口
     * @param button
     * @return 
     */
    public static BasicResult createButtons(Button... button) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = createButtons.replace(params);
        String respString = HttpUtils.post(urlLocation, MenuMessage.wrapCreateButtons(button));
        return JSON.parseObject(respString, BasicResult.class);
    }
    
    /**
     * @description 自定义菜单查询接口
     * @return 
     */
    public QueryMenuResult queryMenu() {
        
        Map<String, Object> params = new HashMap<>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = queryMenu.replace(params);
        String respString = HttpUtils.get(urlLocation);
        return JSON.parseObject(respString, QueryMenuResult.class);
    }
    
}
