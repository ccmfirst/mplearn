package com.first.demo.tools;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BeanUtils {

    /**
     * JavaBean To Map
     * @param bean
     * @param humpToUnderline 驼峰转换为下划线
     * @return
     */
    public static Map<String, Object> beanToMap(Object bean, boolean humpToUnderline){
        Objects.requireNonNull(bean);
        Map<String, Object> retVal = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                Method method = propertyDescriptor.getReadMethod();
                if(Modifier.isNative(method.getModifiers())) {
                    // 忽略 getClass();
                    continue;
                }
                String name = propertyDescriptor.getName();

                Object val = method.invoke(bean);

                retVal.put(humpToUnderline ? humpToUnderline(name) : name, val);
            }
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return retVal;
    }

    // 驼峰转换为下划线
    private static String humpToUnderline(String value) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = value.toCharArray();
        for (char charactor : chars) {
            if (Character.isUpperCase(charactor)) {
                stringBuilder.append("_");
                charactor = Character.toLowerCase(charactor);
            }
            stringBuilder.append(charactor);
        }
        return stringBuilder.toString();
    }
}
