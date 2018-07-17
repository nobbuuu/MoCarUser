package com.dream.moka.UI.ChildFragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.ListCouponBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.CardsContract;
import com.dream.moka.CumstomView.EmptyView;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.CardsPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.RvUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class CardsFragment extends BaseFragmentV4 implements CardsContract {
    @Inject
    CardsPresenter mCardsPresenter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;
    @Bind(R.id.smart_refreshlay)
    SmartRefreshLayout mSmart;
    private PurseAdapter purseAdapter;
    private List<ListCouponBean> dataList = new ArrayList<>();
    private Dialog mLoadingDialog;
    private String mTypeString = "";
    protected boolean isCreate = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
    }

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public void initView() {
        mCardsPresenter.attachView(this);
        RvUtils.setOption_noparam(common_rv, getActivity());
        purseAdapter = new PurseAdapter(R.layout.rvitem_cards,dataList);
        purseAdapter.setEmptyView(new EmptyView(getActivity(),"您还没有优惠券哦~~"));
        common_rv.setAdapter(purseAdapter);
        mLoadingDialog = DialogUtils.initLoadingDialog(getActivity());
        mTypeString = getArguments().getString("type");
        mSmart.setEnableRefresh(false);
        mSmart.setEnableLoadmore(false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_commonrv;
    }

    @Override
    public void initResume() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            //相当于Fragment的onResume
            //在这里处理加载数据等操作
            mLoadingDialog.show();
            mCardsPresenter.getCardData(mTypeString);
        }
    }

    @Override
    public void initEvents() {
    }

    @Override
    public void initDta() {
        if (getUserVisibleHint()) {
            mLoadingDialog.show();
            mCardsPresenter.getCardData(mTypeString);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showError() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void getDataSuccess(List<ListCouponBean> listResultBeans) {
        if (listResultBeans != null && listResultBeans.size() > 0) {
            common_rv.setVisibility(View.VISIBLE);
        } else {
            common_rv.setVisibility(View.GONE);
        }
        if (dataList.size() > 0) {
            dataList.clear();
        }
        dataList.addAll(listResultBeans);
        purseAdapter.notifyDataSetChanged();
    }


    private class PurseAdapter extends BaseQuickAdapter<ListCouponBean,BaseViewHolder> {


        public PurseAdapter(@LayoutRes int layoutResId, @Nullable List<ListCouponBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, ListCouponBean cardListResultBean) {
            TextView money = holder.getView(R.id.money);
            TextView name = holder.getView(R.id.name);
            TextView ruleRight = holder.getView(R.id.rule);
            TextView ruleLeft = holder.getView(R.id.rule1);
            TextView time = holder.getView(R.id.time);

            String amount = cardListResultBean.getAmount();
            double aDouble = Double.parseDouble(amount);
            money.setText("¥" + amount);

            String effectFrom = cardListResultBean.getEffectFrom();
            String effectTo = cardListResultBean.getEffectTo();
            if (effectTo == null || effectTo.equals("")) {
                time.setText("无期限");
            } else {
                time.setText("有期限：" + DateFormatUtil.getTime(effectFrom, Const.YMD_HMS,Const.Y_M_D) + "~" + DateFormatUtil.getTime(effectTo,Const.YMD_HMS,Const.Y_M_D));

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
                    default:
                        ruleRight.setText("");
                }
            }
        }
    }
}
