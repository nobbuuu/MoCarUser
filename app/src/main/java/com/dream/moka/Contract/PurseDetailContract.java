package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.InComeResultBean;
import com.dream.moka.Bean.WithDrawResultBean;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public interface PurseDetailContract extends BaseContract {
    void getShouRuData(InComeResultBean results,boolean isLoadMore);
    void getTiXianData(WithDrawResultBean results,boolean isLoadMore);
    void isLoadAll(boolean isAll);
}
