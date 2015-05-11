package com.chn.wx.monitor;

import org.apache.log4j.Logger;

import com.chn.wx.dto.Context;

public class SyncProcessProxy extends ProcessProxy {

    private final Logger log = Logger.getLogger(SyncProcessProxy.class);
    
    @Override
    public String process(Context context) {
        
        try {
            
            return this.root.doService(context);
        } catch (Exception e) {
            log.error("任务处理出错", e);
        }
        return null;
    }

}
