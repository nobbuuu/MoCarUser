package com.dream.moka.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.trace.LBSTraceClient;
import com.amap.api.trace.TraceListener;
import com.amap.api.trace.TraceLocation;
import com.amap.api.trace.TraceOverlay;
import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.Other.DriverGuijiBean;
import com.dream.moka.Bean.Other.GuijiBean;
import com.dream.moka.Bean.Other.UserGuijiBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.DriverCenter.TrajectoryContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.DriverCenter.TrajectoryPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.DensityUtil;
import com.dream.moka.Utils.DeviceUtils;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.StringUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class GuijiMapActivity extends BaseCommonActivity implements LocationSource, AMapLocationListener, TrajectoryContract.View,TraceListener {

    @Bind(R.id.mapview)
    MapView mapView;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private OnLocationChangedListener mListener;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption clientOption;

    private LBSTraceClient mTraceClient;
    private List<TraceLocation> mTraceList = new ArrayList<>();
    private int mSequenceLineID = 1000;
    private ConcurrentMap<Integer, TraceOverlay> mOverlayList = new ConcurrentHashMap<Integer, TraceOverlay>();
    private int mCoordinateType = LBSTraceClient.TYPE_AMAP;
    private String rsLongitude = "";
    private String rsLatitude = "";
    @Inject
    TrajectoryPresenter mTrajectoryPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guijimap;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {

        mTrajectoryPresenter.attachView(this);
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

    }

    @Override
    public String initTitleText() {
        return "行驶轨迹";
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

    public static void openAct(Context context,String orderId,String driverId,String type,String rsLongitude,String rsLatitude){
        Intent intent = new Intent(context,GuijiMapActivity.class);
        intent.putExtra("orderId",orderId);
        intent.putExtra("driverId",driverId);
        intent.putExtra("type",type);
        intent.putExtra("rsLongitude",rsLongitude);
        intent.putExtra("rsLatitude",rsLatitude);
        context.startActivity(intent);
    }

    @Override
    public void initDatas() {
        String driverHisId = getIntent().getStringExtra(Const.intentTag);
        Log.e("tag", "driverHisId=" + driverHisId);
        if (StringUtil.NoNullOrEmpty(driverHisId)){
            mTrajectoryPresenter.getDrivingTrack(CommonAction.getToken(), driverHisId);
        }else {
            String orderId = getIntent().getStringExtra("orderId");
            String driverId = getIntent().getStringExtra("driverId");
            String type = getIntent().getStringExtra("type");
            Map<String, String> map = MapUtils.getSingleMap();
            map.put(Const.token,CommonAction.getToken());
            map.put("orderId",orderId);
            map.put("driverId",driverId);
            map.put("type",type);
            mTrajectoryPresenter.getUserDrivingTrack(map);
        }

        rsLatitude = getIntent().getStringExtra("rsLatitude");
        rsLongitude = getIntent().getStringExtra("rsLongitude");

        if (StringUtil.NoNullOrEmpty(rsLatitude)&&StringUtil.NoNullOrEmpty(rsLongitude)){
            addMarker(new LatLng(Double.valueOf(rsLatitude),Double.valueOf(rsLongitude)),"维修商位置",R.drawable.merchant_marker);
        }
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

    //位置变化了
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
//            mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
        } else {
            String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
            Log.e("AmapErr", errText);
        }
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (locationClient == null) {
            locationClient = new AMapLocationClient(mActivity);
            clientOption = new AMapLocationClientOption();
            locationClient.setLocationListener(this);
            clientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//高精度定位
//            clientOption.setInterval(30000);
            clientOption.setOnceLocationLatest(true);//设置单次精确定位
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

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showTrackData(DriverGuijiBean dataBean) {
        DriverGuijiBean.DriverHisBean driverHis = dataBean.getDriverHis();
        List<GuijiBean> list = dataBean.getList();
        if (list!=null&&list.size()>0){
            addGuijiLine(list,true);
        }
       /* if (driverHis!=null){
            String sendBack = driverHis.getSendBack();
            if (list.size()>0){
                GuijiBean guijiBean = null;
                if (!sendBack.equals("0")){
                    if (sendBack.equals("3")){
                        guijiBean = list.get(0);
                    }else {
                        guijiBean = list.get(list.size()-1);
                    }
                }
            }
            if (sendBack.equals("0")||sendBack.equals("1")){		// 0接车（司机-用户） 1接车（司机-维修商）2送车（司机-维修商）3送车（维修商-用户）
                //画折线路径（手上没车的时候）
                addGuijiLine(list,true);
            }else {
                //画轨迹纠偏的路径（手上有车的时候）
                addGuijiLine(list,false);
            }
        }*/
    }

    private void addGuijiLine(List<GuijiBean> dataBean, boolean isPolyline) {
        Log.e("tag","dataBean.size()="+dataBean.size());
        if (isPolyline){
            //画折线
            PolylineOptions mapoption = new PolylineOptions();
            for (int i = 0; i <dataBean.size() ; i++) {
                mapoption.add(new LatLng(dataBean.get(i).getLatitude(),dataBean.get(i).getLongtitude()));
            }
            mapoption.color(Color.BLUE);
            mapoption.width(12);
            mapoption.zIndex(10);
            aMap.addPolyline(mapoption);
        }else {
            //轨迹纠偏
            mTraceList.clear();
            for (int i = 0; i < dataBean.size() ; i++) {
                TraceLocation mtrace = new TraceLocation();
                mtrace.setLatitude(dataBean.get(i).getLatitude());
                mtrace.setLongitude(dataBean.get(i).getLongtitude());
                mTraceList.add(mtrace);
            }
       /* if (mOverlayList.containsKey(mSequenceLineID)) {
            TraceOverlay overlay = mOverlayList.get(mSequenceLineID);
            overlay.zoopToSpan();
            int status = overlay.getTraceStatus();
            String tipString = "";
            if (status == TraceOverlay.TRACE_STATUS_PROCESSING) {
                tipString = "该线路轨迹纠偏进行中...";
            } else if (status == TraceOverlay.TRACE_STATUS_FINISH) {
                tipString = "该线路轨迹已完成";
            } else if (status == TraceOverlay.TRACE_STATUS_FAILURE) {
                tipString = "该线路轨迹失败";
            } else if (status == TraceOverlay.TRACE_STATUS_PREPARE) {
                tipString = "该线路轨迹纠偏已经开始";
            }
            Toast.makeText(this.getApplicationContext(), tipString,
                    Toast.LENGTH_SHORT).show();
            return;
        }*/
            TraceOverlay mTraceOverlay = new TraceOverlay(aMap);
            mOverlayList.put(mSequenceLineID, mTraceOverlay);
            List<LatLng> mapList = traceLocationToMap1(mTraceList);
            mTraceOverlay.setProperCamera(mapList);
            mTraceClient = new LBSTraceClient(this.getApplicationContext());
            mTraceClient.queryProcessedTrace(mSequenceLineID, mTraceList, mCoordinateType, this);
        }
        refrashLocation(dataBean);
    }

    private void refrashLocation(List<GuijiBean> dataBean) {
        //移动到轨迹可视区域
        List<LatLng> latLngs = traceLocationToMap(dataBean);
        LatLngBounds.Builder newbounds = new LatLngBounds.Builder();
        for (int i = 0; i < latLngs.size(); i++) {//trajectorylist为轨迹集合
            newbounds.include(latLngs.get(i));
        }
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(newbounds.build(), DensityUtil.dip2px(MyApplication.getInstance(),25)));//第二个参数为四周留空宽度,
//        aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(newbounds.build(), 25));//第二个参数为四周留空宽度
    }

    @Override
    public void showUserTrackData(UserGuijiBean data) {
        String type = getIntent().getStringExtra("type");
        String driverLatitude = data.getLatitude();
        String driverLongitude = data.getLongtitude();
        if (StringUtil.NoNullOrEmpty(driverLatitude)&&StringUtil.NoNullOrEmpty(driverLongitude)){
            addMarker(new LatLng(Double.valueOf(driverLatitude),Double.valueOf(driverLongitude)),"司机位置",R.drawable.driver_marker);
        }
        List<List<GuijiBean>> dataBean = data.getList();
        if (dataBean.size()>0){
            List<GuijiBean> guijiBeans = dataBean.get(0);
            addGuijiLine(guijiBeans,true);
        }
    }

    private void addMarker(LatLng latLng,String name,int res) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(name);
        markerOptions.visible(true);
        markerOptions.setFlat(true);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), res));
        markerOptions.icon(bitmapDescriptor);
        aMap.addMarker(markerOptions);
    }

    /**
     * 轨迹纠偏点转换为地图LatLng
     *
     * @param traceLocationList
     * @return
     */
    public List<LatLng> traceLocationToMap(List<GuijiBean> traceLocationList) {
        List<LatLng> mapList = new ArrayList<LatLng>();
        for (GuijiBean location : traceLocationList) {
            LatLng latlng = new LatLng(location.getLatitude(),
                    location.getLongtitude());
            mapList.add(latlng);
        }
        return mapList;
    }
    /**
     * 轨迹纠偏点转换为地图LatLng
     *
     * @param traceLocationList
     * @return
     */
    public List<LatLng> traceLocationToMap1(List<TraceLocation> traceLocationList) {
        List<LatLng> mapList = new ArrayList<LatLng>();
        for (TraceLocation location : traceLocationList) {
            LatLng latlng = new LatLng(location.getLatitude(),
                    location.getLongitude());
            mapList.add(latlng);
        }
        return mapList;
    }

    //轨迹纠偏失败
    @Override
    public void onRequestFailed(int lineID, String errorInfo) {
        Log.d("tag", "onRequestFailed");
        if (mOverlayList.containsKey(lineID)) {
            TraceOverlay overlay = mOverlayList.get(lineID);
            overlay.setTraceStatus(TraceOverlay.TRACE_STATUS_FAILURE);
        }
    }

    /**
     * 轨迹纠偏过程回调
     */
    @Override
    public void onTraceProcessing(int lineID, int index, List<LatLng> segments) {
        Log.d("tag", "onTraceProcessing");
        if (segments == null) {
            return;
        }
        if (mOverlayList.containsKey(lineID)) {
            TraceOverlay overlay = mOverlayList.get(lineID);
            overlay.setTraceStatus(TraceOverlay.TRACE_STATUS_PROCESSING);
            overlay.add(segments);
        }
    }

    /**
     * 轨迹纠偏结束回调
     */
    @Override
    public void onFinished(int lineID, List<LatLng> linepoints, int distance,
                           int watingtime) {
        Log.d("tag", "onFinished");
        if (mOverlayList.containsKey(lineID)) {
            TraceOverlay overlay = mOverlayList.get(lineID);
            overlay.setTraceStatus(TraceOverlay.TRACE_STATUS_FINISH);
            overlay.setDistance(distance);
            overlay.setWaitTime(watingtime);
        }

    }
}
