package com.dream.moka.UI.Activity.mycar;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.BaseCommonTakePhotoActivity;
import com.dream.moka.Bean.ChooseCarInfoBean;
import com.dream.moka.Bean.Other.CarsbBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.CardsbContract;
import com.dream.moka.Contract.DriverUserInfContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.CardsbPresenter;
import com.dream.moka.Presenter.DriverUserInfoPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.CheckVin;
import com.dream.moka.Utils.CustomHelper;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.ImmUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.TakePhotoUtils;
import com.dream.moka.Utils.ToastUtils;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class CarInfoInputActivity extends BaseCommonTakePhotoActivity implements CardsbContract.View, DriverUserInfContract {

    @Bind(R.id.username_edt)
    EditText username_edt;
    @Bind(R.id.carnum_edt)
    EditText carnum_edt;
    @Bind(R.id.buycar_date)
    TextView buycar_date;
    @Bind(R.id.carlogin_date)
    TextView carlogin_date;

    private TimePickerView dateDialog;
    private int tempTag;
    private InputMethodManager imm;
    private String carNo = "";
    @Inject
    CardsbPresenter mCardsbPresenter;
    @Inject
    DriverUserInfoPresenter mDriverUserInfoPresenter;

    public static void openAct(Context context, ChooseCarInfoBean chooseCarInfoBean) {
        Intent intent = new Intent(context, CarInfoInputActivity.class);
        intent.putExtra("data", chooseCarInfoBean);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_carinfo_input;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mCardsbPresenter.attachView(this);
        mDriverUserInfoPresenter.attachView(this);
        AppManager.getAppManager().addActivity(this);
        imm = ImmUtils.getImm();
        dateDialog = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                long time = date.getTime();
                String dateStr = DateFormatUtil.getTime(time, Const.Y_M_D);
                if (dateStr != null) {
                    switch (tempTag) {
                        case 1:
                            buycar_date.setText(dateStr);
                            break;
                        case 2:
                            carlogin_date.setText(dateStr);
                            break;
                    }
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

    }

    @Override
    public String initTitleText() {
        return "车辆信息";
    }

    @Override
    public String initRightTv() {
        return "行驶证识别";
    }

    @Override
    public boolean isRighttv() {
        String carNo = carnum_edt.getText().toString();
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

    @OnClick({R.id.back_relay, R.id.buycar_relay, R.id.carlogin_relay, R.id.next_tv, R.id.right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.buycar_relay:
                tempTag = 1;
                hideImm();
                dateDialog.show();
                break;
            case R.id.carlogin_relay:
                tempTag = 2;
                hideImm();
                dateDialog.show();
                break;
            case R.id.next_tv:
                if (TextUtils.isEmpty(username_edt.getText())) {
                    ToastUtils.Toast_short("请填写车主名称");
                    return;
                }
               /* if (TextUtils.isEmpty(carnum_edt.getText())) {
                    ToastUtils.Toast_short("请填写车架号");
                    return;
                }*/
                if (TextUtils.isEmpty(buycar_date.getText())) {
                    ToastUtils.Toast_short("请选择购车时间");
                    return;
                }
                if (TextUtils.isEmpty(carlogin_date.getText())) {
                    ToastUtils.Toast_short("请选择上牌日期");
                    return;
                }
               /* if (!CheckVin.isLegal(carnum_edt.getText().toString().trim())){
                    ToastUtils.Toast_short("输入的车架号有误");
                    return;
                }*/
                ChooseCarInfoBean chooseCarInfoBean = (ChooseCarInfoBean) getIntent().getSerializableExtra("data");
                chooseCarInfoBean.setOwner(username_edt.getText().toString());
                chooseCarInfoBean.setBuyDate(buycar_date.getText().toString());
                chooseCarInfoBean.setVinNo(carnum_edt.getText().toString());
                chooseCarInfoBean.setRegisterDate(carlogin_date.getText().toString());
                chooseCarInfoBean.setCardNo(carNo);
                AddMyCarActivity.openAct(mContext, chooseCarInfoBean);
                break;
            case R.id.right_tv:
                String carNo = carnum_edt.getText().toString();
                if (!StringUtil.NoNullOrEmpty(carNo)) {
                    takePhoto();
                }
                break;
        }
    }

    private void hideImm() {
        if (imm != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
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
        Log.e("tag", "picUrl=" + Const.BannerUrl + path);
        mLoadingDialog.show();
        mCardsbPresenter.getDriverInfoByImg(Const.BannerUrl + path);
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
    public void showCardsbData(CarsbBean dataBean) {
        CarsbBean.号牌号码Bean carNoBean = dataBean.get号牌号码();
        if (carNoBean != null) {
            carNo = carNoBean.getWords();
        }
        CarsbBean.车辆识别代号Bean carSB = dataBean.get车辆识别代号();
        if (carSB != null) {
            String words = carSB.getWords();
            if (StringUtil.NoNullOrEmpty(words)) {
                carnum_edt.setText(words);
            }
        }
        CarsbBean.所有人Bean owner = dataBean.get所有人();
        if (owner != null) {
            username_edt.setText(StringUtil.checkNull(owner.getWords()));
        }

        CarsbBean.注册日期Bean buyBean = dataBean.get注册日期();
        if (buyBean != null) {
            String buyBeanWords = buyBean.getWords();
            if (StringUtil.NoNullOrEmpty(buyBeanWords)) {
                Date date = DateFormatUtil.getTime(buyBeanWords, "yyyyMMdd");
                String time = DateFormatUtil.getTime(date, Const.Y_M_D);
                buycar_date.setText(time);
            }
        }

        CarsbBean.发证日期Bean sendBean = dataBean.get发证日期();
        if (sendBean != null) {
            String sendBeanWords = sendBean.getWords();
            if (StringUtil.NoNullOrEmpty(sendBeanWords)) {
                Date date = DateFormatUtil.getTime(sendBeanWords, "yyyyMMdd");
                String time = DateFormatUtil.getTime(date, Const.Y_M_D);
                carlogin_date.setText(time);
            }
        }
    }
}
