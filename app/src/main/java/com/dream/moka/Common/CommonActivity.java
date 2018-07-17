package com.dream.moka.Common;

import android.view.View;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.R;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class CommonActivity extends BaseCommonActivity {

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {

    }

    @Override
    public String initTitleText() {
        return null;
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
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_relay:
                finish();
                break;
        }
    }
}
