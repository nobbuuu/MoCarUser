package com.dream.moka.UI.Activity.drivercenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.DriverDataInfoResultBean;
import com.dream.moka.Bean.TodayDataResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.DataStaticsContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.DataStaticsPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.RvUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class DataStaticsActivity extends BaseCommonActivity implements DataStaticsContract {

    @Inject
    DataStaticsPresenter mDataStaticsPresenter;
    @Bind(R.id.allMoney)
    TextView mAllMoney;
    @Bind(R.id.orderMoney)
    TextView mOrderMoney;
    @Bind(R.id.yongjinMoney)
    TextView mYongjinMoney;
    @Bind(R.id.cal_iv)
    ImageView mCalIv;
    @Bind(R.id.time)
    TextView mTime;
    private Dialog mLoadingDialog;
    private TimePickerView dateDialog;

    public static void openAct(Context context) {
        context.startActivity(new Intent(context, DataStaticsActivity.class));
    }

    @Bind(R.id.common_rv)
    RecyclerView common_rv;
    private List<TodayDataResultBean> dataList = new ArrayList<>();
    private DataAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_data_statistics;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {
        DaggerBaseComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
//                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
        mDataStaticsPresenter.attachView(this);
        RvUtils.setOption(common_rv, this);
        adapter = new DataAdapter(this, dataList, R.layout.rvitem_data_statics);
        common_rv.setAdapter(adapter);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        dateDialog = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                long time = date.getTime();
                String dateStr = DateFormatUtil.getTime(time, Const.Y_M);
                if (dateStr != null) {
                    mTime.setText(dateStr);
                    mDataStaticsPresenter.getTodayData(dateStr);
                }
                Log.e("tag", "dateStr=" + dateStr);
            }
        }).setType(new boolean[]{true, true, false, false, false, false})
                .setContentSize(16)//滚轮文字大小
                .setTitleSize(13)//标题文字大小
                .setCancelText("取消")//取消按钮文字
                .setLabel(" 年", "月", "日", "时", "分", "秒")
                .isCyclic(true)//是否循环滚动
                .build();


    }

    @Override
    public String initTitleText() {
        return "数据统计";
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
        mLoadingDialog.show();
        mDataStaticsPresenter.getData();
    }

    @Override
    public void initDatas() {
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
    public void getDataSucces(DriverDataInfoResultBean results) {
        String allIncome = results.getAllIncome();
        String orderIncome = results.getOrderIncome();
        String rebateIncome = results.getRebateIncome();

        mAllMoney.setText(allIncome == null ? "0" : allIncome);
        mOrderMoney.setText(orderIncome == null ? "0" : orderIncome);
        mYongjinMoney.setText(rebateIncome == null ? "0" : rebateIncome);

    }

    /**
     * 获取每月的数据
     */
    @Override
    public void getToDayData(TodayDataResultBean todayDataResultBean) {


    }


    @OnClick(R.id.cal_iv)
    public void onViewClicked() {
    }

    public class DataAdapter extends RVBaseAdapter<TodayDataResultBean> {
        public DataAdapter(Activity context, List<TodayDataResultBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, TodayDataResultBean todayDataResultBean, int position) {
            String income = todayDataResultBean.getIncome();
            String orderTime = todayDataResultBean.getOrderTime();
            holder.setText(R.id.money,income==null?"":income);
            holder.setText(R.id.time,orderTime==null?"":orderTime);
        }
    }
}
