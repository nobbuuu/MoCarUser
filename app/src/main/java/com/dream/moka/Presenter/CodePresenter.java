package com.dream.moka.Presenter;

import android.util.Base64;
import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Contract.CodeContract;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class CodePresenter extends RxPvPresenter<CodeContract.View> implements CodeContract.Presenter<CodeContract.View>{

    private ApiService mApiService;

    @Inject
    public CodePresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getYzCode(String mobilePhone) {
        String tempPhone = mobilePhone+"_forapp";
        byte[] encode = Base64.encode(tempPhone.getBytes(), Base64.DEFAULT);
        addObserver(mApiService.getPhoneCode(new String(encode)), new BasePvObserver<PhoneCodeResultBean>(mView) {
            @Override
            public void onSuccess(PhoneCodeResultBean results) {
                mView.showData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
