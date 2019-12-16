package com.example.doorsensor.util;

import java.lang.reflect.Field;

/**
 * pojo对象转换工具类
 * 需要参与转换的两个类的属性字段名称必须一样
 * 现成工具类：org.springframework.cglib.beans.BeanCopier.beancopier
 */
public class BeanUtils {

    /**
     * 单个对象间的浅拷贝的转换
     */
    public static void shallowCopyProperties(Object dest, Object src) {
        Class destClass = dest.getClass();
        Class srcClass = src.getClass();
        Field[] fields = destClass.getDeclaredFields();
        try {
            for (Field field : fields) {
                Field srcField = srcClass.getDeclaredField(field.getName());
                field.setAccessible(true);
                srcField.setAccessible(true);
                field.set(dest, srcField.get(src));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
