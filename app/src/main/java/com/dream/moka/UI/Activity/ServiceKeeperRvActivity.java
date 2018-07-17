package com.dream.moka.UI.Activity;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.Online.OnlinerBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.ButlerServiceContract;
import com.dream.moka.CumstomView.EmptyView;
import com.dream.moka.IM.im.activity.ChatActivity;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.ButlerSevicerPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.RvUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class ServiceKeeperRvActivity extends BaseCommonActivity implements ButlerServiceContract.View{

    private List<OnlinerBean> dataList = new ArrayList<>();
    private KeeperAdapter commonAdapter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;

    @Inject
    ButlerSevicerPresenter mButlerSevicerPresenter;

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
        mButlerSevicerPresenter.attachView(this);
        RvUtils.setOptionnoLine(common_rv,this);
        commonAdapter = new KeeperAdapter(R.layout.rvitem_servicehousekeeper,dataList);
        commonAdapter.setEmptyView(new EmptyView(mContext,"您还没有服务管家，快去下单吧~~"));
        common_rv.setAdapter(commonAdapter);
    }

    @Override
    public String initTitleText() {
        return "服务管家";
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
        Map<String, String> map = MapUtils.getSingleMap();
        map.put("token", CommonAction.getToken());
        mLoadingDialog.show();
        mButlerSevicerPresenter.getJSDetail(map);
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_relay:
                finish();
                break;
        }
    }

    @Override
    public void showData(List<OnlinerBean> dataBean) {
        dataList.clear();
        dataList.addAll(dataBean);
        commonAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }

    private class KeeperAdapter extends BaseQuickAdapter<OnlinerBean, BaseViewHolder> {

        public KeeperAdapter(@LayoutRes int layoutResId, @Nullable List<OnlinerBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final OnlinerBean item) {

            helper.setText(R.id.serviceName_Tv,"服务管家"+item.getCode());
            String createDate = item.getCreateDate();
            String time = DateFormatUtil.getTime(createDate, Const.YMD_HMS, Const.YMD_HM);
            helper.setText(R.id.date_Tv, time);
            ImageView keeper_iv = helper.getView(R.id.keeper_iv);
            GlidUtils.LoadCircleImg(mContext.getApplicationContext(),Const.BannerUrl+item.getPhoto(),keeper_iv,R.mipmap.img_avatar01);
            helper.getConvertView().setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    ChatActivity.navToChat(mContext,item);
                }
            });
        }
    }
}
