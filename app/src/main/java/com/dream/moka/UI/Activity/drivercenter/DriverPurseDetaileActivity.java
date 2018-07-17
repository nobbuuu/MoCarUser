package com.dream.moka.UI.Activity.drivercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.BasePagerAdapter;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.R;
import com.dream.moka.UI.ChildFragment.PurseDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class DriverPurseDetaileActivity extends BaseCommonActivity {

    @Bind(R.id.order_vp)
    ViewPager order_vp;
    @Bind(R.id.tablayout)
    TabLayout tablayout;

    public static void openAct(Context context) {
        context.startActivity(new Intent(context, DriverPurseDetaileActivity.class));
    }

    private String[] titles = new String[]{"收入", "提现"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_driverordermanage;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {
        order_vp.setAdapter(new BasePagerAdapter(getSupportFragmentManager(), getFragments()));
        tablayout.setupWithViewPager(order_vp);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fraList = new ArrayList<>();
        PurseDetailFragment getMoney = new PurseDetailFragment();
        Bundle args = new Bundle();
        args.putString("tag","sr");
        getMoney.setArguments(args);
        fraList.add(getMoney);
        PurseDetailFragment putMoney = new PurseDetailFragment();
        Bundle args1 = new Bundle();
        args1.putString("tag","tx");
        putMoney.setArguments(args1);
        fraList.add(putMoney);
        return fraList;
    }

    @Override
    public String initTitleText() {
        return "钱包明细";
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
        for (int i = 0; i < titles.length; i++) {
            tablayout.getTabAt(i).setText(titles[i]);
        }
    }

    @Override
    public void eventListener() {
    }

}
