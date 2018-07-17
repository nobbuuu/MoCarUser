package com.dream.moka.UI.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.BaseCommonTakePhotoActivity;
import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Bean.AddImgBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.DriverUserInfContract;
import com.dream.moka.Contract.FeedBackServiceContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.DriverUserInfoPresenter;
import com.dream.moka.Presenter.FeedBackServicePresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.CustomHelper;
import com.dream.moka.Utils.DensityUtil;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.PicUrlUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.TakePhotoUtils;
import com.dream.moka.Utils.ToastUtils;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class FeedBackServiceActivity extends BaseCommonTakePhotoActivity implements FeedBackServiceContract.View,DriverUserInfContract {

    @Inject
    FeedBackServicePresenter mFeedBackServicePresenter;
    @Inject
    DriverUserInfoPresenter mDriverUserInfoPresenter;
    @Bind(R.id.rearon_edt)
    EditText mRearonEdt;
    @Bind(R.id.describe_edt)
    EditText mDescribeEdt;
    @Bind(R.id.addimg_lay)
    LinearLayout mAddimgLay;
    @Bind(R.id.orderNo_edt)
    EditText mOrderNoEdt;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.addimg_gv2)
    GridView addimg_gv2;
    private AddImgAdapter2 addImgAdapter2;
    private List<AddImgBean> imgsb = new ArrayList<>();
    private int Tag;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback_keeper;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mFeedBackServicePresenter.attachView(this);
        mDriverUserInfoPresenter.attachView(this);
        addImgAdapter2 = new AddImgAdapter2(this, imgsb, R.layout.gvitem_addimg);
        addimg_gv2.setAdapter(addImgAdapter2);
    }

    @Override
    public String initTitleText() {
        return "投诉建议";
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
        initImg();
    }

    private void initImg() {
        imgsb.clear();
        AddImgBean addImgBean = new AddImgBean();
        addImgBean.setAdd(true);
        imgsb.add(addImgBean);
        addImgAdapter2.notifyDataSetChanged();
    }

    @Override
    public void eventListener() {

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

    @OnClick({R.id.back_relay,R.id.addimg_lay,R.id.sure_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.addimg_lay:

                break;
            case R.id.sure_tv:
                String reason = mRearonEdt.getText().toString();
                String describeStr = mDescribeEdt.getText().toString();
                String orderNo = mOrderNoEdt.getText().toString();
                Map<String, String> singleMap = MapUtils.getSingleMap();
                singleMap.put(Const.token, CommonAction.getToken());
                if (reason.isEmpty()){
                    ToastUtils.Toast_short("请填写投诉原因");
                    return;
                }else {
                    singleMap.put("reason",reason);
                }
                if (describeStr.isEmpty()){
                    ToastUtils.Toast_short("请填写投诉说明");
                    return;
                }else {
                    singleMap.put("content",describeStr);
                }
                if (!orderNo.isEmpty()){
                    singleMap.put("orderCode",orderNo);
                }
                if (imgsb.size()>1){
                    StringBuffer buffer = new StringBuffer();
                    for (int i = 0; i < imgsb.size()-1; i++) {
                        buffer.append(imgsb.get(i).getPicUrl()+",");
                    }
                    String tempPic = buffer.toString();
                    if (tempPic.length()>0){
                        singleMap.put("descPic",tempPic.substring(0,tempPic.length()-1));
                    }
                }
                mLoadingDialog.show();
                mFeedBackServicePresenter.addOrderComplaint(singleMap);
                break;
        }
    }

    @Override
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void showData(EmptyBean dataBean) {
        DialogUtils.actionResultDialog(mActivity,"投诉成功","平台会尽快处理").show();
        getCurrentFocus().clearFocus();
        initImg();
        mOrderNoEdt.setText("");
        mRearonEdt.setText("");
        mDescribeEdt.setText("");
    }

    @Override
    public void onSuccess(String filePath) {
        AddImgBean bean = new AddImgBean();
        bean.setDelete(true);
        bean.setAdd(false);
        bean.setPicUrl(filePath);
        AddImgBean addBean = new AddImgBean();
        addBean.setAdd(true);
        addBean.setDelete(false);
        switch (Tag) {
            case 2:
                if (imgsb.size()>0){
                    imgsb.remove(imgsb.size() - 1);
                    imgsb.add(bean);
                    imgsb.add(addBean);
                    addImgAdapter2.notifyDataSetChanged();
                }
                break;
        }
    }

    private class AddImgAdapter2 extends CommonAdapter<AddImgBean> {

        public AddImgAdapter2(Activity context, List<AddImgBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, final AddImgBean baseBean, final int position) {
            holder.getConvertView().setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {

                    if (baseBean.isAdd()) {
//                        imgDialog.show();
                        showTakePhotoDialog();
                        Tag = 2;

                    }
                }
            });

            ImageView added_iv = holder.getView(R.id.added_iv);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(DensityUtil.dip2px(mContext, 72), DensityUtil.dip2px(mContext, 72));
            added_iv.setLayoutParams(params);
            if (StringUtil.NoNullOrEmpty(baseBean.getPicUrl())) {
                String realUrl = PicUrlUtils.getRealUrl(baseBean.getPicUrl());
                Log.e("tag", "PicUrl=" + realUrl);
                added_iv.setVisibility(View.VISIBLE);
                GlidUtils.LoadImgForUrl(mContext, realUrl, added_iv);
            } else {
                added_iv.setVisibility(View.GONE);
            }
            ImageView delete_iv = holder.getView(R.id.delete_iv);
            if (baseBean.isDelete()) {
                delete_iv.setVisibility(View.VISIBLE);
            } else {
                delete_iv.setVisibility(View.GONE);
            }

            delete_iv.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    imgsb.remove(imgsb.get(position));
                    notifyDataSetChanged();
                }
            });
        }
    }
    private void showTakePhotoDialog() {
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

}
