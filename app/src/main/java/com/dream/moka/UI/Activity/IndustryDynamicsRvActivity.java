package com.dream.moka.UI.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.NewsBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.IndustryDynamicsContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.IndustryDynamicsRvPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.other.DynamicActivity;
import com.dream.moka.UI.Activity.other.WebViewActivity;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class IndustryDynamicsRvActivity extends BaseCommonActivity implements IndustryDynamicsContract{

    @Bind(R.id.smart_refreshlay)
    SmartRefreshLayout mSmartRefreshlay;
    private List<NewsBean> dataList = new ArrayList<>();
    private IndustyDyDetailAdapter commonAdapter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;
    @Inject
    IndustryDynamicsRvPresenter mIndustryDynamicsRvPresenter;

    public static void openAct(Context context) {
        context.startActivity(new Intent(context, IndustryDynamicsRvActivity.class));
    }

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
        mIndustryDynamicsRvPresenter.attachView(this);
        RvUtils.setOption_noparam(common_rv, this);
        commonAdapter = new IndustyDyDetailAdapter(this, dataList, R.layout.rvitem_industry_dynamics);
        common_rv.setAdapter(commonAdapter);
        mSmartRefreshlay.setEnableRefresh(false);
        mSmartRefreshlay.setEnableLoadmore(false);
    }

    @Override
    public String initTitleText() {
        return "行业动态";
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
        mIndustryDynamicsRvPresenter.getNewData();
    }

    @Override
    public void eventListener() {

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

    }

    @Override
    public void setNewsData(List<NewsBean> list) {
        dataList.clear();
        dataList.addAll(list);
        commonAdapter.notifyDataSetChanged();
    }

    public class IndustyDyDetailAdapter extends RVBaseAdapter<NewsBean> {

        public IndustyDyDetailAdapter(Activity context, List<NewsBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final NewsBean newsBean, int position) {
            holder.setText(R.id.dyName_tv,newsBean.getTitle());
            holder.setText(R.id.readNum_tv,newsBean.getHits()+"");
            String createDate = newsBean.getCreateDate();
            String time = DateFormatUtil.getTime(createDate, Const.YMD_HMS, Const.YMD_HM);
            holder.setText(R.id.time_tv, time);
            holder.setImageByUrl(R.id.right_iv,Const.BannerUrl+newsBean.getImageSrc());
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    String id = newsBean.getId();
                    if (StringUtil.NoNullOrEmpty(id)){
                        IntentUtils.toActivityWithUrl(DynamicActivity.class,mContext,id,"8");
                    }
                }
            });
        }
    }
}
