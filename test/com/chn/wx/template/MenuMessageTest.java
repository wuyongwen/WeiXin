/**
 * WeiXin
 * @title MenuMessageTest.java
 * @package com.chn.wx.template
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月30日-上午9:49:54
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.template;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.chn.wx.vo.Button;

/**
 * @class MenuMessageTest
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class MenuMessageTest {

    private Button[] buttons = new Button[2];
    
    @Before
    public void constructMenu() {
        
        buttons[0] = new Button();
        buttons[0].setName("今日歌曲");
        buttons[0].setType("click");
        buttons[0].setKey("V1001_TODAY_MUSIC");
        buttons[1] = new Button();
        buttons[1].setName("菜单");
        buttons[1].setSubButton(new Button[3]);
        buttons[1].getSubButton()[0] = new Button();
        buttons[1].getSubButton()[0].setType("view");
        buttons[1].getSubButton()[0].setName("搜索");
        buttons[1].getSubButton()[0].setUrl("http://www.soso.com/");
        buttons[1].getSubButton()[1] = new Button();
        buttons[1].getSubButton()[1].setType("view");
        buttons[1].getSubButton()[1].setName("视频");
        buttons[1].getSubButton()[1].setUrl("http://v.qq.com/");
        buttons[1].getSubButton()[2] = new Button();
        buttons[1].getSubButton()[2].setType("click");
        buttons[1].getSubButton()[2].setName("赞一下我们");
        buttons[1].getSubButton()[2].setKey("V1001_GOOD");
    }
    
    @Test
    public void testCreateMenu() {
        
        String wrapJson = MenuMessage.wrapCreateButtons(buttons);
        System.out.println(wrapJson);
        CreateMenuRequest result = JSON.parseObject(wrapJson, CreateMenuRequest.class);
        Assert.assertEquals(CompareToBuilder.reflectionCompare(buttons, result.getButtons()), 0);
    }

    public static class CreateMenuRequest {
        @JSONField(name="button") private Button[] buttons;
        public Button[] getButtons() {
            return buttons;
        }
        public void setButtons(Button[] buttons) {
            this.buttons = buttons;
        }
    }
    
}
