package com.dream.moka.Common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.TestBaseBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.R;
import com.dream.moka.Utils.RvUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class CommonRvActivity extends BaseCommonActivity {

    private List<TestBaseBean> dataList = new ArrayList<>();
    private RvCommonAdapter commonAdapter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;
    @Override
    public int getLayoutId() {
        return R.layout.activity_commonrv;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {
        RvUtils.setOptionnoLine(common_rv,this);
        commonAdapter = new RvCommonAdapter(this,dataList,R.layout.rvitem_mycar);
        common_rv.setAdapter(commonAdapter);
    }

    @Override
    public String initTitleText() {
        return "我的爱车";
    }

    @Override
    public String initRightTv() {
        return null;
    }

    @Override
    public boolean isRighttv() {
        return false;
    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {
        dataList.clear();
        for (int i = 0; i <2 ; i++) {
            TestBaseBean bean = new TestBaseBean();
            dataList.add(bean);
        }
        commonAdapter.notifyDataSetChanged();
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_relay:
                finish();
                break;
        }
    }
}
