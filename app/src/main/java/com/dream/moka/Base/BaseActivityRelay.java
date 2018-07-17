package com.dream.moka.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RelativeLayout;

import com.dream.moka.Listener.PermissionListener;
import com.dream.moka.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/7/007.
 */
public abstract class BaseActivityRelay extends AppCompatActivity {

    public Bundle savedInstanceState;
    public Activity mActivity;
    public Context mContext;
    @Bind(R.id.root_lay)
    public RelativeLayout root_lay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(getLayoutId());
        getSupportActionBar().hide();
        ButterKnife.bind(this);
//        XuniKeyWord.setShiPei(this,root_lay);
        this.savedInstanceState = savedInstanceState;
        this.mActivity = this;
        this.mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        initDatas();
        eventListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadResum();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public abstract int getLayoutId();

    public abstract void initView();
    public abstract void loadResum();
    public abstract void initDatas();
    public abstract void eventListener();
    /**
     * Intent的简单跳转
     * @param cls
     * @param bundle
     */
    protected void GoToActivity(Class<?>cls,Bundle bundle){
        Intent intent=new Intent(this,cls);
        if (null!=bundle){
            intent.putExtras(bundle);
        }
        startActivity(new Intent(this,cls));
//        overridePendingTransition(R.anim.dync_in_from_right,R.anim.dync_out_to_left);
    }

    /**
     * Intent简单的跳转关闭
     * @param cls
     * @param bundle
     */
    protected void GoToActivityFinish(Class<?>cls,Bundle bundle){
        Intent intent=new Intent(this,cls);
        if (null!=bundle){
            intent.putExtras(bundle);
        }
        startActivity(new Intent(this,cls));
//        overridePendingTransition(R.anim.dync_in_from_right,R.anim.dync_out_to_left);
        this.finish();
    }

    private PermissionListener mListener;
    private static final int PERMISSION_REQUESTCODE = 100;

    public void requestRunPermisssion(String[] permissions, PermissionListener listener){
        mListener = listener;
        List<String> permissionLists = new ArrayList<>();
        for(String permission : permissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                permissionLists.add(permission);
            }
        }

        if(!permissionLists.isEmpty()){
            ActivityCompat.requestPermissions(this, permissionLists.toArray(new String[permissionLists.size()]), PERMISSION_REQUESTCODE);
        }else{
            //表示全都授权了
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUESTCODE:
                if(grantResults.length > 0){
                    //存放没授权的权限
                    List<String> deniedPermissions = new ArrayList<>();
                    for(int i = 0; i < grantResults.length; i++){
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if(grantResult != PackageManager.PERMISSION_GRANTED){
                            deniedPermissions.add(permission);
                        }
                    }
                    if(deniedPermissions.isEmpty()){
                        //说明都授权了
                        mListener.onGranted();
                    }else{
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }
}
