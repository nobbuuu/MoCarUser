package com.dream.moka.UI.Activity.drivercenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.BankResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.BankRvContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.BankRvPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.RvUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class BanksRvActivity extends BaseCommonActivity implements BankRvContract {

    private List<BankResultBean> dataList = new ArrayList<>();
    private BanksAdapter commonAdapter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;
    @Inject
    BankRvPresenter mBankRvPresenter;
    private Dialog mLoadingDialog;

    public static void openAct(Activity activity) {
        Intent intent = new Intent(activity, BanksRvActivity.class);
        activity.startActivityForResult(intent, 0x20);
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
        mBankRvPresenter.attachView(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        RvUtils.setOptionnoLine(common_rv, this);
        commonAdapter = new BanksAdapter(this, dataList, R.layout.rvitem_bank);
        common_rv.setAdapter(commonAdapter);
    }

    @Override
    public String initTitleText() {
        return "开户行";
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
        mBankRvPresenter.getBanksData();
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                setResult(100);
                finish();
                break;
        }
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
    public void onSuccessData(List<BankResultBean> results) {
        if (results != null && results.size() > 0) {
            if (dataList.size() != 0) {
                dataList.clear();
            }
            dataList.addAll(results);
            commonAdapter.notifyDataSetChanged();
        }
    }

    public class BanksAdapter extends RVBaseAdapter<BankResultBean> {

        public BanksAdapter(Activity context, List<BankResultBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, BankResultBean bankResultBean, int position) {
            String logo = bankResultBean.getLogo();
            final String name = bankResultBean.getName();
            final String id = bankResultBean.getId();
            ImageView icon = holder.getView(R.id.logo);
            TextView backname = holder.getView(R.id.name);
            GlidUtils.LoadCircleImg(mContext, Const.BannerUrl + logo, icon, R.mipmap.img_loading_logo);
            backname.setText(name);
            holder.getView(R.id.item_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent data = new Intent();
                    data.putExtra("name",name);
                    data.putExtra("id",id);
                    setResult(0x21, data);
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        setResult(100);
        finish();
    }
}
