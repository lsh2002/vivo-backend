package com.lsh.vivo.util;

import com.alibaba.fastjson.JSON;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lsh
 * @since 2023-09-09 15:43
 */
public class BeanUtil {

    /**
     * bean 转 String
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else if (clazz == LocalDateTime.class){
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }


    /**
     * string转bean
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else if (clazz == LocalDateTime.class){
            return (T) LocalDateTime.parse(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    /**
     * 将JavaBean对象封装到Map集合当中
     *
     * @param bean
     * @return
     */
    public static Map<Object, Object> bean2map(Object bean) throws Exception {
        //创建Map集合对象
        Map<Object, Object> map = new HashMap<>();
        //获取对象字节码信息,不要Object的属性
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
        //获取bean对象中的所有属性
        PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : list) {
            String key = pd.getName();//获取属性名
            Object value = pd.getReadMethod().invoke(bean);//调用getter()方法,获取内容
            map.put(key, value);//增加到map集合当中
        }
        return map;
    }


    /**
     * 将Map集合中的数据封装到JavaBean对象中
     *
     * @param map       集合
     * @param classType 封装javabean对象
     */
    public static <T> T map2bean(Map<Object, Object> map, Class<T> classType) throws Exception {
        //采用反射动态创建对象
        T obj = classType.newInstance();
        //获取对象字节码信息,不要Object的属性
        BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
        //获取bean对象中的所有属性
        PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : list) {
            String key = pd.getName();    //获取属性名
            Object value = map.get(key);  //获取属性值
            pd.getWriteMethod().invoke(obj, value); //调用属性setter()方法,设置到javabean对象当中
        }
        return obj;
    }
}