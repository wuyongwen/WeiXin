package com.chn.wx.listener.impl.process;

import org.apache.log4j.Logger;

import com.chn.wx.dto.Context;
import com.chn.wx.ioc.core.BeanFactory;
import com.chn.wx.listener.ThreadsMode;

public class SyncThreadMode extends ThreadsMode {

    private final Logger log = Logger.getLogger(SyncThreadMode.class);
    
    public SyncThreadMode(BeanFactory factory) {
        
        super(factory);
    }
    
    @Override
    public String process(Context context) {
        
        try {
            
            return super.process(context);
        } catch (Exception e) {
            log.error("任务处理出错", e);
        }
        return null;
    }

}
