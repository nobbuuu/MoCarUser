package com.dream.moka.Utils;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
public class StringUtil {
    public static boolean notEmpty(String tempStr) {
        if (tempStr.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回false表示null或者空
     *
     * @param s
     * @return
     */
    public static boolean NoNullOrEmpty(String s) {
        if (s != null && !s.isEmpty()) {
            return true;
        }
        return false;
    }

    public static String checkNull(String tempStr){
        return tempStr==null?"":tempStr;
    }

    public static String checkNull(String tempStr,String defaultStr){
        return tempStr==null?defaultStr:tempStr;
    }

}
