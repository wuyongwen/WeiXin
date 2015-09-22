/**
 * WeiXin
 * @title MenuMessage.java
 * @package com.chn.wx.template
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月30日-上午9:29:37
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.template;

import java.util.HashMap;
import java.util.Map;

import com.chn.common.StringTemplate;
import com.chn.wx.vo.Button;

import static com.chn.common.StringTemplate.compileFromClassPath;

/**
 * @class MenuMessage
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class MenuMessage {

    private static StringTemplate T_CREATE = compileFromClassPath("/com/chn/wx/template/tpl/menu-create.json");
    private static StringTemplate T_CREATE_BUTTON = compileFromClassPath("/com/chn/wx/template/tpl/menu-create-button.json");

    /**
     * 修改菜单报文封装
     */
    public static String wrapCreateButtons(Button... buttons) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("button", wrapButtons(buttons));
        return T_CREATE.replace(params);
    }
    
    private static String wrapButtons(Button[] buttons) {
        
        StringBuilder sb = new StringBuilder();
        for(Button button : buttons) 
            sb.append(wrapButton(button)).append(',');
        if(sb.length() > 0)
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    private static String wrapButton(Button button) {
        
        Map<String, Object> params = new HashMap<>();
        if(button.getName() != null) 
            params.put("name", button.getName());
        if(button.getType() != null) 
            params.put("type", button.getType());
        if(button.getKey() != null) 
            params.put("key", button.getKey());
        if(button.getUrl() != null) 
            params.put("url", button.getUrl());
        if(button.getSubButton() != null)
            params.put("sub_button", wrapButtons(button.getSubButton()));
        return T_CREATE_BUTTON.replace(params);
    }
    
}
