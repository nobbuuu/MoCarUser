package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.InvoiceBaseBean;
import com.dream.moka.Bean.InvoiceBean;
import com.dream.moka.Contract.SaveInvoiceContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class InvoicePresenter extends RxPvPresenter<SaveInvoiceContract.View> implements SaveInvoiceContract.Presenter<SaveInvoiceContract.View>{

    private ApiService mApiService;

    @Inject
    public InvoicePresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void saveOrderInvoice(String token, Map<String, String> map) {
        addObserver(mApiService.saveOrderInvoice(token,map), new BasePvObserver<InvoiceBean>(mView) {
            @Override
            public void onSuccess(InvoiceBean results) {
                mView.showData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
                mView.showError();
            }
        });
    }

    @Override
    public void updateInvoice(String token, Map<String, String> map) {
        addObserver(mApiService.saveOrUpdateCompInvoice(token,map), new BasePvObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.showUpdateInvoice(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
                mView.showError();
            }
        });
    }

    @Override
    public void getUserCompInvoice(String token) {
        addObserver(mApiService.getUserCompInvoice(token), new BasePvObserver<InvoiceBaseBean>(mView) {
            @Override
            public void onSuccess(InvoiceBaseBean results) {
                mView.showBaseData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
                mView.showError();
            }
        });
    }
}
