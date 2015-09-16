package com.chn.wx.listener.impl.process;

import org.apache.log4j.Logger;

import com.chn.wx.dto.Context;
import com.chn.wx.listener.ThreadsMode;

public class SyncThreadMode extends ThreadsMode {

    private final Logger log = Logger.getLogger(SyncThreadMode.class);
    
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
