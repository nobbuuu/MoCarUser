package com.dream.moka.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.dream.moka.Other.Const;
import com.dream.moka.UI.Activity.other.SpaceImageDetailActivity;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class IntentUtils {
    public static void toActivity(Class<?> tClass, Activity activity) {
        activity.startActivity(new Intent(activity, tClass));
    }

    public static void toActivity_result(Class<?> tClass, Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, tClass), requestCode);
    }

    public static void toActivityWithTag(Class<?> tClass, Context activity, String tag) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(Const.intentTag, tag);
        activity.startActivity(intent);
    }

    public static void toActivityWithUrl(Class<?> tClass, Context activity, String url, String title) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(Const.intentTag, title);
        intent.putExtra(Const.webUrl, url);
        activity.startActivity(intent);
    }

    public static void toActivityWithTag(Class<?> tClass, Activity activity, String tag, int requestCode) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(Const.intentTag, tag);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void toPicture(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)//权限未授予
        {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Const.PICTURE
            );
        } else//已授予权限
        {
            Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activity.startActivityForResult(picture, Const.PICTURE);
        }
    }

    public static void toCamare(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)//权限未授予
        {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Const.CAMERA
            );
        } else//已授予权限
        {
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            activity.startActivityForResult(camera, Const.CAMERA);
        }
    }

    /**
     * 调用拨号功能
     * @param phone 电话号码
     */
    public static void call(String phone, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CALL_PHONE}, Const.CAll
            );
        }else {
            activity.startActivity(intent);
        }
    }

    /**
     * 调用拨号功能
     * @param phone 电话号码
     */
    public static void call_Dall(String phone, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    /*
    * 查看大图
    * */
    public static void toImgDetail(Activity mActivity,ImageView imgView, String picUrl) {
        Intent intent = new Intent(mActivity, SpaceImageDetailActivity.class);
        intent.putExtra("picUrl", picUrl);
        int[] location = new int[2];
        imgView.getLocationOnScreen(location);
        intent.putExtra("locationX", location[0]);
        intent.putExtra("locationY", location[1]);

        intent.putExtra("width", imgView.getWidth());
        intent.putExtra("height", imgView.getHeight());
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(0, 0);
    }
}
