package org.smart4j.framework.util;


import org.apache.commons.lang3.StringUtils;

public  final  class StringUtil {
    /**
     * 判断字符是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str!=null){
            str=str.trim();
        }
        return StringUtils.isEmpty(str);
    }
    public static boolean isNotEmpty(String str){
        return  !isEmpty(str);
    }
}

