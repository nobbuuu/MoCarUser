package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Contract.AddMyCarActContract;
import com.dream.moka.Utils.StringUtil;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class AddMyCarActPresenter extends RxPresenter<AddMyCarActContract> {
    private ApiService mApiService;

    @Inject
    public AddMyCarActPresenter(ApiService ApiService) {
        mApiService = ApiService;
    }

    /**
     * 添加车辆
     * @param configId
     * @param carName
     * @param owner
     * @param buyDate
     * @param vinNo
     * @param registerDate
     * @param registerCity
     * @param lastMainDate
     * @param cardNo
     * @param mileage
     */
//    String configId,车配置id用户唯一标识（String/50/Y））
//    String carName,车名称
//    String owner，车主（string/50/Y)
//    String buyDate,购买日期（string/50/N)
//    String vinNo,车架号购买日期（string/50/Y)
//    String registerDate,上牌日期（string/50/N)
//    String registerCity,上牌城市（string/50/N)
//    String lastMainDate上次保养时间（string/50/N)
//    String cardNo,车牌号(string/50/Y) String recomMaintMile,推荐保养里程（ 上次保养里程)（string/50/N)
//    String mileage,总里程数（string/50/N)
    public void saveData(String configId,String carName,String owner,String buyDate,String vinNo
            ,String registerDate,String registerCity,String lastMainDate,String cardNo,String mileage){
        String token = CommonAction.getToken();
        HashMap<String,String> map=new HashMap<>();
        map.put("token",token);
        map.put("configId", StringUtil.checkNull(configId));
        map.put("carName",StringUtil.checkNull(carName));
        map.put("owner",StringUtil.checkNull(owner));
        map.put("buyDate",StringUtil.checkNull(buyDate));
        map.put("vinNo",StringUtil.checkNull(vinNo));
        map.put("registerDate",StringUtil.checkNull(registerDate));
        map.put("registerCity",StringUtil.checkNull(registerCity));
        map.put("lastMainDate",StringUtil.checkNull(lastMainDate));
        map.put("cardNo",StringUtil.checkNull(cardNo));
        map.put("mileage",StringUtil.checkNull(mileage));
        map.put("status","1");//是否默认默认1
        map.put("recomMaintMile","0");//暂定固定值0
        Log.e("tag","map="+map.toString());
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.onOwnSuccess();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        addObserver(mApiService.addUserCar(map),baseObserver);
    }

}
