package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.InvoiceBaseBean;
import com.dream.moka.Bean.InvoiceBean;

import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface SaveInvoiceContract {

    interface View extends BasePVContract.BaseView {

        void showData(InvoiceBean dataBean);
        void showUpdateInvoice(EmptyBean dataBean);
        void showBaseData(InvoiceBaseBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void saveOrderInvoice(String token, Map<String,String> map);
        void updateInvoice(String token, Map<String,String> map);
        void getUserCompInvoice(String token);
    }
}
