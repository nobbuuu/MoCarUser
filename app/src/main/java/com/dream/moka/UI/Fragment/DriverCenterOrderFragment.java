package com.dream.moka.UI.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Base.BasePagerAdapter;
import com.dream.moka.Bean.PriceResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.PriceContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.PricePresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.drivercenter.RechargeActivity;
import com.dream.moka.UI.ChildFragment.DriverCenterOrderAllRvFragment;
import com.dream.moka.UI.ChildFragment.DriverCenterOrderDoingFragment;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.SpUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
public class DriverCenterOrderFragment extends BaseFragmentV4 implements PriceContract {

    @Bind(R.id.dc_ordervp)
    ViewPager dc_ordervp;
    @Bind(R.id.send_line)
    View send_line;
    @Bind(R.id.all_line)
    View all_line;
    @Bind(R.id.read)
    CheckBox mRead;
    @Bind(R.id.btn)
    TextView mBtn;
    @Bind(R.id.noMoneyLayout)
    RelativeLayout mNoMoneyLayout;
    @Bind(R.id.send_tv)
    TextView mSendTv;
    @Bind(R.id.doing_lay)
    LinearLayout mDoingLay;
    @Bind(R.id.all_tv)
    TextView mAllTv;
    @Bind(R.id.allorder_lay)
    LinearLayout mAllorderLay;
    @Bind(R.id.nomalLayout)
    LinearLayout mNomalLayout;
    @Inject
    PricePresenter mDriverCenterOrderPresenter;
    @Bind(R.id.yajin)
    TextView mYajin;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public void initView() {
        SpUtils.savaUserInfo(Const.OrderPage, "0");
        mDriverCenterOrderPresenter.attachView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_drivercenter_order;
    }

    @Override
    public void initResume() {
        String driverStatus = CommonAction.getDriverStatus();
        if (driverStatus.equals("1")) {
            mNomalLayout.setVisibility(View.VISIBLE);
            mNoMoneyLayout.setVisibility(View.GONE);
        } else {
            mNomalLayout.setVisibility(View.GONE);
            mNoMoneyLayout.setVisibility(View.VISIBLE);
            mDriverCenterOrderPresenter.getRechargePrice();
        }
    }

    @Override
    public void initEvents() {
        dc_ordervp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        refreshDoing();
                        break;
                    case 1:
                        refreshAll();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void refreshAll() {
        initTopItem();
        all_line.setBackgroundColor(ResourcesUtils.getColor(R.color.coloryellow));
        SpUtils.savaUserInfo(Const.OrderPage, "1");
        EventBus.getDefault().post("sx");
    }

    private void refreshDoing() {
        initTopItem();
        send_line.setBackgroundColor(ResourcesUtils.getColor(R.color.coloryellow));
        SpUtils.savaUserInfo(Const.OrderPage, "0");
        EventBus.getDefault().post("sb");

    }

    private void initTopItem() {
        send_line.setBackgroundColor(ResourcesUtils.getColor(R.color.white));
        all_line.setBackgroundColor(ResourcesUtils.getColor(R.color.white));
    }

    @Override
    public void initDta() {
        initFragments();
        dc_ordervp.setAdapter(new BasePagerAdapter(getChildFragmentManager(), fragments));
    }

    private void initFragments() {
        fragments.clear();
        DriverCenterOrderDoingFragment fragment = new DriverCenterOrderDoingFragment();
        fragments.add(fragment);
        DriverCenterOrderAllRvFragment orderFragment = new DriverCenterOrderAllRvFragment();
        fragments.add(orderFragment);

    }

    @OnClick({R.id.doing_lay, R.id.allorder_lay, R.id.btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.doing_lay:
                refreshDoing();
                dc_ordervp.setCurrentItem(0);
                break;
            case R.id.allorder_lay:
                refreshAll();
                dc_ordervp.setCurrentItem(1);

                break;
            case R.id.btn:
                RechargeActivity.openAct(getContext(),"");
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void getYaJinData(PriceResultBean s) {
        mYajin.setText("面试通过后您需要缴纳" + s.getDriverDeposit() + "元押金才能成为司机押金可退（退押金后将停止代驾接单）");

    }

}
