package com.dream.moka.Utils;


import com.dream.moka.Other.Const;

/**
 * Created by Administrator on 2018/1/17 0017.
 */
public class PicUrlUtils {
    public static String getRealUrl(String picUrl){
        if (!StringUtil.NoNullOrEmpty(picUrl)){
            return "";
        }
        if (picUrl.contains("http")){
            return picUrl;
        }else {
            return Const.BannerUrl+picUrl;
        }
    }
}
