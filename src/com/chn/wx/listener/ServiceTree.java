/**
 * WeiXin
 * @title ServiceTree.java
 * @package com.chn.wx.tree
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月16日-下午1:35:42
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.chn.common.Assert;
import com.chn.wx.annotation.Node;
import com.chn.wx.dto.Context;

/**
 * @class ServiceTree
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class ServiceTree {

    private static Logger log = Logger.getLogger(ServiceTree.class);
    private Map<Class<?>, Map<String, Class<? extends Service>>> map = new ConcurrentHashMap<>();
    private ServiceFactory factory = new ServiceFactory();
    
    public ServiceTree(ClassProvider provider) {
        
        this.load(provider);
    }
    
    public Service route(Context context, String identify) {
        
        if(identify == null) return null;
        Class<?> previous = getCallerService();
        Assert.notNull(previous, "不合法的调用栈，请明确父结点位置！");
        return route(context, previous, identify);
    }
    
    public Service route(Context context, Class<?> callFrom, String identify) {
        
        if(identify == null) return null;
        Map<String, Class<? extends Service>> childrenNodes = map.get(callFrom);
        Assert.notNull(childrenNodes, "类[%s]无子节点！", callFrom);
        
        Class<? extends Service> childNodeClass = childrenNodes.get(identify);
        Assert.notNull(childNodeClass, "类[%s]不存在子节点[%s]！", callFrom, identify);
        
        return factory.buildService(context, childNodeClass);
    }
    
    private synchronized void load(ClassProvider provider) {
        
        Set<Class<?>> allClasses = provider.getClasses();
        Map<Node, Class<? extends Service>> allNodes = this.filtService(allClasses);
        
        int nodeSize = allNodes.size();
        do {
            for(Node node : allNodes.keySet()) {
                Map<String, Class<? extends Service>> childrenNodes = map.get(node.parent());
                if(childrenNodes == null) {
                    childrenNodes = new HashMap<>();
                    map.put(node.parent(), childrenNodes);
                }
                Class<? extends Service> previous = childrenNodes.put(node.value(), allNodes.get(node));
                if(previous != null)
                    log.info(String.format("类[%s]的后续结点[%s]从[%s]切换到[%s]", 
                            node.parent().getName(), node.value(), previous.getName(), 
                            allNodes.get(node).getName()));
                else 
                    log.info(String.format("类[%s]的后续结点[%s]为[%s]", 
                            node.parent().getName(), node.value(), allNodes.get(node).getName()));
            }
        } while(nodeSize > (nodeSize = allNodes.size()));
    }
    
    @SuppressWarnings("unchecked")
    private Map<Node, Class<? extends Service>> filtService(Set<Class<?>> allClasses) {
        
        Map<Node, Class<? extends Service>> result = new HashMap<>();
        for(Class<?> clazz : allClasses) 
            if(clazz.isAnnotationPresent(Node.class))
                result.put(clazz.getAnnotation(Node.class), (Class<? extends Service>) clazz);
        return result;
    }
    
    private Class<?> getCallerService() {
        
        try {
            StackTraceElement[] eles = Thread.currentThread().getStackTrace();
            Class<?> clazz = null;
            for(StackTraceElement ele : eles) {
                clazz = Class.forName(ele.getClassName());
                if(Service.class.isAssignableFrom(clazz))
                    return clazz;
            }
        } catch (ClassNotFoundException e) {
            //Ignore
        }
        return null;
    }
    
    public static interface ClassProvider {
        
        public Set<Class<?>> getClasses();
    }
}
