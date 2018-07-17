package com.dream.moka.UI.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Bean.AddServiceUserCarResultBean;
import com.dream.moka.Bean.CarDetailResultBean;
import com.dream.moka.Bean.ChooseCarInfoBean;
import com.dream.moka.Bean.Online.OnlinerBean;
import com.dream.moka.Bean.ServiceBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.ChatContract;
import com.dream.moka.Contract.OneChooseCarContract;
import com.dream.moka.IM.im.activity.ChatActivity;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.OneChooseCarPresenter;
import com.dream.moka.Presenter.Online.ChatPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.FeedBackServiceActivity;
import com.dream.moka.UI.Activity.mycar.MyCarsActivity;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
public class OnlineServiceFragment extends BaseFragmentV4 implements OneChooseCarContract,ChatContract.View{

    @Bind(R.id.service_gv)
    GridView service_gv;

    @Inject
    OneChooseCarPresenter mOneChooseCarPresenter;
    @Bind(R.id.car_icon)
    ImageView mCarIcon;
    @Bind(R.id.car_name)
    TextView mCarName;
    @Bind(R.id.car_typeTv)
    TextView mCarTypeTv;
    @Bind(R.id.carselect_iv)
    ImageView mCarselectIv;
    @Inject
    ChatPresenter mChatPresenter;

    private ServiceAdapter serviceAdapter;
    private int[] imgs = new int[]{R.mipmap.gray_icon_maintenance, R.mipmap.gray_icon_service, R.mipmap.gray_icon_painting, R.mipmap.gray_icon_insurance, R.mipmap.gray_icon_complaints, R.mipmap.gray_icon_other};
    private String[] strings = new String[]{"保养", "维修", "钣喷", "保险", "投诉", "其他"};
    private List<ServiceBean> serviceBeanList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_onlineservice;
    }

    private boolean isSellect;
    @Override
    public void initResume() {
        if (!isSellect){
            mOneChooseCarPresenter.getDefultCar();
        }
    }

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public void initView() {
        mOneChooseCarPresenter.attachView(this);
        mChatPresenter.attachView(this);
        serviceAdapter = new ServiceAdapter(getActivity(), serviceBeanList, R.layout.view_imgtext);
        service_gv.setAdapter(serviceAdapter);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void initDta() {
        mOneChooseCarPresenter.getDefultCar();
        serviceBeanList.clear();
        for (int i = 0; i < imgs.length; i++) {
            ServiceBean bean = new ServiceBean();
            bean.setName(strings[i]);
            bean.setResId(imgs[i]);
            serviceBeanList.add(bean);
        }
        serviceAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    private String carId = "";
    @Override
    public void onSuccess(CarDetailResultBean carDetailResultBean) {
        mCarTypeTv.setVisibility(View.VISIBLE);
        mCarIcon.setVisibility(View.VISIBLE);
        mCarName.setText(carDetailResultBean.getBrandName() + carDetailResultBean.getCateName());
        mCarTypeTv.setText(carDetailResultBean.getConfigName());
        GlidUtils.LoadImgForUrl(getContext(), carDetailResultBean.getBranLogo(), mCarIcon);
        carId = carDetailResultBean.getCarId();
        Log.e("tag","carId="+carId);
    }

    @Override
    public void addServiceUserCarSuccess(AddServiceUserCarResultBean results) {

    }

    @Override
    public void showNoCar() {
        mCarTypeTv.setVisibility(View.GONE);
        mCarName.setText("请先添加爱车");
        carId = "";
        Log.e("tag","carId="+carId);
        mCarIcon.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.car_relay,R.id.service_tv})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.car_relay:
                if (CommonAction.getIsLogin()){
                    MyCarsActivity.openAct(getActivity(), Const.coinSpray);
                    isSellect = true;
                }else {
                    DialogUtils.getLoginTip(getActivity()).show();
                }
                break;
            case R.id.service_tv:
                IntentUtils.call_Dall(CommonAction.getPlatPhone(),getActivity());
                break;
        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(ChooseCarInfoBean carDetailResultBean) {

        mCarTypeTv.setVisibility(View.VISIBLE);
        mCarName.setText(carDetailResultBean.getBrandName() + carDetailResultBean.getCateName());
        mCarTypeTv.setText(carDetailResultBean.getConfigName());
        GlidUtils.LoadImgForUrl(getContext(), carDetailResultBean.getBranLogo(), mCarIcon);
        carId = carDetailResultBean.getCarId();
        Log.e("tag","carId="+carId);

    }

    @Override
    public void showOnlinerData(OnlinerBean dataBean) {
        String userId = dataBean.getUserId();
        if (StringUtil.NoNullOrEmpty(userId)){
            Intent intent = new Intent(getContext(), ChatActivity.class);
            intent.putExtra("data",dataBean);
            startActivity(intent);
        }else {
            ToastUtils.Toast_short("暂无技师，请稍后再试");
        }
    }

    private class ServiceAdapter extends CommonAdapter<ServiceBean> {

        public ServiceAdapter(Activity context, List<ServiceBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, ServiceBean dataBean, final int position) {
            holder.setImageResource(R.id.img_iv, dataBean.getResId());
            holder.setText(R.id.content_tv, dataBean.getName());
            holder.getConvertView().setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    if (CommonAction.getIsLogin()){

                        if (carId.isEmpty()){
                            ToastUtils.Toast_long("请先添加爱车");
                            return;
                        }

                        if ( position != serviceBeanList.size() - 2) {
                            if (StringUtil.NoNullOrEmpty(carId)) {
                                mChatPresenter.getOnliner(carId, String.valueOf(position));
                            }
                        } else {
                            IntentUtils.toActivity(FeedBackServiceActivity.class, getActivity());
                        }
                    }else {
                        DialogUtils.getLoginTip(getActivity()).show();
                    }
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mOneChooseCarPresenter.detachView();
    }
}
