package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Contract.AddBankCardContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class AddBankCardPresenter extends RxPresenter<AddBankCardContract> {
    private ApiService mApiService;
    @Inject
    public AddBankCardPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 新增或者修改银行卡
     * cardId不为空则是修改，否则为新增
     */
    public void saveBankData(String cardId,String username,String Bankid,String accountNo){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.onSuccessData();
            }
        };
        addObserver(mApiService.saveDriverBankInfo(token,cardId,username,Bankid,accountNo),baseObserver);
    }
}
