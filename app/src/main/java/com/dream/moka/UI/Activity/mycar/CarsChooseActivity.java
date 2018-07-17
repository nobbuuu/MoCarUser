package com.dream.moka.UI.Activity.mycar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.CarBrandBean;
import com.dream.moka.Bean.CarBrandResultBean;
import com.dream.moka.Bean.CarCategoryResultBean;
import com.dream.moka.Bean.ChooseCarInfoBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.CarsChooseContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.CarsChoosePresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.DensityUtil;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.FullyGridLayoutManager;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.PinYinUtil;
import com.dream.moka.Utils.PopWindowUtil;
import com.dream.moka.Utils.RvUtils;
import com.kevin.wraprecyclerview.WrapRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class CarsChooseActivity extends BaseCommonActivity implements CarsChooseContract {

    private GridView hotcar_gv;
    @Bind(R.id.cars_rv)
    WrapRecyclerView cars_rv;
    @Bind(R.id.letter_rv)
    RecyclerView letter_rv;
    @Bind(R.id.car_fra)
    FrameLayout car_fra;
    @Bind(R.id.smart_refreshlay)
    SmartRefreshLayout smart_refreshlay;

    private CarsAdapter carsAdapter;
    private LetterAdapter letterAdapter;
    private String[] lArray = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private List<String> letterList = new ArrayList<>();
    private List<CarBrandBean> carsData = new ArrayList<>();
    private List<CarBrandResultBean.ListHotBean> mListHotBeans = new ArrayList<>();
    @Inject
    CarsChoosePresenter carsChoosePresenter;
    private String mFrom;
    private HotAdapter mHotAdapter;

    public static void openAct(Context context, String from) {
        Intent intent = new Intent(context, CarsChooseActivity.class);
        intent.putExtra("from", from);
        context.startActivity(intent);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            title_tv.setText("选车型");
            car_fra.setVisibility(View.VISIBLE);
            car_fra.setAlpha(0.5f);

        }
    };
    private Dialog mLoadingDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_carschoose;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        carsChoosePresenter.attachView(this);
        RvUtils.setOptionnoLine(cars_rv, this);
        RvUtils.setOptionnoLine(letter_rv, mActivity);
        letterAdapter = new LetterAdapter(this, letterList, R.layout.rvitem_onlytext_letter);
        letter_rv.setAdapter(letterAdapter);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        mFrom = getIntent().getStringExtra("from");
        AppManager.getAppManager().addActivity(this);
        mHotAdapter = new HotAdapter(mActivity, mListHotBeans, R.layout.hot_car_layout);

        View hotview = LayoutInflater.from(this).inflate(R.layout.view_hotbrand_car, null);
        hotcar_gv = (GridView) hotview.findViewById(R.id.hotcar_gv);
