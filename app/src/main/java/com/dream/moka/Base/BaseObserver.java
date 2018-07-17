package com.dream.moka.Base;

import com.dream.moka.Bean.base.BaseBean;
import com.dream.moka.IM.im.event.RefreshEvent;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import rx.Observer;

/**
 * 响应实体类型result为对象类型
 * Created by Administrator on 2017/12/13 0013.
 */

public abstract class BaseObserver<T> implements Observer<BaseBean<T>> {

    private BaseContract mBaseView;

    public BaseObserver(BaseContract baseView) {
        mBaseView = baseView;
    }

    @Override
    public void onCompleted() {
        mBaseView.complete();

    }

    @Override
    public void onError(Throwable e) {
//        ToastUtils.Toast_short(ResourcesUtils.getString(R.string.failconnect));
        mBaseView.showError();
    }

    @Override
    public void onNext(BaseBean<T> tBaseBean) {
        if (tBaseBean!=null){
            if (tBaseBean.getErrorCode().equals(Const.ERRORCODE_SUCCESS)) {
                T results = tBaseBean.getResults();
                onSuccess(results);
            } else {
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
    public void onCodeError(String errorCode, String errorMsg) {
//        ToastUtils.Toast_short(errorMsg);
        mBaseView.showError();
        if (errorCode.equals(Const.ERRORCODE_RELOGIN)){
            ToastUtils.Toast_short("账号过期，请重新登录");
            CommonAction.clearUserData();
            EventBus.getDefault().post("loginout");
        }else if (errorCode.equals(Const.ERRORCODE_NOSET)){
            EventBus.getDefault().post("setPwd");
        } else if (StringUtil.NoNullOrEmpty(errorMsg)){
            ToastUtils.Toast_short(errorMsg);
        }
    }

    public abstract void onSuccess(T results);
}
