package com.dream.moka.UI.Fragment;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Bean.CarBrandResultBean;
import com.dream.moka.Bean.CarCategoryResultBean;
import com.dream.moka.Bean.CarsLetterBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.EventBusBean.StopLocationBus;
import com.dream.moka.Bean.Merchant.MerchantsBean;
import com.dream.moka.Bean.TestBaseBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.CarsChooseContract;
import com.dream.moka.Contract.LocationContract;
import com.dream.moka.Contract.MerchantContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.CarsChoosePresenter;
import com.dream.moka.Presenter.LocationPresenter;
import com.dream.moka.Presenter.MerchantPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.BeMerchantActivity;
import com.dream.moka.UI.Activity.MerchantDetailActivity;
import com.dream.moka.Utils.DensityUtil;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.PinYinUtil;
import com.dream.moka.Utils.PopWindowUtil;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.SpUtils;
import com.dream.moka.Utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
public class MerchantFragment extends BaseFragmentV4 implements LocationSource, AMapLocationListener, CarsChooseContract, MerchantContract.View, AMap.OnMarkerClickListener, LocationContract.View {

    @Bind(R.id.mapview)
    MapView mapView;
    @Bind(R.id.pp_lay)
    LinearLayout pp_lay;
    @Bind(R.id.pp_tv)
    TextView pp_tv;
    @Bind(R.id.xj_tv)
    TextView xj_tv;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private OnLocationChangedListener mListener;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption clientOption;
    private List<CarsLetterBean> ppList = new ArrayList<>();
    private List<TestBaseBean> xjList = new ArrayList<>();
    private PopupWindow popupWindowpp, popupWindowxj;

