package com.dream.moka.UI.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.AboutBean;
import com.dream.moka.Bean.EventBusBean.RefreshBean;
import com.dream.moka.Bean.Online.OnlinerBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.AboutContract;
import com.dream.moka.Contract.ChatContract;
import com.dream.moka.IM.im.activity.ChatActivity;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.AboutPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.SpUtils;
import com.dream.moka.Utils.StringUtil;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class TuoCarActivity extends BaseCommonActivity implements ChatContract.View, AboutContract.View {

    @Inject
    AboutPresenter mAboutPresenter;
    @Bind(R.id.tuocar_tv)
    TextView mTuocarTv;
    @Bind(R.id.helpphone_tv)
    TextView mHelpphoneTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tuocarservice;
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
        return "救援拖车";
    }

    @Override
    public String initRightTv() {
        return "联系专家";
    }

    @Override
    public boolean isRighttv() {
        return true;
    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {
        mAboutPresenter.getAbout("1");
    }

    @Override
    public void eventListener() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Const.CAll) {
            String platPhone = CommonAction.getPlatPhone();
            if (!platPhone.isEmpty()){
                IntentUtils.call_Dall(platPhone,mActivity);
            }
        }
    }

    @OnClick({R.id.back_relay, R.id.tuocar_tv, R.id.right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.tuocar_tv://打电话
                IntentUtils.call_Dall(CommonAction.getHelpPhone(), mActivity);
                break;
            case R.id.right_tv:
                EventBus.getDefault().post(new RefreshBean(Const.refresh));
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
    public void showOnlinerData(OnlinerBean dataBean) {
        if (StringUtil.NoNullOrEmpty(dataBean.getUserId())) {
            ChatActivity.navToChat(mContext, dataBean);
        }
    }

    @Override
    public void showData(AboutBean dataBean) {
        String helpPhone = dataBean.getTelphone();
        if (StringUtil.NoNullOrEmpty(helpPhone)) {
            mHelpphoneTv.setText(dataBean.getHelpPhone());
            SpUtils.savaUserInfo(Const.platformTell,helpPhone);
            SpUtils.savaUserInfo(Const.helpTell,dataBean.getHelpPhone());
        }
    }
}
