package com.dream.moka.Bean.EventBusBean;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by Administrator on 2017/10/12 0012.
 */
public class UpBitmapBean {
    private String eventStr;
    private Bitmap bitmap;
    private File file;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getEventStr() {
        return eventStr;
    }

    public void setEventStr(String eventStr) {
        this.eventStr = eventStr;
    }
}
