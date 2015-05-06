package com.chn.wx.core;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.chn.common.Exec;
import com.chn.wx.dto.Context;

public class AsyncProcessProxy extends ProcessProxy {

    private final Logger log = Logger.getLogger(AsyncProcessProxy.class);
    
    @Override
    public String process(Context context) {
        
        Exec.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                
                try {
                    AsyncProcessProxy.this.root.doService(context);
                } catch (Exception e) {
                    log.error("任务处理出错", e);
                }
                return null;
            }
            
        });
        return null; //异步模式忽略返回
    }

}
