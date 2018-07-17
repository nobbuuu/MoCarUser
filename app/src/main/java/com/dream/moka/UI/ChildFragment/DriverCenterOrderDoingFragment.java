package com.dream.moka.UI.ChildFragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Bean.DriverHomeResultBean;
import com.dream.moka.Bean.InTheRelayResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.DriverCenterOrderDoingContract;
import com.dream.moka.Presenter.DriverCenterOrderDoingPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.DaiJiaActivity;
import com.dream.moka.UI.Activity.GuijiMapActivity;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dream.moka.Utils.LshContextUtils.getSystemService;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
public class DriverCenterOrderDoingFragment extends BaseFragmentV4 implements DriverCenterOrderDoingContract {
    @Inject
    DriverCenterOrderDoingPresenter mDriverCenterOrderDoingPresenter;
    @Bind(R.id.emptylayout)
    TextView mEmptylayout;
    @Bind(R.id.guiji_tv)
    TextView mGuijiTv;
    @Bind(R.id.daijia_tv)
    TextView mDaijiaTv;
    @Bind(R.id.nomalLayout)
    LinearLayout mNomalLayout;
    @Bind(R.id.orderNo)
    TextView mOrderNo;
    @Bind(R.id.carNo)
    TextView mCarNo;
    @Bind(R.id.carType)
    TextView mCarType;
    @Bind(R.id.userQQ)
    TextView mUserQQ;
    @Bind(R.id.managerQQ)
    TextView mManagerQQ;
    @Bind(R.id.orderTime)
    TextView mOrderTime;
    @Bind(R.id.jcName_phone)
    TextView mJcNamePhone;
    @Bind(R.id.jc_address)
    TextView mJcAddress;
    @Bind(R.id.scName_phone)
    TextView mScNamePhone;
    @Bind(R.id.sc_address)
    TextView mScAddress;
    protected boolean isCreate = false;
    @Bind(R.id.qqCopy1)
    TextView mQqCopy1;
    @Bind(R.id.qqCopy2)
    TextView mQqCopy2;
    @Bind(R.id.navBtn)
    TextView mNavBtn;
    @Bind(R.id.parentView)
    RelativeLayout mParentView;

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
        mDriverCenterOrderDoingPresenter.attachView(this);
        mNomalLayout.setVisibility(View.VISIBLE);
        mEmptylayout.setVisibility(View.GONE);
        EventBus.getDefault().register(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            //相当于Fragment的onResume
            //在这里处理加载数据等操作
            loadData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain2(String eventStr) {
        if (eventStr.equals("Ordering")) {
            loadData();
        }
    }

    private void loadData() {
        mLoadingDialog.show();
        mDriverCenterOrderDoingPresenter.getDongingOrder();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_doingorder;
    }

    @Override
    public void initResume() {

    }

    @Override
    public void initEvents() {
        mQqCopy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCopy(mQqCopy1);
            }
        });
        mQqCopy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCopy(mQqCopy2);
            }
        });
        mNavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChooseLat == null || mChooseLat.equals("") || mChooseLng == null || mChooseLng.equals("")) {
                    ToastUtils.Toast_short("获取经纬度失败");
                } else {
                    openGuid(mChooseLat, mChooseLng, mParentView);
                }
            }
        });

    }

    @Override
    public void initDta() {
        loadData();
    }

    @OnClick({R.id.guiji_tv, R.id.daijia_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guiji_tv:
                if (StringUtil.NoNullOrEmpty(driverHisId)){
                    IntentUtils.toActivityWithTag(GuijiMapActivity.class, getActivity(),driverHisId);
                }
                break;
            case R.id.daijia_tv:
                IntentUtils.toActivity(DaiJiaActivity.class, getActivity());
                break;
        }
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

    private String mChooseLat = "";
    private String mChooseLng = "";
    private String driverHisId = "";

    @Override
    public void getDataSuccess(InTheRelayResultBean results) {
        if (results != null) {
            driverHisId = results.getDriverHisId();
            if (StringUtil.NoNullOrEmpty(driverHisId)){
                mGuijiTv.setVisibility(View.VISIBLE);
            }
            InTheRelayResultBean.OrderBean order = results.getOrder();
            if (order != null) {
                String orderCode = order.getOrderCode();
                mOrderNo.setText(orderCode + "");
                String status = order.getStatus();
                if (status.equals("4")) {
                    String jcSendTime = order.getJcSendTime();
                    mOrderTime.setText("" + jcSendTime);//派单时间
                    String sendName = order.getSendName();
                    String sendMobile = order.getSendMobile();
                    String sendAddress = order.getSendAddress();
                    mJcNamePhone.setText(sendName + "  " + sendMobile);
                    mJcAddress.setText(sendAddress);

                    DriverHomeResultBean.repairShopBean repairShop = results.getRepairShop();
                    if (repairShop != null) {
                        String name = repairShop.getName();
                        String contactTel = repairShop.getContactTel();
                        String address = repairShop.getAddress();
                        mScNamePhone.setText(name + "  " + contactTel);
                        mScAddress.setText(address);
                        mNavBtn.setVisibility(View.VISIBLE);
                        mChooseLat = repairShop.getLatitude();
                        mChooseLng = repairShop.getLongitude();
                    }

                } else if (status.equals("9")) {
                    String scSendTime = order.getScSendTime();
                    mOrderTime.setText("" + scSendTime);//派单时间

                    DriverHomeResultBean.repairShopBean repairShop = results.getRepairShop();
                    if (repairShop != null) {
                        String name = repairShop.getName();
                        String contactTel = repairShop.getContactTel();
                        String address = repairShop.getAddress();

                        mJcNamePhone.setText(name + "  " + contactTel);
                        mJcAddress.setText(address);
                    }

                    String backName = order.getBackName();
                    String backMobile = order.getBackMobile();
                    String backAddress = order.getBackAddress();
                    mScNamePhone.setText(backName + "  " + backMobile);
                    mScAddress.setText(backAddress);
                    mNavBtn.setVisibility(View.VISIBLE);
                    mChooseLat = order.getLastLatitude();
                    mChooseLng = order.getLastLongitude();

                }

                String createDate = order.getCreateDate();
                if (StringUtil.NoNullOrEmpty(createDate)) {
                    mEmptylayout.setVisibility(View.GONE);
                    mNomalLayout.setVisibility(View.VISIBLE);
                } else {
                    mEmptylayout.setVisibility(View.VISIBLE);
                    mNomalLayout.setVisibility(View.GONE);
                }
            }
            InTheRelayResultBean.UserBean user = results.getUser();
            if (user != null) {
                String qq = user.getQq();
                mUserQQ.setText("" + qq);
            }
            InTheRelayResultBean.UserCarBean userCar = results.getUserCar();
            if (userCar != null) {
                String cardNo = userCar.getCardNo();
                mCarNo.setText("" + cardNo);
                String carname = userCar.getCarname();
                mCarType.setText(carname + "");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }


    public void onClickCopy(TextView v) {
        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(v.getText());
        Toast.makeText(getContext(), "复制成功", Toast.LENGTH_LONG).show();
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    //导航相关
    public void openGuid(final String endlat, final String endLong, View ParentLayout) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.map_pop_layout, null);
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
                if (isAvilible(getActivity(), "com.baidu.BaiduMap")) {//传入指定应用包名
                    double[] doubles = gcj02_To_Bd09(Double.valueOf(endlat), Double.valueOf(endLong));
                    openBaiduNavi(String.valueOf(doubles[0]), String.valueOf(doubles[1]));
                    popupWindow.dismiss();
                } else {//未安装
                    //market为路径，id为包名
                    //显示手机上所有的market商店
                    Toast.makeText(getActivity(), "您尚未安装百度地图", Toast.LENGTH_LONG).show();
                    Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    getActivity().startActivity(intent);
                }
            }
        });
        view.findViewById(R.id.gaode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAvilible(getActivity(), "com.autonavi.minimap")) {
                    openGaoDeNavi(endlat, endLong);
                    popupWindow.dismiss();
                } else {
                    Toast.makeText(getActivity(), "您尚未安装高德地图", Toast.LENGTH_LONG).show();
                    Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    getActivity().startActivity(intent);
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
        getActivity().startActivity(intent);
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
        getActivity().startActivity(intent);
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
