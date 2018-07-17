package com.dream.moka.UI.Activity;

import android.view.View;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.WebViewContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.WebViewPresenter;
import com.dream.moka.R;
import com.tencent.smtt.sdk.WebView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class BeDriverWebActivity extends BaseCommonActivity implements WebViewContract {

    @Bind(R.id.bedriver_tv)
    TextView bedriver_tv;
    @Bind(R.id.web_view)
    WebView web_view;
    @Inject
    WebViewPresenter mWebViewPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
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
        mWebViewPresenter.attachView(this);

    }

    @Override
    public String initTitleText() {
        String Tag = getIntent().getStringExtra(Const.intentTag);
        if (Tag != null) {
            if (!Tag.equals("申请成为司机")) {
                bedriver_tv.setVisibility(View.GONE);
            }
            return Tag;
        } else {
            return "";
        }
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
        String url = getIntent().getStringExtra(Const.webUrl);
        if (url != null && !url.isEmpty()) {
            web_view.getSettings().setBlockNetworkImage(false);
            web_view.loadUrl(url);
        }
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay, R.id.bedriver_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.bedriver_tv:
                mWebViewPresenter.checkCanDriver();
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
    public void onSuccess() {
        BeDriverActivity.openAct(mContext);
    }

    @Override
    public void onError() {
    }
}
