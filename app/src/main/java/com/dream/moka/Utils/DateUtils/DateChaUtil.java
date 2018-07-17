package com.dream.moka.Utils.DateUtils;

/**
 * Created by Administrator on 2017/7/6 0006.
 */
public class DateChaUtil {
    public static String getDateCha(long fdCreateDate) {
        String chaStr = "";
        long currentTime = System.currentTimeMillis();

        long chaLong = currentTime - fdCreateDate;

        //秒差
        long secondCha = chaLong / 1000;
        //分钟差
        long minuteCha = secondCha / 60;
        //小时差
        long hoursCha = minuteCha / 60;
        //天数差
        long dateDays = hoursCha / 24;
        //月数差
        long monthCha = dateDays / 30;
        //年份差
        long yearCha = monthCha / 12;

        if (yearCha < 1) {
            if (monthCha < 1) {
                if (dateDays < 1) {
                    if (hoursCha < 1) {
                        if (minuteCha < 1) {
                            chaStr = secondCha + "秒前";
                        } else {
                            chaStr = minuteCha + "分钟前";
                        }
                    } else {
                        chaStr = hoursCha + "小时前";
                    }
                } else {
                    chaStr = dateDays + "天前";
                }
            } else {
                chaStr = monthCha + "个月前";
            }
        } else {
            chaStr = yearCha + "年前";
        }
        return chaStr;
    }
}
