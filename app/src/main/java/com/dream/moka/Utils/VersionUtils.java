package com.dream.moka.Utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.dream.moka.Other.MyApplication;

/**
 * Created by Administrator on 2018/6/7.
 */

public class VersionUtils {
    public static boolean isNewVersion(String serviceVersion){
        PackageInfo packageInfo = null;
        try {
            packageInfo = MyApplication.getInstance().getPackageManager().getPackageInfo(MyApplication.getInstance().getPackageName(), 0);
            final String localVersionName = packageInfo.versionName;
            Log.e("tag", "localVersionName=" + localVersionName);
            Log.e("tag","serviceVersion="+serviceVersion);
            String[] currentNames = localVersionName.split("\\.");
            String[] webNames = serviceVersion.split("\\.");
            if (currentNames.length == webNames.length) {
                for (int j = 0; j < webNames.length; j++) {
                    if (Integer.parseInt(webNames[j]) < Integer.parseInt(currentNames[j])) {
                        return false;
                    }else if (Integer.parseInt(webNames[j]) > Integer.parseInt(currentNames[j])){//有更高版本的apk
                        return true;
                    }
                }
            } else {
                ToastUtils.Toast_long("请上传正确的最新版本号（eg：1.2.3）");
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
       return false;
    }
}
