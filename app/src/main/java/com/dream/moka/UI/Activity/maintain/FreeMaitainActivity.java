package com.dream.moka.UI.Activity.maintain;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.BasePagerAdapter;
import com.dream.moka.Bean.AddServiceUserCarResultBean;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Bean.EventBusBean.MaintainBusBean;
import com.dream.moka.Bean.EventBusBean.MoneyUpdateBean;
import com.dream.moka.Bean.CarDetailResultBean;
import com.dream.moka.Bean.ChooseCarInfoBean;
import com.dream.moka.Bean.Maintain.FreeMaintainAllBean;
import com.dream.moka.Bean.RepairAreaBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.FreeMaitain.ConfirmMainTainContract;
import com.dream.moka.Contract.OneChooseCarContract;
import com.dream.moka.Contract.RepairAreaContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.FreeMaintain.ConfirmMaintainPresenter;
import com.dream.moka.Presenter.FreeMaintain.FreeMaintainPresenter;
import com.dream.moka.Presenter.OneChooseCarPresenter;
import com.dream.moka.Presenter.RepairShopChosePresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.RepairShopRvActivity;
import com.dream.moka.UI.Activity.SureOrderActivity;
import com.dream.moka.UI.Activity.mycar.MyCarsActivity;
import com.dream.moka.UI.Activity.other.WebViewActivity;
import com.dream.moka.UI.ChildFragment.FreeMaintainRvFragment;
import com.dream.moka.Utils.DensityUtil;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.ImmUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.PicUrlUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class FreeMaitainActivity extends BaseCommonActivity implements OneChooseCarContract, RepairAreaContract.View, ConfirmMainTainContract.View{

    RelativeLayout weix_line;
    @Bind(R.id.common_vp)
    ViewPager common_vp;

    @Inject
    OneChooseCarPresenter mOneChooseCarPresenter;
    @Inject
    FreeMaintainPresenter mFreeMaintainPresenter;
    @Inject
    ConfirmMaintainPresenter mConfirmMaintainPresenter;

    @Bind(R.id.car_icon)
    ImageView mCarIcon;
    @Bind(R.id.carBrand_nameTv)
    TextView mCarBrandNameTv;
    @Bind(R.id.car_nameTv)
    TextView mCarNameTv;
    @Bind(R.id.carselect_iv)
    ImageView mCarselectIv;
    @Bind(R.id.car_relay)
    RelativeLayout mCarRelay;
    @Bind(R.id.repairshop_tv)
    TextView mRepairshopTv;
    @Bind(R.id.right_iv1)
    ImageView mRightIv1;
    @Bind(R.id.repairshop_relay)
    RelativeLayout mRepairshopRelay;
    @Bind(R.id.xupay_tv)
    TextView mXupayTv;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.totalMoney_tv)
    TextView totalMoney_tv;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Inject
    RepairShopChosePresenter mRepairShopChosePresenter;

    private List<Fragment> fragments = new ArrayList<>();


    private String[] tabArray = new String[]{"保养", "维修"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_free_maintenance;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mOneChooseCarPresenter.attachView(this);
        mRepairShopChosePresenter.attachView(this);
        mConfirmMaintainPresenter.attachView(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public String initTitleText() {
        return "自选养车";
    }

    @Override
    public String initRightTv() {
        return "原厂配件参数";
    }

    @Override
    public boolean isRighttv() {
        return true;
    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {
        mOneChooseCarPresenter.getDefultCar();
        loadFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private int currentPosition;

    @Override
    public void eventListener() {
        common_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                refreshMoney();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentPosition = tab.getPosition();
                refreshMoney();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @OnClick({R.id.back_relay, R.id.car_relay, R.id.repairshop_relay, R.id.sure_tv, R.id.bottom_relay,R.id.right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.right_tv:
                if (StringUtil.NoNullOrEmpty(carId)){
                    IntentUtils.toActivityWithUrl(WebViewActivity.class,mActivity,carId,"10");
                }
                break;
            case R.id.repairshop_relay:
                Intent intent = new Intent(mContext, RepairShopRvActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("dataRS", (Serializable) repairList);
                intent.putExtras(bundle);
                startActivityForResult(intent, 106);
                break;
            case R.id.sure_tv:
                Log.e("tag", "carId=" + carId);
                Log.e("tag", "repairShopId=" + repairShopId);
                Map<String, String> map = MapUtils.getSingleMap();
                map.put(Const.token, CommonAction.getToken());
                map.put("carId", carId);
                map.put("repairShopId", repairShopId);
                if (!StringUtil.NoNullOrEmpty(carId)) {
                    ToastUtils.Toast_short("请先添加爱车");
                    break;
                }
                if (!StringUtil.NoNullOrEmpty(repairShopId)) {
                    ToastUtils.Toast_short("暂无维修商，请稍后重试");
                    break;
                }
                if (!isRight){
                    ToastUtils.Toast_short("机油和机油格是配套服务，切勿漏选");
                    break;
                }
                mLoadingDialog.show();
                mConfirmMaintainPresenter.confirmMaintainOrder(map);
                break;
            case R.id.car_relay:
                isHaveCar = false;
                MyCarsActivity.openAct(mActivity, Const.free);
                break;
            case R.id.bottom_relay:
                break;
        }
    }

    private boolean isHaveCar;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(ChooseCarInfoBean chooseCarInfoBean) {
        mCarNameTv.setText(chooseCarInfoBean.getConfigName());
        mCarBrandNameTv.setText(chooseCarInfoBean.getBrandName() + chooseCarInfoBean.getCateName());
        GlidUtils.LoadImgForUrl(mContext.getApplicationContext(), chooseCarInfoBean.getBranLogo(), mCarIcon);
        carId = chooseCarInfoBean.getCarId();
        if (StringUtil.NoNullOrEmpty(repairShopId)){
            refreshFragment();
        }else {
            reloadRepairshop();
        }
        isHaveCar = true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(MoneyUpdateBean moneyUpdateBean) {
        refreshMoney();
    }

    private List<FreeMaintainAllBean.ServiceResultBean> tempList = new ArrayList<>();

    private void refreshMoney() {
        tempList.clear();
        FreeMaintainRvFragment fragment = (FreeMaintainRvFragment) fragments.get(currentPosition);
        List<FreeMaintainAllBean.ServiceResultBean> dataList = fragment.dataList;
        double totalMoney = 0.00;
        Map<Integer, FreeMaintainAllBean.ServiceResultBean> map = new HashMap<>();
        boolean isjy = false;
        boolean isjyg = false;
        for (int i = 0; i < dataList.size(); i++) {
            FreeMaintainAllBean.ServiceResultBean listServiceBean = dataList.get(i);
            String price = listServiceBean.getServiceItem().getPrice();
            List<FreeMaintainAllBean.ServiceResultBean.ListServiceBean> productSelect = listServiceBean.getListService();
            if (productSelect != null && productSelect.size() > 0) {
                for (int j = 0; j < productSelect.size(); j++) {
                    FreeMaintainAllBean.ServiceResultBean.ListServiceBean productSelectBean = productSelect.get(j);
                    if (productSelectBean != null) {
                        FreeMaintainAllBean.ServiceResultBean.ListServiceBean.PartTypeBean typeBean = productSelectBean.getPartType();
                        if (typeBean != null) {
                            String partName = typeBean.getName();
                            List<FreeMaintainAllBean.ServiceResultBean.ListServiceBean.ListPartBean> listPart = productSelectBean.getListPart();
                            boolean isAdd = false;
                            if (listPart != null && listPart.size() > 0) {
                                for (int k = 0; k < listPart.size(); k++) {
                                    if (listPart.get(k).isSelect()) {
                                        if(partName.contains("机油格")&&partName.contains("机油")&&partName.contains("自带")){
                                            isjyg = true;
                                            isjy = true;
                                        }else {
                                            if (partName.contains("机油格")) {
                                                isjyg = true;
                                            } else if (partName.contains("机油")) {
                                                isjy = true;
                                            }
                                        }
                                        double proPrice = Double.valueOf(listPart.get(k).getPrice()) * Double.valueOf(listPart.get(k).getCount());
                                        totalMoney += proPrice;
                                        isAdd = true;
                                    }
                                }
                            }
                            if (isAdd){
                                //最后加上服务费
                                if (StringUtil.NoNullOrEmpty(price)) {
                                    totalMoney += Double.valueOf(price);
                                }
                                map.put(i, listServiceBean);
                            }
                        }
                    }
                }
                //单个服务内限定
               /* if (isjy && isjyg) {
                    //最后加上服务费
                    if (StringUtil.NoNullOrEmpty(price)) {
                        totalMoney += Double.valueOf(price);
                    }
                    map.put(i, listServiceBean);
                } else {
                    if (!isjy && !isjyg) {
                        //最后加上服务费
                        if (StringUtil.NoNullOrEmpty(price)) {
                            totalMoney += Double.valueOf(price);
                        }
                        map.put(i, listServiceBean);
                    } else {
                        ToastUtils.Toast_short("机油和机油格是配套服务，切勿漏选");
                        isRight = false;
                    }
                }*/
            }
        }
        if ((isjy||isjyg)&&!(isjy&&isjyg)){
            ToastUtils.Toast_short("机油和机油格是配套服务，切勿漏选");
            isRight = false;
        }else {
            isRight = true;
        }
        for (Map.Entry<Integer, FreeMaintainAllBean.ServiceResultBean> mm : map.entrySet()) {
            tempList.add(mm.getValue());
        }
        totalMoney_tv.setText(String.valueOf(totalMoney));
    }

    private boolean isRight = true;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 106 && data != null) {
            repairShopId = data.getStringExtra("repairShopId");
            shopType = data.getStringExtra("shopType");
            String shopName = data.getStringExtra("shopName");
            if (repairShopId != null) {
                refreshFragment();
            }
            mRepairshopTv.setText(shopName);
        }
        if (requestCode == 100&&!isHaveCar) {
            mOneChooseCarPresenter.getDefultCar();
        }
    }

    private void refreshFragment() {
        MaintainBusBean bean = new MaintainBusBean();
        bean.setCarId(carId);
        bean.setRepairShopId(repairShopId);
        bean.setShopType(shopType);
        EventBus.getDefault().post(bean);
    }

    private String carId = "";

    @Override
    public void onSuccess(CarDetailResultBean carDetailResultBean) {
        String branLogo = carDetailResultBean.getBranLogo();
        if (StringUtil.NoNullOrEmpty(branLogo)) {
            mCarIcon.setVisibility(View.VISIBLE);
            GlidUtils.LoadImgForUrl(mContext, PicUrlUtils.getRealUrl(branLogo), mCarIcon);
        }
        mCarBrandNameTv.setText(carDetailResultBean.getBrandName() + carDetailResultBean.getCateName());
        mCarNameTv.setText(carDetailResultBean.getConfigName());
        carId = carDetailResultBean.getUserCar().getId();
        reloadRepairshop();
    }

    private void reloadRepairshop() {
        if (StringUtil.NoNullOrEmpty(carId)){
            Map<String, String> map = MapUtils.getSingleMap();
            map.put(Const.token, CommonAction.getToken());
            map.put("carId", carId);
            map.put("longitude", CommonAction.getLongitude());
            map.put("latitude", CommonAction.getLatitude());
            mRepairShopChosePresenter.getSelfChoseShopList(map);
        }
    }

    @Override
    public void addServiceUserCarSuccess(AddServiceUserCarResultBean results) {

    }

    @Override
    public void showNoCar() {
        carId = "";
        mCarIcon.setVisibility(View.INVISIBLE);
        mCarNameTv.setText("请先添加爱车");
        mCarBrandNameTv.setText("");
        refreshFragment();
        refreshMoney();
    }

    @Override
    public void showError() {


    }

    @Override
    public void complete() {

    }

    private void setUpIndicatorWidth(TabLayout tabLayout, int marginLeft, int marginRight) {
        Class<?> tabLayoutClass = tabLayout.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayoutClass.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        LinearLayout layout = null;
        try {
            if (tabStrip != null) {
                layout = (LinearLayout) tabStrip.get(tabLayout);
            }
            for (int i = 0; i < layout.getChildCount(); i++) {
                View child = layout.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    params.setMarginStart(DensityUtil.dip2px(mActivity, marginLeft));
                    params.setMarginEnd(DensityUtil.dip2px(mActivity, marginRight));
                }
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private List<RepairAreaBean> repairList = new ArrayList<>();
    private String repairShopId = "";
    private String shopType = "";

    @Override
    public void showRepairShopData(List<RepairAreaBean> dataBean) {
        repairList.clear();
        if (dataBean.size() > 0) {
            RepairAreaBean repairAreaBean = dataBean.get(0);
            mRepairshopTv.setText(repairAreaBean.getName());
            repairList.addAll(dataBean);
            repairShopId = repairAreaBean.getId();
            shopType = repairAreaBean.getShopType();
            refreshFragment();
        }
    }

    private void loadFragment() {
        for (int i = 0; i < tabArray.length; i++) {
            FreeMaintainRvFragment freeMaintainRvFragment = new FreeMaintainRvFragment();
            freeMaintainRvFragment.setType(i);
            freeMaintainRvFragment.setData(carId, repairShopId, shopType);
            fragments.add(freeMaintainRvFragment);
        }
        common_vp.setAdapter(new BasePagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(common_vp);
        for (int i = 0; i < tabArray.length; i++) {
            tabLayout.getTabAt(i).setText(tabArray[i]);
        }
        setUpIndicatorWidth(tabLayout, 55, 55);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();
        if (ImmUtils.isShouldHideInput(v, ev)&& !isMove) {
            ImmUtils.getImm().hideSoftInputFromWindow(v.getWindowToken(), 0);
            v.clearFocus();
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isMove;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                isMove = true;
                break;
            case MotionEvent.ACTION_UP:
                isMove = false;
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void showConfirmData(ConfirmOrderResultBean dataBean) {
        mLoadingDialog.dismiss();
        if (tempList.size() > 0) {
            Intent intent = new Intent(mContext, SureOrderActivity.class);
            intent.putExtra(Const.intentTag, Const.free);
            intent.putExtra("payMOney", totalMoney_tv.getText().toString());
            intent.putExtra("carId", carId);
            intent.putExtra("repairShopId", repairShopId);
            intent.putExtra("data", (Serializable) tempList);
            intent.putExtra("type", currentPosition + "");
            intent.putExtra("result", dataBean);
            startActivity(intent);
        } else {
            ToastUtils.Toast_long("请选择相关配件");
        }
    }
}
