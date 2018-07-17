package com.dream.moka.UI.ChildFragment;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Adapter.Maintain.FreeMaintaiAdapter;
import com.dream.moka.Adapter.Orders.ProductAdapter;
import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.EventBusBean.MaintainBusBean;
import com.dream.moka.Bean.EventBusBean.MoneyUpdateBean;
import com.dream.moka.Bean.Maintain.FreeMainTainBean;
import com.dream.moka.Bean.Maintain.FreeMaintainAllBean;
import com.dream.moka.Bean.Maintain.ProductSelectBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.FreeMaitain.FreeMainTainContract;
import com.dream.moka.Contract.FreeMaitain.ProductListContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.FreeMaintain.FreeMaintainPresenter;
import com.dream.moka.Presenter.FreeMaintain.ProductsListPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.other.WebViewActivity;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class FreeMaintainRvFragment extends BaseFragmentV4 implements FreeMainTainContract.View {

    @Inject
    FreeMaintainPresenter mFreeMaintainPresenter;

    @Inject
    ProductsListPresenter mProductsListPresenter;

    @Bind(R.id.common_rv)
    RecyclerView common_rv;

    private FreeMaintaiAdapter purseAdapter;
    public List<FreeMaintainAllBean.ServiceResultBean> dataList = new ArrayList<>();

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public void initView() {

        mFreeMaintainPresenter.attachView(this);
        RvUtils.setOptionnoLine(common_rv, getActivity());
        purseAdapter = new FreeMaintaiAdapter(getActivity(), dataList, R.layout.rvitem_program,"select");
        common_rv.setAdapter(purseAdapter);

        EventBus.getDefault().register(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_commonrv_nosmart;
    }

    @Override
    public void initResume() {

    }

    @Override
    public void initEvents() {

    }

    private int type = 0;
    private String carId = "";
    private String repairShopId = "";
    private String shopType = "";

    public void setType(int type) {
        this.type = type;
    }

    public void setData(String carId, String repairShopId,String shopType) {
        this.carId = carId;
        this.repairShopId = repairShopId;
        this.shopType = shopType;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(MaintainBusBean bean) {
        carId = bean.getCarId();
        repairShopId = bean.getRepairShopId();
        shopType = bean.getShopType();
        if (StringUtil.NoNullOrEmpty(carId)&&StringUtil.NoNullOrEmpty(repairShopId)&&StringUtil.NoNullOrEmpty(shopType)){
            loadMaintainData();
        }else {
            dataList.clear();
            purseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void initDta() {

    }


    private void loadMaintainData() {
        Map<String, String> map = MapUtils.getSingleMap();
        map.put(Const.token, CommonAction.getToken());
        map.put("carId", carId);
        map.put("repairShopId", repairShopId);
        map.put("type", String.valueOf(type));
//        mLoadingDialog.show();
        if (type==0){
            showLoadingDialog();
            mLoadingDialog.show();
        }
        mFreeMaintainPresenter.getSelfChoseServiceList(map);
    }

    private String partShopId;
    @Override
    public void showServiceData(List<FreeMaintainAllBean.ServiceResultBean> dataBean) {
        Collections.sort(dataBean, new Comparator<FreeMaintainAllBean.ServiceResultBean>(){
            /*
             * int compare(Person p1, Person p2) 返回一个基本类型的整型，
             * 返回负数表示：p1 小于p2，
             * 返回0 表示：p1和p2相等，
             * 返回正数表示：p1大于p2
             */
            public int compare(FreeMaintainAllBean.ServiceResultBean p1, FreeMaintainAllBean.ServiceResultBean p2) {
                //按照Person的年龄进行降序排列
                String remarks1 = p1.getServiceItem().getRemarks();
                String remarks2 = p2.getServiceItem().getRemarks();
                if (StringUtil.NoNullOrEmpty(remarks1)&&StringUtil.NoNullOrEmpty(remarks2)){
                    if(Integer.valueOf(remarks1) < Integer.valueOf(remarks2)){
                        return 1;
                    }
                    if(Integer.valueOf(remarks1) == Integer.valueOf(remarks2)){
                        return 0;
                    }
                }
                return -1;
            }
        });
        dataList.clear();

        if (dataBean != null && dataBean.size() > 0) {
            dataList.addAll(dataBean);
        }
        EventBus.getDefault().post(new MoneyUpdateBean());
       /* if (StringUtil.NoNullOrEmpty(partShopId)){
        }else {
            ToastUtils.Toast_short("当前维修商不支持该服务，请选择其他维修商");
            Log.e(getClass().getName(),"partShopId="+partShopId);
            Log.e(getClass().getName(),"shopType="+shopType);
        }*/
        purseAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }

}
