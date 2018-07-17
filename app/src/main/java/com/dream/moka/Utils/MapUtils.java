package com.dream.moka.Utils;



import com.dream.moka.Other.Const;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/24 0024.
 */
public class MapUtils{

    public static Map<String,String> getMap(){
        Map<String,String> map = new HashMap<>();
        map.put(Const.pageSize,"10");
        return map;
    }
    public static Map<String,String> getBigMap(){
        Map<String,String> map = new HashMap<>();
        map.put(Const.pageSize,"300");
        return map;
    }
    public static Map<String,String> getSingleMap(){
        Map<String,String> map = new HashMap<>();
        return map;
    }
}
