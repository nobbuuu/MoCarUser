package com.dream.moka.UI.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.moka.Adapter.CounponsAdapter;
import com.dream.moka.Adapter.MainVpAdapter;
import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Bean.AboutBean;
import com.dream.moka.Bean.BannerBean;
import com.dream.moka.Bean.EventBusBean.DownloadBean;
import com.dream.moka.Bean.EventBusBean.RefreshBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.EventBusBean.RefreshMsgBean;
import com.dream.moka.Bean.HotActivityBean;
import com.dream.moka.Bean.ListCouponBean;
import com.dream.moka.Bean.MessageBean;
import com.dream.moka.Bean.NewsBean;
import com.dream.moka.Bean.Other.VersionBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.AboutContract;
import com.dream.moka.Contract.CouponsContract;
import com.dream.moka.Contract.HomeFragmentContract;
import com.dream.moka.Contract.Message.MessageAddContract;
import com.dream.moka.Contract.VersionContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Listener.PermissionListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.AboutPresenter;
import com.dream.moka.Presenter.CouponsPresenter;
import com.dream.moka.Presenter.HomeFragmentPresenter;
import com.dream.moka.Presenter.Message.MessageAddPresenter;
import com.dream.moka.Presenter.VersionPresenter;
import com.dream.moka.R;
import com.dream.moka.Receivers.DownloadReceiver;
import com.dream.moka.Service.DownLoadService;
import com.dream.moka.UI.Activity.drivercenter.AuditActivity;
import com.dream.moka.UI.Activity.drivercenter.DriverCenterActivity;
import com.dream.moka.UI.Activity.login_register.LoginActivity;
import com.dream.moka.UI.Fragment.HomeFragment;
import com.dream.moka.UI.Fragment.MerchantFragment;
import com.dream.moka.UI.Fragment.MineFragment;
import com.dream.moka.UI.Fragment.OnlineServiceFragment;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.HttpUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.SpUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;
import com.dream.moka.Utils.VersionUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

import static com.dream.moka.Utils.IMUtil.IMAcountUtils.loginSig;

public class MainActivity extends BaseActivity implements HomeFragmentContract,MessageAddContract.View,AboutContract.View,CouponsContract.View,VersionContract.View{

    @Bind(R.id.main_vp)
    ViewPager main_vp;
    @Bind(R.id.shouye_lay)
    LinearLayout shouye_lay;
    @Bind(R.id.merchant_lay)
    LinearLayout merchant_lay;
    @Bind(R.id.online_lay)
    LinearLayout online_lay;
    @Bind(R.id.mine_lay)
    LinearLayout mine_lay;

    @Bind(R.id.shouye_iv)
    ImageView shouye_iv;
    @Bind(R.id.shouye_tv)
    TextView shouye_tv;
    @Bind(R.id.merchant_iv)
    ImageView merchant_iv;
    @Bind(R.id.merchant_tv)
    TextView merchant_tv;

    @Bind(R.id.online_iv)
    ImageView online_iv;
    @Bind(R.id.online_tv)
    TextView online_tv;
    @Bind(R.id.mine_iv)
    ImageView mine_iv;
    @Bind(R.id.mine_tv)
    TextView mine_tv;
    @Bind(R.id.bedriver_iv)
    ImageView mBedriverIv;
    @Bind(R.id.bedriver_tv)
    TextView mBedriverTv;
    @Bind(R.id.bedriver_lay)
    LinearLayout mBedriverLay;
    @Bind(R.id.main_botlay)
    LinearLayout mMainBotlay;

    private int mTag;
    @Inject
    HomeFragmentPresenter mHomeFragmentPresenter;
    @Inject
    MessageAddPresenter mMessageAddPresenter;
    @Inject
    AboutPresenter mAboutPresenter;
    @Inject
    CouponsPresenter mCouponsPresenter;
    @Inject
    VersionPresenter mVersionPresenter;

    private Dialog mLoadingDialog;

