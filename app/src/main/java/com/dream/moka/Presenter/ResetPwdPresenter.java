package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.UserResultBean;
import com.dream.moka.Contract.ResetPwdContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class ResetPwdPresenter extends RxPresenter<ResetPwdContract> {
    private ApiService mApiService;

    @Inject
    public ResetPwdPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void saveData(final String phone, final String pwd) {
        BaseObserver<UserResultBean> baseObserver = new BaseObserver<UserResultBean>(mView) {
            @Override
            public void onSuccess(UserResultBean results) {
                if (results != null) {
                    String id = results.getId();
                    saveData2(id, pwd);
                }
            }
        };
        addObserver(mApiService.getUserByPhone(phone), baseObserver);

    }

    private void saveData2(String id, String pwd) {
        BaseObserver<EmptyBean> baseObserver = new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.setPwdSuccess();
            }
        };
        addObserver(mApiService.resetForgetPsw(id, pwd), baseObserver);
    }
}
