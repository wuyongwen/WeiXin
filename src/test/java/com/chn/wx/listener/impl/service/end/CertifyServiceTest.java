package com.chn.wx.listener.impl.service.end;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.chn.wx.dto.Context;

public class CertifyServiceTest extends ServiceTest {

    MockCertifyService service = Mockito.mock(MockCertifyService.class);
    private String url = "signature=%s&timestamp=%s&nonce=%s&echostr=%s";
    
    @Test
    public void test() throws Exception {

        String signature = "b32814f098196861fbcbe6fc06a0d4e90a5e121b";
        String timestamp = "20150917111708";
        String nonce = "nonce";
        String echostr = "succ";
        String params = String.format(url, signature, timestamp, nonce, echostr);
        Context context = this.doGetCtxt(params);
        
        this.registReturn(MockCertifyService.class, service);
        Mockito.when(service.doService(context)).thenCallRealMethod();
        
        this.reRegistAndLoad(MockCertifyService.class);
        Assert.assertEquals(echostr, handler.process(context));
        Mockito.verify(service).doService(context);
    }

    public static class MockCertifyService extends CertifyService {

        @Override
        public String doService(Context context) throws Exception {
            
            return super.doService(context);
        }
        
    }
    
}
