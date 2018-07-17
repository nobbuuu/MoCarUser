package com.dream.moka.UI.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dream.moka.Adapter.Main.HomeGvAdapter;
import com.dream.moka.Adapter.Main.HothdAdapter;
import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Bean.BannerBean;
import com.dream.moka.Bean.EventBusBean.LoginBus;
import com.dream.moka.Bean.EventBusBean.RefreshBean;
import com.dream.moka.Bean.EventBusBean.RefreshOrderBean;
import com.dream.moka.Bean.HomeGvBean;
import com.dream.moka.Bean.HotActivityBean;
import com.dream.moka.Bean.Message.MessageListBean;
import com.dream.moka.Bean.NewsBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.HomeFragmentContract;
import com.dream.moka.Contract.Message.MessageListContract;
import com.dream.moka.CumstomView.AutoVerticalScrollTextView;
import com.dream.moka.CumstomView.RvScrollView;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.HomeFragmentPresenter;
import com.dream.moka.Presenter.Message.MessageListPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.BeDriverWebActivity;
import com.dream.moka.UI.Activity.CityChooseActivity;
import com.dream.moka.UI.Activity.CommonQuestionActivity;
import com.dream.moka.UI.Activity.HuodongRvActivity;
import com.dream.moka.UI.Activity.IndustryDynamicsRvActivity;
import com.dream.moka.UI.Activity.Message.MessageRvActivity;
import com.dream.moka.UI.Activity.TuoCarActivity;
import com.dream.moka.UI.Activity.WebActivity;
import com.dream.moka.UI.Activity.drivercenter.AuditActivity;
import com.dream.moka.UI.Activity.drivercenter.DriverCenterActivity;
import com.dream.moka.UI.Activity.login_register.LoginActivity;
import com.dream.moka.UI.Activity.maintain.FreeMaitainActivity;
import com.dream.moka.UI.Activity.maintain.OneChooseCarActivity;
import com.dream.moka.Utils.DensityUtil;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.GlideImageLoader;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;
import com.kevin.wraprecyclerview.WrapRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/26 0026.
 */
public class HomeFragment extends BaseFragmentV4 implements HomeFragmentContract,MessageListContract.View{

    @Bind(R.id.home_gv)
    GridView home_gv;
    @Bind(R.id.anchor_svtv)
    AutoVerticalScrollTextView scrollTextView;
    @Bind(R.id.city_tv)
    TextView city_tv;
    @Bind(R.id.hothd_rv)
    RecyclerView hothd_rv;
    @Bind(R.id.banner_view)
    Banner mBannerView;
    @Bind(R.id.next_iv)
    ImageView mNextIv;
    @Bind(R.id.city_lay)
    LinearLayout mCityLay;
    @Bind(R.id.message_iv)
    RelativeLayout mMessageIv;
    @Bind(R.id.root_lay)
    RelativeLayout mRootLay;
    @Bind(R.id.temp_onemaintaiLay)
    RelativeLayout temp_onemaintaiLay;
    @Bind(R.id.topRelay)
    RelativeLayout topRelay;
    @Bind(R.id.main_scrollview)
    RvScrollView main_scrollview;

    @Inject
    HomeFragmentPresenter mHomeFragmentPresenter;
    @Inject
    MessageListPresenter mMessageListPresenter;

    @Bind(R.id.dynamic_relay)
    RelativeLayout mDynamicRelay;
    @Bind(R.id.haveMsg_v)
    View mHaveMsgV;

