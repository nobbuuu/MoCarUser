package com.dream.moka.Base;

import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Listener.PermissionListener;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Utils.Dialog.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public abstract class BaseFragmentV4 extends Fragment {
    protected View rootView;
    public Bundle savedInstanceState;

    private PermissionListener mListener;
    private static final int PERMISSION_REQUESTCODE = 100;
    public Dialog mLoadingDialog;

    public  Dialog getLoadingDialog(){
        if (mLoadingDialog==null){
            mLoadingDialog = DialogUtils.initLoadingDialog(getActivity());
        }
        return mLoadingDialog;
    }

    public void showLoadingDialog(){
        if (!mLoadingDialog.isShowing()){
            mLoadingDialog.show();
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoadingDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
       return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        ButterKnife.bind(this,view);
        BaseComponent build = DaggerBaseComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
//                .mainActivityModule(new MainActivityModule(this))
                .build();
        initDaggerView(build);
        initView();
        initEvents();
        initDta();
    }

    @Override
    public void onResume() {
        super.onResume();
        initResume();
    }
    public abstract void initDaggerView(BaseComponent build);

    public abstract void initView();
    public abstract int getLayoutId();
    public abstract void initResume();

    public abstract void initEvents();

    public abstract void initDta();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void requestRunPermisssion(String[] permissions, PermissionListener listener){
        mListener = listener;
        List<String> permissionLists = new ArrayList<>();
        for(String permission : permissions){
            if(ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED){
                permissionLists.add(permission);
            }
        }

        if(!permissionLists.isEmpty()){
            ActivityCompat.requestPermissions(getActivity(), permissionLists.toArray(new String[permissionLists.size()]), PERMISSION_REQUESTCODE);
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
