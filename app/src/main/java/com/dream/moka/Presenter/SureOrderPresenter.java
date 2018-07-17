package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.Other.OnlyStringBean;
import com.dream.moka.Bean.PriceResultBean;
import com.dream.moka.Bean.SaveOrderResultBean;
import com.dream.moka.Contract.SureOrderContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/13 0013.
 */

public class SureOrderPresenter extends RxPresenter<SureOrderContract> {
    private ApiService mApiService;

    @Inject
    public SureOrderPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * @param carId
     * @param planTargetid
     * @param mile
     */
    //已经取消调用
    public void getData(String carId, String planTargetid, String mile) {
        String token = CommonAction.getToken();
        BaseObserver<ConfirmOrderResultBean> baseObserver = new BaseObserver<ConfirmOrderResultBean>(mView) {
            @Override
            public void onSuccess(ConfirmOrderResultBean results) {
                mView.getDataSuccess(results);
            }
        };
        addObserver(mApiService.confirmMaintainOrder(token, carId, planTargetid, mile), baseObserver);
    }

    public void getTimeData() {
        BaseObserver<PriceResultBean> baseObserver = new BaseObserver<PriceResultBean>(mView) {
            @Override
            public void onSuccess(PriceResultBean results) {
                if (results != null) {
                    String averageTime = results.getAverageTime();
                    if (averageTime != null && !averageTime.equals("")) {
                        mView.getSendTimeSuccess(averageTime);
                    }
                }
            }
        };
        addObserver(mApiService.getPriceRatio(), baseObserver);
    }

    /**
     * 获取维修商
     *
     * @param cardId
     * @param targetId
     * @param addressId
     */
    public void changeRepairShopData(String cardId, String targetId, String addressId) {
        String token = CommonAction.getToken();
        BaseListObserver<ConfirmOrderResultBean.ListShopBean> baseObserver = new BaseListObserver<ConfirmOrderResultBean.ListShopBean>(mView) {

            @Override
            public void onSuccess(List<ConfirmOrderResultBean.ListShopBean> results) {
                mView.chooseAddressChangeShopSuccess(results);
            }
        };
        addObserver(mApiService.changeRepairShop(token, cardId, targetId, addressId), baseObserver);
    }

    public void getChangeDjf(String jcAddressId, String scAddressId, String repairShopId,String type) {
        String token = CommonAction.getToken();
        BaseObserver<OnlyStringBean> baseObserver = new BaseObserver<OnlyStringBean>(mView) {

            @Override
            public void onSuccess(OnlyStringBean results) {
                mView.showDJF(results);
            }
        };
        addObserver(mApiService.changeSelfChoseDJF(token, jcAddressId, scAddressId, repairShopId,type), baseObserver);
    }

//    token,用户唯一标识（string/50/Y)
//    String carId ,车辆id（string/50/Y)
//    String isAppointment,是否预约接车(0否 1是)
//    String appointmentDate ,预约时间（string/50/Y)
//    String jcAddressId,接车地址id（string/50/Y)
//    String isSameAddress ,是否相同地址 (0否 1是)
//    String hcAddressId ,回车地址id（string/50/Y)
//    String repairShopId,维修商id（string/50/Y)
//    String planTargetId,保养方案的id（string/50/Y)
//    String miles,总里程
//    String userCouponId,用户优惠券id
//    String payAmount,实付价格
//    String orderAmount,订单价格
//    String invoiceId,发票id（string/50/N)
//    String expectedDate：预计完成小时

    /**
     * 提交0.1保养订单
     */
    public void saveMaintainOrder(String carId, String isAppointment, String appointmentDate, String jcAddressId, String isSameAddress,
                                  String hcAddressId, String repairShopId, String planTargetId, String miles, String userCouponId,
                                  String payAmount, String orderAmount, String invoiceId, String expectedDate, String serviceType,boolean isYouhui) {
        String token = CommonAction.getToken();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("carId", carId);
        map.put("isAppointment", isAppointment);
        Log.e("date", "isAppointment=" + isAppointment);
        if (isAppointment.equals("1")) {
            Log.e("date", "appointmentDate=" + appointmentDate);
            map.put("appointmentDate", appointmentDate);
        }
        map.put("jcAddressId", jcAddressId);
        map.put("isSameAddress", isSameAddress);
        map.put("hcAddressId", hcAddressId);
        map.put("repairShopId", repairShopId);
        map.put("planTargetId", planTargetId);
        map.put("miles", miles);
        if (isYouhui){
            map.put("userCouponId", userCouponId);
        }
        map.put("payAmount", payAmount);
        map.put("orderAmount", orderAmount);
        map.put("invoiceId", invoiceId);
        map.put("expectedDate", expectedDate);
        map.put("serviceType", serviceType);
        BaseObserver<SaveOrderResultBean> baseObserver = new BaseObserver<SaveOrderResultBean>(mView) {
            @Override
            public void onSuccess(SaveOrderResultBean results) {
                mView.saveOrderSuccess(results);
            }
        };
        addObserver(mApiService.saveMaintainOrder(map), baseObserver);
    }

