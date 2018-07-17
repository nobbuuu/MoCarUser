package com.dream.moka.Bean;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public class AddImgBean {
    private Bitmap bitmap;
    private String picUrl;
    private String botStr;
    private boolean isAdd;
    private boolean isDelete;

    public String getBotStr() {
        return botStr;
    }

    public void setBotStr(String botStr) {
        this.botStr = botStr;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }
}
