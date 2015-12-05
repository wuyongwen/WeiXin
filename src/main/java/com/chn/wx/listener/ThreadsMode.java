package com.chn.wx.listener;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.chn.wx.annotation.Node;
import com.chn.wx.dto.Context;
import com.chn.wx.listener.impl.service.route.MethodRouter;

public abstract class ThreadsMode {

    protected ServiceAgent root;
    
    public abstract String process(Context context);
    
    /*public ThreadsMode(ClassProvider provider) {

        LinkedHashSet<Class<?>> allClass = new LinkedHashSet<>();
        Set<Class<?>> allClasses = provider.getClasses();
        allClass.removeAll(allClasses);
        allClass.addAll(allClasses);
        this.load(this.filtService(allClass), root);
    }*/
    
    private void load(Map<Node, Class<? extends Service>> allNodes, 
                      ServiceAgent holder) {
        
        Map<Class<?>, ServiceAgent> all = new HashMap<>();
        for(Entry<Node, Class<? extends Service>> entry : allNodes.entrySet()) {
            Node node = entry.getKey();
            Class<? extends Service> realService = entry.getValue();
            for(Class<? extends Service> each : node.parents()) {
                ServiceAgent pNode = all.get(each);
                if(pNode == null) {
                    pNode = new ServiceAgent();
                    pNode.setRealServiceClass(each);
                    all.put(each, pNode);
                }
                ServiceAgent nNode = all.get(realService);
                if(nNode == null) {
                    nNode = pNode.registNext(node.value(), realService);
                    all.put(realService, nNode);
                } else
                    pNode.registNext(node.value(), nNode);
            }
        }
        this.root = all.get(MethodRouter.class);
    }

    @SuppressWarnings("unchecked")
    private Map<Node, Class<? extends Service>> filtService(Set<Class<?>> allClasses) {

        Map<Node, Class<? extends Service>> result = new HashMap<>();
        for (Class<?> clazz : allClasses)
            if (clazz.isAnnotationPresent(Node.class))
                result.put(clazz.getAnnotation(Node.class), (Class<? extends Service>) clazz);
        return result;
    }

    public static interface ClassProvider {

        public Set<Class<?>> getClasses();
    }

}
