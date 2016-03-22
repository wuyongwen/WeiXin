package com.chn.wx.store;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.ho.yaml.Yaml;

import com.chn.common.Lang;
/**
 * 平台信息保存到YAML配置文件中
 * @author wen
 */
public class PlatformConfigYamlStorage implements PlatformConfigStorage {
	public static Logger log = Logger
			.getLogger(PlatformConfigYamlStorage.class);
	static final String YML = "platformconf.yml";
	static PlatformConfig config = PlatformConfigYamlStorage.load();

	@Override
	public void updateVerifyTicket(String verify_ticketb,String createTime) {
		config.setVerifyTicket(verify_ticketb);
		store();
	}

	private void store() {
		try {
			Yaml.dump(config, Lang.saveClassPathFile(YML));
		} catch (FileNotFoundException e) {
			log.error("保存平台验证信息到文件失败！", e);
		} catch (IOException e) {
			log.error("保存平台验证信息到文件失败！", e);
		}
	}

	private static PlatformConfig load() {
		if (!Lang.checkClassPathFile(YML))
			return new PlatformConfig();
		return (PlatformConfig) Yaml.load(new String(Lang
				.loadFromClassPath("/"+YML)));
	}

	@Override
	public String getTicket() {
		return config.getVerifyTicket();
	}

	@Override
	public void updateAccessToken(String accessToken, Integer expiresIn) {
		config.setAccessToken(accessToken);
		config.setExpiresIn(System.currentTimeMillis() + (expiresIn - 200)* 1000l);
		store();
	}

	@Override
	public String getAccessToken() {
		if(isAccessTokenExpired()) return null;
		return config.getAccessToken();
	}

	
	@Override
	public boolean isAccessTokenExpired() {
		return System.currentTimeMillis() > config.getExpiresIn();
	}

	@Override
	public void updatePreAuthCode(String preAuthCode, Integer expiresIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPreAuthCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPreAuthCodeExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
}

