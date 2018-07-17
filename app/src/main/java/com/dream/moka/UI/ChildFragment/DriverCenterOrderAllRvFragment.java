package com.dream.moka.UI.ChildFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Base.MultiLayoutsBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.AllOrderResultBean;
import com.dream.moka.Bean.EventBusBean.ChooseOrderBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.DriverCenterOrderAllContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Presenter.DriverCenterOrderAllPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.DriverOrderDetail2Activity;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class DriverCenterOrderAllRvFragment extends BaseFragmentV4 implements DriverCenterOrderAllContract {

    @Inject
    DriverCenterOrderAllPresenter mDriverCenterOrderAllPresenter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;
    @Bind(R.id.emptylayout)
    TextView mEmptylayout;
    @Bind(R.id.smart)
    SmartRefreshLayout mSmart;
    private AllOrderAdapter orderAdapter;
    private List<AllOrderResultBean.ItemsBean> orderList = new ArrayList<>();
    protected boolean isCreate = false;
    private String chooseStatus = "";
    private String chooseOrderId = "";
    private String chooseStartTime = "";
    private String chooseEndTime = "";

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
        mDriverCenterOrderAllPresenter.attachView(this);
        RvUtils.setOptionnoLine(common_rv, getActivity());
        orderAdapter = new AllOrderAdapter(getActivity(), orderList, new int[]{R.layout.rvitem_drivercenter_all});
        common_rv.setAdapter(orderAdapter);
        common_rv.setVisibility(View.VISIBLE);
        mEmptylayout.setVisibility(View.GONE);
        mSmart.setPrimaryColorsId(R.color.white, R.color.colorPrimary);//全局设置主题颜色
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ChooseOrderBean chooseOrderBean) {
        String status = chooseOrderBean.getStatus();
        String beginDate = chooseOrderBean.getBeginDate();
        String endDate = chooseOrderBean.getEndDate();
        String orderCode = chooseOrderBean.getOrderCode();
        mDriverCenterOrderAllPresenter.getAllOrderData(status, orderCode, beginDate, endDate, false);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            //相当于Fragment的onResume
            //在这里处理加载数据等操作
            mLoadingDialog.show();
            mDriverCenterOrderAllPresenter.getAllOrderData(chooseStatus, chooseOrderId, chooseStartTime, chooseEndTime, false);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_commonrv;
    }

    @Override
    public void initResume() {

    }

    @Override
    public void initEvents() {
        mSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mDriverCenterOrderAllPresenter.getAllOrderData(chooseStatus, chooseOrderId, chooseStartTime, chooseEndTime, false);
            }
        });
        mSmart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mDriverCenterOrderAllPresenter.getAllOrderData(chooseStatus, chooseOrderId, chooseStartTime, chooseEndTime, true);
            }
        });
    }

    @Override
    public void initDta() {
    }

    @Override
    public void showError() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        mSmart.finishRefresh();
        mSmart.finishLoadmore();

    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        mSmart.finishRefresh();
        mSmart.finishLoadmore();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void getDataSuccess(List<AllOrderResultBean.ItemsBean> items, boolean isMore) {
        if (isMore) {
            orderList.addAll(items);
        } else {
            if (orderList.size() > 0) {
                orderList.clear();
            }
            orderList.addAll(items);
        }
        if (orderList.size() > 0) {
            mEmptylayout.setVisibility(View.GONE);
            common_rv.setVisibility(View.VISIBLE);
        } else {
            mEmptylayout.setVisibility(View.VISIBLE);
            common_rv.setVisibility(View.GONE);
        }
        orderAdapter.notifyDataSetChanged();

    }

    @Override
    public void isAll(boolean isAll) {
        mSmart.setLoadmoreFinished(isAll);
    }

    private class AllOrderAdapter extends MultiLayoutsBaseAdapter<AllOrderResultBean.ItemsBean> {


        public AllOrderAdapter(Activity context, List<AllOrderResultBean.ItemsBean> data, int[] layoutIds) {
            super(context, data, layoutIds);
        }

        @Override
        public int getItemType(int position) {
            return 0;
        }

        // 待接车：2,7  接送中：3,4,8,9  已完成：5,6,10,12     已取消：11,99
        @Override
        public void onBinds(RVBaseHolder holder, AllOrderResultBean.ItemsBean itemsBean, int position, int itemType) {

            String status = itemsBean.getStatus();
            final String driverStatus = itemsBean.getDriverStatus();
            final String id = itemsBean.getId();

            if (status.equals("11") || status.equals("99")) {
                holder.setText(R.id.status, "已取消");
            } else {
                if (driverStatus.equals("0")) {//接车单
                    if (!status.equals("") && Double.valueOf(status) >= 5) {
                        holder.setText(R.id.status, "已完成");
                    } else {
                        if (status.equals("2")) {
                            holder.setText(R.id.status, "待接车");
                        } else if (status.equals("3") || status.equals("4")) {
                            holder.setText(R.id.status, "接送中");
                        }
                    }

                } else {//送车单
                    if (status.equals("2") || status.equals("7")) {
                        holder.setText(R.id.status, "待接车");
                    } else if (status.equals("3") || status.equals("4") || status.equals("8") || status.equals("9")) {
                        holder.setText(R.id.status, "接送中");
                    } else if (status.equals("5") || status.equals("6") || status.equals("10") || status.equals("12")) {
                        holder.setText(R.id.status, "已完成");
                    } else if (status.equals("11") || status.equals("99")) {
                        holder.setText(R.id.status, "已取消");
                    }
                }

            }

            String orderCode = itemsBean.getOrderCode();
            String cardNo = itemsBean.getCar().getCardNo();
            String carname = itemsBean.getCar().getCarname();
            holder.setText(R.id.carNo, "" + cardNo);
            holder.setText(R.id.carname, "" + carname);
            String scSendTime = itemsBean.getScSendTime();
            String jcSendTime = itemsBean.getJcSendTime();
            Log.e("tag","scSendTime"+scSendTime);
            Log.e("tag","jcSendTime"+jcSendTime);
            if (StringUtil.NoNullOrEmpty(status)) {
                if (Integer.valueOf(status) >= 7) {
                    holder.setText(R.id.time, "" + scSendTime);
                } else {
                    holder.setText(R.id.time, "" + jcSendTime);
                }
            }
            holder.setText(R.id.orderNo, "" + orderCode);
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    Intent intent = new Intent(getContext(), DriverOrderDetail2Activity.class);
                    intent.putExtra("orderId", id);
                    intent.putExtra("driverStatus", driverStatus);
                    startActivity(intent);
                }
            });
        }
    }
}
