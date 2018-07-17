package com.dream.moka.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/4 0004.
 * 保存银行卡信息用来传递的实体
 */

public class BankInfoBean implements Serializable{
    private String username;
    private String cardid;
    private String bankId;
    private String bankName;
    private String cardNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
