package com.dream.moka.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Base.BasePagerAdapter;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.other.WebViewActivity;
import com.dream.moka.UI.ChildFragment.CardsFragment;
import com.dream.moka.Utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class MyCardsActivity extends BaseActivity {

    @Bind(R.id.mycards_vp)
    ViewPager mycards_vp;
    List<Fragment> fraList = new ArrayList<>();
    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.foruse_tv)
    TextView mForuseTv;
    @Bind(R.id.line1)
    RelativeLayout mLine1;
    @Bind(R.id.canUselayout)
    LinearLayout mCanUselayout;
    @Bind(R.id.line2)
    RelativeLayout mLine2;
    @Bind(R.id.noUseLayout)
    LinearLayout mNoUseLayout;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;

    public static void openAct(Context context) {
        context.startActivity(new Intent(context, MyCardsActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mycards;
    }

    @Override
    public void initView() {
        initFragments();
        mycards_vp.setAdapter(new BasePagerAdapter(getSupportFragmentManager(), fraList));
    }

    public List<Fragment> initFragments() {
        fraList.clear();
        for (int i = 0; i < 2; i++) {
            CardsFragment cardsFragment = new CardsFragment();
            if (i == 0) {
                Bundle args = new Bundle();
                args.putString("type", "0");//可用
                cardsFragment.setArguments(args);
            } else {
                Bundle args = new Bundle();
                args.putString("type", "1");//失效
                cardsFragment.setArguments(args);
            }
            fraList.add(cardsFragment);
        }
        return fraList;
    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void eventListener() {
        mCanUselayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mycards_vp.setCurrentItem(0);
            }
        });
        mNoUseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mycards_vp.setCurrentItem(1);
            }
        });

        mycards_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mLine1.setBackgroundColor(ContextCompat.getColor(mContext,R.color.fengexian4));
                    mLine2.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
                } else {
                    mLine2.setBackgroundColor(ContextCompat.getColor(mContext,R.color.fengexian4));
                    mLine1.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @OnClick({R.id.back_relay, R.id.foruse_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.foruse_tv:
                IntentUtils.toActivityWithTag(WebViewActivity.class,mActivity,"4");
                break;
        }
    }
}
