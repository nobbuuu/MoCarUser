package com.dream.moka.UI.Activity.Message;

import android.view.View;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.OrderPayActivity;
import com.dream.moka.Utils.IntentUtils;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class MessageDetailSureOrderActivity extends BaseCommonActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_messagedetai_sureorder;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {


    }

    @Override
    public void initView() {

    }

    @Override
    public String initTitleText() {
        return "确认订单";
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

    @OnClick({R.id.back_relay,R.id.sure_tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_relay:
                finish();
                break;
            case R.id.sure_tv:
                IntentUtils.toActivity(OrderPayActivity.class,mActivity);
                break;
        }
    }
}
