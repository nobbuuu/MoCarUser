package com.dream.moka.Presenter;

import android.util.Base64;
import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Bean.UserResultBean;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Contract.LoginContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Utils.ToastUtils;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/15 0015.
 */

public class LoginPresenter extends RxPresenter<LoginContract> {
    private ApiService mApiService;

    @Inject
    public LoginPresenter(ApiService ApiService) {
        mApiService = ApiService;
    }

    public void Login(String phone,String pwd,String type){

       /* String drviceToken = CommonAction.getDrviceToken();
        if (drviceToken.isEmpty()){
            ToastUtils.Toast_short("您的设备暂不支持该服务，请稍后再试");
            return;
        }*/

        BaseObserver<UserResultBean> baseObserver=new BaseObserver<UserResultBean>(mView) {
            @Override
            public void onSuccess(UserResultBean results) {
                CommonAction.saveUserData(results);
                mView.loginSuccess(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(),"onError="+e);
            }
        };

        addObserver(mApiService.loginByPhone(phone,pwd,type,"mocar_user","1"),baseObserver);
    }

    public void getCode(String phone){
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
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        String tempPhone = phone+"_forapp";
        byte[] encode = Base64.encode(tempPhone.getBytes(), Base64.DEFAULT);
        addObserver(mApiService.getPhoneCode(new String(encode)),baseObserver);
    }

    public void thirdLogin(final String openId, final String type, final String name, final String headUrl){//（0:微信登录 1：QQ登录 2：微博登录）
        String drviceToken = CommonAction.getDrviceToken();
        BaseObserver<UserResultBean> baseObserver=new BaseObserver<UserResultBean>(mView) {
            @Override
            public void onSuccess(UserResultBean results) {
                CommonAction.saveUserData(results);
                mView.loginSuccess(results);
            }

            @Override
            public void onCodeError(String errorCode, String errorMsg) {
                if (errorCode.equals("-10105")){
                    mView.goBindPhone(openId,type,name,headUrl);
                }else if (errorCode.equals(Const.ERRORCODE_RELOGIN)){
                    ToastUtils.Toast_short("账号过期，请重新登录");
                    CommonAction.clearUserData();
                }else {
                    ToastUtils.Toast_short(errorMsg);
                }
            }
        };
        addObserver(mApiService.loginByOthers(openId,name,headUrl,type,drviceToken,"1"),baseObserver);

    }

}
