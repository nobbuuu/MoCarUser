package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.AllActivityBean;
import com.dream.moka.Contract.HuoDongContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class HuodongPresenter extends RxPresenter<HuoDongContract> {
    private static final String PAGESIZE="10";
    private int mPage=1;
    private ApiService mApiService;

    @Inject
    public HuodongPresenter(ApiService ApiService) {
        mApiService = ApiService;
    }

    public void getActivity(boolean isLoadMore){
        String page=isLoadMore?String.valueOf(mPage+1):"1";
        BaseObserver<AllActivityBean> baseObserver=new BaseObserver<AllActivityBean>(mView) {
            @Override
            public void onSuccess(AllActivityBean results) {
                int totalPage = results.getTotalPage();
                int pageNum = results.getPageNum();
                mPage=pageNum;
                List<AllActivityBean.ListBean> list = results.getList();
                mView.setHuoDongData(list);

                mView.setFinishLoadMore(mPage>=totalPage);
            }
        };
        addObserver(mApiService.getActivityByPage(page,PAGESIZE),baseObserver);
    }
}
