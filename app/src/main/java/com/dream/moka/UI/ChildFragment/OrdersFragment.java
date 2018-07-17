package com.dream.moka.UI.ChildFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.EventBusBean.WXPaySuccessBean;
import com.dream.moka.Bean.OrderListResultBean;
import com.dream.moka.Bean.PayResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.OrdersContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Listener.onReturnClickListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Presenter.OrdersPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.AppraiseActivity;
import com.dream.moka.UI.Activity.Message.MessageSpecialActivity;
import com.dream.moka.UI.Activity.OrderDetailActivity;
import com.dream.moka.UI.Activity.OrderPayActivity;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;
import com.dream.moka.Utils.WechatPayUtils;
import com.dream.moka.Utils.alipay.AliPayUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class OrdersFragment extends BaseFragmentV4 implements OrdersContract {

    @Inject
    OrdersPresenter mOrdersPresenter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;
    @Bind(R.id.emptylayout)
    TextView mEmptylayout;
    @Bind(R.id.smart)
    SmartRefreshLayout mSmart;
    private OrdersAdapter purseAdapter;
    private List<OrderListResultBean.ListBean> dataList = new ArrayList<>();
    private String mType;
    /**
     * 是否创建
     */
    protected boolean isCreate = false;

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
    }

    @Override
    public void initView() {
        mOrdersPresenter.attachView(this);
        RvUtils.setOptionnoLine(common_rv, getActivity());
        purseAdapter = new OrdersAdapter(getActivity(), dataList, R.layout.rvitem_order);
        common_rv.setAdapter(purseAdapter);
        Bundle arguments = getArguments();
        mType = arguments.getString("type");
        mEmptylayout.setVisibility(View.GONE);
        EventBus.getDefault().register(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_commonrv;
    }

    @Override
    public void initResume() {
        refreshData();
    }

    @Override
    public void initEvents() {
        mSmart.setPrimaryColorsId(R.color.white, R.color.colorPrimary);
        mSmart.setEnableLoadmore(true);
        mSmart.setEnableRefresh(true);
        mSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshData();
            }
        });
        mSmart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mOrdersPresenter.getOrderData(mType, true);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            //相当于Fragment的onResume
            //在这里处理加载数据等操作
            refreshData();
        } else {
            //相当于Fragment的onPause
        }
    }

    private void refreshData() {
        if (mLoadingDialog!=null){
            mLoadingDialog.show();
        }
        mOrdersPresenter.getOrderData(mType, false);
    }

    @Override
    public void initDta() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showError() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        if(mSmart!=null){
            mSmart.finishLoadmore();
            mSmart.finishRefresh();
        }
    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        if (mSmart!=null){
            mSmart.finishLoadmore();
            mSmart.finishRefresh();
        }
    }

    @Override
    public void getDataSuccess(OrderListResultBean results, boolean isMore, boolean isAll) {
        List<OrderListResultBean.ListBean> list = results.getList();
        if (list.size() == 0) {
            mEmptylayout.setVisibility(View.VISIBLE);
            common_rv.setVisibility(View.GONE);
        } else {
            mEmptylayout.setVisibility(View.GONE);
            common_rv.setVisibility(View.VISIBLE);
        }
        if (isMore) {
            dataList.addAll(list);
            purseAdapter.notifyDataSetChanged();
        } else {
            if (dataList.size() != 0) {
                dataList.clear();
            }
            dataList.addAll(list);
            purseAdapter.notifyDataSetChanged();
        }
        mSmart.setLoadmoreFinished(isAll);
    }

    /**
     * 删除订单成功
     *
     * @param position
     */
    @Override
    public void deleteSuccess(int position) {
        dataList.remove(position);
        purseAdapter.notifyDataSetChanged();
    }

    /**
     * 更新订单数据
     */
    @Override
    public void orderUpdata() {
        refreshData();
    }

    //收车成功
    @Override
    public void sureGetCar() {
        CommonAction.setIsUpLoad();
        refreshData();
    }

    /**
     * 二次支付
     *
     * @param payResultBean
     */
    @Override
    public void payAgainSuccess(PayResultBean payResultBean, String type) {
        if (type.equals("0")) {//zhi
            String alipayInfo = payResultBean.getAlipayInfo();
            new AliPayUtils() {
                @Override
                public void paySuccess() {
                    refreshData();
                }
            }.alPay(alipayInfo, getActivity());
        } else if (type.equals("1")) {//wei
            PayResultBean.WxpayInfoBean wxpayInfo = payResultBean.getWxpayInfo();
            String appid = wxpayInfo.getAppid();
            String mch_id = wxpayInfo.getMch_id();
            String nonce_str = wxpayInfo.getNonce_str();
            String prepay_id = wxpayInfo.getPrepay_id();
            String sign = wxpayInfo.getSign();
            String timestamp = wxpayInfo.getTimestamp();
            new WechatPayUtils().toWechatPay(appid, mch_id, nonce_str, prepay_id, sign, timestamp);
        }

    }

    @Subscribe
    public void onEvent(WXPaySuccessBean closeConformOrder) {
        ToastUtils.Toast_short(" 支付成功");
        refreshData();
    }

    // 状态 1待支付、2待接车、3去接车、4接车中、5待服务、6服务中、7待送车、 8去送车、 9送车中 、10已完成、12服务完成（维修商）
