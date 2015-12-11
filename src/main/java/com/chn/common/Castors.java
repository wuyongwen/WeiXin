package com.chn.common;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;


@SuppressWarnings("unchecked")
public class Castors {

    private static MultiKeyMap map = new MultiKeyMap();
    
    static {
        //初始化转换器
        map.put(String.class, byte.class, new StringToByte());
        map.put(String.class, Byte.class, new StringToByte());
        map.put(String.class, int.class, new StringToInt());
        map.put(String.class, Integer.class, new StringToInt());
        map.put(String.class, short.class, new StringToShort());
        map.put(String.class, Short.class, new StringToShort());
        map.put(String.class, long.class, new StringToLong());
        map.put(String.class, Long.class, new StringToLong());
        map.put(String.class, float.class, new StringToFloat());
        map.put(String.class, Float.class, new StringToFloat());
        map.put(String.class, double.class, new StringToDouble());
        map.put(String.class, Double.class, new StringToDouble());
        map.put(String.class, boolean.class, new StringToBoolean());
        map.put(String.class, Boolean.class, new StringToBoolean());
        map.put(String.class, Date.class, new StringToDate());
    }
    
    /**
     * @param fromClass
     * @param toClass
     * @param fromObj
     * @return
     */
    private static <F, T> T cast(Class<F> fromClass, Class<T> toClass, F fromObj) {
        
        if(fromClass == null || toClass == null) throw new NullPointerException("参数禁止为空");
        if(toClass.isAssignableFrom(fromClass)) return (T) fromObj;
        if(fromObj == null) return null;
        
        if(toClass.isArray()) {
            Class<?> componentType = toClass.getComponentType();
            String[] splits = fromObj.toString().split("[, ]+");
            T result = (T) Array.newInstance(componentType, splits.length);
            for(int i = 0; i < splits.length; i ++)
                Array.set(result, i, Castors.cast(componentType, splits[i]));
            return result;
        } else {
            Castor<F, T> castor = map.get(fromClass, toClass);
            if(castor == null)
                throw new IllegalArgumentException("不允许的类型转换 " + fromClass +"-->" + toClass);
            return castor.cast(fromObj);
        }
    }
    
    /**
     * 转换类型，将 fromObj 转换为 toClass 类型
     * @param toClass
     * @param fromObj
     * @return
     */
    public static <F, T> T cast(Class<T> toClass, F fromObj) {
        
        if(fromObj == null) return null;
        Class<F> fromClass = (Class<F>) fromObj.getClass();
        return cast(fromClass, toClass, fromObj);
    }
    
    private static class StringToByte implements Castor<String, Byte> {
        @Override
        public Byte cast(String from) {
            return StringUtils.isEmpty(from) ? null : new Byte(from);
        }
    }
    private static class StringToInt implements Castor<String, Integer> {
        @Override
        public Integer cast(String from) {
            return StringUtils.isEmpty(from) ? null : new Integer(from);
        }
    }
    private static class StringToShort implements Castor<String, Short> {
        @Override
        public Short cast(String from) {
            return StringUtils.isEmpty(from) ? null : new Short(from);
        }
    }
    private static class StringToLong implements Castor<String, Long> {
        @Override
        public Long cast(String from) {
            return StringUtils.isEmpty(from) ? null : new Long(from);
        }
    }
    private static class StringToFloat implements Castor<String, Float> {
        @Override
        public Float cast(String from) {
            return StringUtils.isEmpty(from) ? null : new Float(from);
        }
    }
    private static class StringToDouble implements Castor<String, Double> {
        @Override
        public Double cast(String from) {
            return StringUtils.isEmpty(from) ? null : new Double(from);
        }
    }
    private static class StringToBoolean implements Castor<String, Boolean> {
        @Override
        public Boolean cast(String from) {
            return Boolean.valueOf(from);
        }
    }
    private static class StringToDate implements Castor<String, Date> {
        private static final String[] FORMATS = new String[] {
            "yyy-MM-dd HH:mm:ss",
            "yyy-MM-dd",
        };
        @Override
        public Date cast(String from) {
            for(String format : FORMATS)
                if(tryCast(from, format) != null)
                    return tryCast(from, format);
            throw new RuntimeException("未识别格式 " + from + " 请添加实现");
        }
        private Date tryCast(String from, String format) {
            try {
                return StringUtils.isEmpty(from) ? null : new SimpleDateFormat(format).parse(from);
            } catch (Exception e) {
                return null;
            }
        }
    }
    
    public static interface Castor<F, T> {
        public T cast(F from);
    }
    
}
