package com.dream.moka.Base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Listener.PermissionListener;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/7/007.
 */
public abstract class BaseCommonActivity extends AppCompatActivity {

    public Bundle savedInstanceState;
    public Activity mActivity;
    public Context mContext;
    public Bundle msaveInstance;
    public LinearLayout root_lay;
    private PermissionListener mListener;
    private static final int PERMISSION_REQUESTCODE = 100;
    @Bind(R.id.title_tv)
    public TextView title_tv;
    @Bind(R.id.titlebar_relay)
    public RelativeLayout titlebar_relay;
    @Bind(R.id.right_tv)
    public TextView right_tv;
    private FrameLayout basecontent_view;
    public Dialog mLoadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //v7包下去除标题栏代码：
        getSupportActionBar().hide();
        this.savedInstanceState = savedInstanceState;
        this.mActivity = this;
        this.mContext = this;
        this.msaveInstance = savedInstanceState;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //父布局
        root_lay = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_common, null);
        //父布局中的帧布局
        basecontent_view = (FrameLayout) root_lay.findViewById(R.id.basecontent_view);
        //把子布局添加到帧布局里面
        basecontent_view.addView(LayoutInflater.from(this).inflate(getLayoutId(), null));
        //设置view
        setContentView(root_lay);

        mLoadingDialog = DialogUtils.initLoadingDialog(this);

        //这里统一注册，子类就无需再注册了
        ButterKnife.bind(this);
//        XuniKeyWord.setShiPei(this, root_lay);//沉设置沉浸式（根据需求来确定）

        //动态设置标题
        title_tv.setText(initTitleText());

        //控制右上角是否要显示
        if (isRighttv()) {
            right_tv.setVisibility(View.VISIBLE);
            right_tv.setText(initRightTv());
        } else {
            right_tv.setVisibility(View.GONE);
        }
        //利用dagger2初始化activity（也就是presenter里面的view）
        BaseComponent build = DaggerBaseComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
//                .mainActivityModule(new MainActivityModule(this))
                .build();
        initDaggerView(build);


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

    public abstract void initDaggerView(BaseComponent baseComponent);

    public abstract void initView();

    public abstract String initTitleText();

    public abstract String initRightTv();

    public abstract boolean isRighttv();

    public abstract void loadResum();

    public abstract void initDatas();

    public abstract void eventListener();


    public void requestRunPermisssion(String[] permissions, PermissionListener listener) {
        mListener = listener;
        List<String> permissionLists = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionLists.add(permission);
            }
        }

        if (!permissionLists.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionLists.toArray(new String[permissionLists.size()]), PERMISSION_REQUESTCODE);
        } else {
            //表示全都授权了
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUESTCODE:
                if (grantResults.length > 0) {
                    //存放没授权的权限
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        //说明都授权了
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            case Const.CAMERA:
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, Const.CAMERA);
                break;
            case Const.PICTURE:
                Intent picture = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, Const.PICTURE);
                break;
            default:
                break;
        }
    }
    @OnClick({R.id.back_relay})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_relay:
                finish();
                break;
        }
    }
}
