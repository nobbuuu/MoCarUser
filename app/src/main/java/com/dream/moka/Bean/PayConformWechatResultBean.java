package com.dream.moka.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class PayConformWechatResultBean {
    /**
     * payInfo : {"nonce_str":"zxfxGICw1zBnYl13XOYxsKQyo02HesYn","package":"Sign=WXPay","appid":"wx563a278c479199ff","sign":"3D60D91FD29B9454C28996699546E713","trade_type":"APP","return_msg":"OK","result_code":"SUCCESS","mch_id":"1496084512","return_code":"SUCCESS","prepay_id":"wx20180106142131bfebd84ac40133499207","timestamp":"1515219691"}
     */

    private PayInfoBean payInfo;

    public PayInfoBean getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(PayInfoBean payInfo) {
        this.payInfo = payInfo;
    }

    public static class PayInfoBean {
        /**
         * nonce_str : zxfxGICw1zBnYl13XOYxsKQyo02HesYn
         * package : Sign=WXPay
         * appid : wx563a278c479199ff
         * sign : 3D60D91FD29B9454C28996699546E713
         * trade_type : APP
         * return_msg : OK
         * result_code : SUCCESS
         * mch_id : 1496084512
         * return_code : SUCCESS
         * prepay_id : wx20180106142131bfebd84ac40133499207
         * timestamp : 1515219691
         */

        private String nonce_str;
        @SerializedName("package")
        private String packageX;
        private String appid;
        private String sign;
        private String trade_type;
        private String return_msg;
        private String result_code;
        private String mch_id;
        private String return_code;
        private String prepay_id;
        private String timestamp;

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
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

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
