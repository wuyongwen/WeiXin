package com.chn.wx.listener;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.chn.wx.annotation.Node;
import com.chn.wx.dto.Context;

public abstract class ProcessProxy {

    protected ServiceProxy root = new ServiceProxy();
    
    public abstract String process(Context context);
    
    public void loadClass(ClassProvider... providers) {

        LinkedHashSet<Class<?>> allClass = new LinkedHashSet<>();
        for (ClassProvider provider : providers)
            allClass.addAll(provider.getClasses());
        this.loopload(this.filtService(allClass), root);
    }
    
    private synchronized void loopload(Map<Node, 
                                       Class<? extends Service>> allNodes, 
                                       ServiceProxy holder) {

        for (Entry<Node, Class<? extends Service>> entry : allNodes.entrySet()) {
            Node node = entry.getKey();
            Class<? extends Service> serviceClass = entry.getValue();
            if (root.getRealService() == null) {
                // TODO: 为什么 parent 不能为 null?
                if ("root".equals(node.value())) {
                    root.setRealServiceClass(serviceClass);
                    loopload(allNodes, holder);
                }
            } else {
                if (root.getRealService().equals(node.parents())) {
                    loopload(allNodes, root.registNext(node.value(), serviceClass));
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Map<Node, Class<? extends Service>> filtService(Set<Class<?>> allClasses) {

        Map<Node, Class<? extends Service>> result = new HashMap<>();
        for (Class<?> clazz : allClasses)
            if (clazz.isAnnotationPresent(Node.class))
                result.put(clazz.getAnnotation(Node.class),
                        (Class<? extends Service>) clazz);
        return result;
    }

    public static interface ClassProvider {

        public Set<Class<?>> getClasses();
    }

}
