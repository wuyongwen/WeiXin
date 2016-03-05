package com.chn.wx.store;

import junit.framework.Assert;

import org.junit.Test;

public class PlatformConfigYamlStorageTest {

	PlatformConfigYamlStorage configStore = new PlatformConfigYamlStorage();
	@Test
	public void test() {
		
		Assert.assertEquals(null, configStore.getTicket());
		Assert.assertEquals(null, configStore.getAccessToken());
		Assert.assertEquals(true, configStore.isAccessTokenExpired());
		configStore.updateVerifyTicket("ticket");
		Assert.assertEquals("ticket", configStore.getTicket());
	}

}
