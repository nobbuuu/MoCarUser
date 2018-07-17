package com.dream.moka.UI.Activity.mycar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.CarResultBean;
import com.dream.moka.Bean.ChooseCarInfoBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.MyCarContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.MyCarPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Bean.EventBusBean.RefreshMyCar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class MyCarsActivity extends BaseCommonActivity implements MyCarContract {

    private String mFrom;
    private List<CarResultBean> dataList = new ArrayList<>();
    private CarsAdapter commonAdapter;

    @Bind(R.id.cars_rv)
    RecyclerView common_rv;
    @Inject
    MyCarPresenter mMyCarPresenter;
    private Dialog mLoadingDialog;

    public static void openAct(Activity context, String from) {
        Intent intent = new Intent(context, MyCarsActivity.class);
        intent.putExtra("from", from);
        context.startActivityForResult(intent,100);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mycars;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mMyCarPresenter.attachView(this);
        RvUtils.setOptionnoLine(common_rv, this);
        commonAdapter = new CarsAdapter(this, dataList, R.layout.rvitem_mycar);
        common_rv.setAdapter(commonAdapter);
        EventBus.getDefault().register(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        mFrom = getIntent().getStringExtra("from");
    }

    @Subscribe
    public void onEvent(RefreshMyCar refreshMyCar) {
        //刷新列表
        mMyCarPresenter.getMyCarData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
        mLoadingDialog.show();
        mMyCarPresenter.getMyCarData();
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay, R.id.addcar_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                setResult(100);
                finish();
                break;
            case R.id.addcar_tv:
                if (mFrom!=null){
                    CarsChooseActivity.openAct(mActivity, mFrom);
                }
                break;
        }
    }

    @Override
    public void showError() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void onDeleteSuccess(int position) {
        dataList.remove(position);
        commonAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDefultSuccess(int position) {
        for (int i = 0; i < dataList.size(); i++) {
            if (i == position) {
                dataList.get(i).setStatus("1");
            } else {
                dataList.get(i).setStatus("0");
            }
        }
        commonAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDataSuccess(List<CarResultBean> results) {
        dataList.clear();
        if (results != null && results.size() != 0) {
            dataList.addAll(results);
        }
        commonAdapter.notifyDataSetChanged();
    }

    private class CarsAdapter extends RVBaseAdapter<CarResultBean> {

        public CarsAdapter(Activity context, List<CarResultBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final CarResultBean carResultBean, final int position) {
            holder.setImageByUrl(R.id.car_icon, carResultBean.getBranLogo(),true);
            holder.setText(R.id.brandName, carResultBean.getUserName()+"   "+carResultBean.getCardNo());
//            holder.setText(R.id.stylename, carResultBean.getCateName() + carResultBean.getConfigName());
            holder.setText(R.id.stylename, "下次保养里程数："+carResultBean.getMileage()+" km");
            TextView textView = holder.getView(R.id.defult);
            String status = carResultBean.getStatus();
            if (status != null) {
                if (status.equals("1")) {//默认
                    textView.setBackgroundResource(R.drawable.shape_yellow);
                    textView.setTextColor(Color.parseColor("#ffffff"));
                    holder.setText(R.id.defult, "已设为默认");
                } else {
                    textView.setBackgroundResource(R.drawable.shape_stroke_input);
                    textView.setTextColor(Color.parseColor("#333333"));
                    holder.setText(R.id.defult, "设为默认");
                    holder.getView(R.id.defult).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mLoadingDialog.show();
                            mMyCarPresenter.setDefult(carResultBean.getCarId(), position);
                        }
                    });

                }
            }
            holder.setOnClickListener(R.id.delete, new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    DialogUtils.showDeleteDialog(mActivity, new NoDoubleClickListener() {
                        @Override
                        public void onNoDoubleClick(View view) {
                            mLoadingDialog.show();
                            mMyCarPresenter.deleteCarData(carResultBean.getCarId(), position);
                        }
                    }).show();
                }
            });
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    if (mFrom != null&&mFrom.equals("mine")) {
                        CarInfoActivity.openAct(mContext, carResultBean.getCarId());
                    }else {
                        String brandName = carResultBean.getBrandName();
                        String cateName = carResultBean.getCateName();
                        String configName = carResultBean.getConfigName();
                        ChooseCarInfoBean chooseCarInfoBean = new ChooseCarInfoBean();
                        chooseCarInfoBean.setBrandName(brandName);
                        chooseCarInfoBean.setCateName(cateName);
                        chooseCarInfoBean.setConfigName(configName);
                        chooseCarInfoBean.setCarName(brandName + cateName + configName);
                        chooseCarInfoBean.setConfigId(carResultBean.getConfigId());
                        chooseCarInfoBean.setCarId(carResultBean.getCarId());
                        chooseCarInfoBean.setBranLogo(carResultBean.getBranLogo());
                        EventBus.getDefault().post(chooseCarInfoBean);
                        finish();
                    }
                }
            });
        }
    }
}
