package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public interface IndustryDynamicsContract extends BaseContract{
    void setNewsData(List<NewsBean> list);
}
