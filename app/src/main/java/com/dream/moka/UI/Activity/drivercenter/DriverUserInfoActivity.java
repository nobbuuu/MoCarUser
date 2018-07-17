package com.dream.moka.UI.Activity.drivercenter;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.DriverUserInfContract;
import com.dream.moka.Listener.PictureListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.DriverUserInfoPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.CustomHelper;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.GlidUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class DriverUserInfoActivity extends BaseCommonActivity implements DriverUserInfContract, TakePhoto.TakeResultListener, InvokeListener {

    @Bind(R.id.head_icon)
    ImageView mHeadIcon;
    @Bind(R.id.head_iconrelay)
    RelativeLayout mHeadIconrelay;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.phone)
    TextView mPhone;
    @Bind(R.id.city)
    TextView mCity;
    @Bind(R.id.type)
    TextView mType;
    @Bind(R.id.qq)
    TextView mQq;
    @Bind(R.id.car_type)
    TextView mCarType;
    @Bind(R.id.carCardNo)
    TextView mCarCardNo;
    @Bind(R.id.getCardData)
    TextView mGetCardData;
    @Inject
    DriverUserInfoPresenter mDriverUserInfoPresenter;
    private CustomHelper mCustomHelper;
    private String mDriverId;
    private Dialog mLoadingDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_driver_info;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {
        DaggerBaseComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
//                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
        mDriverUserInfoPresenter.attachView(this);
        String driverPhoto = CommonAction.getDriverPhoto();
        String driverName = CommonAction.getDriverName();
        String driverPhone = CommonAction.getDriverPhone();
        String driverCity = CommonAction.getDriverCity();
        String driverQQ = CommonAction.getDriverQQ();
        String driverDrivingType = CommonAction.getDriverDrivingType();
        String driverLicenseCode = CommonAction.getDriverLicenseCode();
        String driverlicenseDate = CommonAction.getDriverlicenseDate();

        GlidUtils.LoadCircleImg(mContext, Const.BannerUrl+driverPhoto, mHeadIcon,R.mipmap.icon_avatar01);
        mName.setText(driverName);
        mPhone.setText(driverPhone);
        mCity.setText(driverCity);
        mQq.setText(driverQQ);
//        <option value="1">C1</option>
//										<option value="2">B2</option>
//										<option value="3">B1</option>
//										<option value="4">A3</option>
//										<option value="5">A2</option>
//										<option value="6">A1</option>
        String s="";
        switch (driverDrivingType){
            case "1":
                s="C1";
                break;
            case "2":
                s="B2";
                break;
            case "3":
                s="B1";
                break;
            case "4":
                s="A3";
                break;
            case "5":
                s="A2";
                break;
            case "6":
                s="A1";
                break;
                default:
                    break;
        }
        mCarType.setText(s);
        mCarCardNo.setText(driverLicenseCode);
        mGetCardData.setText(driverlicenseDate.length()>=10?driverlicenseDate.substring(0,10):"");

    }

    @Override
    public String initTitleText() {
        return "司机信息";
    }

    @Override
    public String initRightTv() {
        return null;
    }

    @Override
    public boolean isRighttv() {
        return false;
    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {
        mDriverId = CommonAction.getDriverId();
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay, R.id.head_iconrelay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.head_iconrelay:
                DialogUtils.initPictureMethodDialog(mActivity, new PictureListener() {
                    @Override
                    public void onCamareClick() {
                        mCustomHelper = CustomHelper.INSTANCE(getTakePhoto(), "600", "600", false, "600", "600");
                        mCustomHelper.onClick(true);
                    }

                    @Override
                    public void onPictureClick() {
                        mCustomHelper = CustomHelper.INSTANCE(getTakePhoto(), "600", "600", false, "600", "600");
                        mCustomHelper.onClick(false);
                    }
                }).show();
                break;
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {
        if (mLoadingDialog!=null){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void onSuccess(String path) {
        GlidUtils.LoadFromFileLocation(mContext,path,mHeadIcon,R.mipmap.icon_avatar01);
    }

    @Override
    public void takeSuccess(TResult result) {
        ArrayList<TImage> images = result.getImages();
        String compressPath = images.get(images.size() - 1).getCompressPath();
        mLoadingDialog.show();
        mDriverUserInfoPresenter.uploadPic(mDriverId,compressPath);
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    private InvokeParam invokeParam;

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    private TakePhoto takePhoto;
    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
