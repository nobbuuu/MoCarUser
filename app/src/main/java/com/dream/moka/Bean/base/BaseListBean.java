package com.dream.moka.Bean.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class BaseListBean<T> implements Serializable {
    private String errorCode;
    private String errorMsg;
    private boolean success;
    private List<T> results;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
