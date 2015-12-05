package com.chn.wx.ioc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.chn.wx.ioc.provider.IocProvider;


public class Ioc {

    private IocProvider[] providers;
    
    public synchronized void registProvider(IocProvider provider) {
        
        List<IocProvider> list = new ArrayList<>();
        if(providers != null)
            list.addAll(Arrays.asList(providers));
        list.add(provider);
        providers = list.toArray(new IocProvider[0]);
    }
    
}
