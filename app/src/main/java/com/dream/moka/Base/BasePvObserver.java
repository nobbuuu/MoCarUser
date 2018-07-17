package com.dream.moka.Base;


import com.dream.moka.Bean.base.BaseBean;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 响应实体类型result为对象类型
 * Created by Administrator on 2017/12/13 0013.
 */

public abstract class BasePvObserver<T> implements Observer<BaseBean<T>> {

    private BasePVContract.BaseView mBaseView;
    public BasePvObserver(BasePVContract.BaseView baseView) {
        mBaseView = baseView;


    }

    @Override
    public void onCompleted() {
        mBaseView.complete();
    }

    @Override
    public void onError(Throwable e) {
        mBaseView.showError();

//        ToastUtils.Toast_short(ResourcesUtils.getString(R.string.failconnect));
    }


    @Override
    public void onNext(BaseBean<T> tBaseBean) {
        if (tBaseBean!=null){
            if (tBaseBean.getErrorCode().equals(Const.ERRORCODE_SUCCESS)) {
                T results = tBaseBean.getResults();
                onSuccess(results);
            } else {
//            ToastUtils.Toast_short(tBaseBean.getErrorMsg());
                String errorCode = tBaseBean.getErrorCode();
                String errorMsg = tBaseBean.getErrorMsg();
                onCodeError(errorCode, errorMsg);
            }
        }
    }

    /**
     * 响应吗错误
     *
     * @param errorCode
     * @param errorMsg
     */
    private void onCodeError(String errorCode, String errorMsg) {
//        ToastUtils.Toast_short(errorMsg);
        mBaseView.showError();
        if(Const.ERRORCODE_RELOGIN.equals(errorCode)){
            ToastUtils.Toast_short("您的账号已在其它设备登录");
        }else if (StringUtil.NoNullOrEmpty(errorMsg)){
            ToastUtils.Toast_short(errorMsg);
        }
    }

    public abstract void onSuccess(T results);
}
