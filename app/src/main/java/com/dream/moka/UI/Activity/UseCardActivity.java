package com.dream.moka.UI.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.ListCouponBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.other.WebViewActivity;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.RvUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class UseCardActivity extends BaseCommonActivity {


    @Bind(R.id.recycler)
    RecyclerView mRecycler;
    private List<ListCouponBean> dataList = new ArrayList<>();
    private PurseAdapter mPurseAdapter;
    public static void openAct(Activity activity, List<ListCouponBean> listResultBeans, int questCode){
        Intent intent = new Intent(activity, UseCardActivity.class);
        intent.putExtra("data",(Serializable) listResultBeans);
        activity.startActivityForResult(intent,questCode);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_use_card;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {
        List<ListCouponBean> data = (List<ListCouponBean>) getIntent().getSerializableExtra("data");
        if (data!=null){
            dataList.addAll(data);
        }
        RvUtils.setOption_noparam(mRecycler, mActivity);
        mPurseAdapter = new PurseAdapter(mActivity, dataList, R.layout.rvitem_cards);
        mRecycler.setAdapter(mPurseAdapter);

    }

    @Override
    public String initTitleText() {
        return "我的优惠券";
    }

    @Override
    public String initRightTv() {
        return "如何使用？";
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

    }

    @Override
    public void eventListener() {
    }

    @OnClick({R.id.right_tv,R.id.back_relay})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.right_tv:
                IntentUtils.toActivityWithTag(WebViewActivity.class,mActivity,"4");
                break;
            case R.id.back_relay:
                finish();
                break;
        }
    }

    private class PurseAdapter extends RVBaseAdapter<ListCouponBean> {

        public PurseAdapter(Activity context, List<ListCouponBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final ListCouponBean cardListResultBean, int position) {
            TextView money = holder.getView(R.id.money);
            TextView name = holder.getView(R.id.name);
            TextView ruleRight = holder.getView(R.id.rule);
            TextView ruleLeft = holder.getView(R.id.rule1);
            TextView time = holder.getView(R.id.time);
            TextView useNow=holder.getView(R.id.useNow);

            String amount = cardListResultBean.getAmount();
            money.setText("¥" + amount);

            String effectFrom = cardListResultBean.getEffectFrom();
            String effectTo = cardListResultBean.getEffectTo();
            if (effectTo == null || effectTo.equals("")) {
                time.setText("无期限");
            } else {
                time.setText("有期限：" + DateFormatUtil.getTime(effectFrom, Const.YMD_HMS,Const.Y_M_D) + " ~ " + DateFormatUtil.getTime(effectTo,Const.YMD_HMS,Const.Y_M_D));
            }
            String cardName = cardListResultBean.getName();
            name.setText(cardName);

            String couponType = cardListResultBean.getCouponType();
            if (couponType != null) {
                switch (couponType) {
                    case "5":
                        String carName = cardListResultBean.getCarName();
                        ruleRight.setText("只限" + carName);
                        break;
                }

            }

            useNow.setVisibility(View.VISIBLE);
            useNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent data = new Intent();
                    data.putExtra("backData",cardListResultBean);
                    setResult(200, data);
                    finish();
                }
            });
        }
    }
}
