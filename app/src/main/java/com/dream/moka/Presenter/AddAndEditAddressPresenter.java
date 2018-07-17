package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.ChangeAddressResultBean;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Contract.AddAndEditAddressContract;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class AddAndEditAddressPresenter extends RxPresenter<AddAndEditAddressContract> {
    private ApiService mApiService;

    @Inject
    public AddAndEditAddressPresenter(ApiService ApiService) {
        mApiService = ApiService;
    }

    /**
     * 新增地址
     * @param name
     * @param phone
     * @param province
     * @param city
     * @param area
     * @param address
     * @param Long
     * @param lat
     */
    public void addAddressData(String name,String phone,String province,String city,String area,String address,String Long,String lat){
        String token = CommonAction.getToken();
        HashMap<String,String> map=new HashMap<>();
        map.put("token",token);
        map.put("recipientsName",name);
        map.put("telephone",phone);
        map.put("province",province);
        map.put("city",city);
        map.put("area",area);
        map.put("detailAddr",address);
        map.put("status","1");
        map.put("longitude",Long);
        map.put("latitude",lat);
        BaseObserver<ChangeAddressResultBean> baseObserver=new BaseObserver<ChangeAddressResultBean>(mView) {
            @Override
            public void onSuccess(ChangeAddressResultBean results) {
                mView.onSuccess();
            }
        };
        addObserver(mApiService.addUserDeliveryAddress(map),baseObserver);

    }

    /**
     * 修改地址
     * @param name
     * @param phone
     * @param province
     * @param city
     * @param area
     * @param address
     * @param Long
     * @param lat
     */
    public void editAddressData(String id,String name,String phone,String province,String city,String area,String address,String Long,String lat,String status){
        String token = CommonAction.getToken();
        HashMap<String,String> map=new HashMap<>();
        map.put("id",id);
        map.put("token",token);
        map.put("recipientsName",name);
        map.put("telephone",phone);
        map.put("province",province);
        map.put("city",city);
        map.put("area",area);
        map.put("status",status);
        map.put("detailAddr",address);
        map.put("longitude",Long);
        map.put("latitude",lat);
        BaseObserver<ChangeAddressResultBean> baseObserver=new BaseObserver<ChangeAddressResultBean>(mView) {
            @Override
            public void onSuccess(ChangeAddressResultBean results) {
                mView.onSuccess();
            }
        };
        addObserver(mApiService.editUserDeliveryAddress(map),baseObserver);

    }

}
