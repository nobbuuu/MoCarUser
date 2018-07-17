package com.dream.moka.UI.Activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.BasePagerAdapter;
import com.dream.moka.Bean.IntegralBean;
import com.dream.moka.Bean.MyAccountBean;
import com.dream.moka.Bean.WalletBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.PurseJifenContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.PurseJifenPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.drivercenter.RechargeActivity;
import com.dream.moka.UI.Activity.other.WebViewActivity;
import com.dream.moka.UI.ChildFragment.JifenDetailFragment;
import com.dream.moka.Utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class PurseJifenActivity extends BaseCommonActivity implements PurseJifenContract{

    @Inject
    PurseJifenPresenter mPurseJifenPresenter;
    @Bind(R.id.purse_vp)
    ViewPager purse_vp;
    @Bind(R.id.pursemoney_tv)
    TextView pursemoney_tv;
    @Bind(R.id.jifennum_tv)
    TextView jifennum_tv;
    @Bind(R.id.right_tv)
    TextView jifenrule_tv;

    @Bind(R.id.all_tv)
    TextView all_tv;
    @Bind(R.id.relay1)
    RelativeLayout relay1;
    @Bind(R.id.second_tv)
    TextView second_tv;
    @Bind(R.id.relay2)
    RelativeLayout relay2;
    @Bind(R.id.third_tv)
    TextView third_tv;
    @Bind(R.id.relay3)
    RelativeLayout relay3;

    @Bind(R.id.purse_relay)
    RelativeLayout purse_relay;
    @Bind(R.id.jifen_relay)
    RelativeLayout jifen_relay;

    private BasePagerAdapter mVpAdapter;

    private String intentTag;

    @Override
    public int getLayoutId() {
        return R.layout.activity_purse;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mPurseJifenPresenter.attachView(this);
        intentTag = getIntent().getStringExtra(Const.intentTag);
        mVpAdapter = new BasePagerAdapter(getSupportFragmentManager(), getFragments());
        purse_vp.setAdapter(mVpAdapter);

    }

    @Override
    public String initTitleText() {
        return "我的钱包";
    }

    @Override
    public String initRightTv() {
        return "积分规则";
    }

    @Override
    public boolean isRighttv() {
        return true;
    }

    @Override
    public void loadResum() {
        if (intentTag.equals("jifen")) {
            purse_relay.setVisibility(View.GONE);
            jifen_relay.setVisibility(View.VISIBLE);
            jifenrule_tv.setVisibility(View.VISIBLE);
            second_tv.setText("获取");
            third_tv.setText("使用");
            right_tv.setVisibility(View.VISIBLE);
            String userScore = CommonAction.getUserScore();
            jifennum_tv.setText("" + userScore);
            title_tv.setText("我的积分");
        } else {
            purse_relay.setVisibility(View.VISIBLE);
            jifen_relay.setVisibility(View.GONE);
            jifenrule_tv.setVisibility(View.GONE);
            right_tv.setVisibility(View.GONE);
            String userBalance = CommonAction.getUserBalance();
            pursemoney_tv.setText("" + userBalance);
            mPurseJifenPresenter.getUserAccountData();
        }
    }

    @Override
    public void initDatas() {
    }

    private List<Fragment> getFragments() {
        String tag = "";
        if (intentTag.equals("jifen")) {
            tag = "jf";
        } else {
            tag = "ye";
        }
        List<Fragment> fraList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            JifenDetailFragment detailFra = new JifenDetailFragment();
            detailFra.setTag(i, tag);
            fraList.add(detailFra);
        }
        return fraList;
    }

    @Override
    public void eventListener() {
        all_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purse_vp.setCurrentItem(0);
            }
        });
        second_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purse_vp.setCurrentItem(1);
            }
        });
        third_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purse_vp.setCurrentItem(2);
            }
        });
        purse_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        relay1.setBackgroundColor(Color.parseColor("#f4a114"));
                        relay2.setBackgroundColor(Color.parseColor("#ffffff"));
                        relay3.setBackgroundColor(Color.parseColor("#ffffff"));
                        break;
                    case 1:
                        relay2.setBackgroundColor(Color.parseColor("#f4a114"));
                        relay1.setBackgroundColor(Color.parseColor("#ffffff"));
                        relay3.setBackgroundColor(Color.parseColor("#ffffff"));
                        break;
                    case 2:
                        relay3.setBackgroundColor(Color.parseColor("#f4a114"));
                        relay2.setBackgroundColor(Color.parseColor("#ffffff"));
                        relay1.setBackgroundColor(Color.parseColor("#ffffff"));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.back_relay, R.id.recharge_tv,R.id.right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.recharge_tv:
                RechargeActivity.openAct(mContext, "user");
                break;
            case R.id.right_tv:
                IntentUtils.toActivityWithTag(WebViewActivity.class,mActivity,"6");
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
    public void getAccount(WalletBean walletBean) {
        String balance = walletBean.getBalance();
        if (balance != null) {
            pursemoney_tv.setText("" + balance);
        }
    }

    @Override
    public void getQianBaoData(MyAccountBean results, boolean isMore) {

    }

    @Override
    public void isLoadAll(boolean isAll) {

    }

    @Override
    public void showScoreList(List<IntegralBean> dataList) {

    }
}