//        hotcar_gv.setLayoutManager(new FullyGridLayoutManager(mContext, 5));
//        RvUtils.setOptionnoLine_h(hotcar_gv,this);
        hotcar_gv.setAdapter(mHotAdapter);
        cars_rv.addHeaderView(hotview);

        carsAdapter = new CarsAdapter(this, carsData, R.layout.rvitem_citylistitem);
        cars_rv.setAdapter(carsAdapter);

    }

    @Override
    public String initTitleText() {
        return "选品牌";
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
        carsChoosePresenter.getCarsData(MapUtils.getBigMap());
    }

    private int pageNo = 2;
    private boolean isMore;
    private int totalPage;
    private void loadData(Map<String,String> map) {
        carsChoosePresenter.getCarsData(map);
    }

    @Override
    public void eventListener() {
        smart_refreshlay.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isMore = false;
                pageNo = 2;
                Map<String, String> singleMap = MapUtils.getBigMap();
                loadData(singleMap);
            }
        });
        smart_refreshlay.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                isMore = true;
                Map<String, String> singleMap = MapUtils.getBigMap();
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
                finish();
                break;
        }
    }


    @Override
    public void showData(CarBrandResultBean results) {
        //热门
        List<CarBrandResultBean.ListHotBean> listHot = results.getListHot();
        mListHotBeans.clear();
        if (listHot != null && listHot.size() > 0) {
            mListHotBeans.addAll(listHot);
        }
        mHotAdapter.notifyDataSetChanged();
        //所有
        CarBrandResultBean.ListBeanX listBeanX = results.getList();
        if (listBeanX != null) {
            totalPage = listBeanX.getTotalPage();
            List<CarBrandResultBean.ListBeanX.ListBean> list = listBeanX.getList();
            if (!isMore){
                carsData.clear();
            }
            letterList.clear();
            if (list != null&&list.size()>0) {
                List<CarBrandBean> tempList = new ArrayList<>();//品牌适配器数据源（二级）
                for (int i = 0; i < lArray.length; i++) {
                    CarBrandBean carBrandBean = new CarBrandBean();
                    List<CarBrandBean.ResultBean> beanList = new ArrayList<>();//车品牌实体
                    for (int j = 0; j < list.size(); j++) {
                        String name = list.get(j).getName();
                        String pinYinHeadChar = PinYinUtil.getInstance().getPinYinHeadChar(name);
                        String tempChar = "";
                        if (pinYinHeadChar != null && pinYinHeadChar.length() > 1) {
                            tempChar = pinYinHeadChar.substring(0, 1);
                        }
                        if (lArray[i].equals(tempChar)) {
                            CarBrandBean.ResultBean resultBean = new CarBrandBean.ResultBean();
                            resultBean.setName(name);
                            resultBean.setCarLogo(list.get(j).getCarLogo());
                            resultBean.setId(list.get(j).getId());
                            beanList.add(resultBean);
                        }
                    }
                    if (beanList.size() > 0) {
                        carBrandBean.setBeanList(beanList);
                        carBrandBean.setTitle(lArray[i]);
                        tempList.add(carBrandBean);
                        letterList.add(lArray[i]);
                    }
                }
                carsData.addAll(tempList);
            }
            letterAdapter.notifyDataSetChanged();
            carsAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void showCarStleData(List<CarCategoryResultBean> results) {
        //加载弹出框的布局
        View contentView = LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.pop_carstyle, null);
        PopupWindow popupWindow = PopWindowUtil.getPopupWindow(mActivity, contentView, R.style.activityAnimation, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ListView popcarstyle_rv = (ListView) contentView.findViewById(R.id.popcarstyle_rv);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                title_tv.setText("选品牌");
                car_fra.setVisibility(View.GONE);
            }
        });
        popcarstyle_rv.setAdapter(new CarStyleAdapter(mActivity, results, R.layout.rvitem_onlytext_city));
        mHandler.sendEmptyMessage(101);
        popupWindow.showAtLocation(root_lay, Gravity.RIGHT, 0, 0);

    }


    @Override
    public void showError() {
        finishDialog();
    }

    private void finishDialog() {
        mLoadingDialog.dismiss();
        if (smart_refreshlay!=null){
            smart_refreshlay.finishLoadmore();
            smart_refreshlay.finishRefresh();
        }
    }

    @Override
    public void complete() {
        finishDialog();
    }


    private class HotAdapter extends CommonAdapter<CarBrandResultBean.ListHotBean> {

        public HotAdapter(Activity context, List<CarBrandResultBean.ListHotBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, final CarBrandResultBean.ListHotBean listHotBean, int position) {
            TextView city_tv = holder.getView(R.id.content_tv);
            city_tv.setText(listHotBean.getName());
            holder.setImageByUrl(R.id.img_iv, listHotBean.getCarLogo(), false);
            holder.getConvertView().setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    carsChoosePresenter.getCarStyle(listHotBean.getId());
                    tempName = listHotBean.getName();
                }
            });
        }
    }

    private class LetterAdapter extends RVBaseAdapter<String> {

        public LetterAdapter(Activity context, List<String> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final String s, int position) {

            holder.setText(R.id.only_tv, s);
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    String nameStr = s;
                    for (int j = 0; j < letterList.size(); j++) {
                        if (nameStr.equals(letterList.get(j))) {
                            RvUtils.MoveToPosition((LinearLayoutManager) cars_rv.getLayoutManager(), j+1);
                        }
                    }
                }
            });
        }
    }

    private String tempName = "";//选中的车品牌

    private class CarsAdapter extends RVBaseAdapter<CarBrandBean> {

        public CarsAdapter(Activity context, List<CarBrandBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final CarBrandBean carBrandBean, int position) {
            holder.setText(R.id.city_letter, carBrandBean.getTitle());
            ListView cityitem_lv = holder.getView(R.id.cityitem_lv);
            List<CarBrandBean.ResultBean> beanList = carBrandBean.getBeanList();
            if (beanList != null) {
                cityitem_lv.setAdapter(new SmallCityAdapter(mActivity, beanList, R.layout.view_imgtexth));
            }
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {

                }
            });
        }

        private class SmallCityAdapter extends CommonAdapter<CarBrandBean.ResultBean> {

            public SmallCityAdapter(Activity context, List<CarBrandBean.ResultBean> mDatas, int itemLayoutId) {
                super(context, mDatas, itemLayoutId);
            }

            @Override
            public void convert(BaseViewHolder holder, final CarBrandBean.ResultBean resultBean, int position) {
                TextView city_tv = holder.getView(R.id.content_tv);
                city_tv.setText(resultBean.getName());
                holder.setImageByUrl(R.id.img_iv, resultBean.getCarLogo(), false);
                holder.getConvertView().setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View view) {
                        carsChoosePresenter.getCarStyle(resultBean.getId());
                        tempName = resultBean.getName();
                    }
                });
            }
        }
    }

    private class CarStyleAdapter extends CommonAdapter<CarCategoryResultBean> {

        public CarStyleAdapter(Activity context, List<CarCategoryResultBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, final CarCategoryResultBean dataBean, int position) {
            TextView city_tv = holder.getView(R.id.only_tv);
            city_tv.setText(dataBean.getName());
            city_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChooseCarInfoBean chooseCarInfoBean = new ChooseCarInfoBean();
                    chooseCarInfoBean.setCarName(tempName + dataBean.getName());
                    chooseCarInfoBean.setFrom(mFrom == null ? "" : mFrom);
                    CarConfigActivity.openAct(mContext, dataBean.getId(), chooseCarInfoBean);
                }
            });
        }
    }
}
