package com.dream.moka.UI.Activity.login_register;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.dream.moka.Bean.UserInfo;
import com.dream.moka.IM.InitBusiness;
import com.dream.moka.IM.TLSService;
import com.dream.moka.IM.TlsBusiness;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.MainActivity;
import com.dream.moka.UI.Activity.drivercenter.DriverCenterActivity;
import com.dream.moka.Utils.LogUtils;
import com.dream.moka.Utils.SpUtils;
import com.dream.moka.Utils.ToastUtils;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplahActivity extends AppCompatActivity {

    @Bind(R.id.splash)
    ImageView mSplash;
    private boolean openMain = true;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Intent intent = new Intent(SplahActivity.this, MainActivity.class);
                    startActivity(intent);
                    mSplash.clearAnimation();
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splah);
        ButterKnife.bind(this);
        XGPushClickedResult message = XGPushManager.onActivityStarted(this);
        // 判断是否从推送通知栏打开的
        if (message != null) {
            String customContent = message.getCustomContent();
            try {
                JSONObject jsonObject = new JSONObject(customContent);
                String type = jsonObject.getString("type");
                switch (type) {
                    case "4":
                        if (!isTaskRoot()) {
                            openMain = false;
                            Intent intentCenter = new Intent(SplahActivity.this, DriverCenterActivity.class);
                            intentCenter.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            intentCenter.putExtra("tag","backMain");
                            startActivity(intentCenter);
                            finish();
                        }
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (openMain) {
            mSplash.startAnimation(AnimationUtils.loadAnimation(SplahActivity.this, R.anim.alpha));
            mHandler.sendEmptyMessageDelayed(0, 800);
            initXinGe();
        }
        initIM();
        onImListener();

    }

    private void initXinGe() {
        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
//token在设备卸载重装的时候有可能会变
                LogUtils.e("TPush", "注册成功，设备token为：" + data);
                CommonAction.setDrviceToken((String) data);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                LogUtils.e("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
    }
    private void initIM(){
        int loglvl = (int) SpUtils.getParam("loglvl",TIMLogLevel.DEBUG.ordinal());
        //初始化IMSDK
        InitBusiness.start(getApplicationContext(),loglvl);

        //初始化TLS
        TlsBusiness.init(getApplicationContext());

        String id =  TLSService.getInstance().getLastUserIdentifier();
        UserInfo.getInstance().setId(id);
        UserInfo.getInstance().setUserSig(TLSService.getInstance().getUserSig(id));

    }


    public void onImListener(){
        //登录之前要初始化群和好友关系链缓存
        TIMUserConfig userConfig = new TIMUserConfig();
        userConfig.setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
                Log.e(Const.IM, "receive force offline message");
            }

            @Override
            public void onUserSigExpired() {
                //票据过期，需要重新登录
                ToastUtils.Toast_short("票据过期，需要重新登录");
            }
        })
                .setConnectionListener(new TIMConnListener() {
                    @Override
                    public void onConnected() {
                        Log.e(Const.IM, "onConnected");
                    }

                    @Override
                    public void onDisconnected(int code, String desc) {
                        Log.e(Const.IM, "onDisconnected");
                    }

                    @Override
                    public void onWifiNeedAuth(String name) {
                        Log.e(Const.IM, "onWifiNeedAuth");
                    }
                });

    }
    private boolean isAppIsInBackground(Context context) {

        boolean isInBackground = true;

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                //前台程序
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

}
