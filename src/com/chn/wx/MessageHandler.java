/**
 * WeiXin
 * @title MessageHandler.java
 * @package com.chn.wx
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2015年4月29日-下午10:17:34
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx;

import java.util.LinkedHashSet;
import java.util.Set;

import com.chn.common.Cfg;
import com.chn.common.Scans;
import com.chn.common.StringUtils;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.ServiceTree;
import com.chn.wx.listener.ServiceTree.ClassProvider;


/**
 * @class MessageHandler
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class MessageHandler {

    private ServiceTree serviceTree;
    
    public void init() {
        
        Cfg cfg = Cfg.getCfg("/weixin.properties");
        String packages = cfg.get("weixin.service.package");
        
        this.serviceTree = new ServiceTree();
        this.serviceTree.loadClass(new PackageClassProvider("com.chn.wx.listener"));
        this.serviceTree.loadClass(new PackageClassProvider(packages));
    }

    public String process(Context context) throws Exception {
        
        return serviceTree.route(context, "root").doService(serviceTree, context);
    }
    
    public void destroy() {
        
    }
    
    private static class PackageClassProvider implements ClassProvider {
        
        private String packages;
        
        public PackageClassProvider(String packages) {
            this.packages = packages;
        }
        
        @Override
        public Set<Class<?>> getClasses() {
            
            Set<Class<?>> result = new LinkedHashSet<>();
            if(StringUtils.isEmpty(packages)) 
                return result;
            for(String pkg : packages.split("\\|"))
                if(!StringUtils.isEmpty(pkg))
                    result.addAll(Scans.getClasses(pkg));
            return result;
        }
        
    }
}
