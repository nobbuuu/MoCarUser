package com.dream.moka.Base;



import com.dream.moka.Bean.base.BaseListBean;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Utils.ToastUtils;

import java.util.List;

import rx.Observer;

/**
 * 响应实体类型result为集合类型
 * Created by Administrator on 2017/12/13 0013.
 */

public abstract class BaseListObserver<T> implements Observer<BaseListBean<T>> {
    private BaseContract mBaseView;
    public BaseListObserver(BaseContract baseView) {
        mBaseView=baseView;
    }

    @Override
    public void onCompleted() {
        mBaseView.complete();
    }

    @Override
    public void onError(Throwable e) {
        mBaseView.showError();
    }

    @Override
    public void onNext(BaseListBean<T> tBaseListBean) {
        if (tBaseListBean.getErrorCode().equals(Const.ERRORCODE_SUCCESS)){
            List<T> results = tBaseListBean.getResults();
            onSuccess(results);
        }else {
            String errorCode = tBaseListBean.getErrorCode();
            String errorMsg = tBaseListBean.getErrorMsg();
            onCodeError(errorCode,errorMsg);
        }
    }

    /**
     * 响应吗错误
     * @param errorCode
     * @param errorMsg
     */
    private void onCodeError(String errorCode, String errorMsg) {
        mBaseView.showError();
//        ToastUtils.Toast_short(errorMsg);
        if (errorCode.equals(Const.ERRORCODE_RELOGIN)){
            //token实现重新登录
            ToastUtils.Toast_short("账号过期，请重新登录");
            CommonAction.clearUserData();
        }else {
            ToastUtils.Toast_short(errorMsg);
        }
    }

    public abstract void onSuccess(List<T> results);
}
