package com.chn.wx;

import org.junit.Test;

import com.chn.common.Lang;
import com.chn.wx.ioc.provider.JsonIocProvider;

public class MessageHandlerTest {

	@Test
	public void test() {
		new JsonIocProvider(new String(Lang.loadFromClassPath("/weixin.js")));
	}

}
