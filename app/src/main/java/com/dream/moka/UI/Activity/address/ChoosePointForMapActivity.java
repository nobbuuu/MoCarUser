package com.dream.moka.UI.Activity.address;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.geocoder.StreetNumber;
import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.LocationBean;
import com.dream.moka.Bean.SearchResultAddressBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 选择经纬度
 */
public class ChoosePointForMapActivity extends BaseCommonActivity implements GeocodeSearch.OnGeocodeSearchListener {


    @Bind(R.id.mapview)
    MapView mMapview;
    @Bind(R.id.recycler)
    RecyclerView mRecycler;
    @Bind(R.id.search)
    EditText mSearch;
    @Bind(R.id.search_button)
    ImageView mSearchButton;
    @Bind(R.id.kuang)
    LinearLayout mKuang;
    @Bind(R.id.searchList)
    RecyclerView mSearchList;
    private AMap aMap;
    private GeocodeSearch mGeocodeSearch;
    private List<LocationBean> mLocationBeanList = new ArrayList<>();
    private List<SearchResultAddressBean> mSearchResultList = new ArrayList<>();
    private MyAdapter mMyAdapter;
    private double mChooseLatitude;
    private double mChooseLongitude;
    private Dialog mLoadingDialog;
    private MyLocationStyle mMyLocationStyle;
    private SearchAdapter mSearchAdapter;
    private Geocoder mGeocoder;

    public static void openAct(Activity activity, int requestcode) {
        Intent intent = new Intent(activity, ChoosePointForMapActivity.class);
        activity.startActivityForResult(intent, requestcode);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_point_for_map;

    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        mGeocodeSearch = new GeocodeSearch(mContext);
        mGeocodeSearch.setOnGeocodeSearchListener(this);
        mMapview.onCreate(savedInstanceState);// 此方法必须重写
        if (aMap == null) {
            aMap = mMapview.getMap();
        }
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);

