package com.chn.turing.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.chn.common.NetWorkCenter;
import com.chn.common.StringUtils;
import com.chn.wx.vo.result.BasicResult;

public class TuringRobotApi {
	public static final String URL = "http://www.tuling123.com/openapi/api";
	public static final String KEY = "1b1175146795f7d2f6d8d1e5698771a5";

	public static TuringRobotBean talking(String info, String userid) {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("key", KEY);
		paramData.put("info",info);
		if (!StringUtils.isEmpty(userid)) {
			paramData.put("userid", userid);
		}
		BasicResult r = NetWorkCenter.get(URL, paramData);
		String resultJson = !r.hasError() ? r.getErrmsg() : r.toJsonString();
		TuringRobotBean response = JSON.parseObject(resultJson, TuringRobotBean.class);
		return response;
	}
	public static void main(String[] args) {
		String info = talking("今天的新闻", null).getTextInfo();
		System.out.println(info);
	}
}
