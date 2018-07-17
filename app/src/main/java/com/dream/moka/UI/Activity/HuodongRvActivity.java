package com.dream.moka.UI.Activity;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.AllActivityBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.HuoDongContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.HuodongPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.other.WebViewActivity;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class HuodongRvActivity extends BaseCommonActivity implements HuoDongContract {

    @Bind(R.id.smart_refreshlay)
    SmartRefreshLayout mSmartRefreshlay;
    private List<AllActivityBean.ListBean> dataList = new ArrayList<>();
    private HuoDonAdapter commonAdapter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;
    @Inject
    HuodongPresenter mHuodongPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commonrv;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mHuodongPresenter.attachView(this);
        RvUtils.setOptionnoLine(common_rv, this);
        commonAdapter = new HuoDonAdapter(this, dataList, R.layout.rvitem_hot);
        common_rv.setAdapter(commonAdapter);
    }

    @Override
    public String initTitleText() {
        return "全部活动";
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
        mHuodongPresenter.getActivity(false);
    }

    @Override
    public void eventListener() {
        mSmartRefreshlay.setEnableLoadmore(true);
        mSmartRefreshlay.setEnableRefresh(true);
        mSmartRefreshlay.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mHuodongPresenter.getActivity(false);
            }
        });
        mSmartRefreshlay.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mHuodongPresenter.getActivity(true);
            }
        });
    }

    @OnClick({R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {
        mSmartRefreshlay.finishLoadmore();
        mSmartRefreshlay.finishRefresh();
    }

    @Override
    public void setHuoDongData(List<AllActivityBean.ListBean> list) {
        dataList.clear();
        dataList.addAll(list);
        commonAdapter.notifyDataSetChanged();
    }

    @Override
    public void setFinishLoadMore(boolean isAll) {
        mSmartRefreshlay.setLoadmoreFinished(isAll);
    }

    public class HuoDonAdapter extends RVBaseAdapter<AllActivityBean.ListBean> {

        public HuoDonAdapter(Activity context, List<AllActivityBean.ListBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final AllActivityBean.ListBean listBean, int position) {
            ImageView picIv = holder.getView(R.id.img);
            GlidUtils.LoadImgForUrl(mContext.getApplicationContext(), Const.BannerUrl+listBean.getPic(),picIv);
            holder.setText(R.id.title,listBean.getTitle());
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    if (listBean.getType()==0){
                        if (StringUtil.NoNullOrEmpty(listBean.getLinkAddr())){
                            IntentUtils.toActivityWithUrl(WebActivity.class, mActivity, listBean.getLinkAddr(), "活动详情");
                        }
                    }else if (StringUtil.NoNullOrEmpty(listBean.getId())){
                        IntentUtils.toActivityWithUrl(WebViewActivity.class,mActivity,listBean.getId(),"2");
                    }
                }
            });
        }
    }
}
