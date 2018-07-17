package com.dream.moka.UI.Activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class DaiJiaActivity extends BaseCommonActivity {

    @Bind(R.id.web)
    WebView mWeb;
    @Bind(R.id.img)
    ImageView mImg;
    @Bind(R.id.txt)
    TextView mTxt;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {
        mImg.setImageResource(R.drawable.daijiaxian_img);
    }

    @Override
    public String initTitleText() {
        return "代驾险";
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

    }

    @OnClick({R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
        }
    }

}