        //显示定位蓝点
        mMyLocationStyle = new MyLocationStyle();
        mMyLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        mMyLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次
        aMap.setMyLocationStyle(mMyLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo((float) 17.5));
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                aMap.clear();
                mChooseLatitude = latLng.latitude;
                mChooseLongitude = latLng.longitude;
                mGeocodeSearch.getFromLocationAsyn(new RegeocodeQuery(new LatLonPoint(mChooseLatitude, mChooseLongitude), 50, GeocodeSearch.AMAP));
                //绘制marker
                Marker marker = aMap.addMarker(new MarkerOptions()
                        .position(new LatLng(mChooseLatitude, mChooseLongitude))
                        .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.mipmap.location_blue)))
                        .draggable(true));

            }
        });
        initRecycler();
        mLoadingDialog.show();
        mGeocoder = new Geocoder(this);
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mMyAdapter = new MyAdapter(mActivity, mLocationBeanList, R.layout.choose_point_item);
        mRecycler.setAdapter(mMyAdapter);

        mSearchList.setLayoutManager(new LinearLayoutManager(mContext));
        mSearchAdapter = new SearchAdapter(mActivity, mSearchResultList, R.layout.search_address_item);
        mSearchList.setAdapter(mSearchAdapter);
    }

    @Override
    public String initTitleText() {
        return "区域位置";
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
        mMapview.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                setResult(0x13, new Intent());
                finish();
                break;
        }
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onPause() {
        super.onPause();
        mMapview.onPause();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapview.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMapview != null) {
            mMapview.onDestroy();
        }
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void eventListener() {
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if (mLoadingDialog != null) {
                    mLoadingDialog.dismiss();
                }
            }
        });
    }


    /**
     * 反编码结果
     *
     * @param regeocodeResult
     * @param i
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
        String province = regeocodeAddress.getProvince();
        String city = regeocodeAddress.getCity();
        String district = regeocodeAddress.getDistrict();
        StreetNumber streetNumber = regeocodeAddress.getStreetNumber();
        String street = streetNumber.getStreet();
        String number = streetNumber.getNumber();
        String formatAddress = regeocodeAddress.getFormatAddress();

        LocationBean locationBean = new LocationBean();
        locationBean.setProvince(province);
        locationBean.setCity(city);
        locationBean.setName(formatAddress);
        locationBean.setArea(district);
        locationBean.setStree(street);
        locationBean.setNumber(number);
        locationBean.setDetail(street + number);
        mLocationBeanList.clear();
        mLocationBeanList.add(locationBean);
        mMyAdapter.notifyDataSetChanged();
    }

    /**
     * 编码结果
     *
     * @param geocodeResult
     * @param i
     */
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        if (i == 1000) {
            if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null && geocodeResult.getGeocodeAddressList().size() > 0) {
                List<GeocodeAddress> geocodeAddressList = geocodeResult.getGeocodeAddressList();
                List<SearchResultAddressBean> list = new ArrayList<>();
                for (int j = 0; j < geocodeAddressList.size(); j++) {
                    String province = geocodeAddressList.get(j).getProvince();
                    String city = geocodeAddressList.get(j).getCity();
                    String district = geocodeAddressList.get(j).getDistrict();
                    LatLonPoint latLonPoint = geocodeAddressList.get(j).getLatLonPoint();
                    String formatAddress = geocodeAddressList.get(j).getFormatAddress();
                    String building = geocodeAddressList.get(j).getBuilding();

                    SearchResultAddressBean searchResultAddressBean = new SearchResultAddressBean();
                    searchResultAddressBean.setAddressName(district + building);
                    searchResultAddressBean.setContent(formatAddress);
                    searchResultAddressBean.setLat(latLonPoint.getLatitude());
                    searchResultAddressBean.setLng(latLonPoint.getLongitude());
                    searchResultAddressBean.setPrivince(province);
                    searchResultAddressBean.setCity(city);
                    searchResultAddressBean.setArea(district);

                    list.add(searchResultAddressBean);
                }
                mSearchResultList.clear();
                mSearchResultList.addAll(list);
                mSearchAdapter.notifyDataSetChanged();
                mSearchList.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSearch.getWindowToken(), 0) ;

            }else {
                ToastUtils.Toast_short("没有搜索结果");
                mSearchList.setVisibility(View.GONE);
            }
        } else {
            ToastUtils.Toast_short("没有搜索结果");
            mSearchList.setVisibility(View.GONE);
        }

    }


    @OnClick(R.id.search_button)
    public void onViewClicked() {
        if (!TextUtils.isEmpty(mSearch.getText().toString().trim())) {
            mGeocodeSearch.getFromLocationNameAsyn(new GeocodeQuery(mSearch.getText().toString().trim(), "深圳市"));
//            try {
//                List<Address> fromLocationName = mGeocoder.getFromLocationName(mSearch.getText().toString().trim(), 5);
//                if (fromLocationName.size()>0){
//
//
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } else {
            ToastUtils.Toast_short("请输入搜索地址");
        }
    }

    public class MyAdapter extends RVBaseAdapter<LocationBean> {
        public MyAdapter(Activity context, List<LocationBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final LocationBean locationBean, int position) {
            String name = locationBean.getName();
            final String detail = locationBean.getDetail();
            holder.setText(R.id.name, name);
            holder.setText(R.id.detail, detail);

            holder.setOnClickListener(R.id.locationLayout, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    backMethod(String.valueOf(mChooseLatitude), String.valueOf(mChooseLongitude), locationBean.getProvince()
                            , locationBean.getCity(), locationBean.getArea(), locationBean.getName(), detail);
                }
            });
        }
    }

    public class SearchAdapter extends RVBaseAdapter<SearchResultAddressBean> {

        public SearchAdapter(Activity context, List<SearchResultAddressBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final SearchResultAddressBean searchResultAddressBean, int position) {
            holder.setText(R.id.txt, searchResultAddressBean.getContent());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    LocationBean locationBean = new LocationBean();
//                    locationBean.setProvince(searchResultAddressBean.getPrivince());
//                    locationBean.setCity(searchResultAddressBean.getCity());
//                    locationBean.setName(searchResultAddressBean.getContent());
//                    locationBean.setArea(searchResultAddressBean.getArea());
//                    locationBean.setStree(searchResultAddressBean.getBuliding());
//                    locationBean.setNumber("");
//                    locationBean.setDetail(searchResultAddressBean.getAddressName());
                    mChooseLatitude = searchResultAddressBean.getLat();
                    mChooseLongitude = searchResultAddressBean.getLng();

                    aMap.clear();
                    aMap.addMarker(new MarkerOptions()
                            .position(new LatLng(mChooseLatitude, mChooseLongitude))
                            .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                    .decodeResource(getResources(), R.mipmap.location_blue)))
                            .draggable(true));

//                    mLocationBeanList.clear();
//                    mLocationBeanList.add(locationBean);
//                    mMyAdapter.notifyDataSetChanged();
                    Double lat = searchResultAddressBean.getLat();
                    Double lng = searchResultAddressBean.getLng();
                    LatLng latLng = new LatLng(lat, lng);
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
                    mSearchList.setVisibility(View.GONE);
                    mSearchResultList.clear();
                    mSearchAdapter.notifyDataSetChanged();

                    mGeocodeSearch.getFromLocationAsyn(new RegeocodeQuery(new LatLonPoint(mChooseLatitude, mChooseLongitude), 50, GeocodeSearch.AMAP));
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        setResult(0x13, new Intent());
        finish();
    }

    public void backMethod(String lat, String Long, String p, String c, String a, String name, String detail) {
        Intent intent = new Intent();
        intent.putExtra("name", name);
        intent.putExtra("lat", lat);
        intent.putExtra("Long", Long);
        intent.putExtra("p", p);
        intent.putExtra("c", c);
        intent.putExtra("a", a);
        intent.putExtra("detail", detail);
        setResult(0x20, intent);
        finish();
    }
}