    private MainVpAdapter mainVpAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        DaggerBaseComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
//                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
        mHomeFragmentPresenter.attachView(this);
        mMessageAddPresenter.attachView(this);
        mAboutPresenter.attachView(this);
        mCouponsPresenter.attachView(this);
        mVersionPresenter.attachView(this);
        EventBus.getDefault().register(this);
        requestRunPermisssion(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                ToastUtils.Toast_short("拒绝定位权限，将无法获取当前位置，可在设置中重新获取");
            }
        });
        mLoadingDialog = DialogUtils.initLoadingDialog(this);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void loadResum() {
        Log.e("user", "token=" + CommonAction.getToken());
        Log.e("user", "userId=" + CommonAction.getUserId());
        initBotItem();
        switch (mTag) {
            case 0:
                clickShouye();
                break;
            case 1:
                clickMerchant();
                break;
            case 2:
                clickOnlineService();
                break;
            case 3:
                clickMine();
                break;
        }
    }


    @Override
    public void initDatas() {
        initFragments();
        mainVpAdapter = new MainVpAdapter(getSupportFragmentManager(), fragmentList);
        main_vp.setAdapter(mainVpAdapter);
        main_vp.setOffscreenPageLimit(4);

        String userSign = CommonAction.getUserSign();
        String userId = CommonAction.getUserId();
        Log.e("tag","userId="+userId);
        Log.e("tag","userSign="+userSign);
        if (StringUtil.NoNullOrEmpty(userSign)&&StringUtil.NoNullOrEmpty(userId)){
            loginSig(userId,userSign);
        }
        mAboutPresenter.getAbout("1");

        if (CommonAction.getIsLogin()){
            mCouponsPresenter.getPlatFormCoupon(CommonAction.getToken());
        }

        mVersionPresenter.getNewVersion("1","android");
    }

    private List<Fragment> initFragments() {
        fragmentList.clear();
        HomeFragment homeFragment = new HomeFragment();
        MerchantFragment merchantFragment = new MerchantFragment();
        OnlineServiceFragment serviceFragment = new OnlineServiceFragment();
        MineFragment mineFragment = new MineFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(merchantFragment);
        fragmentList.add(serviceFragment);
        fragmentList.add(mineFragment);
        return fragmentList;
    }

    @Override
    public void eventListener() {

        main_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initBotItem();
                switch (position) {
                    case 0:
                        clickShouye();
                        break;
                    case 1:
                        clickMerchant();
                        break;
                    case 2:
                        clickOnlineService();
                        break;
                    case 3:
                        clickMine();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    private void clickMine() {
//        mine_lay.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
        mine_iv.setImageResource(R.mipmap.blue_icon_mine);
        mine_tv.setTextColor(ResourcesUtils.getColor(R.color.blue_them));
        mTag = 3;

    }

    private void clickOnlineService() {
//        online_lay.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
        online_iv.setImageResource(R.mipmap.blue_icon_expert);
        online_tv.setTextColor(ResourcesUtils.getColor(R.color.blue_them));
        mTag = 2;

    }

    private void clickMerchant() {
//        merchant_lay.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
        merchant_iv.setImageResource(R.mipmap.blue_icon_businessmen);
        merchant_tv.setTextColor(ResourcesUtils.getColor(R.color.blue_them));
        mTag = 1;

    }

    private void clickShouye() {
//        shouye_lay.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
        shouye_iv.setImageResource(R.mipmap.blue_icon_home);
        shouye_tv.setTextColor(ResourcesUtils.getColor(R.color.blue_them));
        mTag = 0;
    }

    private void clickBeDriver() {
//        shouye_lay.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
        mBedriverIv.setImageResource(R.mipmap.blue_icon_driver);
        mBedriverTv.setTextColor(ResourcesUtils.getColor(R.color.blue_them));
    }

    private void initBotItem() {
        shouye_lay.setBackgroundColor(ResourcesUtils.getColor(R.color.white));
        merchant_lay.setBackgroundColor(ResourcesUtils.getColor(R.color.white));
        online_lay.setBackgroundColor(ResourcesUtils.getColor(R.color.white));
        mine_lay.setBackgroundColor(ResourcesUtils.getColor(R.color.white));

        shouye_iv.setImageResource(R.mipmap.gray_icon_home);
        shouye_tv.setTextColor(ResourcesUtils.getColor(R.color.color333));
        merchant_iv.setImageResource(R.mipmap.gray_icon_businessmen);
        merchant_tv.setTextColor(ResourcesUtils.getColor(R.color.color333));
        online_iv.setImageResource(R.mipmap.gray_icon_expert);
        online_tv.setTextColor(ResourcesUtils.getColor(R.color.color333));
        mine_iv.setImageResource(R.mipmap.icon_my);
        mine_tv.setTextColor(ResourcesUtils.getColor(R.color.color333));
        mBedriverIv.setImageResource(R.mipmap.gray_icon_driver);
        mBedriverTv.setTextColor(ResourcesUtils.getColor(R.color.color333));
    }

    @OnClick({R.id.shouye_lay, R.id.merchant_lay, R.id.online_lay, R.id.mine_lay, R.id.bedriver_lay})
    public void onClick(View view) {
        initBotItem();
        switch (view.getId()) {
            case R.id.shouye_lay:
                clickShouye();
                main_vp.setCurrentItem(0);
                break;
            case R.id.merchant_lay:
                clickMerchant();
                main_vp.setCurrentItem(1);
                break;
            case R.id.online_lay:
                clickOnlineService();
                main_vp.setCurrentItem(2);
                break;
            case R.id.mine_lay:
                clickMine();
                main_vp.setCurrentItem(3);
                break;
            case R.id.bedriver_lay:
                clickBeDriver();
                boolean isLogin = CommonAction.getIsLogin();
                if (!isLogin) {
                    IntentUtils.toActivity_result(LoginActivity.class,mActivity,150);
                } else {
//                    mLoadingDialog.show();
                    mHomeFragmentPresenter.getData();
                }
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(RefreshBean refreshBean) {
        if (refreshBean.getEventStr().equals(Const.refresh)) {
            clickOnlineService();
            main_vp.setCurrentItem(2);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(MessageBean bean) {
        if (bean.getEventStr().equals(Const.refresh) && CommonAction.getIsLogin()) {
            mMessageAddPresenter.addUserMessage(CommonAction.getToken(),bean.getTagId());
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(RefreshMsgBean bean) {
        if (bean.getEventStr().equals(Const.refresh)) {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(mContext.getApplicationContext(), notification);
            r.play();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        AppManager.getAppManager().finishAllActivity();
    }

    @Override
    public void setBannerInfo(List<BannerBean> list) {

    }

    @Override
    public void setNewsInfo(List<NewsBean> list) {

    }

    @Override
    public void setHotInfo(List<HotActivityBean> list) {

    }

    @Override
    public void driverSuccess(String status) {
        String userType = CommonAction.getUserType();
        if (!userType.equals("2")) {
            Intent intent1 = new Intent(mActivity, BeDriverWebActivity.class);
            intent1.putExtra(Const.webUrl, Const.API_BASE_URL + "index/applyHtml");
            intent1.putExtra(Const.intentTag, "申请成为司机");
            startActivity(intent1);
        } else {
            switch (status) {
                case "1":
                    startActivity(new Intent(mActivity, DriverCenterActivity.class));
//                IntentUtils.toActivity(DriverCenterActivity.class, getActivity());
                    break;
                case "2":
                    startActivity(new Intent(mActivity, DriverCenterActivity.class));
//                IntentUtils.toActivity(DriverCenterActivity.class, getActivity());
                    break;
                case "-2":
                    Intent intent1 = new Intent(mActivity, BeDriverWebActivity.class);
                    intent1.putExtra(Const.webUrl, Const.API_BASE_URL + "index/applyHtml");
                    intent1.putExtra(Const.intentTag, "申请成为司机");
                    startActivity(intent1);
                    break;
                default:
                    AuditActivity.openAct(mActivity, "mine", status);
                    break;
            }
        }
    }

    @Override
    public void openShenqing() {
        Intent intent1 = new Intent(mActivity, BeDriverWebActivity.class);
        intent1.putExtra(Const.webUrl, Const.API_BASE_URL + "index/applyHtml");
        intent1.putExtra(Const.intentTag, "申请成为司机");
        startActivity(intent1);
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
    public void showMsgAddData(EmptyBean dataBean) {
        Log.e("tag","消息新增成功");
        eventMain(new RefreshMsgBean(Const.refresh));
    }

    @Override
    public void showData(AboutBean dataBean) {
        SpUtils.savaUserInfo(Const.platformTell,dataBean.getTelphone());
        SpUtils.savaUserInfo(Const.helpTell,dataBean.getHelpPhone());
    }

    public  Dialog initCounponsDialog(final Activity activity, final List<ListCouponBean> coupons) {
        final Dialog headDialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View headView = LayoutInflater.from(activity).inflate(R.layout.dialog_coupons, null);
        TextView getCoupons_tv = (TextView) headView.findViewById(R.id.getCoupons_tv);
        ImageView close_iv = (ImageView) headView.findViewById(R.id.close_iv);
        RecyclerView coupons_rv = (RecyclerView) headView.findViewById(R.id.coupons_rv);
        RvUtils.setOptionnoLine(coupons_rv,mActivity);
        coupons_rv.setAdapter(new CounponsAdapter(mActivity,coupons,R.layout.rvitem_coupons));
        headDialog.setContentView(headView);
        //获取当前Activity所在的窗体
        Window dialogWindow1 = headDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity(Gravity.BOTTOM);
        headDialog.setCancelable(true);
        close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headDialog.dismiss();
            }
        });
        getCoupons_tv.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i <coupons.size() ; i++) {
                    if (i!=coupons.size()-1){
                        buffer.append(coupons.get(i).getId()+",");
                    }else {
                        buffer.append(coupons.get(i).getId());
                    }
                }
                mCouponsPresenter.acceptCoupon(CommonAction.getToken(),buffer.toString());
            }
        });
        return headDialog;
    }
    private long exitTime;
    private Dialog couponsDialog;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showCouponsData(List<ListCouponBean> dataList) {
        if (dataList.size()>0){
            couponsDialog = initCounponsDialog(mActivity,dataList);
            couponsDialog.show();
        }
    }

    @Override
    public void showaAceptCoupon(EmptyBean dataBean) {
        couponsDialog.dismiss();
    }

    private String apkUrl,version;
    @Override
    public void showVersionData(VersionBean dataBean) {
        apkUrl = dataBean.getUrl();
        version = dataBean.getVersion();
        Log.e("tag", "apkUrl=" + apkUrl);
        Log.e("tag", "version=" + version);
        if (apkUrl != null && !apkUrl.isEmpty() && version != null && !version.isEmpty()) {//对url和version进行判断
            if (VersionUtils.isNewVersion(version)){
                DialogUtils.getVersionDialog(version, apkUrl, this);
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mainEvent1(DownloadBean busBean) {
        String eventStr = busBean.getEventStr();
        if (eventStr.equals("fail")) {
            ToastUtils.Toast_short("更新失败");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mainEvent2(DownloadBean busBean) {
        String eventStr = busBean.getEventStr();
        if (eventStr.equals("doing")) {
            ToastUtils.Toast_short("已在后台进行更新...");
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mainEvent3(String eventStr) {
        if (eventStr.equals("downloadApk")) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                startService_updateApk();
            }else {
                ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},666);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==666&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            startService_updateApk();
        }
    }

    private void startService_updateApk() {
        Intent intent = new Intent(mContext,DownLoadService.class);
        intent.putExtra("apkUrl",apkUrl);
        intent.putExtra("versionName",version);
        startService(intent);
    }
}
