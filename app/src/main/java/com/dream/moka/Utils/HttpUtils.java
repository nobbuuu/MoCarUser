package com.dream.moka.Utils;

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.webkit.MimeTypeMap;


import com.dream.moka.Bean.EventBusBean.DownloadBean;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Receivers.DownloadReceiver;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/6/15 0015.
 */
public class HttpUtils {

    public static MultipartBody.Part getRequestBodyPart(String element, File mFile) {
        if (mFile != null) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//ParamKey.TOKEN 自定义参数key常量类，即参数名
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), mFile);
            builder.addFormDataPart(element, mFile.getName(), imageBody);//element 后台接收图片流的参数名
            MultipartBody.Part part = builder.build().part(0);
            return part;
        } else {
            return null;
        }
    }

    public static RequestBody getBody(String str) {
        return RequestBody.create(null, str);
    }

    /**
     * @param path 图片路径
     * @return
     * @将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @author QQ986945193
     * @Date 2015-01-26
     */
    public static String imageToBase64(String path) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {

            InputStream in = new FileInputStream(path);

            data = new byte[in.available()];

            in.read(data);

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        String encodedString = Base64.encodeToString(data, Base64.DEFAULT);
        return encodedString;

    }

    public static File downloadFile(String httpurl, File dir, String rename) {

        File target = new File(dir, rename);
        InputStream inputStream = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL(httpurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
//            conn.setReadTimeout(5000);
//            conn.setConnectTimeout(5000);
            conn.connect();
           /* int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
            }*/
            long contentLength = conn.getContentLength();
            Log.e("tag", "target.length=" + target.length());
            Log.e("tag", "contentLength=" + contentLength);
            byte[] buff = new byte[1024 * 1024 * 100];
            inputStream = conn.getInputStream();
            fos = new FileOutputStream(target);
            int read = -1;
            long download = 0;
            NotificationManager manager = NotifyUtil.getNotificationManager(MyApplication.getInstance());
            while ((read = inputStream.read(buff)) != -1) {
                fos.write(buff, 0, read);
                fos.flush();
                download += read;
                //计算下载百分比
                final int progress = (int) (100 * download / contentLength);
                Log.e("tag", "progress=" + progress);
                Log.e("tag", "download=" + download);
                       /* runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });*/

                NotifyUtil.sendAppVersionNotify(MyApplication.getInstance(), manager, progress);
            }
            Log.e("tag", "文件下载成功！");
            FileUtil.installApk(MyApplication.getInstance(), target);
            return target;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    // fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.e("TAG", "文件下载失败！");
        DownloadBean bean = new DownloadBean();
        bean.setEventStr("fail");
        EventBus.getDefault().post(bean);
        return null;

    }

    //使用系统下载器下载
    public static void downloadAPK(String versionUrl, String versionName) {
        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(versionUrl));
        request.setAllowedOverRoaming(false);//漫游网络是否可以下载

        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(versionUrl));
        request.setMimeType(mimeString);


        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setVisibleInDownloadsUi(true);
        request.setTitle("正在下载");

        //sdcard的目录下的download文件夹，必须设置
        request.setDestinationInExternalPublicDir("/download/", versionName);
        //request.setDestinationInExternalFilesDir(),也可以自己制定下载路径

        //将下载请求加入下载队列
        DownloadManager downloadManager = (DownloadManager) MyApplication.getInstance().getSystemService(Context.DOWNLOAD_SERVICE);
        //加入下载队列后会给该任务返回一个long型的id，
        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法
        long mskId = downloadManager.enqueue(request);
        //注册广播接收者，监听下载状态
        MyApplication.getInstance().registerReceiver(new DownloadReceiver(mskId,downloadManager,versionName), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {//如果仅仅是用来判断网络连接
                //则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
