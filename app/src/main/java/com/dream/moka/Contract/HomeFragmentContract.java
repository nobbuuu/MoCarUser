package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.BannerBean;
import com.dream.moka.Bean.HotActivityBean;
import com.dream.moka.Bean.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public interface HomeFragmentContract extends BaseContract{
    void setBannerInfo(List<BannerBean> list);
    void setNewsInfo(List<NewsBean> list);
    void setHotInfo(List<HotActivityBean> list);
    void driverSuccess(String status);
    void openShenqing();

}
