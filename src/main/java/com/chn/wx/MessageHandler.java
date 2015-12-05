package com.chn.wx;

import java.util.LinkedHashSet;
import java.util.Set;

import com.chn.common.CacheUtils;
import com.chn.common.CacheUtils.Provider;
import com.chn.common.Scans;
import com.chn.common.StringUtils;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.ThreadsMode;
import com.chn.wx.listener.ThreadsMode.ClassProvider;

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
	
	public static class PackageClassProvider implements ClassProvider {

        private String[] packages;

        public PackageClassProvider(String packages) {
            this.packages = packages.split("\\|");
        }

        @Override
        public Set<Class<?>> getClasses() {

            Set<Class<?>> result = new LinkedHashSet<Class<?>>();
            for(String each : packages)
                result.addAll(CacheUtils.getValue(each, new Provider<String, Set<Class<?>>>() {
                    @Override
                    public Set<Class<?>> get(String key) {
                        
                        Set<Class<?>> result = new LinkedHashSet<>();
                        if (StringUtils.isEmpty(key))
                            return result;
                        for (String pkg : key.split("\\|"))
                            if (!StringUtils.isEmpty(pkg))
                                result.addAll(Scans.getClasses(pkg));
                        return result;
                    }
    
                    @Override
                    public int maxSize() {
                        return 10;
                    }
                }));
            return result;
        }

    }

}
