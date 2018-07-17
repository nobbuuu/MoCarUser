package com.dream.moka.UI.Activity.maintain;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.BaseCommonTakePhotoActivity;
import com.dream.moka.Bean.AddServiceUserCarResultBean;
import com.dream.moka.Bean.CarDetailResultBean;
import com.dream.moka.Bean.ChooseCarInfoBean;
import com.dream.moka.Bean.Other.CarsbBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.CardsbContract;
import com.dream.moka.Contract.DriverUserInfContract;
import com.dream.moka.Contract.OneChooseCarContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.CardsbPresenter;
import com.dream.moka.Presenter.DriverUserInfoPresenter;
import com.dream.moka.Presenter.OneChooseCarPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.CityChooseActivity;
import com.dream.moka.UI.Activity.mycar.CarsChooseActivity;
import com.dream.moka.UI.Activity.mycar.MyCarsActivity;
import com.dream.moka.UI.Activity.other.OpenServiceActivity;
import com.dream.moka.Utils.CheckForAllUtils;
import com.dream.moka.Utils.CustomHelper;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.Dialog.CarNoFirstDialog;
import com.dream.moka.Utils.Dialog.CarNoKeyboardDialog;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.ImmUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.TakePhotoUtils;
import com.dream.moka.Utils.ToastUtils;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 0.1保养开启时选择车辆等信息的页面
 */

public class OneChooseCarActivity extends BaseCommonTakePhotoActivity implements OneChooseCarContract, DriverUserInfContract, CardsbContract.View {

    @Inject
    OneChooseCarPresenter mOneChooseCarPresenter;
    @Bind(R.id.carcity_tv)
    TextView mCarcityTv;
    @Bind(R.id.city_relay)
    RelativeLayout mCityRelay;
    @Bind(R.id.car)
    TextView mCar;
    @Bind(R.id.car_nametv)
    TextView mCarNametv;
    @Bind(R.id.right_iv)
    ImageView mRightIv;
    @Bind(R.id.car_relay)
    RelativeLayout mCarRelay;
    @Bind(R.id.baoy_timetv)
    TextView mBaoyTimetv;
    @Bind(R.id.carNoFirst_tv)
    TextView mCarNoFirstTv;
    @Bind(R.id.img_dd)
    ImageView mImgDd;
    @Bind(R.id.baoy_time)
    RelativeLayout mBaoyTime;
    @Bind(R.id.carnum_edt)
    EditText mCarnumEdt;
    @Bind(R.id.car_miles_edt)
    EditText mCarMilesEdt;
    @Bind(R.id.total_miles_edt)
    EditText mTotalMilesEdt;
    @Bind(R.id.tobaoy_tv)
    TextView mTobaoyTv;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    private Dialog mLoadingDialog;
    private boolean hasDefultCar = false;//是否有默认车辆
    private TimePickerView dateDialog;
    private static final int CITYCODE = 101;
    private String mConfigId = "";