    private int driverIndex = 5;
    private HomeGvAdapter homeGvAdapter;
    private HothdAdapter hothdAdapter;
    private int[] imgArray = new int[]{R.mipmap.home_icon_yuankm, R.mipmap.home_icon_self_maintenance, R.mipmap.home_icon_service, R.mipmap.home_icon_painting,
            R.mipmap.home_icon_rescue, R.mipmap.home_icon_driver, R.mipmap.home_icon_activity, R.mipmap.home_icon_problem};
    //    private int[] imgArray = new int[]{R.mipmap.home_icon_yuankm, R.mipmap.home_icon_selfmaintenance__, R.mipmap.home_icon_service__,
//            R.mipmap.home_icon_painting__, R.mipmap.home_icon_rescue__, R.mipmap.home_icon_driver, R.mipmap.home_icon_activity__, R.mipmap.home_icon_problem__};
//    private int[] imgArray = new int[]{R.mipmap.home_icon_yuankm, R.mipmap.home_icon_self_maintenance, R.mipmap.home_icon_activity, R.mipmap.home_icon_problem};
    private String[] strArray = new String[]{"0.1元/km保养", "自选养车", "维修", "钣喷", "救援服务", "司机招募", "全部活动", "常见问题"};

    //    private String[] strArray = new String[]{"0.1元/KM保养", "自选保养", "全部活动", "常见问题"};
    private List<HomeGvBean> dataList = new ArrayList<>();
    private List<NewsBean> noticeBeans = new ArrayList<>();
    private List<HotActivityBean> hothdList = new ArrayList<>();

