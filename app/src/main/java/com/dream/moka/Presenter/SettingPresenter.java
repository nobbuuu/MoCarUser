package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Contract.SettingContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Utils.ToastUtils;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/16 0016.
 */

public class SettingPresenter extends RxPresenter<SettingContract> {
    private ApiService mApiService;
    @Inject
    public SettingPresenter(ApiService ApiService) {
        mApiService = ApiService;
    }
    public void checkHasPayCode(){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.onSuccess(true);
            }
            @Override
            public void onCodeError(String errorCode, String errorMsg) {
                mView.showError();
                if (errorCode.equals(Const.ERRORCODE_RELOGIN)){
                    ToastUtils.Toast_short("账号过期，请重新登录");
                }else {
                    mView.onSuccess(false);
                }
            }
        };
        addObserver(mApiService.checkExitTradPsw(token),baseObserver);

    }

    public void checkOut(){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.outLoginSuccess();
            }
        };
        addObserver(mApiService.loginOut(token),baseObserver);
    }
}
