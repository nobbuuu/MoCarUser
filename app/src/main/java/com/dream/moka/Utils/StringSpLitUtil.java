package com.dream.moka.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/12/26 0026.
 */
public class StringSpLitUtil {


    /**
     * 将字符串以“，”拆分组成 List《String》
     *
     * @param str
     * @return
     */
    public static List<String> getAddString(String str) {
        if (isStirngNull(str)) {
            return new ArrayList<String>();
        }
        return Arrays.asList(str.split(","));
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isStirngNull(String str) {
        if (str != null && !str.trim().equals("")) {
            return false;
        }
        return true;
    }


    /**
     * html去除
     *
     * @param str
     * @return
     */
    public static String replaceAll(String str) {
        if (isStirngNull(str)) {
            return "";
        } else {
            str = str.replace("\\r", "");
            str = str.replace("\\n", "<br/>");
            str = str.replace("\\t", " ");
        }
        return str;
    }

}
