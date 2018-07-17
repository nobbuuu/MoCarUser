package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.NewsDetailBean;
import com.dream.moka.Bean.PhoneCodeResultBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface DynamicDetailContract {

    interface View extends BasePVContract.BaseView {

        void showDynamicData(NewsDetailBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getDynamicContent(String id);

    }
}
