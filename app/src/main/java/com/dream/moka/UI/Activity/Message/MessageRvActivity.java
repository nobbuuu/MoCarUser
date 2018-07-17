package com.dream.moka.UI.Activity.Message;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.dream.moka.Adapter.Orders.MessageAdapter;
import com.dream.moka.Adapter.Orders.MessageDriverAdapter;
import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.EventBusBean.RefreshMsgBean;
import com.dream.moka.Bean.EventBusBean.RefreshOrderBean;
import com.dream.moka.Bean.Message.MessageListBean;
import com.dream.moka.Bean.TestBaseBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.Message.MessageListContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.Message.MessageListPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.MainActivity;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.RvUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class MessageRvActivity extends BaseCommonActivity implements MessageListContract.View{

    private List<MessageListBean.ListBean> dataList = new ArrayList<>();
    private List<TestBaseBean> driverData = new ArrayList<>();
    private MessageAdapter commonAdapter;
    private MessageDriverAdapter driverAdapter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;
    @Bind(R.id.smart_refreshlay)
    SmartRefreshLayout smart_refreshlay;

    @Inject
    MessageListPresenter mMessageListPresenter;

    private String intentTag;

    public static void openAct(Context context,String from) {
        Intent intent = new Intent(context, MessageRvActivity.class);
        intent.putExtra(Const.intentTag,from);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_commonrv;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mMessageListPresenter.attachView(this);
        RvUtils.setOptionnoLine(common_rv, this);
        intentTag = getIntent().getStringExtra(Const.intentTag);
        driverAdapter = new MessageDriverAdapter(this, driverData, R.layout.rvitem_message_driver);
        commonAdapter = new MessageAdapter(this, dataList, R.layout.rvitem_message);
        if (intentTag!=null){
            if (intentTag.equals("driver")) {
                common_rv.setAdapter(driverAdapter);
            } else {
                common_rv.setAdapter(commonAdapter);
            }
        }
        EventBus.getDefault().register(this);
    }

    @Override
    public String initTitleText() {
        return "消息";
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
        mMessageListPresenter.getMyMessageList(CommonAction.getToken(), MapUtils.getMap());
    }

    @Override
    public void initDatas() {
    }

    private int pageNo = 2;
    private boolean isMore;
    private int totalPage;
    private void loadData(Map<String,String> map) {
        mMessageListPresenter.getMyMessageList(CommonAction.getToken(), map);
    }

    @Override
    public void eventListener() {
        smart_refreshlay.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isMore = false;
                pageNo = 2;
                Map<String, String> singleMap = MapUtils.getMap();
                loadData(singleMap);
            }
        });
        smart_refreshlay.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                isMore = true;
                Map<String, String> singleMap = MapUtils.getMap();
                if (totalPage>=pageNo){
                    singleMap.put(Const.pageNo,String.valueOf(pageNo));
                    loadData(singleMap);
                    pageNo++;
                }else {
                    smart_refreshlay.finishLoadmore();
                }
            }
        });
    }

    @OnClick({R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finishActivity();
                break;
        }
    }

    @Override
    public void showMsgListData(MessageListBean dataBean) {
        totalPage = dataBean.getTotalPage();
        if (!isMore){
            dataList.clear();
        }
        List<MessageListBean.ListBean> list = dataBean.getList();
        if (list!=null&&list.size()>0){
            dataList.addAll(list);
        }
        commonAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        if (smart_refreshlay!=null){
            smart_refreshlay.finishRefresh();
            smart_refreshlay.finishLoadmore();
        }
    }

    @Override
    public void complete() {
        if (smart_refreshlay!=null){
            smart_refreshlay.finishRefresh();
            smart_refreshlay.finishLoadmore();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(RefreshOrderBean bean){
        if (bean.getEventStr().equals(Const.refresh)){
            Log.e(getClass().getName(),"您有新的消息，请注意查收");
            isMore = false;
            pageNo = 2;
            Map<String, String> singleMap = MapUtils.getMap();
            loadData(singleMap);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finishActivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void finishActivity() {
        if (!AppManager.getAppManager().isInStack(MainActivity.class)){
            startActivity(new Intent(mContext, MainActivity.class));
        }
        finish();
    }
}
