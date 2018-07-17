package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.BankCardBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Contract.ChangeBankContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Utils.ToastUtils;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class ChangeBankPresenter extends RxPresenter<ChangeBankContract> {
    private ApiService mApiService;

    @Inject
    public ChangeBankPresenter(ApiService apiService) {
        mApiService = apiService;
    }


    /**
     * 获取银行卡信息
     */
    public void getBankData(){
        String token = CommonAction.getToken();
        BaseObserver<BankCardBean> baseObserver=new BaseObserver<BankCardBean>(mView) {
            @Override
            public void onSuccess(BankCardBean results) {
                mView.getBankDataSuccess(results);
            }
        };
        addObserver(mApiService.getDriverBankCard(token),baseObserver);

    }

    /**
     * 检测是否有支付密码
     */
    public void checkHasPwd(){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.hasPayCode(true);
            }
            @Override
            public void onCodeError(String errorCode, String errorMsg) {
                mView.showError();
                if (errorCode.equals(Const.ERRORCODE_RELOGIN)){
                    ToastUtils.Toast_short("账号过期，请重新登录");
                }else {
                    mView.hasPayCode(false);
                }
            }
        };
        addObserver(mApiService.checkExitTradPsw(token),baseObserver);

    }

    /**
     * 检测密码是否正确
     * @param pwd
     */
    public void CheckPwd(String pwd){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.checkPwdSuccess();
            }
        };
        addObserver(mApiService.checkDriverBankByTradPsw(token,pwd),baseObserver);

        
    }
}
