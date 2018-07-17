package com.dream.moka.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class PayResultBean {
    /**
     * alipayInfo :
     * wxpayInfo : {"timestamp":"1516011575","result_code":"SUCCESS","sign":"F3E0C6EAD509DE98C07E98819B729441","mch_id":"1496084512","prepay_id":"wx20180115181935829dfb368f0961006079","return_msg":"OK","package":"Sign=WXPay","appid":"wx563a278c479199ff","nonce_str":"n6XZhqfMu37w0ww6iB6eqgWHg1g4sIVw","return_code":"SUCCESS","trade_type":"APP"}
     */

    private String alipayInfo;
    private WxpayInfoBean wxpayInfo;

    public String getAlipayInfo() {
        return alipayInfo;
    }

    public void setAlipayInfo(String alipayInfo) {
        this.alipayInfo = alipayInfo;
    }

    public WxpayInfoBean getWxpayInfo() {
        return wxpayInfo;
    }

    public void setWxpayInfo(WxpayInfoBean wxpayInfo) {
        this.wxpayInfo = wxpayInfo;
    }

    public static class WxpayInfoBean {
        /**
         * timestamp : 1516011575
         * result_code : SUCCESS
         * sign : F3E0C6EAD509DE98C07E98819B729441
         * mch_id : 1496084512
         * prepay_id : wx20180115181935829dfb368f0961006079
         * return_msg : OK
         * package : Sign=WXPay
         * appid : wx563a278c479199ff
         * nonce_str : n6XZhqfMu37w0ww6iB6eqgWHg1g4sIVw
         * return_code : SUCCESS
         * trade_type : APP
         */

        private String timestamp;
        private String result_code;
        private String sign;
        private String mch_id;
        private String prepay_id;
        private String return_msg;
        @SerializedName("package")
        private String packageX;
        private String appid;
        private String nonce_str;
        private String return_code;
        private String trade_type;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }
    }
}
