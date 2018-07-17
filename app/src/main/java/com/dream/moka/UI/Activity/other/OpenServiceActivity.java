package com.dream.moka.UI.Activity.other;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.PriceResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.PriceContract;
import com.dream.moka.Presenter.PricePresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.PayOpenServiceActivity;
import com.dream.moka.Utils.IntentUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class OpenServiceActivity extends BaseCommonActivity implements PriceContract{

    @Inject
    PricePresenter mPricePresenter;
    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.price_tv1)
    TextView mPriceTv1;
    @Bind(R.id.price_tv2)
    TextView mPriceTv2;
    @Bind(R.id.price_tv3)
    TextView mPriceTv3;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;

    private String tempMoney;
    @Override
    public int getLayoutId() {
        return R.layout.activity_starservice;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mPricePresenter.attachView(this);
    }

    @Override
    public String initTitleText() {
        return "开启服务";
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
        mLoadingDialog.show();
        mPricePresenter.getRechargePrice();
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay, R.id.sure_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.sure_tv:
                if (tempMoney!=null){
                    IntentUtils.toActivityWithTag(PayOpenServiceActivity.class, mActivity,tempMoney);
                    finish();
                }
                break;
        }
    }

    @Override
    public void getYaJinData(PriceResultBean dataBean) {
        tempMoney = dataBean.getRechargeAmt();
        mPriceTv1.setText("需充值"+tempMoney+"元");
        mPriceTv2.setText("2.500积分（可抵扣"+tempMoney+"元代驾券）");
        mPriceTv3.setText("3.充值"+tempMoney+"元可在用户钱包查看");
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }

}
