package com.dream.moka.UI.Activity.maintain;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Adapter.Orders.ProgramAdapter;
import com.dream.moka.Base.BaseActivityRelay;
import com.dream.moka.Bean.AddServiceUserCarResultBean;
import com.dream.moka.Bean.BaoYangFangAnResultBean;
import com.dream.moka.Bean.CarDetailResultBean;
import com.dream.moka.Bean.ChooseCarInfoBean;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.OneChooseCarContract;
import com.dream.moka.Contract.OneMaintainContract;
import com.dream.moka.CumstomView.numberview.KeyBoardAdapter;
import com.dream.moka.CumstomView.numberview.VirtualKeyboardView;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.OneChooseCarPresenter;
import com.dream.moka.Presenter.OneMainTainPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.SureOrderActivity;
import com.dream.moka.UI.Activity.mycar.MyCarsActivity;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
public class OnekeyMaintainActivity extends BaseActivityRelay implements OneMaintainContract.View, OneChooseCarContract {

    @Inject
    OneMainTainPresenter mOneMainTainPresenter;
    @Inject
    OneChooseCarPresenter mOneChooseCarPresenter;
    @Bind(R.id.maintain_rv)
    RecyclerView maintain_rv;
    @Bind(R.id.car_milesedt)
    TextView car_milesedt;
    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.car_name)
    TextView mCarName;
    @Bind(R.id.edt_iv)
    TextView mEdtIv;
    @Bind(R.id.car_icon)
    ImageView mCarIcon;
    @Bind(R.id.currentkm_tv)
    TextView mCurrentkmTv;
    @Bind(R.id.right_lay)
    LinearLayout mRightLay;
    @Bind(R.id.line2)
    RelativeLayout mLine2;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.numberView)
    VirtualKeyboardView mNumberView;
    @Bind(R.id.openMyCar)
    ImageView mOpenMyCar;
    @Bind(R.id.baseRadio)
    TextView mBaseRadio;
    @Bind(R.id.taocanRadio)
    TextView mTaocanRadio;
    @Bind(R.id.problem)
    ImageView mProblem;
    @Bind(R.id.fuwuGroup)
    LinearLayout mFuwuGroup;
    @Bind(R.id.shiFu)
    TextView mShiFu;
    @Bind(R.id.yuanJia)
    TextView mYuanJia;
    @Bind(R.id.shengJia)
    TextView mShengJia;
    @Bind(R.id.baoyanggongli_txt)
    TextView mBaoyanggongliTxt;

    private ProgramAdapter programAdapter;
    private List<BaoYangFangAnResultBean.ListBaseBean> gramList = new ArrayList<>();
    private List<BaoYangFangAnResultBean.ListBaseBean> mTempListBase = new ArrayList<>();
    private List<BaoYangFangAnResultBean.ListBaseBean> mTempListPart = new ArrayList<>();

    private GridView gridView;
    private ArrayList<Map<String, String>> valueList;
    private String mCarId;
    private String mRecomMaintMile;
    private String mLatestMile;
    private String mInputMile;
    private String mMile;
    private String mIsFirstTime;
    private Dialog mLoadingDialog;
    private boolean baseSelect = false;
    private boolean taocanSelect = false;
    private String mPayMoney = "0";
    private String mCardNo;
    private String planTargetId = "";

    public static void openAct(Context context, String carId, String recomMaintMile, String latestMile, String mile, String isFirstTime, String carName, String cardNo,String inputMile) {
        Intent intent = new Intent(context, OnekeyMaintainActivity.class);
        intent.putExtra("carId", carId);
        intent.putExtra("recomMaintMile", recomMaintMile);
        intent.putExtra("latestMile", latestMile);
        intent.putExtra("mile", mile);
        intent.putExtra("isFirstTime", isFirstTime);
        intent.putExtra("carName", carName);
        intent.putExtra("cardNo", cardNo);
        intent.putExtra("inputMile", inputMile);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_maintain;
    }

    @Override
    public void initView() {
        BaseComponent build = DaggerBaseComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
//                .mainActivityModule(new MainActivityModule(this))
                .build();
        build.inject(this);
        RvUtils.setOptionnoLineforScroll(maintain_rv, this);
        programAdapter = new ProgramAdapter(this, gramList, R.layout.rvitem_program);
        maintain_rv.setAdapter(programAdapter);
        maintain_rv.setNestedScrollingEnabled(false);
        mOneMainTainPresenter.attachView(this);
        mOneChooseCarPresenter.attachView(this);
        gridView = mNumberView.getGridView();
        initValueList();
        setupView();
        mCarId = getIntent().getStringExtra("carId");
        mCardNo = getIntent().getStringExtra("cardNo");
        mRecomMaintMile = getIntent().getStringExtra("recomMaintMile");
        mLatestMile = getIntent().getStringExtra("latestMile");
        mMile = getIntent().getStringExtra("mile");
        mInputMile = getIntent().getStringExtra("inputMile");
        mIsFirstTime = getIntent().getStringExtra("isFirstTime");
        String carName = getIntent().getStringExtra("carName");
        mCarName.setText(carName);
        EventBus.getDefault().register(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        if (mIsFirstTime.equals("true")) {
            mBaoyanggongliTxt.setText("保养间隔公里数");
            car_milesedt.setText(mRecomMaintMile);
            textCode = mRecomMaintMile;
        } else {
            mBaoyanggongliTxt.setText("行使总里程数");
            if (StringUtil.NoNullOrEmpty(mInputMile)){
                car_milesedt.setText(mInputMile);
            }
            textCode = mInputMile;
        }
    }


    // 这里，我们没有使用默认的数字键盘，因为第10个数字不显示.而是空白
    private void initValueList() {
        valueList = new ArrayList<>();
        // 初始化按钮上应该显示的数字
        for (int i = 1; i < 13; i++) {
            Map<String, String> map = new HashMap<String, String>();
            if (i < 10) {
                map.put("name", String.valueOf(i));
            } else if (i == 10) {
                map.put("name", "");
            } else if (i == 11) {
                map.put("name", String.valueOf(0));
            } else if (i == 12) {
                map.put("name", "");
            }
            valueList.add(map);
        }
    }

    private String textCode = "";

    private void setupView() {
        // 这里、重新为数字键盘gridView设置了Adapter
        KeyBoardAdapter keyBoardAdapter = new KeyBoardAdapter(mContext, valueList);
        gridView.setAdapter(keyBoardAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < 11 && position != 9) {    //点击0~9按钮
                    String name = valueList.get(position).get("name");
                    textCode = textCode + name;
                    car_milesedt.setText(textCode);
                } else {
                    if (position == 11) {      //点击退格键
                        if (textCode.length() > 0) {
                            textCode = textCode.substring(0, textCode.length() - 1);
                            car_milesedt.setText(textCode);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {
        mLoadingDialog.show();
        getData();
    }

    /**
     * 请求保养方案数据
     */
    private void getData() {
//            String token,//用户唯一标识（string/50/Y)
//            String carId,//车辆id（string/50/Y)
//            String recomMaintMile：间隔公里数
//            String latestMile：上次保养公里数
//            String mile：总行驶公里数
//            String isFirstTime：是否第一次开启服务
        String token = CommonAction.getToken();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("carId", mCarId);
        map.put("latestMile", mLatestMile);
        map.put("isFirstTime", mIsFirstTime);
        if (mIsFirstTime.equals("true")) {
            map.put("recomMaintMile", textCode);
            map.put("mile", mMile);
        } else {
            map.put("recomMaintMile", "");
            map.put("mile", textCode);
        }
        mOneMainTainPresenter.getMaintenancePlan(map);
    }

    @Override
    public void eventListener() {
        mNumberView.getCancle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNumberView.setVisibility(View.GONE);
                car_milesedt.setText(mRecomMaintMile);
                textCode = mRecomMaintMile;
            }
        });
        mNumberView.getYes().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNumberView.setVisibility(View.GONE);
                if (mIsFirstTime.equals("true")) {
                    if (Integer.valueOf(textCode) < 5000) {
                        ToastUtils.Toast_short("您的爱车未到保养周期，有需求请咨询技师");
                        car_milesedt.setText(mRecomMaintMile);
                        textCode = mRecomMaintMile;
                        return;
                    }
                    if (Integer.valueOf(textCode) > 12000) {
                        ToastUtils.Toast_short("您的爱车超过保养周期，有需求请咨询技师");
                        car_milesedt.setText(mRecomMaintMile);
                        textCode = mRecomMaintMile;
                        return;
                    }
                    mLoadingDialog.show();
                    getData();
                } else {
                    mLoadingDialog.show();
                    getData();
                }


            }
        });

        mProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DialogUtils
            }
        });

    }

    @OnClick({R.id.back_relay, R.id.sure_tv, R.id.edt_iv, R.id.openMyCar, R.id.baseRadio, R.id.taocanRadio})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openMyCar:
                MyCarsActivity.openAct(mActivity, "one");
                break;
            case R.id.back_relay:
                finish();
                break;
            case R.id.edt_iv:
                mNumberView.setVisibility(View.VISIBLE);
                textCode = "";
                car_milesedt.setText("");
                break;
            case R.id.sure_tv:
                if (TextUtils.isEmpty(car_milesedt.getText())) {
                    if (mIsFirstTime.equals("true")) {
                        ToastUtils.Toast_short("请填写保养间隔公里数");
                    } else {
                        ToastUtils.Toast_short("请填写行使总里程数");
                    }
                    return;
                }
                if (planTargetId.equals("") || gramList.size() == 0) {
                    ToastUtils.Toast_short("请确定保养项目");
                    return;
                }
                mLoadingDialog.show();
                mOneMainTainPresenter.getData(mCarId, planTargetId, mMile);
                break;
            case R.id.taocanRadio:
                if (!taocanSelect) {
                    taocanSelect = true;
                    baseSelect = false;
                    setDrawableLeft(mBaseRadio, R.mipmap.icon_radio_default);
                    setDrawableLeft(mTaocanRadio, R.mipmap.icon_radio_selected);
                    if (gramList != null) {
                        gramList.clear();
                    }
                    gramList.addAll(mTempListPart);
                    programAdapter.notifyDataSetChanged();
//                    String yuanjia = getYuanjia(gramList, true);
//                    mYuanJia.setText(yuanjia);
                    String shifu = getShifu(mPayMoney, true);
                    mShiFu.setText(shifu);
//                    mShengJia.setText(String.valueOf(Double.valueOf(yuanjia)-Double.valueOf(shifu)));
                    serviceType = "1";
                    planTargetId = packegePlantId;
                }
                break;
            case R.id.baseRadio:
                if (!baseSelect) {
                    baseSelect = true;
                    taocanSelect = false;
                    setDrawableLeft(mBaseRadio, R.mipmap.icon_radio_selected);
                    setDrawableLeft(mTaocanRadio, R.mipmap.icon_radio_default);
                    if (gramList != null) {
                        gramList.clear();
                    }
                    gramList.addAll(mTempListBase);
                    programAdapter.notifyDataSetChanged();
//                    String yuanjia = getYuanjia(gramList, false);
//                    mYuanJia.setText(yuanjia);
                    String shifu = getShifu(mPayMoney, false);
                    mShiFu.setText(shifu);
//                    mShengJia.setText(String.valueOf(Double.valueOf(yuanjia)-Double.valueOf(shifu)));
                    serviceType = "0";
                    planTargetId = basePlantId;
                }
                break;
        }
    }

    private String serviceType = "0";
    private String basePlantId,packegePlantId;
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

    @Override
    public void showData(BaoYangFangAnResultBean dataBean) {
        if (dataBean != null) {
            BaoYangFangAnResultBean.PlanTargetBean planTarget = dataBean.getPlanTarget();
            BaoYangFangAnResultBean.PlanTargetBean planTargetPackage = dataBean.getPlanTargetPackage();

            if (planTargetPackage != null) {
                planTargetId = planTargetPackage.getId();
                packegePlantId = planTargetPackage.getId();
            }

            mPayMoney = dataBean.getPayMoney();
            String payMoney = mPayMoney;
            List<BaoYangFangAnResultBean.ListBaseBean> listBase = dataBean.getListBase();
            if (listBase.size() > 0) {//40000以上

                if (planTarget != null) {
                    basePlantId = planTarget.getId();
                    String name = planTarget.getName();
                    mBaseRadio.setText("基础服务" + "（" + name + "保养）");
                }
                mFuwuGroup.setVisibility(View.VISIBLE);
                if (mTempListBase != null) {
                    mTempListBase.clear();
                }
                mTempListBase.addAll(listBase);

                List<BaoYangFangAnResultBean.ListBaseBean> listPackage = dataBean.getListPackage();
                if (listPackage.size() > 0) {
                    if (mTempListPart != null) {
                        mTempListPart.clear();
                    }
                    mTempListPart.addAll(listPackage);

                }

                if (baseSelect) {
                    if (gramList != null) {
                        gramList.clear();
                    }
                    gramList.addAll(mTempListBase);
                    programAdapter.notifyDataSetChanged();
//                    String yuanjia = getYuanjia(gramList, false);
//                    mYuanJia.setText(yuanjia);
                    String shifu = getShifu(mPayMoney, false);
                    mShiFu.setText(shifu);
//                    mShengJia.setText(String.valueOf(Double.valueOf(yuanjia)-Double.valueOf(shifu)));
                } else if (taocanSelect) {
                    if (gramList != null) {
                        gramList.clear();
                    }
                    gramList.addAll(mTempListPart);
                    programAdapter.notifyDataSetChanged();
//                    String yuanjia = getYuanjia(gramList, true);
//                    mYuanJia.setText(yuanjia);
                    String shifu = getShifu(mPayMoney, true);
                    mShiFu.setText(shifu);
//                    mShengJia.setText(String.valueOf(Double.valueOf(yuanjia)-Double.valueOf(shifu)));
                }

            } else {//40000一下
                mFuwuGroup.setVisibility(View.GONE);
                List<BaoYangFangAnResultBean.ListBaseBean> listPackage = dataBean.getListPackage();
                if (listPackage.size() > 0) {
                    if (gramList.size() != 0) {
                        gramList.clear();
                    }
                    gramList.addAll(listPackage);
                    programAdapter.notifyDataSetChanged();

//                    String yuanjia = getYuanjia(listPackage, false);
//                    mYuanJia.setText(yuanjia);
                    String shifu = getShifu(payMoney, false);
                    mShiFu.setText(shifu);
//                    mShengJia.setText("省¥"+String.valueOf(Double.valueOf(yuanjia)-Double.valueOf(shifu)));
                }

            }

        }

    }

    /**
     * 确认订单成功
     *
     * @param results
     */
    @Override
    public void getDataSuccess(ConfirmOrderResultBean results) {
        SureOrderActivity.openAct(mContext, "onekey", mShiFu.getText().toString().trim(), mYuanJia.getText().toString().trim()
                , planTargetId, mCarId, mMile, gramList, results,serviceType);
        finish();
    }

    /**
     * 计算实际支付
     */
    private String getShifu(String payMoney, boolean more) {
        if (more) {
            return String.valueOf(Double.valueOf(payMoney) * 4);
        } else {
            return String.valueOf(Double.valueOf(payMoney));
        }
    }

