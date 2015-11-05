package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.chn.wx.vo.Button;

public class QueryMenuResult extends BasicResult {

    private static final long serialVersionUID = 4737358468829187197L;
    
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
