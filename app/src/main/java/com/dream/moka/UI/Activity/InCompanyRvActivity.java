package com.dream.moka.UI.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.Other.IncompanyBean;
import com.dream.moka.Bean.TestBaseBean;
import com.dream.moka.Common.RvCommonAdapter;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.InCompanyContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Presenter.InCompanyPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.DensityUtil;
import com.dream.moka.Utils.RvUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class InCompanyRvActivity extends BaseCommonActivity implements InCompanyContract.View{

    private List<IncompanyBean> dataList = new ArrayList<>();
    private InCompanyAdapter commonAdapter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;

    @Inject
    InCompanyPresenter mInCompanyPresenter;

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
        mInCompanyPresenter.attachView(this);
        RvUtils.setOption_noparam(common_rv,this);
        commonAdapter = new InCompanyAdapter(this,dataList,R.layout.rvitem_onlytext_config);
        common_rv.setAdapter(commonAdapter);
    }

    @Override
    public String initTitleText() {
        return "保险公司";
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
        mLoadingDialog.show();
        mInCompanyPresenter.getInsuranceList();
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
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();

    }

    @Override
    public void showData(List<IncompanyBean> dataBean) {
        if (dataBean!=null&&dataBean.size()>0){
            dataList.clear();
            dataList.addAll(dataBean);
            commonAdapter.notifyDataSetChanged();
        }
    }

    public class InCompanyAdapter extends RVBaseAdapter<IncompanyBean> {

        public InCompanyAdapter(Activity context, List<IncompanyBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final IncompanyBean testBaseBean, int position) {
            holder.setText(R.id.only_tv,testBaseBean.getName());
            TextView only_tv = holder.getView(R.id.only_tv);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = DensityUtil.dip2px(mContext,12);
            only_tv.setLayoutParams(params);
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {

                @Override
                public void onNoDoubleClick(View view) {
                    Intent intent = getIntent();
                    intent.putExtra("incomName",testBaseBean.getName());
                    intent.putExtra("incomId",testBaseBean.getId());
                    setResult(111,intent);
                    finish();
                }
            });
        }
    }

}
