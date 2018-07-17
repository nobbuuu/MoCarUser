package com.dream.moka.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.DriverCenter.DriverOrderDetailBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.DriverCenter.OrderDetailContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.DriverCenter.OrderDetailPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class GotoGetCarActivity extends BaseCommonActivity implements OrderDetailContract.View {

    @Inject
    OrderDetailPresenter mOrderDetailPresenter;
    @Bind(R.id.orderNo_tv)
    TextView mOrderNoTv;
    @Bind(R.id.carNo_tv)
    TextView mCarNoTv;
    @Bind(R.id.car_name)
    TextView mCarName;
    @Bind(R.id.sendName_tv)
    TextView mSendNameTv;
    @Bind(R.id.sendMobile_tv)
    TextView mSendMobileTv;
    @Bind(R.id.sendAddress_tv)
    TextView mSendAddressTv;
    @Bind(R.id.shopName_tv)
    TextView mShopNameTv;
    @Bind(R.id.shopPhone_tv)
    TextView mShopPhoneTv;
    @Bind(R.id.shopAddress_tv)
    TextView mShopAddressTv;
    @Bind(R.id.getOrder_dateTv)
    TextView mGetOrderDateTv;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.navBtn)
    TextView mNavBtn;
    @Bind(R.id.parentView)
    ScrollView mParentView;
    @Bind(R.id.navBtn1)
    TextView mNavBtn1;
    private String mTag;
    private String mStartLatitude;
    private String mStartLongitude;

    @Override
    public int getLayoutId() {
        return R.layout.activity_togetcar;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mOrderDetailPresenter.attachView(this);
    }

    @Override
    public String initTitleText() {
        return "待确认取车";
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

    @Override
    public void initDatas() {
        mLoadingDialog.show();
        String orderId = getIntent().getStringExtra("orderId");
        mTag = getIntent().getStringExtra("tag");
        Log.e("tag", "orderId=" + orderId);
        if (StringUtil.NoNullOrEmpty(orderId)) {
            mOrderDetailPresenter.getOrderDetail(orderId);
        }
    }

    @Override
    public void eventListener() {
        mNavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStartLatitude == null || mStartLatitude.equals("") || mStartLongitude == null || mStartLongitude.equals("")) {
                    ToastUtils.Toast_short("获取经纬度失败");
                } else {
                    openGuid(mStartLatitude, mStartLongitude, mParentView);
                }

            }
        });
        mNavBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStartLatitude == null || mStartLatitude.equals("") || mStartLongitude == null || mStartLongitude.equals("")) {
                    ToastUtils.Toast_short("获取经纬度失败");
                } else {
                    openGuid(mStartLatitude, mStartLongitude, mParentView);
                }

            }
        });
    }

    @OnClick({R.id.back_relay, R.id.sure_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.sure_tv:
                if (StringUtil.NoNullOrEmpty(orderId)) {
                    Intent intent = new Intent(mContext,GetCaredInputActivity.class);
                    intent.putExtra(Const.intentTag,orderId);
                    if (StringUtil.NoNullOrEmpty(backDriverId)){
                        intent.putExtra("isBackDriver",true);
                    }else {
                        intent.putExtra("isBackDriver",false);
                    }
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    private String orderId = "",backDriverId = "";

    @Override
    public void showOrderDetailData(DriverOrderDetailBean dataBean) {

        DriverOrderDetailBean.OrdersBean ordersBean = dataBean.getOrders();
        DriverOrderDetailBean.RepairShopBean repairShop = dataBean.getRepairShop();
        DriverOrderDetailBean.UserCarBean carBean = dataBean.getUserCar();
        if (ordersBean != null) {
            mOrderNoTv.setText(ordersBean.getOrderCode());
            if (mTag.equals("3")) {
                mSendAddressTv.setText(ordersBean.getSendAddress());
                mSendNameTv.setText(ordersBean.getSendName());
                mSendMobileTv.setText(ordersBean.getSendMobile());
                mStartLatitude = ordersBean.getStartLatitude();
                mStartLongitude = ordersBean.getStartLongitude();
                if (mStartLatitude != null && !mStartLatitude.equals("")) {
                    mNavBtn.setVisibility(View.VISIBLE);
                    mNavBtn1.setVisibility(View.GONE);
                } else {
                    mNavBtn.setVisibility(View.GONE);
                    mNavBtn1.setVisibility(View.GONE);
                }
            } else {
                mSendAddressTv.setText(ordersBean.getBackAddress());
                mSendNameTv.setText(ordersBean.getBackName());
                mSendMobileTv.setText(ordersBean.getBackMobile());
                if (repairShop != null) {
                    mStartLatitude = repairShop.getLatitude();
                    mStartLongitude = repairShop.getLongitude();
                    if (mStartLatitude != null && !mStartLatitude.equals("")) {
                        mNavBtn.setVisibility(View.GONE);
                        mNavBtn1.setVisibility(View.VISIBLE);
                    } else {
                        mNavBtn.setVisibility(View.GONE);
                        mNavBtn1.setVisibility(View.GONE);
                    }
                }

            }
            mGetOrderDateTv.setText(ordersBean.getUpdateDate());
            orderId = ordersBean.getId();
            backDriverId = ordersBean.getBackDriverId();
        }
        if (carBean != null) {
            mCarNoTv.setText(carBean.getCardNo());
            mCarName.setText(carBean.getCarname());
        }

        if (repairShop != null) {
            mShopNameTv.setText(repairShop.getName());
            mShopPhoneTv.setText(repairShop.getContactTel());
            mShopAddressTv.setText(repairShop.getAddress());
        }


    }

    @Override
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }



    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    //导航相关
    public void openGuid(final String endlat, final String endLong, View ParentLayout) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.map_pop_layout, null);
        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(ParentLayout, Gravity.BOTTOM, 0, 0);
        backgroundAlpha(6.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        view.findViewById(R.id.baidu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAvilible(mContext, "com.baidu.BaiduMap")) {//传入指定应用包名
                    double[] doubles = gcj02_To_Bd09(Double.valueOf(endlat), Double.valueOf(endLong));
                    openBaiduNavi(String.valueOf(doubles[0]), String.valueOf(doubles[1]));
                    popupWindow.dismiss();
                } else {//未安装
                    //market为路径，id为包名
                    //显示手机上所有的market商店
                    Toast.makeText(mContext, "您尚未安装百度地图", Toast.LENGTH_LONG).show();
                    Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }
            }
        });
        view.findViewById(R.id.gaode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAvilible(mContext, "com.autonavi.minimap")) {
                    openGaoDeNavi(endlat, endLong);
                    popupWindow.dismiss();
                } else {
                    Toast.makeText(mContext, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
                    Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }
            }
        });
        view.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });


    }

    /**
     * 打开百度地图导航客户端
     * intent = Intent.getIntent("baidumap://map/navi?location=34.264642646862,108.95108518068&type=BLK&src=thirdapp.navi.you
     * location 坐标点 location与query二者必须有一个，当有location时，忽略query
     * query    搜索key   同上
     * type 路线规划类型  BLK:躲避拥堵(自驾);TIME:最短时间(自驾);DIS:最短路程(自驾);FEE:少走高速(自驾);默认DIS
     *
     * @param endlat
     * @param endLong
     */
    private void openBaiduNavi(String endlat, String endLong) {
        StringBuffer stringBuffer = new StringBuffer("baidumap://map/navi?location=")
                .append(endlat).append(",").append(endLong).append("&type=TIME&src=thirdapp.navi.you");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(stringBuffer.toString()));
        intent.setPackage("com.baidu.BaiduMap");
        mContext.startActivity(intent);
    }

    /**
     * 启动高德App进行导航
     * sourceApplication 必填 第三方调用应用名称。如 amap
     * poiname           非必填 POI 名称
     * dev               必填 是否偏移(0:lat 和 lon 是已经加密后的,不需要国测加密; 1:需要国测加密)
     * style             必填 导航方式(0 速度快; 1 费用少; 2 路程短; 3 不走高速；4 躲避拥堵；5 不走高速且避免收费；6 不走高速且躲避拥堵；7 躲避收费和拥堵；8 不走高速躲避收费和拥堵))
     */
    private void openGaoDeNavi(String endlat, String endLong) {
        StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=")
                .append("dinglifang")
                .append("&lat=").append(endlat)
                .append("&lon=").append(endLong)
                .append("&dev=").append(0)
                .append("&style=").append(0);
        ;
//        if (!TextUtils.isEmpty(poiname)) {
//            stringBuffer.append("&poiname=").append(poiname);
//        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(stringBuffer.toString()));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage("com.autonavi.minimap");
        mContext.startActivity(intent);
    }

    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }


    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标
     *
     * @param lat
     * @param lon
     */
    public static double[] gcj02_To_Bd09(double lat, double lon) {
        double x = lon, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double tempLon = z * Math.cos(theta) + 0.0065;
        double tempLat = z * Math.sin(theta) + 0.006;
        double[] gps = {tempLat, tempLon};
        return gps;
    }

    public static double pi = 3.1415926535897932384626;
    public static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    public static double a = 6378245.0;
    public static double ee = 0.00669342162296594323;

}
