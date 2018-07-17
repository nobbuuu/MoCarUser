package com.dream.moka.UI.Activity.mycar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.CarConfigureResultBean;
import com.dream.moka.Bean.ChooseCarInfoBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.CarConfigContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.CarsConfigPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.maintain.OneChooseCarActivity;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.RvUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class CarConfigActivity extends BaseCommonActivity implements CarConfigContract {

    @Bind(R.id.smart_refreshlay)
    SmartRefreshLayout mSmartRefreshlay;
    private CarConfigAdapter commonAdapter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;
    @Inject
    CarsConfigPresenter carsConfigPresenter;
    private List<CarConfigureResultBean> configList = new ArrayList<>();
    private ChooseCarInfoBean mChooseCarInfoBean;
    private String mCarName;

    public static void openAct(Context context, String id, ChooseCarInfoBean chooseCarInfoBean) {
        Intent intent = new Intent(context, CarConfigActivity.class);
        intent.putExtra("data", chooseCarInfoBean);
        intent.putExtra("id", id);
        context.startActivity(intent);

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
        carsConfigPresenter.attachView(this);
        RvUtils.setOption_noparam(common_rv, this);
        commonAdapter = new CarConfigAdapter(this, configList, R.layout.rvitem_onlytext_config);
        common_rv.setAdapter(commonAdapter);
        mSmartRefreshlay.setEnableRefresh(false);
        mSmartRefreshlay.setEnableLoadmore(false);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public String initTitleText() {
        return "选配置";
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
        String parentid = getIntent().getStringExtra("id");
        mChooseCarInfoBean = (ChooseCarInfoBean) getIntent().getSerializableExtra("data");
        if (mChooseCarInfoBean!=null){
            mCarName = mChooseCarInfoBean.getCarName();
            if (parentid != null && !parentid.isEmpty()) {
                carsConfigPresenter.getCarConfig(parentid);//请求按照年份升序排序的数据
            }
        }
    }

    @Override
    public void eventListener() {

    }

//    @OnClick({R.id.back_relay})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.back_relay:
//                finish();
//                break;
//        }
//    }

    @Override
    public void showData(List<CarConfigureResultBean> results) {
        configList.clear();
        configList.addAll(results);
        commonAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    private class CarConfigAdapter extends RVBaseAdapter<CarConfigureResultBean> {

        public CarConfigAdapter(Activity context, List<CarConfigureResultBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final CarConfigureResultBean carConfigureResultBean, int position) {

            TextView city_tv = holder.getView(R.id.only_tv);
            city_tv.setText(carConfigureResultBean.getName());
            city_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mChooseCarInfoBean.setConfigId(carConfigureResultBean.getId());
                    mChooseCarInfoBean.setCarName(mCarName+carConfigureResultBean.getName());
                    String from = mChooseCarInfoBean.getFrom();
                    Log.e(Const.intentTag,"from="+from);
                    if (from.equals("one")){
                        EventBus.getDefault().post(mChooseCarInfoBean);
                        OneChooseCarActivity.openAct(mContext,"choose");
                    }else {
                        CarInfoInputActivity.openAct(mContext,mChooseCarInfoBean);
                    }
                }
            });
        }
    }
}
