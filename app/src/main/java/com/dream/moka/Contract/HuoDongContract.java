package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.AllActivityBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public interface HuoDongContract extends BaseContract {
    void setHuoDongData(List<AllActivityBean.ListBean> list);
    void setFinishLoadMore(boolean isAll);//是否加载到最后一页数据，取消继续响应上啦事件
}
