package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.CarResultBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public interface MyCarContract extends BaseContract {
    void onDeleteSuccess(int position);//要删除的位置
    void onDefultSuccess(int position);//要修改的位置
    void onDataSuccess(List<CarResultBean> results);
}
