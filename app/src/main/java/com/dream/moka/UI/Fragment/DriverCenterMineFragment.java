package com.dream.moka.UI.Fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.DriverMineContract;
import com.dream.moka.Listener.PermissionListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.DriverMinePresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.drivercenter.DriverPurseActivity;
import com.dream.moka.UI.Activity.drivercenter.DriverUserInfoActivity;
import com.dream.moka.UI.Activity.FeedBackActivity;
import com.dream.moka.UI.Activity.QrCodeActivtiy;
import com.dream.moka.UI.Activity.set.UserXieYiActivity;
import com.dream.moka.UI.Activity.address.ChoosePointForMapActivity;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.MyUtils;
import com.dream.moka.Utils.ToastUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
public class DriverCenterMineFragment extends BaseFragmentV4 implements DriverMineContract {

    @Inject
    DriverMinePresenter mDriverMinePresenter;
    @Bind(R.id.driver_head)
    ImageView mDriverHead;
    @Bind(R.id.driver_name)
    TextView mDriverName;
    @Bind(R.id.driver_phone)
    TextView mDriverPhone;
    @Bind(R.id.driver_qq)
    TextView mDriverQq;
    @Bind(R.id.qr_iv)
    ImageView mQrIv;
    @Bind(R.id.driver_info)
    LinearLayout mDriverInfo;
    @Bind(R.id.purse_relay)
    RelativeLayout mPurseRelay;
    @Bind(R.id.data_statics)
    RelativeLayout mDataStatics;
    @Bind(R.id.swich_btn)
    ImageView mSwichBtn;
    @Bind(R.id.gohome_relay)
    LinearLayout mGohomeRelay;
    @Bind(R.id.feedback_relay)
    RelativeLayout mFeedbackRelay;
    @Bind(R.id.driver_xieyi)
    RelativeLayout mDriverXieyi;
    @Bind(R.id.homeAddress)
    TextView mHomeAddress;
    private boolean isOpen = true;
    private Dialog mLoadingDialog;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        BaseComponent build = DaggerBaseComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
//                .mainActivityModule(new MainActivityModule(this))
                .build();
        build.inject(this);
        mDriverMinePresenter.attachView(this);
    }

    @Override
    public void initDaggerView(BaseComponent build) {
//        build.inject(this);

    }

    @Override
    public void initView() {
        mLoadingDialog = DialogUtils.initLoadingDialog(getActivity());
        String driverName = CommonAction.getDriverName();
        String driverPhone = CommonAction.getDriverPhone();
        String driverQQ = CommonAction.getDriverQQ();
        mDriverName.setText(driverName == null ? "" : driverName);
        mDriverPhone.setText(driverPhone == null ? "" : driverPhone);
        mDriverQq.setText(driverQQ == null ? "" : driverQQ);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_drivercenter_mine;
    }

    @Override
    public void initResume() {
        boolean userVisibleHint = getUserVisibleHint();
        if (userVisibleHint) {
            mDriverMinePresenter.getDriverInfo();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mDriverMinePresenter.getDriverInfo();
        }
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void initDta() {

    }

    @OnClick({R.id.purse_relay, R.id.data_statics, R.id.swich_btn, R.id.gohome_relay, R.id.feedback_relay, R.id.driver_xieyi, R.id.qr_iv, R.id.driver_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.purse_relay://我的钱包
                DriverPurseActivity.openAct(getContext());
                break;
            case R.id.data_statics://数据统计
                ToastUtils.Toast_short("开发中，敬请期待");
//                DataStaticsActivity.openAct(getContext());
                break;
            case R.id.swich_btn://回家模式
                if (MyUtils.isCurrentInTimeScope(16, 0, 19, 0)) {
                    mLoadingDialog.show();
                    if (isOpen) {
                        mDriverMinePresenter.changeHomeMode("0");
                    } else {
                        mDriverMinePresenter.changeHomeMode("1");
                    }
                } else {
                    ToastUtils.Toast_short("不在服务时间内");
                }
                break;
            case R.id.gohome_relay://回家地址
                requestRunPermisssion(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        ChoosePointForMapActivity.openAct(getActivity(),0x12);
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        ToastUtils.Toast_short("拒绝定位权限，将无法获取当前位置，可在设置中重新获取");
                    }
                });

                break;
            case R.id.feedback_relay://意见反馈
                FeedBackActivity.openAct(getContext());
                break;
            case R.id.driver_xieyi://司机协议
                UserXieYiActivity.openAct(getContext(),"driver");
                break;
            case R.id.qr_iv://二维码
                startActivity(new Intent(getActivity(), QrCodeActivtiy.class));
                break;
            case R.id.driver_info://司机个人信息
                startActivity(new Intent(getActivity(), DriverUserInfoActivity.class));
                break;

        }
    }

    @Override
    public void showError() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void setHomeModeSuccess(String homeMode) {
        if (MyUtils.isCurrentInTimeScope(16, 0, 19, 0)){
            if (homeMode.equals("1")) {
                mSwichBtn.setImageResource(R.mipmap.icon_switch_selected);
                isOpen = true;
            } else {
                mSwichBtn.setImageResource(R.mipmap.icon_switch_default);
                isOpen = false;
            }
        }else {
            mSwichBtn.setImageResource(R.mipmap.icon_switch_default);
            isOpen = false;
        }

        String driverPhoto = CommonAction.getDriverPhoto();
        GlidUtils.LoadCircleImg(getContext(),Const.BannerUrl+driverPhoto,mDriverHead,R.mipmap.icon_avatar01);
        String driverHomeAddress = CommonAction.getDriverHomeAddress();
        mHomeAddress.setText(driverHomeAddress!=null?driverHomeAddress:"");
    }

    @Override
    public void saveddrseeSuccess(String lat, String lng, String Address) {
        mHomeAddress.setText(Address);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x12 && resultCode == 0x20) {
            String lat = data.getStringExtra("lat");
            String aLong = data.getStringExtra("Long");
            String address = data.getStringExtra("detail");
            String a = data.getStringExtra("a");
            mLoadingDialog.show();
            mDriverMinePresenter.saveHomeAddress(lat,aLong,a+address);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
