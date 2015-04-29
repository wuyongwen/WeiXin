/**
 * WeiXin
 * @title QueryMenuResult.java
 * @package com.chn.wx.vo.result
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月30日-上午10:45:18
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.chn.wx.vo.Button;

/**
 * @class QueryMenuResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class QueryMenuResult {

    @JSONField(name="menu") private Menu menu;
    
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public Button[] getButtons() {
        
        return menu == null ? new Button[0] : menu.getButtons();
    }

    public static class Menu {
        @JSONField(name="button") private Button[] buttons;
        
        public Button[] getButtons() {
            return buttons;
        }
        public void setButtons(Button[] buttons) {
            this.buttons = buttons;
        }
        
    }
    
}
