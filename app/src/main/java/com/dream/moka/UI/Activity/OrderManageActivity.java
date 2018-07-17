package com.dream.moka.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.BasePagerAdapter;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.R;
import com.dream.moka.UI.ChildFragment.OrdersFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class OrderManageActivity extends BaseCommonActivity {

    @Bind(R.id.order_vp)
    ViewPager order_vp;
    @Bind(R.id.order_tablayout)
    TabLayout order_tablayout;
    private List<Fragment> fragments = new ArrayList<>();
    private String [] titles = new String[]{"全部","待支付","服务中","已完成"};
    private String mFromString="";

    public static void openAct(Context context,int index,String from){
        Intent intent = new Intent(context, OrderManageActivity.class);
        intent.putExtra("index",index);
        intent.putExtra("from",from);
        context.startActivity(intent);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_ordermanage;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {
        int index = getIntent().getIntExtra("index", 0);
        mFromString = getIntent().getStringExtra("from");
        initFragments();
        order_vp.setAdapter(new BasePagerAdapter(getSupportFragmentManager(),fragments));
        order_tablayout.setupWithViewPager(order_vp);
        order_vp.setOffscreenPageLimit(3);
        order_vp.setCurrentItem(index);
        for (int i = 0; i < fragments.size(); i++) {
            order_tablayout.getTabAt(i).setText(titles[i]);
        }
    }

    @Override
    public void onBackPressed() {
        if (mFromString.equals("pay")){
            Intent intent = new Intent(mContext, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }else {
            finish();
        }
    }

    private void initFragments(){
        fragments.clear();
        for (int i = 0; i <4 ; i++) {
            OrdersFragment ordersFragment = new OrdersFragment();
            Bundle args = new Bundle();
            args.putString("type",String.valueOf(i+1));
            ordersFragment.setArguments(args);
            fragments.add(ordersFragment);
        }


    }
    @Override
    public String initTitleText() {
        return "订单管理";
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
                if (mFromString.equals("pay")){
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }else {
                    finish();
                }
                break;
        }
    }
}
