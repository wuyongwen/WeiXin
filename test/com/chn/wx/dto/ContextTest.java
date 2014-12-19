/**
 * WeiXin
 * @title ContextTest.java
 * @package com.chn.wx.trans
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月16日-下午5:36:46
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.dto;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;

/**
 * @class ContextTest
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ContextTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    
    private  Context context;
    
    private static final String DEFAULT = "defaultValue";
    private static final String[] VALUE1 = new String[]{};
    private static final String[] VALUE2 = new String[]{"12"};
    private static final String[] VALUE3 = new String[]{"value31", "value32"};
    
    @Before
    public void init() {
        
        Map<String, String[]> params = new HashMap<>();
        params.put("v1", VALUE1);
        params.put("v2", VALUE2);
        params.put("v3", VALUE3);
        Mockito.when(request.getParameterMap()).thenReturn(params);
        context = new Context(request, response);
    }
    
    @Test
    public void test() {
        
        TestBean bean = new TestBean();
        context.injectField(bean);
        Assert.assertEquals("", bean.v1);
        Assert.assertEquals(new Integer(VALUE2[0]), bean.v2);
        Assert.assertArrayEquals(VALUE3, bean.v3);
        Assert.assertEquals(DEFAULT, bean.v4);
    }

    static class TestBean {
        
        @Param private String v1;
        @Param private Integer v2;
        @Param(value="v3") private String[] v3;
        @Param(value="v4", defaultValue=ContextTest.DEFAULT) private String v4;
    }
}