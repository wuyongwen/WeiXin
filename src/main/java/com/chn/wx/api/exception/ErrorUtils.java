package com.chn.wx.api.exception;

import com.chn.wx.vo.result.BasicResult;

public class ErrorUtils {

	public static void checkWXError(BasicResult result) throws WechatErrorException {
		if (!result.hasError())
			return;
		ErrorMsg msg = ERROR.WEERR.buildErrorMsg(result);
		throw new WechatErrorException(msg);
	}
}
