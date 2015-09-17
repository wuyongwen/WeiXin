package com.chn.wx.listener.impl.process;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.chn.common.Exec;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.ThreadsMode;

public class AsyncThreadMode extends ThreadsMode {

    private final Logger log = Logger.getLogger(AsyncThreadMode.class);
    
    @Override
    public String process(final Context context) {
        
        Exec.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                
                try {
                    AsyncThreadMode.this.root.doService(context);
                } catch (Exception e) {
                    log.error("任务处理出错", e);
                }
                return null;
            }
            
        });
        return null; //异步模式忽略返回
    }

}
