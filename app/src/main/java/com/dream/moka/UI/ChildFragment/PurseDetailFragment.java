package com.dream.moka.UI.ChildFragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ListView;

import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Bean.InComeResultBean;
import com.dream.moka.Bean.WithDrawResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.PurseDetailContract;
import com.dream.moka.Presenter.PurseDetailPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class PurseDetailFragment extends BaseFragmentV4 implements PurseDetailContract {
    @Inject
    PurseDetailPresenter mPurseDetailPresenter;
    @Bind(R.id.common_rv)
    ListView common_rv;
    @Bind(R.id.smart)
    SmartRefreshLayout mSmart;
    private Dialog mLoadingDialog;
    private PurseSRAdapter mPurseSRAdapter;
    private PurseTXAdapter mPurseTXAdapter;
    private List<InComeResultBean.ListBean> mBeanListIn = new ArrayList<>();
    private List<WithDrawResultBean.ListBean> mBeanListWith = new ArrayList<>();
    private String mTag;

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public void initView() {
        Bundle arguments = getArguments();
        mTag = arguments.getString("tag");
        mPurseSRAdapter = new PurseSRAdapter(getActivity(), mBeanListIn, R.layout.rvitem_purse);
        mPurseTXAdapter = new PurseTXAdapter(getActivity(), mBeanListWith, R.layout.rvitem_purse);
        mPurseDetailPresenter.attachView(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(getActivity());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_commonlv;
    }

    @Override
    public void initResume() {

    }

    @Override
    public void initEvents() {
        mSmart.setEnableRefresh(false);
        mSmart.setEnableLoadmore(true);
        mSmart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (mTag.equals("sr")) {
                    mPurseDetailPresenter.getShouRuData(true);

                } else if (mTag.equals("tx")) {
                    mPurseDetailPresenter.getTiXianData(true);

                }
            }
        });
    }

    @Override
    public void initDta() {
        if (mTag.equals("sr")) {
            mLoadingDialog.show();
            mPurseDetailPresenter.getShouRuData(false);

        } else if (mTag.equals("tx")) {
            mLoadingDialog.show();
            mPurseDetailPresenter.getTiXianData(false);

        }
    }

    @Override
    public void showError() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        mSmart.finishLoadmore();
    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        mSmart.finishLoadmore();
    }

    @Override
    public void getShouRuData(InComeResultBean resultBean, boolean isLoadmore) {
        List<InComeResultBean.ListBean> items = resultBean.getList();
        if (items.size() != 0) {

            if (isLoadmore) {
                mBeanListIn.addAll(resultBean.getList());
                mPurseSRAdapter.notifyDataSetChanged();
            } else {
                if (mBeanListIn.size() != 0) {
                    mBeanListIn.clear();
                }
                mBeanListIn.addAll(resultBean.getList());
            }
            common_rv.setAdapter(mPurseSRAdapter);
        }
    }

    @Override
    public void getTiXianData(WithDrawResultBean resultBean, boolean isLoadmore) {
        List<WithDrawResultBean.ListBean> items = resultBean.getList();
        if (items.size() != 0) {

            if (isLoadmore) {
                mBeanListWith.addAll(resultBean.getList());
                mPurseTXAdapter.notifyDataSetChanged();
            } else {
                if (mBeanListWith.size() != 0) {
                    mBeanListWith.clear();
                }
                mBeanListWith.addAll(resultBean.getList());

            }
            common_rv.setAdapter(mPurseTXAdapter);
        }
    }

    @Override
    public void isLoadAll(boolean isAll) {
        mSmart.setLoadmoreFinished(isAll);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class PurseSRAdapter extends CommonAdapter<InComeResultBean.ListBean> {

        public PurseSRAdapter(Activity context, List<InComeResultBean.ListBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, InComeResultBean.ListBean dataBean, int position) {
            holder.setText(R.id.cusume_tv, "收入");
            String receiptPayment = dataBean.getReceiptPayment();
            String amount = dataBean.getAmount();
            if (receiptPayment != null) {
                if (receiptPayment.equals("0")) {
                    holder.setText(R.id.money, "-" + amount);
                } else {
                    holder.setText(R.id.money, "+" + amount);
                }
            } else {
                holder.setText(R.id.money, amount == null ? "0" : amount);
            }
            String createDate = dataBean.getCreateDate();
            holder.setText(R.id.time, createDate == null ? "" : createDate);
        }
    }

    private class PurseTXAdapter extends CommonAdapter<WithDrawResultBean.ListBean> {

        public PurseTXAdapter(Activity context, List<WithDrawResultBean.ListBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, WithDrawResultBean.ListBean dataBean, int position) {
            holder.setText(R.id.cusume_tv, "提现");
            String receiptPayment = dataBean.getTransType();
            String amount = dataBean.getAmount();
            if (receiptPayment != null) {
                if (receiptPayment.equals("1")) {
                    holder.setText(R.id.money, "-" + amount);
                } else {
                    holder.setText(R.id.money, "+" + amount);
                }
            } else {
                holder.setText(R.id.money, amount == null ? "0" : amount);
            }
            String createDate = dataBean.getCreateDate();
            holder.setText(R.id.time, createDate == null ? "" : createDate);
        }
    }
}