//    /**
//     * 计算原价
//     */
//    private String getYuanjia(List<BaoYangFangAnResultBean.ListBaseBean> listPackage, boolean isMore) {
//        Double Totalprice = 0.0;
//        for (int i = 0; i < listPackage.size(); i++) {
//            BaoYangFangAnResultBean.ListBaseBean listBaseBean = listPackage.get(i);
//            String price = listBaseBean.getServiceItem().getPrice();
//            List<BaoYangFangAnResultBean.ListBaseBean.ListPartBean> listPart = listBaseBean.getListPart();
//            Double listPrice = 0.0;
//            for (int j = 0; j < listPart.size(); j++) {
//                String price1 = listPart.get(j).getPrice();
//                String count = listPart.get(j).getCount();
////                double v = Double.valueOf(price1.equals("") ? "0" : price1) * Double.valueOf(count);
//                double v = Double.valueOf(price1.equals("") ? "0" : price1) * Double.valueOf("1");
//                listPrice += v;
//            }
//            Double aDouble = Double.valueOf(price.equals("") ? "0" : price);
//            aDouble += listPrice;
//            Totalprice += aDouble;
//        }
//        return String.valueOf(isMore ? (Totalprice * 4) : Totalprice);
//    }


    @Subscribe
    public void onEvent(ChooseCarInfoBean chooseCarInfoBean) {
        String carId = chooseCarInfoBean.getCarId();
        if (!carId.equals("")) {
            mLoadingDialog.show();
            mOneChooseCarPresenter.getCarInfo(carId);
            baseSelect = false;
            taocanSelect = false;
            gramList.clear();
            programAdapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 获取车辆详情
     *
     * @param carDetailResultBean
     */
    @Override
    public void onSuccess(CarDetailResultBean carDetailResultBean) {
        CarDetailResultBean.UserCarBean userCar = carDetailResultBean.getUserCar();
        if (userCar != null) {
            String registerCity = userCar.getRegisterCity();
            String carname = userCar.getCarname();
            String lastMainDate = userCar.getLastMainDate();
            String cardNo = userCar.getCardNo();
            String mileage = userCar.getMileage();
            String carConfigureId = userCar.getCarConfigureId();
            mLoadingDialog.show();
            mOneChooseCarPresenter.addServiceUserCar(carConfigureId, carname, registerCity, lastMainDate, cardNo, mileage);
        }
    }

    /**
     * 0.1添加车辆
     *
     * @param results
     */
    @Override
    public void addServiceUserCarSuccess(AddServiceUserCarResultBean results) {
        if (results != null) {
            String isFirstTime = results.getIsFirstTime();
            String latestMile = results.getLatestMile();
            AddServiceUserCarResultBean.UserCarBean userCar = results.getUserCar();
            if (userCar != null) {
                String id = userCar.getId();
                String recomMaintMile = userCar.getRecomMaintMile();
                String mileage = userCar.getMileage();
                String carname = userCar.getCarname();
                String cardNo = userCar.getCardNo();
                mCarName.setText(carname);

                mCarId = id;
                mCardNo = cardNo;
                mRecomMaintMile = recomMaintMile;
                car_milesedt.setText(mRecomMaintMile);
                textCode = mRecomMaintMile;
                mLatestMile = latestMile;
//                mMile = mileage;
                mIsFirstTime = isFirstTime;
                mLoadingDialog.show();
                getData();
            }

        }
    }

    @Override
    public void showNoCar() {

    }


    /**
     * 设置textview 的drawable属性
     *
     * @param attention
     * @param drawableId
     */
    private void setDrawableLeft(TextView attention, int drawableId) {
        Drawable drawable = getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        attention.setCompoundDrawables(drawable, null, null, null);
    }
}