// 11退款中、-1推单信息（APP）没点提交订单前不显示 ,99交易已关闭（退款或未支付）
    private class OrdersAdapter extends RVBaseAdapter<OrderListResultBean.ListBean> {

        public OrdersAdapter(Activity context, List<OrderListResultBean.ListBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final OrderListResultBean.ListBean listBean, final int position) {
//            final TextView refunds_tv = holder.getView(R.id.three_tv);
            TextView oneview = holder.getView(R.id.one_tv);
            TextView twoview = holder.getView(R.id.two_tv);
            TextView threeview = holder.getView(R.id.three_tv);
            TextView type = holder.getView(R.id.orderType);
            View line = holder.getView(R.id.line);
            String orderType = listBean.getOrderType();
            final String increaseType = listBean.getIncreaseType();
            String createDate = listBean.getCreateDate();
            final String orderCode = listBean.getOrderCode();
//            final String repairShopScore = listBean.getRepairShopScore();
            String repairShopUserScore = listBean.getRepairShopUserScore();
            final String payAmount = listBean.getPayAmount();
            final String status = listBean.getStatus();
            String cardNo = listBean.getCar().getCardNo();
            String carname = listBean.getCar().getCarname();
            final String id = listBean.getId();
            holder.setText(R.id.time,  StringUtil.checkNull(createDate));
            holder.setText(R.id.carNo, StringUtil.checkNull(cardNo));
            holder.setText(R.id.carName,  StringUtil.checkNull(carname));
            holder.setText(R.id.money,  StringUtil.checkNull(payAmount));
            switch (orderType) {//订单类型 0【用户】的维修（包含技师APP的维修推单）、1所有保养 、2钣喷 、3保险、 4pc总后台的维修推单（只限pc总后台）
                case "0":
                    type.setText("维修");
                    break;
                case "1":
                    type.setText("保养");
                    break;
                case "2":
                    type.setText("钣喷");
                    break;
                case "3":
                    type.setText("保险");
                    break;
                default:
                    type.setText("维修");
                    break;
            }

            String statusString;
            if (status.equals("1")) {//待支付
                statusString = "待支付";
            } else if (status.equals("10")) {//已完成
                statusString = "已完成";
            } else if (status.equals("11")) {//退款中
                statusString = "退款中";
            } else if (status.equals("99")) {//交易关闭
                statusString = "交易关闭";
            } else if (status.equals("2")) {//交易关闭
                statusString = "待接车";
            } else if (status.equals("9")){//待收车
                statusString = "待收车";
            }else {
                statusString = "服务中";
            }
            // 状态 1待支付、2待接车、3去接车、4接车中、5待服务、6服务中、7待送车、 8去送车、 9送车中 、10已完成、12服务完成（维修商）
// 11退款中、-1推单信息（APP）没点提交订单前不显示 ,99交易已关闭（退款或未支付）
            switch (status) {
                case "1":
                    setVisib(false, true, true, oneview, twoview, threeview, line);
                    setName("", "删除订单", "去支付", oneview, twoview, threeview);
                    break;
                case "2":
                    setVisib(false, false, true, oneview, twoview, threeview, line);
                    setName("", "", "申请退款", oneview, twoview, threeview);
                    break;
                case "3":
                    setVisib(false, false, false, oneview, twoview, threeview, line);
                    setName("", "", "", oneview, twoview, threeview);
                    break;
                case "4":
                    setVisib(false, false, false, oneview, twoview, threeview, line);
                    setName("", "", "", oneview, twoview, threeview);
                    break;
                case "5":
                    setVisib(false, false, false, oneview, twoview, threeview, line);
                    setName("", "", "", oneview, twoview, threeview);
                    break;
                case "6":
                    setVisib(false, false, false, oneview, twoview, threeview, line);
                    setName("", "", "", oneview, twoview, threeview);
                    break;
                case "7":
                    setVisib(false, false, false, oneview, twoview, threeview, line);
                    setName("", "", "", oneview, twoview, threeview);
                    break;
                case "8":
                    setVisib(false, false, false, oneview, twoview, threeview, line);
                    setName("", "", "", oneview, twoview, threeview);
                    break;
                case "9":
                    setVisib(false, false, true, oneview, twoview, threeview, line);
                    setName("", "", "已收到车", oneview, twoview, threeview);
                    break;
                case "10":
                    if (repairShopUserScore != null && !repairShopUserScore.equals("")) {
                        setVisib(false, false, false, oneview, twoview, threeview, line);
                        setName("", "", "", oneview, twoview, threeview);
                    } else {
                        setVisib(false, false, true, oneview, twoview, threeview, line);
                        setName("", "", "评价", oneview, twoview, threeview);
                    }

                    break;
                case "11":
                    setVisib(false, false, false, oneview, twoview, threeview, line);
                    setName("", "", "", oneview, twoview, threeview);
                    break;
                case "12":
                    setVisib(false, false, false, oneview, twoview, threeview, line);
                    setName("", "", "", oneview, twoview, threeview);
                    break;
                case "99":
                    setVisib(false, false, false, oneview, twoview, threeview, line);
                    setName("", "", "", oneview, twoview, threeview);
                    break;
            }
            if (increaseType != null && increaseType.equals("3")) {//增项订单
                type.setText("增项维修");
                setVisib(false, false, false, oneview, twoview, threeview, line);
            }

            holder.setText(R.id.status, statusString + "");

            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    if (StringUtil.NoNullOrEmpty(id)) {
                        if (increaseType != null && increaseType.equals("3")) {
                            MessageSpecialActivity.openAct(mActivity, id, "orderList");
                        } else {
                            //跳转到普通订单详情
                            OrderDetailActivity.openAct(getContext(), id);
                        }
                    }
                }
            });
            holder.setOnClickListener(R.id.one_tv, new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {//没有点击事件
//                    IntentUtils.toActivity(OrderPayActivity.class, getActivity());
                }
            });
            holder.setOnClickListener(R.id.two_tv, new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    if (status.equals("1")) {//删除订单
                        final Dialog dialog = DialogUtils.showDeleteDialog(getActivity(), new NoDoubleClickListener() {
                            @Override
                            public void onNoDoubleClick(View view) {
                                mLoadingDialog.show();
                                mOrdersPresenter.deleteOrderById(id, position);
                            }
                        });
                        dialog.show();
                    }
//                    IntentUtils.toActivity(RefundActivity.class, getActivity());
                }
            });
            holder.setOnClickListener(R.id.three_tv, new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    if (status.equals("1")) {//再支付
                        OrderPayActivity.openAct(getContext(), "again", id, payAmount);
//                        String transChannel = listBean.getTransChannel();//交渠道（0：支付宝:1：微信，2：余额）
//                        mLoadingDialog.show();
//                        mOrdersPresenter.payAgain(id, transChannel);

                    } else if (status.equals("2")) {//申请退款
                        String title;
                        if (orderCode.contains("mts")) {
                            title = "退款平台将取消赠送3张套餐优惠券";
                        } else {
                            title = "";
                        }
                        final Dialog dialog = DialogUtils.returnMoneyDialog(title, getActivity(), new onReturnClickListener() {
                            @Override
                            public void onLiftClickListener(String reason) {
                                mLoadingDialog.show();
                                mOrdersPresenter.returnMoney(id, reason);
                            }

                            @Override
                            public void onRightClickListener() {

                            }
                        });
                        dialog.show();
                    } else if (status.equals("9")) {//收车
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("温馨提示")
                                .setMessage("请确认已收到车")
                                .setCancelable(true)
                                .setNegativeButton("确认收车", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mLoadingDialog.show();
                                        mOrdersPresenter.confirmOrderById(id);

                                    }
                                }).setPositiveButton("", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

                    } else if (status.equals("10")) {//评价
                        Intent intent = new Intent(getActivity(), AppraiseActivity.class);
                        intent.putExtra("id", id);
                        getActivity().startActivity(intent);

                    }
                }
            });
        }

        /**
         * 设置按钮的文字
         *
         * @param s
         * @param s1
         * @param s2
         * @param oneview
         * @param twoview
         * @param threeview
         */
        private void setName(String s, String s1, String s2, TextView oneview, TextView twoview, TextView threeview) {
            oneview.setText(s);
            twoview.setText(s1);
            threeview.setText(s2);
        }

        /**
         * 设置按钮的显示、隐藏
         *
         * @param button1
         * @param button2
         * @param button3
         * @param one
         * @param two
         * @param three
         */
        private void setVisib(boolean button1, boolean button2, boolean button3, View one, View two, View three, View line) {
            if (button1) {
                one.setVisibility(View.VISIBLE);
            } else {
                one.setVisibility(View.GONE);
            }
            if (button2) {
                two.setVisibility(View.VISIBLE);
            } else {
                two.setVisibility(View.GONE);
            }
            if (button3) {
                three.setVisibility(View.VISIBLE);
            } else {
                three.setVisibility(View.GONE);
            }

            if (!button1 && !button2 && !button3) {
                line.setVisibility(View.GONE);
            }

        }
    }
}
