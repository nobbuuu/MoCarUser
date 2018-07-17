package com.dream.moka.UI.Activity.mycar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.dream.moka.Base.BaseCommonTakePhotoActivity;
import com.dream.moka.Bean.ChooseCarInfoBean;
import com.dream.moka.Bean.EventBusBean.RefreshMyCar;
import com.dream.moka.Bean.Other.CarsbBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.AddMyCarActContract;
import com.dream.moka.Contract.CardsbContract;
import com.dream.moka.Contract.DriverUserInfContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.AddMyCarActPresenter;
import com.dream.moka.Presenter.CardsbPresenter;
import com.dream.moka.Presenter.DriverUserInfoPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.CityChooseActivity;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.CheckForAllUtils;
import com.dream.moka.Utils.CustomHelper;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.Dialog.CarNoFirstDialog;
import com.dream.moka.Utils.Dialog.CarNoKeyboardDialog;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.TakePhotoUtils;
import com.dream.moka.Utils.ToastUtils;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class AddMyCarActivity extends BaseCommonTakePhotoActivity implements AddMyCarActContract, CardsbContract.View, DriverUserInfContract {

    @Inject
    AddMyCarActPresenter mAddMyCarActPresenter;
    @Bind(R.id.car_relay)
    RelativeLayout car_relay;
    @Bind(R.id.baoy_timetv)
    TextView baoy_timetv;
    @Bind(R.id.carcity_tv)
    TextView carcity_tv;
    @Bind(R.id.car_nametv)
    TextView car_nametv;
    @Bind(R.id.sure_tv)
    TextView sure_tv;
    @Bind(R.id.city_relay)
    RelativeLayout mCityRelay;
    @Bind(R.id.car)
    TextView mCar;
    @Bind(R.id.right_iv)
    ImageView mRightIv;
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
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    @Bind(R.id.carNoFirst_tv)
    TextView mCarNoFirstTv;
    @Bind(R.id.carNoFirst_iv)
    ImageView mCarNoFirstIv;
    private TimePickerView dateDialog;
    private Dialog mLoadingDialog;
    private ChooseCarInfoBean mChooseCarInfoBean;
    private static final int CITYCODE = 100;

    @Inject
    CardsbPresenter mCardsbPresenter;
    @Inject
    DriverUserInfoPresenter mDriverUserInfoPresenter;

    public static void openAct(Context context, Serializable data) {
        Intent intent = new Intent(context, AddMyCarActivity.class);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_addmycar;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mAddMyCarActPresenter.attachView(this);
        mDriverUserInfoPresenter.attachView(this);
        mCardsbPresenter.attachView(this);
        mChooseCarInfoBean = (ChooseCarInfoBean) getIntent().getSerializableExtra("data");
        if (mChooseCarInfoBean!=null){
            String cardNo = mChooseCarInfoBean.getCardNo();
            if (StringUtil.NoNullOrEmpty(cardNo)&&cardNo.length()>1){
                mCarNoFirstTv.setText(cardNo.substring(0,1));
                mCarnumEdt.setText(cardNo.substring(1,cardNo.length()));
            }
        }
        car_nametv.setText(mChooseCarInfoBean.getCarName());
        initTimePick();
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        AppManager.getAppManager().addActivity(this);
    }

    /**
     * 初始化时间picker
     */
    private void initTimePick() {
        dateDialog = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                long time = date.getTime();
                String dateStr = DateFormatUtil.getTime(time, Const.Y_M_D);
                if (dateStr != null) {
                    baoy_timetv.setText(dateStr);
                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setContentSize(16)//滚轮文字大小
                .setTitleSize(13)//标题文字大小
                .setCancelText("取消")//取消按钮文字
                .setLabel(" 年", "月", "日", "时", "分", "秒")
                .isCyclic(true)//是否循环滚动
                .build();
    }

    @Override
    public String initTitleText() {
        return "填写爱车信息";
    }

    @Override
    public String initRightTv() {
        return "行驶证扫描";
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
        mCarnumEdt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    mCarnumEdt.requestFocus();
                    new CarNoKeyboardDialog(mActivity, mCarnumEdt) {
                        @Override
                        public void onResult(int type,String inputStr) {
                            if (type==2){
                                /*String firstStr = mCarNoFirstTv.getText().toString();
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
                return true;
            }
        });
    }

    @OnClick({R.id.back_relay, R.id.city_relay, R.id.baoy_time, R.id.sure_tv, R.id.right_tv,R.id.carNoFirst_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.city_relay:
                CityChooseActivity.openAct(mActivity, CITYCODE);
                break;
            case R.id.baoy_time:
                dateDialog.show();
                break;
            case R.id.carNoFirst_iv:
                new CarNoFirstDialog(mActivity) {
                    @Override
                    public void onResult(String inputStr) {
                        mCarNoFirstTv.setText(inputStr);
                    }
                };
                break;
            case R.id.sure_tv:
                if (CheckEmpty()) {
                    mLoadingDialog.show();
                    mAddMyCarActPresenter.saveData(mChooseCarInfoBean.getConfigId(), mChooseCarInfoBean.getCarName(), mChooseCarInfoBean.getOwner(),
                            mChooseCarInfoBean.getBuyDate(), mChooseCarInfoBean.getVinNo(), mChooseCarInfoBean.getRegisterDate(),
                            mChooseCarInfoBean.getRegisterCity(), mChooseCarInfoBean.getLastMainDate(), mChooseCarInfoBean.getCardNo(),
                            mChooseCarInfoBean.getMileage());
                }
                break;
            case R.id.right_tv:
                String carNo = mCarnumEdt.getText().toString();
                if (!StringUtil.NoNullOrEmpty(carNo)) {
                    takePhoto();
                }
                break;
        }
    }

    private boolean CheckEmpty() {
        if (TextUtils.isEmpty(carcity_tv.getText())) {
            ToastUtils.Toast_short("请选择城市");
            return false;
        }
//        if (TextUtils.isEmpty(baoy_timetv.getText())) {
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
        mChooseCarInfoBean.setRegisterCity(carcity_tv.getText().toString());
        mChooseCarInfoBean.setLastMainDate(TextUtils.isEmpty(baoy_timetv.getText()) ? "" : baoy_timetv.getText().toString().trim());
        mChooseCarInfoBean.setCardNo(mCarNoFirstTv.getText().toString()+mCarnumEdt.getText().toString());
        mChooseCarInfoBean.setMileage(mTotalMilesEdt.getText().toString());
        return true;
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
    public void onOwnSuccess() {
        EventBus.getDefault().post(new RefreshMyCar());//刷新爱车列表
        AppManager.getAppManager().finishSomeActivity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CITYCODE) {
            if (data != null) {
                String city = data.getStringExtra("city");
                carcity_tv.setText(city);
            }
        }
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

    @Override
    public void onSuccess(String path) {
        mLoadingDialog.show();
        mCardsbPresenter.getDriverInfoByImg(Const.BannerUrl + path);
    }

}
