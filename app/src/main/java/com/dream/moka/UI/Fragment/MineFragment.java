package com.dream.moka.UI.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Bean.DriverHomeResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.DriverCenterMainContract;
import com.dream.moka.Contract.MineContract;
import com.dream.moka.Listener.ListeningScrollView;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.DriverCenterMainPresenter;
import com.dream.moka.Presenter.MinePresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.drivercenter.AuditActivity;
import com.dream.moka.UI.Activity.drivercenter.DriverCenterActivity;
import com.dream.moka.UI.Activity.login_register.LoginActivity;
import com.dream.moka.UI.Activity.Message.MessageRvActivity;
import com.dream.moka.UI.Activity.MyCardsActivity;
import com.dream.moka.UI.Activity.OrderManageActivity;
import com.dream.moka.UI.Activity.PurseJifenActivity;
import com.dream.moka.UI.Activity.ServiceKeeperRvActivity;
import com.dream.moka.UI.Activity.set.SetActivity;
import com.dream.moka.UI.Activity.UserInfoActivity;
import com.dream.moka.UI.Activity.mycar.MyCarsActivity;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.IMUtil.IMAcountUtils;
import com.dream.moka.Utils.ImmUtils;
import com.dream.moka.Utils.IntentUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
public class MineFragment extends BaseFragmentV4 implements MineContract,DriverCenterMainContract {

    @Bind(R.id.mine_sv)
    ListeningScrollView mine_sv;
    @Bind(R.id.titlebar)
    RelativeLayout titlebar;
    @Bind(R.id.head_icon)
    ImageView head_icon;
    @Bind(R.id.login_tv)
    TextView mLoginTv;
    @Bind(R.id.userinfo_relay)
    LinearLayout mUserinfoRelay;
    @Bind(R.id.purse_tv)
    TextView mPurseTv;
    @Bind(R.id.coupons_tv)
    TextView mCouponsTv;
    @Bind(R.id.jifen_tv)
    TextView mJifenTv;
    @Bind(R.id.right_iv)
    ImageView mRightIv;
    @Bind(R.id.allorder_lay)
    RelativeLayout mAllorderLay;
    @Bind(R.id.wait_pay)
    LinearLayout mWaitPay;
    @Bind(R.id.servicing_lay)
    LinearLayout mServicingLay;
    @Bind(R.id.wait_appraise)
    LinearLayout mWaitAppraise;
    @Bind(R.id.mycar_relay)
    RelativeLayout mMycarRelay;
    @Bind(R.id.service_keeper)
    RelativeLayout mServiceKeeper;
    @Bind(R.id.drivercenter_relay)
    RelativeLayout mDrivercenterRelay;
    @Bind(R.id.evalue_relay)
    RelativeLayout mEvalueRelay;
    @Bind(R.id.message_relay)
    RelativeLayout mMessageRelay;
    @Bind(R.id.set_tv)
    TextView mSetTv;
    @Bind(R.id.root_lay)
    RelativeLayout mRootLay;
    @Bind(R.id.user_layout)
    LinearLayout mUserLayout;
    @Bind(R.id.nickName)
    TextView mNickName;
    @Bind(R.id.carname)
    TextView mCarname;
    @Bind(R.id.haveOrder_tv)
    TextView haveOrder_tv;
    @Inject
    MinePresenter mMinePresenter;
    @Bind(R.id.drivercenter_line)
    RelativeLayout mDrivercenterLine;

    @Inject
    DriverCenterMainPresenter mDriverCenterMainPresenter;

