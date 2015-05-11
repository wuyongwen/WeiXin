package com.chn.common;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLConnection;

public class IOUtils {

    public static void closeQuietly(Closeable os) {
        
        try {
            if(os != null) os.close();
        } catch (Exception e) {
            //Ignore
        }
    }

    public static void close(URLConnection conn) {
        if(conn instanceof HttpURLConnection) {
            ((HttpURLConnection)conn).disconnect();
        }
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }
    
    public static int copy(InputStream input, OutputStream output) 
            throws IOException {
        
        byte[] buffer = new byte[1024];
        long count = 0L;
        int n = 0;
        while(-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return (int)count;
    }

    public static String toString(InputStream is, String characterEncoding) 
            throws UnsupportedEncodingException, IOException {
        
        return new String(toByteArray(is), characterEncoding);
    }
      
}
