package com.dream.moka.Contract.FreeMaitain;


import android.support.v7.widget.RecyclerView;

import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Maintain.FreeMainTainBean;
import com.dream.moka.Bean.Maintain.ProductSelectBean;

import java.util.List;
import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface ProductListContract {

    interface View extends BasePVContract.BaseView {

        void showProductsData(List<ProductSelectBean> dataBean, RecyclerView recyclerView,FreeMainTainBean.ListServiceBean products);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getSelfChosePartList(Map<String, String> map,RecyclerView recyclerView,FreeMainTainBean.ListServiceBean bean);
    }
}