    @Inject
    DriverUserInfoPresenter mDriverUserInfoPresenter;
    @Inject
    CardsbPresenter mCardsbPresenter;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mCarnumEdt.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (!hasDefultCar){
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        if (event.getAction() == KeyEvent.ACTION_DOWN) {
                            mCarnumEdt.requestFocus();
                            new CarNoKeyboardDialog(mActivity, mCarnumEdt) {
                                @Override
                                public void onResult(int type,String inputStr) {
                                    if (type==2){
                                       /* String firstStr = mCarNoFirstTv.getText().toString();
                                        if (!CheckForAllUtils.isCarnumberNO(firstStr+inputStr)){
                                            ToastUtils.Toast_long("请填写正确的车牌号");
                                        }else {
                                            getCurrentFocus().clearFocus();
                                        }*/
                                        getCurrentFocus().clearFocus();
                                    }
                                    mCarnumEdt.setText(inputStr);
                                }
                            };
                        }
                    }
                    return true;
                }
            });
        }
    };
    public static void openAct(Context context, String from) {
        Intent intent = new Intent(context, OneChooseCarActivity.class);
        intent.putExtra("from", from);
        if (from.equals("choose")) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_one_choose_car;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mOneChooseCarPresenter.attachView(this);
        mDriverUserInfoPresenter.attachView(this);
        mCardsbPresenter.attachView(this);
        EventBus.getDefault().register(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        dateDialog = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                long time = date.getTime();
                String dateStr = DateFormatUtil.getTime(time, Const.Y_M_D);
                if (dateStr != null) {
                    mBaoyTimetv.setText(dateStr);
                }
                Log.e("tag", "dateStr=" + dateStr);
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setContentSize(16)//滚轮文字大小
                .setTitleSize(13)//标题文字大小
                .setCancelText("取消")//取消按钮文字
                .setLabel(" 年", "月", "日", "时", "分", "秒")
                .isCyclic(true)//是否循环滚动
                .build();
        mLoadingDialog.show();
        mOneChooseCarPresenter.getDefultCar();
    }

    @Subscribe
    public void onEvent(ChooseCarInfoBean chooseCarInfoBean) {
        String carName = chooseCarInfoBean.getCarName();
        mConfigId = chooseCarInfoBean.getConfigId();
        mCarNametv.setText(carName);
        String carId = chooseCarInfoBean.getCarId();
        if (StringUtil.NoNullOrEmpty(carId)) {
            mOneChooseCarPresenter.getCarInfo(carId);
        } else {
            right_tv.setVisibility(View.VISIBLE);
            mCarnumEdt.setText("");
            mCarMilesEdt.setText("");
            mTotalMilesEdt.setText("");
            mBaoyTimetv.setText("");
            hasDefultCar = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public String initTitleText() {
        return "填写爱车信息";
    }

    @Override
    public String initRightTv() {
        return "行驶证识别";
    }

    @Override
    public boolean isRighttv() {
        String carNo = mCarnumEdt.getText().toString();
        if (StringUtil.NoNullOrEmpty(carNo)) {
            return false;
        }
        return true;
    }

    @Override
    public void loadResum() {
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void eventListener() {
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
     * 获取默认车辆成功
     *
     * @param carDetailResultBean
     */
    @Override
    public void onSuccess(CarDetailResultBean carDetailResultBean) {

        CarDetailResultBean.UserCarBean userCar = carDetailResultBean.getUserCar();
        if (userCar != null) {
            hasDefultCar = true;
            String registerCity = userCar.getRegisterCity();
            String carname = userCar.getCarname();
            String lastMainDate = userCar.getLastMainDate();
            String cardNo = userCar.getCardNo();
            String mileage = userCar.getMileage();
            mCarcityTv.setText(registerCity == null ? "" : registerCity);
            mCarNametv.setText(carname == null ? "" : carname);
            mBaoyTimetv.setText(StringUtil.NoNullOrEmpty(lastMainDate) ? lastMainDate.substring(0, 10) : "");
            if (StringUtil.NoNullOrEmpty(cardNo)){
                String firstStr = cardNo.substring(0, 1);
                mCarNoFirstTv.setText(firstStr);
                String carNoStr = cardNo.substring(1, cardNo.length());
                mCarnumEdt.setFocusable(false);
                mCarnumEdt.setFocusableInTouchMode(false);
                mCarnumEdt.setText(carNoStr);
            }
            mTotalMilesEdt.setText(mileage);
            mConfigId = userCar.getCarConfigureId();
            right_tv.setVisibility(View.GONE);
        } else {
            hasDefultCar = false;
            right_tv.setVisibility(View.VISIBLE);
        }
        mHandler.sendEmptyMessage(010);
    }

    /**
     * 检查车辆信息或者添加成功
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
                OnekeyMaintainActivity.openAct(mContext, id, recomMaintMile, latestMile, mTotalMilesEdt.getText().toString(), isFirstTime, carname, cardNo, mTotalMilesEdt.getText().toString());
            }
        }
    }

    @Override
    public void showNoCar() {

    }

    @OnClick({R.id.city_relay, R.id.car_relay, R.id.tobaoy_tv, R.id.baoy_time, R.id.right_tv, R.id.carNoFirst_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.city_relay://选择城市
                CityChooseActivity.openAct(mActivity, CITYCODE);
                break;
            case R.id.car_relay://选择车辆
                boolean isLogin = CommonAction.getIsLogin();
                if (!isLogin) {
                    CarsChooseActivity.openAct(mActivity, "one");
                } else {//已登录
                    if (hasDefultCar) {
                        MyCarsActivity.openAct(mActivity, "one");
                    } else {
                        CarsChooseActivity.openAct(mActivity, "one");
                    }
                }
                break;
            case R.id.baoy_time://选择保养时间
                dateDialog.show();
                break;
            case R.id.carNoFirst_iv:
                if (!hasDefultCar){
                    new CarNoFirstDialog(mActivity) {
                        @Override
                        public void onResult(String inputStr) {
                            mCarNoFirstTv.setText(inputStr);
                        }
                    };
                }
                break;
            case R.id.tobaoy_tv:

                if (!CommonAction.getIsLogin()) {
                    DialogUtils.getLoginTip(mActivity).show();
                } else {
                    if (CommonAction.getIsOpenService()) {
                        String carName = mCarNametv.getText().toString();
                        if (carName.isEmpty()) {
                            ToastUtils.Toast_short("请选择车辆");
                        } else {
                            if (checkEmpty()) {
                                mOneChooseCarPresenter.addServiceUserCar(mConfigId, carName, mCarcityTv.getText().toString().trim(), mBaoyTimetv.getText().toString().trim()
                                        , mCarNoFirstTv.getText().toString()+mCarnumEdt.getText().toString().trim(), mTotalMilesEdt.getText().toString().trim());

                            }
                        }
                    } else {
                        IntentUtils.toActivity(OpenServiceActivity.class, mActivity);
                    }
                }

                break;
            case R.id.right_tv:
                if (!hasDefultCar) {
                    takePhoto();
                }
                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        ArrayList<TImage> images = result.getImages();
        if (images.size() > 0) {
            String compressPath = images.get(images.size() - 1).getCompressPath();
            mLoadingDialog.show();
            mDriverUserInfoPresenter.uploadPicforType(compressPath, "vehicleLicense");
        }
    }

    private void takePhoto() {
        new TakePhotoUtils(mActivity) {
            @Override
            protected void onPhoto() {
                CustomHelper.toPicture(getTakePhoto());
            }

            @Override
            protected void onCamara() {
                CustomHelper.toCamara(getTakePhoto());
            }
        };
    }

    private boolean checkEmpty() {
        if (TextUtils.isEmpty(mCarcityTv.getText())) {
            ToastUtils.Toast_short("请选择城市");
            return false;
        }
        if (TextUtils.isEmpty(mCarNametv.getText())) {
            ToastUtils.Toast_short("请选择车辆");
            return false;
        }
//        if (TextUtils.isEmpty(mBaoyTimetv.getText())) {
//            ToastUtils.Toast_short("请选择上次保养时间");
//            return false;
//        }
        if (TextUtils.isEmpty(mCarnumEdt.getText())) {
            ToastUtils.Toast_short("请填写车牌号");
            return false;
        }
        if (TextUtils.isEmpty(mTotalMilesEdt.getText())) {
            ToastUtils.Toast_short("请填写行驶总里程数");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CITYCODE) {
            if (data != null) {
                String city = data.getStringExtra("city");
                mCarcityTv.setText(city);
            }
        }
    }

    @Override
    public void onSuccess(String path) {
        mLoadingDialog.show();
        mCardsbPresenter.getDriverInfoByImg(Const.BannerUrl + path);
    }

    @Override
    public void showCardsbData(CarsbBean dataBean) {
        CarsbBean.号牌号码Bean bean = dataBean.get号牌号码();
        if (bean != null) {
            String words = bean.getWords();
            if (StringUtil.NoNullOrEmpty(words)) {
                String substring = words.substring(0, 1);
                String substring1 = words.substring(1, words.length());
                mCarNoFirstTv.setText(substring);
                mCarnumEdt.setText(substring1);
            }
        }
    }
}
