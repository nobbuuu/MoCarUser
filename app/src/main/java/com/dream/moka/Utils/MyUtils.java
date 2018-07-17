package com.dream.moka.Utils;

import android.net.ParseException;
import android.text.InputType;
import android.text.format.Time;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/12/4 0004.
 * 作者 sst
 */

public class MyUtils {
    /**
     * 将参数名与文件装换成parts对象用于retrofit文件上传。
     *
     * @param element
     * @param files
     * @return
     */
    public static MultipartBody.Part[] getRequestBodyParts(String element, List<File> files) {
        if (files != null) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//ParamKey.TOKEN 自定义参数key常量类，即参数名
            MultipartBody.Part[] parts = new MultipartBody.Part[files.size()];
            for (int i = 0; i < files.size(); i++) {
                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), files.get(i));
                builder.addFormDataPart(element, files.get(i).getName(), imageBody);//imgfile 后台接收图片流的参数名
                parts[i] = builder.build().part(i);
            }
            return parts;
        } else {
            return null;
        }
    }

    /**
     * 将参数名与文件装换成parts对象用于retrofit文件上传。
     *
     * @param element
     * @param files
     * @return
     */
    public static MultipartBody.Part getRequestBodyParts(String element, File files) {
        if (files != null) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//ParamKey.TOKEN 自定义参数key常量类，即参数名
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), files);
            builder.addFormDataPart(element, files.getName(), imageBody);//imgfile 后台接收图片流的参数名
            MultipartBody.Part part = builder.build().part(0);
            return part;
        } else {
            return null;
        }
    }

    /**
     * 检测字符串的null与empty状态
     *
     * @param s
     * @return
     */
    public static boolean CheckStringEmpty(String s) {
        if (s == null) {
            return false;
        }
        if ("".equals(s)) {
            return false;
        }
        return true;
    }

    public static RequestBody getBody(String str){
        return RequestBody.create(null,str);
    }


    /**
     * 判断当前系统时间是否在指定时间的范围内
     *
     * @param beginHour
     * 开始小时，例如22
     * @param beginMin
     * 开始小时的分钟数，例如30
     * @param endHour
     * 结束小时，例如 8
     * @param endMin
     * 结束小时的分钟数，例如0
     * @return true表示在范围内，否则false
     */
    public static boolean isCurrentInTimeScope(int beginHour, int beginMin, int endHour, int endMin) {
        boolean result = false;
        final long aDayInMillis = 1000 * 60 * 60 * 24;
        final long currentTimeMillis = System.currentTimeMillis();

        Time now = new Time();
        now.set(currentTimeMillis);

        Time startTime = new Time();
        startTime.set(currentTimeMillis);
        startTime.hour = beginHour;
        startTime.minute = beginMin;

        Time endTime = new Time();
        endTime.set(currentTimeMillis);
        endTime.hour = endHour;
        endTime.minute = endMin;

        if (!startTime.before(endTime)) {
// 跨天的特殊情况（比如22:00-8:00）
            startTime.set(startTime.toMillis(true) - aDayInMillis);
            result = !now.before(startTime) && !now.after(endTime); // startTime <= now <= endTime
            Time startTimeInThisDay = new Time();
            startTimeInThisDay.set(startTime.toMillis(true) + aDayInMillis);
            if (!now.before(startTimeInThisDay)) {
                result = true;
            }
        } else {
// 普通情况(比如 8:00 - 14:00)
            result = !now.before(startTime) && !now.after(endTime); // startTime <= now <= endTime
        }
        return result;
    }


    /**
     * 检测时间在区域内
     * @param time
     * @param beginHour
     * @param beginMin
     * @param endHour
     * @param endMin
     * @return
     */
    public static boolean isCurrentInTimeScope(long time,int beginHour, int beginMin, int endHour, int endMin) {
        boolean result = false;
        final long aDayInMillis = 1000 * 60 * 60 * 24;
        final long currentTimeMillis = System.currentTimeMillis();
        Time now = new Time();
        now.set(time);

        Time startTime = new Time();
        startTime.set(currentTimeMillis);
        startTime.hour = beginHour;
        startTime.minute = beginMin;

        Time endTime = new Time();
        endTime.set(currentTimeMillis);
        endTime.hour = endHour;
        endTime.minute = endMin;

        if (!startTime.before(endTime)) {
// 跨天的特殊情况（比如22:00-8:00）
            startTime.set(startTime.toMillis(true) - aDayInMillis);
            result = !now.before(startTime) && !now.after(endTime); // startTime <= now <= endTime
            Time startTimeInThisDay = new Time();
            startTimeInThisDay.set(startTime.toMillis(true) + aDayInMillis);
            if (!now.before(startTimeInThisDay)) {
                result = true;
            }
        } else {
// 普通情况(比如 8:00 - 14:00)
            result = !now.before(startTime) && !now.after(endTime); // startTime <= now <= endTime
        }
        return result;
    }

//    /**
//     * 判断给定字符串时间是否为今日
//     * @param sdate
//     * @return boolean
//     */
//    public static boolean isToday(String sdate){
//        boolean b = false;
//        Date time = toDate(sdate);
//        Date today = new Date();
//        if(time != null){
//            String nowDate = dateFormater2.get().format(today);
//            String timeDate = dateFormater2.get().format(time);
//            if(nowDate.equals(timeDate)){
//                b = true;
//            }
//        }
//        return b;
//    }

    /**
     * 判断是否为今天(效率比较高)
     * data  传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsToday(Date date) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将字符串转位日期类型
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static void initEye(final EditText mPassword, CheckBox mVisible) {
        //默认隐藏密码
        mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mVisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // 显示密码
                    mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                } else {
                    // 隐藏密码
                    mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                mPassword.setSelection(mPassword.getText().length());
            }
        });
    }

}
