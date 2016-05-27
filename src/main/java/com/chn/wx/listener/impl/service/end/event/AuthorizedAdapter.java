package com.chn.wx.listener.impl.service.end.event;

import org.apache.log4j.Logger;

import com.chn.wx.annotation.Node;
import com.chn.wx.annotation.Param;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.Service;
import com.chn.wx.listener.impl.service.route.EventRouter;
import com.chn.wx.listener.impl.service.route.PlatFormEventRouter;

@Node(parents = { PlatFormEventRouter.class, EventRouter.class }, value = "authorized")
public class AuthorizedAdapter implements Service {
	protected Logger log = Logger.getLogger(AuthorizedAdapter.class);
	@Param
	protected String AppId;
	@Param
	protected String CreateTime;
	@Param
	protected String AuthorizerAppid;
	@Param
	protected String AuthorizationCode;
	@Param
	protected String AuthorizationCodeExpiredTime;

	@Override
	public String doService(Context context) throws Exception {
		log.debug(String.format("收到授权事件，公众号ID： %s", AuthorizerAppid));
		return DEFAULT_RETURN;
	}

}