    /**
     * 提交订单
     */
    public void commitOrder(String carId, String isAppointment, String appointmentDate, String jcAddressId, String isSameAddress, String hcAddressId,
                                       String repairShopId, String userCouponId, String payAmount, String orderAmount, String invoiceId, String expectedDate,
                                       String servicePartItems,String type,String orderId,String sideIds,String djf,String payDjf,String usedJF,String deduMoney,String tag,boolean isYouhui) {
        String token = CommonAction.getToken();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("carId", carId);
        map.put("isAppointment", isAppointment);
        if (isAppointment.equals("1")) {
            Log.e("date", "appointmentDate=" + appointmentDate);
            map.put("appointmentDate", appointmentDate);
        }

        map.put("jcAddressId", jcAddressId);
        map.put("isSameAddress", isSameAddress);
        map.put("hcAddressId", hcAddressId);
        map.put("repairShopId", repairShopId);
        if (isYouhui){
            map.put("userCouponId", userCouponId);
        }
        map.put("orderAmount", orderAmount);
        map.put("payAmount", payAmount);
        map.put("invoiceId", invoiceId);
        map.put("expectedDate", expectedDate);
        map.put("djf", djf);
        map.put("payDjf", payDjf);
        map.put("usedJF", usedJF);
        map.put("deduMoney", deduMoney);
        if (StringUtil.NoNullOrEmpty(orderId)){
            map.put("orderId", orderId);
        }
        BaseObserver<SaveOrderResultBean> baseObserver = new BaseObserver<SaveOrderResultBean>(mView) {
            @Override
            public void onSuccess(SaveOrderResultBean results) {
                mView.saveOrderSuccess(results);
            }
        };
        if (tag.equals(Const.onekey)||tag.equals(Const.free)){
            if (orderId.isEmpty()){
                map.put("type", type);
            }else {
                map.put("type", "0");
            }
            map.put("servicePartItems", servicePartItems);
            Log.e("tag","map="+map.toString());
            addObserver(mApiService.saveSelfChoseOrder(map), baseObserver);
        }else {
            switch (tag){
                case Const.coinSpray://钣喷
                    Log.e("tag","map="+map.toString());
                    if (StringUtil.NoNullOrEmpty(sideIds)){
                        map.put("sideIds",sideIds);
                    }
                    addObserver(mApiService.saveSprayOrder(map), baseObserver);
                    break;
                case Const.weixiu:
                    map.put("type", type);
                    map.put("servicePartItems", servicePartItems);
                    Log.e("tag","map="+map.toString());
                    addObserver(mApiService.saveSelfChoseOrder(map), baseObserver);
                    break;
                case Const.insurance:
                    Log.e("tag","map="+map.toString());
                    addObserver(mApiService.saveInsuranceOrder(map), baseObserver);
                    break;
            }
        }

    }
    /**
     * 提交钣喷订单
     */
    public void saveMaintainOrder_coinspray(String carId, String isAppointment, String appointmentDate, String jcAddressId, String isSameAddress, String hcAddressId,
                                       String repairShopId, String userCouponId, String payAmount, String orderAmount, String invoiceId, String expectedDate,
                                       String servicePartItems,String type,String orderId,String djf,String payDjf,String usedJF,String deduMoney) {
        String token = CommonAction.getToken();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("carId", carId);
        map.put("isAppointment", isAppointment);
        if (isAppointment.equals("1")) {
            Log.e("date", "appointmentDate=" + appointmentDate);
            map.put("appointmentDate", appointmentDate);
        }
        map.put("jcAddressId", jcAddressId);
        map.put("isSameAddress", isSameAddress);
        map.put("hcAddressId", hcAddressId);
        map.put("repairShopId", repairShopId);
        map.put("userCouponId", userCouponId);
        map.put("payAmount", payAmount);
        map.put("orderAmount", orderAmount);
        map.put("invoiceId", invoiceId);
        map.put("expectedDate", expectedDate);
        map.put("servicePartItems", servicePartItems);
        map.put("type", type);
        map.put("djf", djf);
        map.put("payDjf", payDjf);
        map.put("usedJF", usedJF);
        map.put("orderId", orderId);
        map.put("deduMoney", deduMoney);
        Log.e("tag","map="+map.toString());
        BaseObserver<SaveOrderResultBean> baseObserver = new BaseObserver<SaveOrderResultBean>(mView) {
            @Override
            public void onSuccess(SaveOrderResultBean results) {
                mView.saveOrderSuccess(results);
            }
        };
    }
}