    @Inject
    CarsChoosePresenter mCarsChoosePresenter;
    @Inject
    MerchantPresenter mMerchantPresenter;
    @Inject
    LocationPresenter mLocationPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_merchant;
    }

    @Override
    public void initResume() {
        mapView.onResume();
        refreshData();
    }

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public void initView() {
        mCarsChoosePresenter.attachView(this);
        mMerchantPresenter.attachView(this);
        mLocationPresenter.attachView(this);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo((float) 15));
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);

        //关闭地图根据手势旋转
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        EventBus.getDefault().register(this);
    }

    private void initPopwindowPP() {
        //加载弹出框的布局
        View contentView = LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.pop_ppselect, null);
        popupWindowpp = PopWindowUtil.getPopupWindow(getActivity(), contentView, R.style.top2botAnimation, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ListView popcarstyle_rv = (ListView) contentView.findViewById(R.id.ppselect_lv);
        if (ppList.size() >= 6) {
            ViewGroup.LayoutParams layoutParams = popcarstyle_rv.getLayoutParams();
            layoutParams.height = DensityUtil.dip2px(getContext(), 303);
            popcarstyle_rv.setLayoutParams(layoutParams);
        }
        popcarstyle_rv.setAdapter(new PPselectAdapter(getActivity(), ppList, R.layout.rvitem_citylistitem));
//        popcarstyle_rv.setAdapter(new PPselectAdapter(getActivity(), ppList, R.layout.rvitem_onlytext_ppselect));
    }

    private void initPopwindowXJ() {
        //加载弹出框的布局
        View contentView = LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.pop_ppselect, null);
        popupWindowxj = PopWindowUtil.getPopupWindow(getActivity(), contentView, R.style.top2botAnimation, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ListView popcarstyle_rv = (ListView) contentView.findViewById(R.id.ppselect_lv);
        popcarstyle_rv.setAdapter(new XJselectAdapter(getActivity(), xjList, R.layout.rvitem_onlytext_ppselect));
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void initDta() {
        Map<String, String> bigMap = MapUtils.getBigMap();
        mCarsChoosePresenter.getCarsData(bigMap);
        xjList.clear();
        for (int i = 0; i < 5; i++) {
            TestBaseBean bean = new TestBaseBean();
            xjList.add(bean);
        }
        initPopwindowXJ();
    }

    @OnClick({R.id.bemerchant_relay, R.id.pp_lay, R.id.xj_lay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bemerchant_relay:
                if (CommonAction.getIsLogin()) {
                    IntentUtils.toActivity(BeMerchantActivity.class, getActivity());
                } else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }
                break;
            case R.id.pp_lay:
                if (popupWindowpp != null && pp_lay != null) {
                    popupWindowpp.showAsDropDown(pp_lay);
                }
                break;
            case R.id.xj_lay:
                if (popupWindowxj != null && pp_lay != null) {
                    popupWindowxj.showAsDropDown(pp_lay);
                }
                break;
        }
    }

    private int selectPosition2;

    @Override
    public void showData(CarBrandResultBean results) {
        ppList.clear();
        CarBrandResultBean.ListBeanX listBeanX = results.getList();
        List<CarBrandResultBean.ListBeanX.ListBean> listBeen = listBeanX.getList();
        if (listBeen != null && listBeen.size() > 0) {
           /* CarsLetterBean bean = new CarsLetterBean();
            bean.setName("全部");
            bean.setSelect(true);*/
            for (int i = 0; i < Const.lArray.length; i++) {
                CarsLetterBean simFrend = new CarsLetterBean();
                List<CarBrandResultBean.ListBeanX.ListBean> contactList = new ArrayList<>();
                for (int j = 0; j < listBeen.size(); j++) {
                    String name = listBeen.get(j).getName();
                    if (name != null && !name.isEmpty()) {
                        String pinYinHeadChar = PinYinUtil.getInstance().getPinYinHeadChar(name.substring(0, 1));
                        if (Const.lArray[i].equals(pinYinHeadChar)) {
                            contactList.add(listBeen.get(j));
                        }
                    }
                }
                if (contactList.size() != 0) {
                    simFrend.setLetter(Const.lArray[i]);
                    simFrend.setCarList(contactList);
                    ppList.add(simFrend);
                }
            }

            Log.e("tag", "ppList.size()=" + ppList.size());
            initPopwindowPP();
        }

    }

    @Override
    public void showCarStleData(List<CarCategoryResultBean> results) {

    }

    @Override
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }

    private String brandId, xjStr;

    @Override
    public void showMerchantData(List<MerchantsBean> data) {
        aMap.clear();
        for (int i = 0; i < data.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            String latitude = data.get(i).getLatitude();
            String longitude = data.get(i).getLongitude();
            if (latitude != null && longitude != null && !latitude.isEmpty() && !longitude.isEmpty()) {
                markerOptions.position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)));
            } else {
                ToastUtils.Toast_short("数据有误!");
            }
            markerOptions.title("维修商位置");
            markerOptions.visible(true);
            markerOptions.setFlat(true);
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.location_blue));
            markerOptions.icon(bitmapDescriptor);
            Marker marker = aMap.addMarker(markerOptions);
            marker.setObject(data.get(i));
        }
        // 绑定 Marker 被点击事件
        aMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        MerchantsBean markerBean = (MerchantsBean) marker.getObject();
        IntentUtils.toActivityWithTag(MerchantDetailActivity.class, getActivity(), markerBean.getId());
        return true;
    }

    @Override
    public void showLocationData(EmptyBean dataBean) {

    }

    public class PPselectAdapter extends CommonAdapter<CarsLetterBean> {
        public PPselectAdapter(Activity context, List<CarsLetterBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, final CarsLetterBean dataBean, final int position) {
            holder.setText(R.id.city_letter, dataBean.getLetter());
            ListView cars_lv = holder.getView(R.id.cityitem_lv);
            cars_lv.setAdapter(new CarsSelectAdapter(mActivity, dataBean.getCarList(), R.layout.rvitem_onlytext_ppselect, position));
        }
    }

    private class CarsSelectAdapter extends CommonAdapter<CarBrandResultBean.ListBeanX.ListBean> {

        private int mInt;

        public CarsSelectAdapter(Activity context, List<CarBrandResultBean.ListBeanX.ListBean> mDatas, int itemLayoutId, int position) {
            super(context, mDatas, itemLayoutId);
            mInt = position;
        }

        @Override
        public void convert(BaseViewHolder holder, final CarBrandResultBean.ListBeanX.ListBean dataBean, final int position) {
            final TextView ppTv = holder.getView(R.id.only_tv);
            ppTv.setText(dataBean.getName());
            ImageView view = holder.getView(R.id.right_iv);
            if (dataBean.isSelect()) {
                view.setVisibility(View.VISIBLE);
                ppTv.setTextColor(ResourcesUtils.getColor(R.color.coloryellow));
            } else {
                ppTv.setTextColor(ResourcesUtils.getColor(R.color.color333));
                view.setVisibility(View.GONE);
            }
            holder.getConvertView().setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    pp_tv.setText(dataBean.getName());
                    for (int j = 0; j < ppList.size(); j++) {
                        if (mInt == j) {
                            dataBean.setSelect(true);
                            List<CarBrandResultBean.ListBeanX.ListBean> carList = ppList.get(j).getCarList();
                            for (int i = 0; i < carList.size(); i++) {
                                if (i == position) {
                                    carList.get(i).setSelect(true);
                                } else {
                                    carList.get(i).setSelect(false);
                                }
                            }
                            brandId = dataBean.getId();
                            refreshData();
                        } else {
                            List<CarBrandResultBean.ListBeanX.ListBean> carList = ppList.get(j).getCarList();
                            for (int i = 0; i < carList.size(); i++) {
                                carList.get(i).setSelect(false);
                            }
                        }
                    }
                    popupWindowpp.dismiss();
                }
            });
        }
    }

    public class XJselectAdapter extends CommonAdapter<TestBaseBean> {
        public XJselectAdapter(Activity context, List<TestBaseBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, TestBaseBean dataBean, final int position) {
            final TextView ppTv = holder.getView(R.id.only_tv);
            ppTv.setText((position + 1) + "星级");
            ImageView view = holder.getView(R.id.right_iv);
            if (position == selectPosition2) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
            holder.getConvertView().setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    selectPosition2 = position;
                    view.setVisibility(View.VISIBLE);
                    xjStr = String.valueOf(position + 1);
                    xj_tv.setText(ppTv.getText().toString());
                    refreshData();
                    popupWindowxj.dismiss();
                }
            });
        }
    }

    private void refreshData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("latitude", String.valueOf(mLatitude));
        map.put("longitude", String.valueOf(mLongitude));
        if (xjStr != null) {
            map.put("score", xjStr);
        }
        if (brandId != null) {
            map.put("brandId", brandId);
        }
        map.put("distance", "100000");
        mLoadingDialog.show();
        mMerchantPresenter.getRepairShopList(map);
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private double mLongitude, mLatitude;
    private double mTempLongitude, mTempLatitude;
    private boolean isFirst = true;

    //位置变化的回调
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
//        ToastUtils.Toast_short("位置变化了~");
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            mListener.onLocationChanged(aMapLocation);
            mLongitude = aMapLocation.getLongitude();
            mLatitude = aMapLocation.getLatitude();

            SpUtils.savaUserInfo(Const.latitude, String.valueOf(mLatitude));
            SpUtils.savaUserInfo(Const.longitude, String.valueOf(mLongitude));
            if (isFirst) {
                refreshData();
                isFirst = false;
            }
            String keysId = CommonAction.getKeysId();
            if (!keysId.isEmpty()&&CommonAction.getIsLogin()) {
                if (mTempLongitude != mLongitude && mTempLatitude != mLatitude) {//避免重复上传
                    if (CommonAction.getIsUpLoad()) {
                        //上传位置
                        String url = Const.locationUrl + "?longitude=" + mLongitude+ "&latitude=" + mLatitude+ "&key=" + keysId;
//                        Log.e("tag", "url=" + url);
//                        ToastUtils.Toast_short("longitude="+mLongitude+"mLatitude="+mLatitude);
                        mLocationPresenter.uplaodLocation(url);
                        mTempLongitude = mLongitude;
                        mTempLatitude = mLatitude;
                    }
                }
            }
        } else {
            String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
            Log.e("AmapErr", errText);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMian(StopLocationBus eventbean) {
        if (eventbean.getEventStr().equals("stop")) {
            SpUtils.savaUserInfo(Const.isUpload, false);
        }
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (locationClient == null) {
            locationClient = new AMapLocationClient(getActivity());
            clientOption = new AMapLocationClientOption();
            locationClient.setLocationListener(this);
            clientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//高精度定位
            clientOption.setOnceLocationLatest(true);//设置单次精确定位
            clientOption.setInterval(1500);//1s定位一次
            locationClient.setLocationOption(clientOption);
            locationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (locationClient != null) {
            locationClient.stopLocation();
            locationClient.onDestroy();
        }
        locationClient = null;
    }

}
