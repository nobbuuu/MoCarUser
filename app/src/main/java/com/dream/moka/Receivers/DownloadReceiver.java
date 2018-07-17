package com.dream.moka.Receivers;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

import com.dream.moka.Other.MyApplication;
import com.dream.moka.Utils.FileUtil;
import com.dream.moka.Utils.HttpUtils;

import java.io.File;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class DownloadReceiver extends BroadcastReceiver {
    private long mTaskId;
    private DownloadManager downloadManager;
    private String versionName;
    public DownloadReceiver(long mskId,DownloadManager downloadManager,String versionName){
        this.mTaskId = mskId;
        this.downloadManager = downloadManager;
        this.versionName=versionName;
    }
    public DownloadReceiver(){

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        checkDownloadStatus();
    }
    //检查下载状态
    private void checkDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(mTaskId);//筛选下载任务，传入任务ID，可变参数
        //创建下载任务
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_PAUSED:
                    Log.e(getClass().getName(),">>>下载暂停");
                case DownloadManager.STATUS_PENDING:
                    Log.e(getClass().getName(),">>>下载延迟");

                case DownloadManager.STATUS_RUNNING:
                    Log.e(getClass().getName(),">>>正在下载");

                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    Log.e(getClass().getName(),">>>下载完成");
                    //下载完成安装APK
                    String downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + versionName;
                    FileUtil.installApk(MyApplication.getInstance(),new File(downloadPath));
                    break;
                case DownloadManager.STATUS_FAILED:
                    Log.e(getClass().getName(),">>>下载失败");
                    break;
            }
        }
    }
}
