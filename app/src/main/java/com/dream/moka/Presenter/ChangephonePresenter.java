package com.dream.moka.Presenter;

import android.util.Base64;
import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Contract.ChangephoneContract;
import com.dream.moka.Other.CommonAction;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class ChangephonePresenter extends RxPresenter<ChangephoneContract> {
    private ApiService mApiService;

    @Inject
    public ChangephonePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void getCodeData(String phone){
        BaseObserver<PhoneCodeResultBean> baseObserver=new BaseObserver<PhoneCodeResultBean>(mView) {
            @Override
            public void onSuccess(PhoneCodeResultBean results) {
                String resultsPhone = results.getPhone();
                String code = results.getCode();
                mView.getCodeSuccess(resultsPhone,code);
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
                mView.showError();
            }
        };
        String tempPhone = phone+"_forapp";
        byte[] encode = Base64.encode(tempPhone.getBytes(), Base64.DEFAULT);
        addObserver(mApiService.getPhoneCode(new String(encode)),baseObserver);
    }

    public void valicationPhone(String phone){
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.showValidationPhone(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
                mView.showError();
            }
        };
        Log.e("tag","token="+CommonAction.getToken());
        Log.e("tag","phone="+phone);
        addObserver(mApiService.editUserPhone(CommonAction.getToken(),phone),baseObserver);
    }
    public void setNewPhone(String phone){
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.showChangePhone(results);
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
                mView.showError();
            }
        };
        addObserver(mApiService.saveUserPhone(CommonAction.getToken(),phone),baseObserver);
    }

}
