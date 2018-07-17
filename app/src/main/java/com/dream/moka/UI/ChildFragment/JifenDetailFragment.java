package com.dream.moka.UI.ChildFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import com.dream.moka.Adapter.IntegralAdapter;
import com.dream.moka.Adapter.PurseQBAdapter;
import com.dream.moka.Adapter.PurseTXAdapter;
import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Bean.IntegralBean;
import com.dream.moka.Bean.MyAccountBean;
import com.dream.moka.Bean.WalletBean;
import com.dream.moka.Bean.WithDrawResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.PurseJifenContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Presenter.PurseJifenPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.MapUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class JifenDetailFragment extends BaseFragmentV4 implements PurseJifenContract {
    @Inject
    PurseJifenPresenter mPurseJifenPresenter;
    @Bind(R.id.common_rv)
    ListView common_rv;
    @Bind(R.id.smart)
    SmartRefreshLayout mSmart;
    private Dialog mLoadingDialog;
    private PurseQBAdapter mQBAdapter;
    private IntegralAdapter mIntegralAdapter;
    private PurseTXAdapter mPurseTXAdapter;
    private List<MyAccountBean.ListBean> mBeanListQb = new ArrayList<>();
    private List<WithDrawResultBean.ListBean> mBeanListWith = new ArrayList<>();
    private List<IntegralBean> mIntegralList = new ArrayList<>();
    private String mTag;
    private String mType;
    protected boolean isCreate = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
    }

    public void setTag(int type, String tag) {
        mTag = tag;
        switch (type) {
            case 0:
                mType = "";
                break;
            case 1:
                mType = "0";
                break;
            case 2:
                mType = "1";
                break;
        }
    }

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public void initView() {
        mQBAdapter = new PurseQBAdapter(getActivity(), mBeanListQb, R.layout.rvitem_purse);
        mPurseTXAdapter = new PurseTXAdapter(getActivity(), mBeanListWith, R.layout.rvitem_purse);
        mIntegralAdapter = new IntegralAdapter(getActivity(),mIntegralList,R.layout.rvitem_purse);
        mPurseJifenPresenter.attachView(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(getActivity());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_commonlv;
    }

    @Override
    public void initResume() {
        if (mTag.equals("jf")) {
            common_rv.setAdapter(mIntegralAdapter);
            loadScore();
        } else if (mTag.equals("ye")) {
            mLoadingDialog.show();
            mPurseJifenPresenter.getYUeData(mType, false);
            common_rv.setAdapter(mQBAdapter);
        }
    }

    @Override
    public void initEvents() {
        mSmart.setEnableRefresh(false);
        mSmart.setEnableLoadmore(true);
        mSmart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (mTag.equals("jf")) {
                    loadScore();
                } else if (mTag.equals("ye")) {
                    mPurseJifenPresenter.getYUeData(mType, true);
                }
            }
        });
    }

    private void loadScore() {
        Map<String, String> singleMap = MapUtils.getSingleMap();
        singleMap.put("token", CommonAction.getToken());
        if (!mType.isEmpty()){
            singleMap.put("type",mType);
        }
        mLoadingDialog.show();
        mPurseJifenPresenter.getJiFenData(singleMap);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            //相当于Fragment的onResume
            //在这里处理加载数据等操作
            if (mTag.equals("jf")) {
                mLoadingDialog.show();
                loadScore();
            } else if (mTag.equals("ye")) {
                mLoadingDialog.show();
                mPurseJifenPresenter.getYUeData(mType, false);

            }
        }
    }

    @Override
    public void initDta() {

    }

    @Override
    public void showError() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        mSmart.finishLoadmore();
    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        mSmart.finishLoadmore();
    }

    @Override
    public void getAccount(WalletBean walletBean) {

    }

    @Override
    public void getQianBaoData(MyAccountBean results, boolean isMore) {
        if (results != null) {
            List<MyAccountBean.ListBean> items = results.getList();
            if (items!=null&&items.size() != 0) {
                if (isMore) {
                    mBeanListQb.addAll(items);
                } else {
                    if (mBeanListQb.size() != 0) {
                        mBeanListQb.clear();
                    }
                    mBeanListQb.addAll(items);
                }
                mQBAdapter.notifyDataSetChanged();
            }

        }

    }

    @Override
    public void isLoadAll(boolean isAll) {
        mSmart.setLoadmoreFinished(isAll);
    }

    @Override
    public void showScoreList(List<IntegralBean> dataList) {
        Log.e("tag","scoreList.size()="+dataList.size());
        mIntegralList.clear();
        mIntegralList.addAll(dataList);
        mIntegralAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