    private boolean refreshVertival = true;
    private int number;
    private boolean isBannerLoaded = false;
    private boolean isNewsLoaded = false;
    private boolean isHotLoaded = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (noticeBeans.size() >= 1) {
                scrollTextView.setData(noticeBeans.get(0));
                if (refreshVertival) {
                    mHandler.post(mVerticalTextviewRunnable);
                    refreshVertival = false;
                }
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public void initView() {
        mHomeFragmentPresenter.attachView(this);
        mMessageListPresenter.attachView(this);
        homeGvAdapter = new HomeGvAdapter(getActivity(), dataList, R.layout.view_imgtext);
        home_gv.setAdapter(homeGvAdapter);
        RvUtils.setOption_noparam(hothd_rv, getActivity());
        hothdAdapter = new HothdAdapter(getActivity(), hothdList, R.layout.rvitem_hot);
        hothd_rv.setAdapter(hothdAdapter);
        mLoadingDialog = DialogUtils.initLoadingDialog(getActivity());

        temp_onemaintaiLay.setVisibility(View.GONE);
        home_gv.setVisibility(View.VISIBLE);
       /* if (Const.isTest) {
        } else {
            temp_onemaintaiLay.setVisibility(View.VISIBLE);
            home_gv.setVisibility(View.GONE);
        }*/
        EventBus.getDefault().register(this);
    }


    @Override
    public void initResume() {
        if (CommonAction.getIsLogin()){
            mMessageListPresenter.getMyMessageList(CommonAction.getToken(), MapUtils.getMap());
        }
    }

    private Dialog mLoadingDialog;

    @Override
    public void initEvents() {
        home_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                switch (i) {
                    case 0://0.1/km保养
//                        ToastUtils.Toast_short("开发中，敬请期待");
                        OneChooseCarActivity.openAct(getActivity(), "home");
                        break;
                    case 1://自选保养
                        if (CommonAction.getIsLogin()) {
                            IntentUtils.toActivity(FreeMaitainActivity.class, getActivity());
                        } else {
                            DialogUtils.getLoginTip(getActivity()).show();
                        }
                        break;
                    case 2://维修
                        EventBus.getDefault().post(new RefreshBean(Const.refresh));
//                        IntentUtils.toActivity(HuodongRvActivity.class, getActivity());
                        break;
                    case 3://钣喷
//                        IntentUtils.toActivity(BanPenActivity.class, getActivity());
//                        IntentUtils.toActivity(CommonQuestionActivity.class, getActivity());
                        if (CommonAction.getIsLogin()) {
                            String url = Const.API_BASE_URL + "index/applySpray?" + Const.token + "=" + CommonAction.getToken() + "&" + Const.longitude + "=" + CommonAction.getLongitude()
                                    + "&" + Const.latitude + "=" + CommonAction.getLatitude();
                            IntentUtils.toActivityWithUrl(WebActivity.class, getActivity(), url, "钣金喷漆");
                        } else {
                            DialogUtils.getLoginTip(getActivity()).show();
                        }
                        break;
                    case 4:
                        IntentUtils.toActivity(TuoCarActivity.class, getActivity());
                        break;
                    case 5:
                        if (!CommonAction.getIsLogin()) {
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        } else {
                            mLoadingDialog.show();
                            mHomeFragmentPresenter.getData();
                        }
//                        IntentUtils.toActivityWithTag(WebViewActivity.class, getActivity(), "申请成为司机");
                        break;
                    case 6:
//                        ToastUtils.Toast_short("开发中，敬请期待");
                        IntentUtils.toActivity(HuodongRvActivity.class, getActivity());
                        break;
                    case 7:
//                        ToastUtils.Toast_short("开发中，敬请期待");
                        IntentUtils.toActivity(CommonQuestionActivity.class, getActivity());
                        break;
                }
            }
        });

        main_scrollview.setOnScrollListener(new RvScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int bannerH = DensityUtil.dip2px(getContext(), 172);
                int toplayH = DensityUtil.dip2px(getContext(), 40);
                int tempH = bannerH - toplayH;
                //                Log.e("tag","scrollY="+scrollY);
                //                Log.e("tag","oldScrollY="+oldScrollY);
                //                Log.e("tag","scrolledH="+scrolledH);
                if (scrollY==0||oldScrollY==0){
                    topRelay.setVisibility(View.VISIBLE);
                }else {
                    topRelay.setVisibility(View.GONE);
                }
            }
        });

    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser){
//            if (!isBannerLoaded) {
//                mHomeFragmentPresenter.getBannerData();
//            }
//            if (!isNewsLoaded) {
//                mHomeFragmentPresenter.getNewData();
//            }
//            if (!isHotLoaded) {
//                mHomeFragmentPresenter.getHotData();
//            }
//        }
//    }

    @Override
    public void initDta() {
        mHomeFragmentPresenter.getBannerData();
        mHomeFragmentPresenter.getNewData();
        mHomeFragmentPresenter.getHotData();
        if (CommonAction.getIsLogin()&&strArray.length>driverIndex){
            String userType = CommonAction.getUserType();
            if (!userType.equals("2")){
                strArray[driverIndex] = "司机招募";
            }else {
                strArray[driverIndex] = "司机中心";
            }
        }
        refreshHomeItem();
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
    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * 设置banner
     *
     * @param list
     */
    @Override
    public void setBannerInfo(final List<BannerBean> list) {
        List<String> images = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String pic = list.get(i).getPic();
            images.add(Const.BannerUrl + pic);
        }
        //设置banner样式
        mBannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        GlideImageLoader imageLoader = new GlideImageLoader();
        mBannerView.setImageLoader(imageLoader);
        //设置图片集合
        mBannerView.setImages(images);
        //设置banner动画效果
        mBannerView.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBannerView.isAutoPlay(true);
        //设置轮播时间
        mBannerView.setDelayTime(4000);
        //设置指示器位置（当banner模式中有指示器时）
        mBannerView.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBannerView.start();
        mBannerView.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String linkAddr = list.get(position).getLinkAddr();
                if (StringUtil.NoNullOrEmpty(linkAddr)) {
                    IntentUtils.toActivityWithUrl(WebActivity.class, getActivity(), linkAddr, "");
                }
            }
        });
        isBannerLoaded = true;
    }

    /**
     * 展示新闻
     *
     * @param list
     */
    @Override
    public void setNewsInfo(List<NewsBean> list) {
        noticeBeans.clear();
        noticeBeans.addAll(list);
        //通知加载文字上下滚动条目
        Message scmsg = Message.obtain();
        mHandler.sendMessage(scmsg);
        isNewsLoaded = true;
    }

    /**
     * 展示热门活动
     *
     * @param list
     */
    @Override
    public void setHotInfo(List<HotActivityBean> list) {
        hothdList.clear();
        hothdList.addAll(list);
        hothdAdapter.notifyDataSetChanged();
        isHotLoaded = true;
    }

    @Override
    public void driverSuccess(String status) {
        String userType = CommonAction.getUserType();
        if (!userType.equals("2")) {
            Intent intent1 = new Intent(getActivity(), BeDriverWebActivity.class);
            intent1.putExtra(Const.webUrl, Const.API_BASE_URL + "index/applyHtml");
            intent1.putExtra(Const.intentTag, "申请成为司机");
            startActivity(intent1);
        } else {
            switch (status) {
                case "1":
                    startActivity(new Intent(getActivity(), DriverCenterActivity.class));
//                IntentUtils.toActivity(DriverCenterActivity.class, getActivity());
                    break;
                case "2":
                    startActivity(new Intent(getActivity(), DriverCenterActivity.class));
//                IntentUtils.toActivity(DriverCenterActivity.class, getActivity());
                    break;
                case "-2":
                    Intent intent1 = new Intent(getActivity(), BeDriverWebActivity.class);
                    intent1.putExtra(Const.webUrl, Const.API_BASE_URL + "index/applyHtml");
                    intent1.putExtra(Const.intentTag, "申请成为司机");
                    startActivity(intent1);
                    break;
                default:
                    AuditActivity.openAct(getActivity(), "mine", status);
                    break;
            }
        }
    }

    private void refreshHomeItem() {
        dataList.clear();
        for (int i = 0; i < imgArray.length; i++) {
            HomeGvBean bean = new HomeGvBean();
            bean.setResId(imgArray[i]);
            bean.setName(strArray[i]);
            bean.setType(String.valueOf(i));
            dataList.add(bean);
        }
        homeGvAdapter.notifyDataSetChanged();
    }

    @Override
    public void openShenqing() {
        Intent intent1 = new Intent(getActivity(), BeDriverWebActivity.class);
        intent1.putExtra(Const.webUrl, Const.API_BASE_URL + "index/applyHtml");
        intent1.putExtra(Const.intentTag, "申请成为司机");
        startActivity(intent1);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBannerView.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBannerView.stopAutoPlay();
    }


    @OnClick({R.id.city_lay, R.id.message_iv, R.id.dynamic_relay, R.id.temp_onemaintaiLay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.city_lay:
                IntentUtils.toActivity_result(CityChooseActivity.class, getActivity(), 111);
                break;
            case R.id.message_iv:
                if (CommonAction.getIsLogin()) {
                    IntentUtils.toActivityWithTag(MessageRvActivity.class, getActivity(), "user");
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }
                break;
            case R.id.dynamic_relay:
                IndustryDynamicsRvActivity.openAct(getContext());
                break;
            case R.id.temp_onemaintaiLay:
                OneChooseCarActivity.openAct(getActivity(), "home");
                break;

        }
    }

    private Runnable mVerticalTextviewRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this, Const.time);
            scrollTextView.next();
            number++;
            scrollTextView.setData(noticeBeans.get(number % noticeBeans.size()));
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && data != null) {
            String city = data.getStringExtra("city");
            if (city != null && !city.isEmpty()) {
                city_tv.setText(city);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacks(mVerticalTextviewRunnable);
        }
    }


    @Override
    public void showMsgListData(MessageListBean dataBean) {
        List<MessageListBean.ListBean> list = dataBean.getList();
        if (list!=null&&list.size()>0){
            boolean isVisible = false;
            for (int i = 0; i <list.size() ; i++) {
                String status = list.get(i).getStatus();
                if (status.equals("0")){
                    isVisible = true;
                    break;
                }
            }

            if (isVisible){
                mHaveMsgV.setVisibility(View.VISIBLE);
            }else {
                mHaveMsgV.setVisibility(View.GONE);
            }
        }
    }

    //您有新的消息，请注意查收
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(RefreshOrderBean bean){
        if (bean.getEventStr().equals(Const.refresh)){
            mHaveMsgV.setVisibility(View.VISIBLE);
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getContext().getApplicationContext(), notification);
            r.play();
        }
    }
    //登录成功，刷新ui
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(LoginBus bean){
        if (strArray.length>driverIndex){
            String userType = CommonAction.getUserType();
            if (!userType.equals("2")){
                strArray[driverIndex] = "司机招募";
            }else {
                strArray[driverIndex] = "司机中心";
            }
            refreshHomeItem();
        }
    }
}