    private Dialog mLoadingDialog;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initResume() {
        String userHeadUrl = CommonAction.getUserHeadUrl();
        if (CommonAction.getIsLogin()) {
            mLoginTv.setVisibility(View.GONE);
            mUserLayout.setVisibility(View.VISIBLE);
            mNickName.setText(CommonAction.getUserName());
            mCarname.setText(CommonAction.getUserCarName());
            if (userHeadUrl.contains("MoCar")){
                GlidUtils.LoadCircleImg(getContext().getApplicationContext(), Const.BannerUrl+userHeadUrl,head_icon,R.mipmap.img_defaultavatar);
            }else {
                GlidUtils.LoadCircleImg(getContext().getApplicationContext(), userHeadUrl,head_icon,R.mipmap.img_defaultavatar);
            }
            mMinePresenter.getUserData();
        } else {
            loginOut(userHeadUrl);
        }

    }

    private void loginOut(String userHeadUrl) {
        mLoginTv.setVisibility(View.VISIBLE);
        mUserLayout.setVisibility(View.GONE);
        mPurseTv.setText("0元" + "\n" + "钱包");
        mJifenTv.setText("0分" + "\n" + "积分");
        GlidUtils.LoadCircleImg(getActivity(), Const.BannerUrl + userHeadUrl, head_icon, R.mipmap.img_defaultavatar);
    }

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public void initView() {
        mMinePresenter.attachView(this);
        mDriverCenterMainPresenter.attachView(this);
        EventBus.getDefault().register(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(getActivity());
    }

    @Subscribe
    public void onEvent(String eventStr) {
        if (eventStr.equals("loginout")){
            loginOut(CommonAction.getUserHeadUrl());
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    @Override
    public void getUserDataSuccess(String userType) {
        if (userType.equals("2")) {
            mDrivercenterLine.setVisibility(View.VISIBLE);
            mDrivercenterRelay.setVisibility(View.VISIBLE);
            mDriverCenterMainPresenter.getData();
        } else {
            mDrivercenterLine.setVisibility(View.GONE);
            mDrivercenterRelay.setVisibility(View.GONE);
        }
        mCarname.setText(CommonAction.getUserCarName());
        String userBalance = CommonAction.getUserBalance();
        mPurseTv.setText((userBalance.equals("") ? "0" : userBalance) + "元" + "\n" + "钱包");
        mJifenTv.setText(CommonAction.getUserScore() + "分" + "\n" + "积分");
        mCouponsTv.setText(CommonAction.getUserCouponNum() + "张" + "\n" + "优惠券");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initEvents() {

      /*  mine_sv.setScrollViewListener(new ListeningScrollView.ScrollListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy, int computeVerticalScrollRange) {
                int tempy = oldy - y;
                ViewGroup.LayoutParams layoutParams = titlebar.getLayoutParams();
                if (tempy < 0) {
                    layoutParams.height = Math.abs(tempy);
                    titlebar.getBackground().mutate().setAlpha(10);
                } else {
                    titlebar.getBackground().mutate().setAlpha(255);
                }
            }
        });*/
    }

    @Override
    public void initDta() {

    }

    @OnClick({R.id.head_icon, R.id.drivercenter_relay, R.id.purse_tv, R.id.message_relay, R.id.service_keeper,
            R.id.evalue_relay, R.id.coupons_tv, R.id.jifen_tv, R.id.set_tv, R.id.mycar_relay, R.id.allorder_lay,
            R.id.userinfo_relay, R.id.wait_pay, R.id.wait_appraise, R.id.servicing_lay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_icon://头像
                if (CommonAction.getIsLogin()) {
                    UserInfoActivity.openAct(getActivity());
                } else {
                    LoginActivity.openAct(getActivity());
                }
                break;
            case R.id.purse_tv://钱包
                if (CommonAction.getIsLogin()) {
                    toPurseJifen("purse");
                } else {
                    IntentUtils.toActivity(LoginActivity.class, getActivity());
                }
                break;
            case R.id.coupons_tv://优惠券
                if (CommonAction.getIsLogin()) {
                    MyCardsActivity.openAct(getActivity());
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }

                break;
            case R.id.jifen_tv://积分
                if (CommonAction.getIsLogin()) {
                    toPurseJifen("jifen");
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }

                break;
            case R.id.set_tv://设置
                if (CommonAction.getIsLogin()) {
                    startActivity(new Intent(getActivity(), SetActivity.class));
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }
                break;
            case R.id.mycar_relay://我的爱车
                if (CommonAction.getIsLogin()) {
                    MyCarsActivity.openAct(getActivity(), "mine");
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }

                break;
            case R.id.allorder_lay://我的订单
                if (CommonAction.getIsLogin()) {
                    OrderManageActivity.openAct(getActivity(), 0, "");
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }

                break;
            case R.id.wait_appraise://待评价
                if (CommonAction.getIsLogin()) {
                    OrderManageActivity.openAct(getActivity(), 3, "");
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }

                break;
            case R.id.servicing_lay://服务中
                if (CommonAction.getIsLogin()) {
                    OrderManageActivity.openAct(getActivity(), 2, "");
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }

                break;
            case R.id.wait_pay://待支付
                if (CommonAction.getIsLogin()) {
                    OrderManageActivity.openAct(getActivity(), 1, "");
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }

                break;
            case R.id.userinfo_relay:
                if (CommonAction.getIsLogin()) {
                    UserInfoActivity.openAct(getActivity());
                } else {
                    LoginActivity.openAct(getActivity());
                }
                break;
            case R.id.service_keeper://服务管家
                if (CommonAction.getIsLogin()) {
                    IntentUtils.toActivity(ServiceKeeperRvActivity.class, getActivity());
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }

                break;
            case R.id.evalue_relay://待评价
                if (CommonAction.getIsLogin()) {
                    OrderManageActivity.openAct(getActivity(), 3, "");
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }

                break;
            case R.id.message_relay:
                if (CommonAction.getIsLogin()) {
                    IntentUtils.toActivityWithTag(MessageRvActivity.class, getActivity(), "user");
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }

                break;
            case R.id.drivercenter_relay:
                if (CommonAction.getIsLogin()) {
                    mLoadingDialog.show();
                    mMinePresenter.getData();
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }
                break;
        }
    }

    private void toPurseJifen(String intentTag) {
        IntentUtils.toActivityWithTag(PurseJifenActivity.class, getActivity(), intentTag);
    }

    @Override
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }

    //-1：初审中 -2 ：已驳回 -3：终审中 1：通过已支付  2：通过未支付
    @Override
    public void getDriverInfoSuccess(String status) {
        switch (status) {
            case "1":
                startActivity(new Intent(getActivity(), DriverCenterActivity.class));
//                IntentUtils.toActivity(DriverCenterActivity.class, getActivity());
                break;
            case "2":
                startActivity(new Intent(getActivity(), DriverCenterActivity.class));
//                IntentUtils.toActivity(DriverCenterActivity.class, getActivity());
                break;
            default:
                AuditActivity.openAct(getActivity(), "mine", status);
                break;
        }
    }

    @Override
    public void getYaJinData(String s) {

    }

    @Override
    public void getHomeData(DriverHomeResultBean driverHomeResultBean) {
        List<DriverHomeResultBean.ListDJCBean> listDJC = driverHomeResultBean.getListDJC();
        List<DriverHomeResultBean.ListDSCBean> listDSC = driverHomeResultBean.getListDSC();
        List<DriverHomeResultBean.ListQJCBean> listQJC = driverHomeResultBean.getListQJC();
        List<DriverHomeResultBean.ListQSCBean> listQSC = driverHomeResultBean.getListQSC();
        if ((listDJC!=null&&listDJC.size()>0)||(listDSC!=null&&listDSC.size()>0)||(listQJC!=null&&listQJC.size()>0)||(listQSC!=null&&listQSC.size()>0)){
            haveOrder_tv.setVisibility(View.VISIBLE);
        }else {
            haveOrder_tv.setVisibility(View.GONE);
        }
    }

    @Override
    public void changeOrderStaus() {

    }
}
