package com.dream.moka.Utils;

import com.dream.moka.Other.MyApplication;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2018/1/10 0010.
 */
public  class WechatPayUtils {

    public void toWechatPay(String appid, String mch_id, String nonce_str, String prepay_id, String sign, String time){
        genPayReq(appid,mch_id,nonce_str,prepay_id,sign,time);
        sendPayReq(appid);
    }
    /**
     * 微信方法
     */
    IWXAPI api;
    PayReq req = new PayReq();

    private   void genPayReq(String appid, String mch_id, String nonce_str, String prepay_id, String sign, String time) {
        LogUtils.e(appid+"+"+mch_id+"+"+nonce_str+"+"+prepay_id+"+"+sign+time);
        req.appId = appid;// 商户在微信开放平台申请的应用id,支付请求对象req的字段appId的值
        req.partnerId = mch_id;// 商户id
        req.prepayId = prepay_id;// 预支付订单
        req.packageValue = "Sign=WXPay";// 商家根据文档填写的数据和签名
        req.nonceStr = nonce_str;// 随机串，防重发
        req.timeStamp = time;// 时间戳，防重发
        req.sign = sign;// 签名字段
    }

    /**
     * 微信支付方法
     */
    private void sendPayReq(String appid) {
        LogUtils.e("开始支付");
        // 注册微信app，注册成功后该应用将显示在微信的app列表中
        api = WXAPIFactory.createWXAPI(MyApplication.getInstance(), null);
        api.registerApp(appid);
        api.sendReq(req);
    }

}
