package org.smart4j.framework.helper;


import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 */
public final class IocHelper {
    static{
        //获取所有的Bean类和Bean实例之间的映射关系(Bean Map);
        Map<Class<?>,Object> beanMap=BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty( beanMap)){
            //遍历Bean Map
            for(Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()){
                //从BeanMap 中获取Bean 类与Bean实例
                Class<?> beanClass=beanEntry.getKey();
                Object beanInstance=beanEntry.getValue();
                //获取Bean类定义的所有成员变量(Bean Field);
                Field[] beanFields= beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(beanFields)){
                    //遍历Bean Field
                    for(Field beanField:beanFields ){
                        //判断当前Bean Field是否带有Inject 注解
                        if(beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass=beanField.getType();
                            Object beanFieldInstance=beanMap.get(beanFieldClass);
                            if(beanFieldInstance!=null){
                                //通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }

                    }
                }
            }
        }
    }
}
