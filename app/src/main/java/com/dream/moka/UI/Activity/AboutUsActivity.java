package com.dream.moka.UI.Activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.AboutBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.AboutContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.AboutPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.ToastUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class AboutUsActivity extends BaseCommonActivity implements AboutContract.View{

    @Bind(R.id.login_icon)
    ImageView mLoginIcon;
    @Bind(R.id.version_tv)
    TextView mVersionTv;
    @Bind(R.id.describe_tv)
    TextView mDescribeTv;

    @Inject
    AboutPresenter mAboutPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mAboutPresenter.attachView(this);
    }

    @Override
    public String initTitleText() {
        return "关于我们";
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
        mAboutPresenter.getAbout("1");
        PackageInfo packageInfo = null;
        try {
            packageInfo = MyApplication.getInstance().getPackageManager().getPackageInfo(MyApplication.getInstance().getPackageName(), 0);
            final String localVersionName = packageInfo.versionName;
            Log.e("tag", "localVersionName=" + localVersionName);
            int localVersionCode = packageInfo.versionCode;
            String[] currentNames = localVersionName.split("\\.");
            mVersionTv.setText("版本号v"+localVersionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eventListener() {

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

    }

    @Override
    public void complete() {

    }

    @Override
    public void showData(AboutBean dataBean) {
        GlidUtils.LoadImgForUrl(mContext, Const.BannerUrl+dataBean.getLogo(),mLoginIcon);
        mDescribeTv.setText(dataBean.getDescription());
    }
}
