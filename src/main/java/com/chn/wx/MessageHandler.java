package com.chn.wx;

import com.chn.wx.dto.Context;
import com.chn.wx.listener.ThreadsMode;

/**
 * @class MessageHandler
 * @author lzxz1234
 * @description
 * @version v1.0
 */
public class MessageHandler {

    private ThreadsMode proxy;
    
	public ThreadsMode getThreadsMode() {
	    
	    return proxy;
	}
	
	public void setThreadsMode(ThreadsMode proxy) {
	    
	    this.proxy = proxy;
	}
	
	public String process(Context context) {

		return proxy.process(context);
	}

}
