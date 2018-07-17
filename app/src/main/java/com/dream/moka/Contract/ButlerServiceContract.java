package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Online.OnlinerBean;

import java.util.List;
import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface ButlerServiceContract {

    interface View extends BasePVContract.BaseView {

        void showData(List<OnlinerBean> dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getJSDetail(Map<String,String> map);
    }
}
