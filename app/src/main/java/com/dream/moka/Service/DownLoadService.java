package com.dream.moka.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.dream.moka.Utils.HttpUtils;
import com.dream.moka.Utils.StringUtil;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class DownLoadService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public DownLoadService() {
        super("downLoadService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String apkUrl = intent.getStringExtra("apkUrl");
        String versionName = intent.getStringExtra("versionName");
        if (StringUtil.NoNullOrEmpty(apkUrl)){
            HttpUtils.downloadAPK(apkUrl,versionName);
        }
    }
}
