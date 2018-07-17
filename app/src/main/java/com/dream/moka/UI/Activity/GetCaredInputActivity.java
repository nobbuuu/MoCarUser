package com.dream.moka.UI.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonTakePhotoActivity;
import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Bean.AddImgBean;
import com.dream.moka.Bean.EventBusBean.FinishBus;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.KeysBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.DriverCenter.SureGetCarContract;
import com.dream.moka.Contract.DriverUserInfContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.DriverCenter.SureGetCarPresenter;
import com.dream.moka.Presenter.DriverUserInfoPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.BitmapUtils;
import com.dream.moka.Utils.CustomHelper;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.SpUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.TakePhotoUtils;
import com.dream.moka.Utils.ToastUtils;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class GetCaredInputActivity extends BaseCommonTakePhotoActivity implements SureGetCarContract.View,DriverUserInfContract {

    @Inject
    SureGetCarPresenter mSureGetCarPresenter;

    @Inject
    DriverUserInfoPresenter mDriverUserInfoPresenter;

    @Bind(R.id.getcared_gv)
    GridView getcared_gv;
    @Bind(R.id.addimg_iv1)
    ImageView mAddimgIv1;
    @Bind(R.id.addimg_tv1)
    TextView mAddimgTv1;
    @Bind(R.id.addimg_relay1)
    RelativeLayout mAddimgRelay1;
    @Bind(R.id.addimg_iv2)
    ImageView mAddimgIv2;
    @Bind(R.id.addimg_tv2)
    TextView mAddimgTv2;
    @Bind(R.id.addimg_relay2)
    RelativeLayout mAddimgRelay2;
    @Bind(R.id.addimg_iv3)
    ImageView mAddimgIv3;
    @Bind(R.id.addimg_tv3)
    TextView mAddimgTv3;
    @Bind(R.id.addimg_relay3)
    RelativeLayout mAddimgRelay3;
    @Bind(R.id.addimg_iv4)
    ImageView mAddimgIv4;
    @Bind(R.id.addimg_tv4)
    TextView mAddimgTv4;
    @Bind(R.id.addimg_relay4)
    RelativeLayout mAddimgRelay4;
    @Bind(R.id.addimg_iv5)
    ImageView mAddimgIv5;
    @Bind(R.id.addimg_tv5)
    TextView mAddimgTv5;
    @Bind(R.id.addimg_relay5)
    RelativeLayout mAddimgRelay5;
    @Bind(R.id.addimg_iv6)
    ImageView mAddimgIv6;
    @Bind(R.id.addimg_tv6)
    TextView mAddimgTv6;
    @Bind(R.id.addimg_relay6)
    RelativeLayout mAddimgRelay6;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    private String[] strings = new String[]{"前方45°", "后方45°", "左方位45°", "右方45°", "仪表盘", "客户确认单"};
    private AddImgAdapter imgAdapter;
    private List<AddImgBean> dataList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_getcared_input;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        boolean isBackDriver = getIntent().getBooleanExtra("isBackDriver", false);
        if (isBackDriver){
            strings = new String[]{"左前45°", "右前45°", "左后45°", "右后45°", "仪表盘", "已完工清单"};
        }
        mSureGetCarPresenter.attachView(this);
        mDriverUserInfoPresenter.attachView(this);
        imgAdapter = new AddImgAdapter(this, dataList, R.layout.gvitem_getcared_addimg);
        getcared_gv.setAdapter(imgAdapter);
    }

    @Override
    public String initTitleText() {
        return "待确认取车";
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
        for (int i = 0; i < strings.length; i++) {
            AddImgBean imgBean = new AddImgBean();
            imgBean.setBotStr(strings[i]);
            dataList.add(imgBean);
        }
        imgAdapter.notifyDataSetChanged();
    }

    @Override
    public void eventListener() {

    }

    private int imgTag;
    private String frontPic,backPic,backLeftPic,backRightPic,dashboardPic,orderconfirmPic;

    @OnClick({R.id.back_relay, R.id.sure_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.sure_tv:
                String orderId = getIntent().getStringExtra(Const.intentTag);
                if (StringUtil.NoNullOrEmpty(orderId)){
                    Map<String, String> map = MapUtils.getSingleMap();
                    if (frontPic==null||backPic==null||backLeftPic==null||backRightPic==null||dashboardPic==null||orderconfirmPic==null){
                        ToastUtils.Toast_long("请上传完所有方位的照片");
                    }else {
                        map.put("frontPic",frontPic);
                        map.put("backPic",backPic);
                        map.put("backLeftPic",backLeftPic);
                        map.put("backRightPic",backRightPic);
                        map.put("dashboardPic",dashboardPic);
                        map.put("orderconfirmPic",orderconfirmPic);
                        map.put("longitude",CommonAction.getLongitude());
                        map.put("latitude",CommonAction.getLatitude());
                        mLoadingDialog.show();
                        mSureGetCarPresenter.saveCarLive(CommonAction.getToken(), orderId, map);
                    }
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        ArrayList<TImage> images = result.getImages();
        if (images.size()>0){
            String compressPath = images.get(images.size() - 1).getCompressPath();
            mLoadingDialog.show();
            mDriverUserInfoPresenter.uploadPic(compressPath);

        }
    }

    private void takePhoto() {
        new TakePhotoUtils(mActivity) {
            @Override
            protected void onPhoto() {
                CustomHelper.toPicture(getTakePhoto());
            }

            @Override
            protected void onCamara() {
                CustomHelper.toCamara(getTakePhoto());
            }
        };
    }

    @Override
    public void showSureGetCar(KeysBean dataBean) {
        DialogUtils.showCaredTip(mActivity);
        SpUtils.savaUserInfo(Const.keysId,dataBean.getDriverHisId());
        SpUtils.savaUserInfo(Const.isUpload,true);
        EventBus.getDefault().post("Ordering");
        finish();
    }

    @Override
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }


    private int takePhotoTag;

    @OnClick({R.id.addimg_iv1, R.id.addimg_iv2, R.id.addimg_iv3, R.id.addimg_iv4, R.id.addimg_iv5, R.id.addimg_iv6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addimg_iv1:
                takePhoto();
                takePhotoTag = 1;
                break;
            case R.id.addimg_iv2:
                takePhoto();
                takePhotoTag = 2;

                break;
            case R.id.addimg_iv3:
                takePhoto();
                takePhotoTag = 3;

                break;
            case R.id.addimg_iv4:
                takePhoto();
                takePhotoTag = 4;

                break;
            case R.id.addimg_iv5:
                takePhoto();
                takePhotoTag = 5;

                break;
            case R.id.addimg_iv6:
                takePhoto();
                takePhotoTag = 6;

                break;
        }
    }

    @Override
    public void onSuccess(String path) {
        switch (takePhotoTag) {
            case 1:
                GlidUtils.LoadImgForUrl(mContext,Const.BannerUrl+path,mAddimgIv1);
                frontPic = path;
                break;
            case 2:
                GlidUtils.LoadImgForUrl(mContext,Const.BannerUrl+path,mAddimgIv2);
                backPic = path;

                break;
            case 3:
                GlidUtils.LoadImgForUrl(mContext,Const.BannerUrl+path,mAddimgIv3);
                backLeftPic = path;
                break;
            case 4:
                GlidUtils.LoadImgForUrl(mContext,Const.BannerUrl+path,mAddimgIv4);
                backRightPic = path;
                break;
            case 5:
                GlidUtils.LoadImgForUrl(mContext,Const.BannerUrl+path,mAddimgIv5);
                dashboardPic = path;
                break;
            case 6:
                GlidUtils.LoadImgForUrl(mContext,Const.BannerUrl+path,mAddimgIv6);
                orderconfirmPic = path;
                break;
        }
    }


    private class AddImgAdapter extends CommonAdapter<AddImgBean> {

        public AddImgAdapter(Activity context, List<AddImgBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, AddImgBean dataBean, final int position) {
            holder.setText(R.id.bot_tv, dataBean.getBotStr());
            final ImageView addimg_iv1 = holder.getView(R.id.addimg_iv1);
            if (dataBean.getBitmap() != null) {
                addimg_iv1.setImageBitmap(dataBean.getBitmap());
            }
            holder.setOnClickListener(R.id.addimg_relay1, new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    DialogUtils.initPictureMethodDialog(mActivity).show();
                    imgTag = position;
                }
            });
            ImageView add_tv = holder.getView(R.id.addimg_tv1);
            if (dataBean.isAdd()) {
                add_tv.setVisibility(View.VISIBLE);
                addimg_iv1.setVisibility(View.VISIBLE);

            } else {
                add_tv.setVisibility(View.GONE);
                addimg_iv1.setVisibility(View.GONE);
            }

            add_tv.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    dataList.get(position).setAdd(false);
                    imgAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(FinishBus finishBus){

    }
}
