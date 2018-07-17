package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Other.VersionBean;
import com.dream.moka.Bean.PhoneCodeResultBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface VersionContract {

    interface View extends BasePVContract.BaseView {

        void showVersionData(VersionBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getNewVersion(String type,String appType);
    }
}
