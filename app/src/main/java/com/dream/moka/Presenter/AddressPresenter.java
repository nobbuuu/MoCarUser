package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.AddressListResultBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Contract.AddressContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class AddressPresenter extends RxPresenter<AddressContract> {
    private ApiService mApiService;
    @Inject
    public AddressPresenter(ApiService ApiService) {
        mApiService = ApiService;
    }

    /**
     * 获取地址
     */
    public void getAddressListData(){
        String token = CommonAction.getToken();
        BaseListObserver<AddressListResultBean> baseListObserver=new BaseListObserver<AddressListResultBean>(mView) {
            @Override
            public void onSuccess(List<AddressListResultBean> results) {
                mView.getDataSuccess(results);
            }
        };
        addObserver(mApiService.getUserDeliveryAddress(token),baseListObserver);
    }

    /**
     * 设为默认
     * @param id
     */
    public void setDefault(String id, final int position){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.setDefultAddressSuccess(position);
            }
        };
        addObserver(mApiService.setUserDeliveryAddress(token,id),baseObserver);

    }

    /**
     * 删除地址
     * @param id
     */
    public void deleteAddress(String id, final int position){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.deleteAddressSuccess(position);
            }
        };
        addObserver(mApiService.deleteUserDeliveryAddress(token,id),baseObserver);
    }
}
