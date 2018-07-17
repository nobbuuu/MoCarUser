package com.dream.moka.UI.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.CityDataBean;
import com.dream.moka.Bean.CitysResultBean;
import com.dream.moka.Bean.LetterBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.CityChooseContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Presenter.CityChoosePresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.RvUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
public class CityChooseActivity extends BaseActivity implements CityChooseContract {
    @Inject
    CityChoosePresenter mCityChoosePresenter;
    @Bind(R.id.hotcity_gv)
    GridView hotcity_gv;
    @Bind(R.id.city_rv)
    RecyclerView city_rv;
    @Bind(R.id.letter_rv)
    RecyclerView letter_rv;
    @Bind(R.id.current_city)
    TextView current_city;

    private HotCityAdapter hotCityAdapter;
    private CityListAdapter citysAdapter;
    private LetterAdapter letterAdapter;
    private List<CitysResultBean.ListHotBean> dataList = new ArrayList<>();
    private List<CityDataBean> cityDataList = new ArrayList<>();
    private String[] lArray = new String[]{"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private List<LetterBean> letterList = new ArrayList<>();

    private String tempCity = "";
    private Dialog mLoadingDialog;

    public static void openAct(Activity activity, int request) {
        activity.startActivityForResult(new Intent(activity, CityChooseActivity.class), request);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_citychoose;
    }


    @Override
    public void initView() {
        mCityChoosePresenter.attachView(this);
        hotCityAdapter = new HotCityAdapter(this, dataList, R.layout.rvitem_city);
        hotcity_gv.setAdapter(hotCityAdapter);
        RvUtils.setOption_noparam(city_rv, mActivity);
        citysAdapter = new CityListAdapter(this, cityDataList, R.layout.rvitem_citylistitem);
        city_rv.setAdapter(citysAdapter);
        RvUtils.setOptionnoLine(letter_rv, mActivity);
        letterAdapter = new LetterAdapter(this, letterList, R.layout.rvitem_onlytext_letter);
        letter_rv.setAdapter(letterAdapter);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {
        mLoadingDialog.show();
        mCityChoosePresenter.getCityData();
    }

    @Override
    public void eventListener() {
        hotcity_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tempCity = dataList.get(i).getName();
                current_city.setText(tempCity);
                current_city.setTextColor(ResourcesUtils.getColor(R.color.color333));

            }
        });
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
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
    public void getDataSuccess(CitysResultBean citysResultBean) {
        if (citysResultBean != null) {
            List<CitysResultBean.ListHotBean> listHot = citysResultBean.getListHot();
            if (listHot != null && listHot.size() != 0) {
                dataList.addAll(listHot);
                hotCityAdapter.notifyDataSetChanged();
            }
            List<CityDataBean> cityDataBeanList=new ArrayList<>();
            List<CitysResultBean.ListBean> list = citysResultBean.getList();
            if (list!=null&&list.size()!=0){
                for (int i = 0; i < lArray.length; i++) {
                    CityDataBean cityDataBean = new CityDataBean();
                    List<CityDataBean.CityItemBean> itemBeanList=new ArrayList<>();
                    LetterBean bean = new LetterBean();
                    for (int j = 0; j < list.size(); j++) {
                        String s = list.get(j).getInitials().toUpperCase();
                        if (lArray[i].equals(s)){
                            cityDataBean.setLetter(lArray[i]);
                            CityDataBean.CityItemBean cityItemBean = new CityDataBean.CityItemBean();
                            cityItemBean.setCityName(list.get(j).getName());
                            itemBeanList.add(cityItemBean);
                            bean.setNameStr(lArray[i]);
                        }
                    }
                    if (itemBeanList.size()>0){
                        cityDataBean.setCityItemBeanList(itemBeanList);
                        cityDataBeanList.add(cityDataBean);
                    }

                    if (bean.getNameStr()!=null&&!bean.getNameStr().equals("")){
                        letterList.add(bean);
                    }
                }
                cityDataList.addAll(cityDataBeanList);
                citysAdapter.notifyDataSetChanged();
                letterAdapter.notifyDataSetChanged();
            }
        }
    }

    private class LetterAdapter extends RVBaseAdapter<LetterBean> {

        public LetterAdapter(Activity context, List<LetterBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final LetterBean cityBean, int position) {
            holder.setText(R.id.only_tv, cityBean.getNameStr());
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    String nameStr = cityBean.getNameStr();
                    for (int j = 0; j < letterList.size(); j++) {
                        if (nameStr.equals(letterList.get(j))) {
                            RvUtils.MoveToPosition((LinearLayoutManager) city_rv.getLayoutManager(), j);
                        }
                    }
                }
            });
        }
    }

    private class HotCityAdapter extends CommonAdapter<CitysResultBean.ListHotBean> {

        public HotCityAdapter(Activity context, List<CitysResultBean.ListHotBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, final CitysResultBean.ListHotBean listHotBean, int position) {
            holder.setText(R.id.city_itemtv, listHotBean.getName());
            holder.getConvertView().setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    setResult("city", listHotBean.getName(), 110);
                }
            });
        }
    }

    private void setResult(String intentTag, String nameStr, int resultCode) {
        Intent intent = getIntent();
        intent.putExtra(intentTag, nameStr);
        setResult(resultCode, intent);
        finish();
    }

    private class CityListAdapter extends RVBaseAdapter<CityDataBean> {

        public CityListAdapter(Activity context, List<CityDataBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, CityDataBean cityDataBean, int position) {
            holder.setText(R.id.city_letter, cityDataBean.getLetter());
            ListView cityitem_lv = holder.getView(R.id.cityitem_lv);
            cityitem_lv.setAdapter(new SmallCityAdapter(mActivity, cityDataBean.getCityItemBeanList(), R.layout.rvitem_onlytext_city));
        }

        private class SmallCityAdapter extends CommonAdapter<CityDataBean.CityItemBean> {

            public SmallCityAdapter(Activity context, List<CityDataBean.CityItemBean> mDatas, int itemLayoutId) {
                super(context, mDatas, itemLayoutId);
            }

            @Override
            public void convert(BaseViewHolder holder, final CityDataBean.CityItemBean dataBean, int position) {
                TextView city_tv = holder.getView(R.id.only_tv);
                city_tv.setTextColor(ResourcesUtils.getColor(R.color.color666));
                city_tv.setText(dataBean.getCityName());
                holder.getConvertView().setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View view) {
                        setResult("city", dataBean.getCityName(), 110);
                    }
                });
            }
        }
    }

    @OnClick({R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                setResult("city", tempCity, 111);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            Intent intent = getIntent();
            intent.putExtra("city", tempCity);
            setResult(111, intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
