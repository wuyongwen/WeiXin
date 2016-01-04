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
    private static <F, T> T cast(Class<F> fromClass, Class<T> toClass, F fromObj, T defaultValue) {
        
        if(fromClass == null || toClass == null) throw new NullPointerException("参数禁止为空");
        if(toClass.isAssignableFrom(fromClass)) return (T) fromObj;
        if(fromObj == null) return defaultValue;
        
        if(toClass.isArray()) {
            Class<?> componentType = toClass.getComponentType();
            String[] splits = fromObj.toString().split("[, ]+");
            T result = (T) Array.newInstance(componentType, splits.length);
            for(int i = 0; i < splits.length; i ++)
                Array.set(result, i, Castors.cast(componentType, splits[i]));
            return result;
        } else {
            Castor<F, T> castor = map.get(fromClass, toClass);
            if(castor != null) return castor.cast(fromObj, defaultValue);
            Castor<T, F> castor2 = map.get(toClass, fromClass);
            if(castor2 != null) return castor2.reverse(fromObj, defaultValue);
            throw new IllegalArgumentException("不允许的类型转换 " + fromClass +"-->" + toClass);
        }
    }
    
    /**
     * 转换类型，将 fromObj 转换为 toClass 类型
     * @param toClass
     * @param fromObj
     * @return
     */
    public static <F, T> T cast(Class<T> toClass, F fromObj) {
        
        return cast(toClass, fromObj, null);
    }
    
    public static <F, T> T cast(Class<T> toClass, F fromObj, T defaultValue) {
        
        if(fromObj == null) return defaultValue;
        Class<F> fromClass = (Class<F>) fromObj.getClass();
        return cast(fromClass, toClass, fromObj, defaultValue);
    }
    
    private static class StringToByte extends StringAdapter<Byte> {
        @Override
        public Byte cast(String from, Byte defauleValue) {
            return StringUtils.isEmpty(from) ? defauleValue : new Byte(from);
        }
    }
    private static class StringToInt extends StringAdapter<Integer> {
        @Override
        public Integer cast(String from, Integer defauleValue) {
            return StringUtils.isEmpty(from) ? defauleValue : new Integer(from);
        }
    }
    private static class StringToShort extends StringAdapter<Short> {
        @Override
        public Short cast(String from, Short defauleValue) {
            return StringUtils.isEmpty(from) ? defauleValue : new Short(from);
        }
    }
    private static class StringToLong extends StringAdapter<Long> {
        @Override
        public Long cast(String from, Long defauleValue) {
            return StringUtils.isEmpty(from) ? defauleValue : new Long(from);
        }
    }
    private static class StringToFloat extends StringAdapter<Float> {
        @Override
        public Float cast(String from, Float defauleValue) {
            return StringUtils.isEmpty(from) ? defauleValue : new Float(from);
        }
    }
    private static class StringToDouble extends StringAdapter<Double> {
        @Override
        public Double cast(String from, Double defauleValue) {
            return StringUtils.isEmpty(from) ? defauleValue : new Double(from);
        }
    }
    private static class StringToBoolean extends StringAdapter<Boolean> {
        @Override
        public Boolean cast(String from, Boolean defaultValue) {
            return StringUtils.isEmpty(from) ? defaultValue : Boolean.valueOf(from);
        }
    }
    private static class StringToDate extends StringAdapter<Date> {
        private static final String[] FORMATS = new String[] {
            "yyy-MM-dd HH:mm:ss",
            "yyy-MM-dd HH:mm",
            "yyy-MM-dd",
        };
        @Override
        public Date cast(String from, Date defaultValue) {
            if(StringUtils.isEmpty(from)) return defaultValue;
            for(String format : FORMATS)
                if(tryCast(from, format) != null)
                    return tryCast(from, format);
            throw new RuntimeException("未识别格式 " + from + " 请添加实现");
        }
        private Date tryCast(String from, String format) {
            try {
                return new SimpleDateFormat(format).parse(from);
            } catch (Exception e) {
                return null;
            }
        }
    }
    private static abstract class StringAdapter<T> implements Castor<String, T> {

        @Override
        public String reverse(T from, String defaultValue) {
            return from == null ? defaultValue : from.toString();
        }
        
    }
    
    public static interface Castor<F, T> {
        public T cast(F from, T defaultValue);
        public F reverse(T from, F defaultValue);
    }
    
}
