package com.dream.moka.UI.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.OrderDetailBean;
import com.dream.moka.Bean.OrderDriverBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.OrderDetailContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.OrderDetailPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.StringUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class DriverInfoActivity extends BaseCommonActivity implements OrderDetailContract {
    @Inject
    OrderDetailPresenter mOrderDetailPresenter;
    @Bind(R.id.driver_icon)
    ImageView mDriverIcon;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.phone)
    TextView mPhone;
    @Bind(R.id.call_phone)
    ImageView mCallPhone;
    @Bind(R.id.qq)
    TextView mQq;
    @Bind(R.id.licence_Code)
    TextView mLicenceCode;
    @Bind(R.id.time)
    TextView mTime;
    @Bind(R.id.estimatedTime_lay)
    LinearLayout mEstimatedTimeLay;
    private String mId;
    private String resultsPhone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_driverinfo;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mId = getIntent().getStringExtra("id");
        mOrderDetailPresenter.attachView(this);
        mLoadingDialog.show();
        mOrderDetailPresenter.getOrderDriver(mId);

        String time = getIntent().getStringExtra("time");
        if (StringUtil.NoNullOrEmpty(time)){
            mEstimatedTimeLay.setVisibility(View.VISIBLE);
            mTime.setText(time);
        }
    }

    @Override
    public String initTitleText() {
        String titleStr = getIntent().getStringExtra(Const.intentTag);
        return titleStr + "司机信息";
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
    }

    @Override
    public void eventListener() {
        mCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.call_Dall("" + resultsPhone, mActivity);
            }
        });
    }

    @OnClick({R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
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
    public void getDetaildataSuccess(OrderDetailBean detailBean) {

    }

    @Override
    public void getDriverData(OrderDriverBean results) {
        resultsPhone = results.getPhone();
        String phone = resultsPhone;
        String driverName = results.getDriverName();
        String qq = results.getQq();
        String licenseCode = results.getLicenseCode();
        String photo = results.getPhoto();

        mName.setText(driverName);
        mPhone.setText("电话：" + phone);
        mQq.setText(qq + "");
        mLicenceCode.setText(licenseCode + "");
        GlidUtils.LoadImgForUrl(mContext, Const.BannerUrl + photo, mDriverIcon, R.mipmap.img_placeholderfigure01);

    }

}
