package com.dream.moka.Contract.Message;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.HotActivityBean;
import com.dream.moka.Bean.NewsDetailBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface ContentContract {

    interface View extends BasePVContract.BaseView {

        void showContentData(HotActivityBean dataBean);
    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getContent(String id);
    }
}
