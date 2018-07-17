package com.dream.moka.Base;

/**
 * @author yuyh.
 * @date 16/8/6.
 */
public interface BasePVContract {

    interface BasePresenter<T> {

        void attachView(T view);

        void detachView();
    }

    interface BaseView {

        void showError();

        void complete();

    }
}
