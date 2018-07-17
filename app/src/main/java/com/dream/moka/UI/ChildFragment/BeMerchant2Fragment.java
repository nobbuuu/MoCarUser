package com.dream.moka.UI.ChildFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dream.moka.Base.BaseTakePhotoFragmentV4;
import com.dream.moka.Bean.EventBusBean.AFRefreshBean;
import com.dream.moka.Bean.EventBusBean.UpBitmapBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.Merchant.AplyMerchantBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.BeMerchantContract;
import com.dream.moka.Contract.DriverUserInfContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.BeMerchantPresenter;
import com.dream.moka.Presenter.DriverUserInfoPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.CustomHelper;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.TakePhotoUtils;
import com.dream.moka.Utils.ToastUtils;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class BeMerchant2Fragment extends BaseTakePhotoFragmentV4 implements BeMerchantContract.View, DriverUserInfContract {

    @Bind(R.id.addphoto_lay1)
    LinearLayout addphoto_lay1;
    @Bind(R.id.addphoto_lay2)
    LinearLayout addphoto_lay2;
    @Bind(R.id.addphoto_lay3)
    LinearLayout addphoto_lay3;
    @Bind(R.id.addphoto_lay4)
    LinearLayout addphoto_lay4;

    @Bind(R.id.addphoto_relay1)
    RelativeLayout addphoto_relay1;
    @Bind(R.id.addphoto_relay2)
    RelativeLayout addphoto_relay2;
    @Bind(R.id.addphoto_relay3)
    RelativeLayout addphoto_relay3;
    @Bind(R.id.addphoto_relay4)
    RelativeLayout addphoto_relay4;

    @Bind(R.id.addphoto_iv1)
    ImageView addphoto_iv1;
    @Bind(R.id.addphoto_iv2)
    ImageView addphoto_iv2;
    @Bind(R.id.addphoto_iv3)
    ImageView addphoto_iv3;
    @Bind(R.id.addphoto_iv4)
    ImageView addphoto_iv4;

    @Bind(R.id.select_iv)
    ImageView select_iv;


    @Inject
    BeMerchantPresenter mBeMerchantPresenter;

    @Inject
    DriverUserInfoPresenter mDriverUserInfoPresenter;

    private Dialog resultDialog;

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public void initView() {
        mBeMerchantPresenter.attachView(this);
        mDriverUserInfoPresenter.attachView(this);
        EventBus.getDefault().register(this);
        resultDialog = DialogUtils.actionResultDialog(getActivity(), "申请成功", "我们会在三个工作日内反馈结果给您");

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bemerchant2;
    }

    @Override
    public void initResume() {

    }

    @Override
    public void initEvents() {
        resultDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void initDta() {

    }

    private int tempAction;
    private boolean isSelect;
    private String businessLicense, qualifications, technicalCertificate, picture;

    @OnClick({R.id.sure_tv, R.id.select_iv, R.id.addphoto_relay1, R.id.addphoto_relay2, R.id.addphoto_relay3, R.id.addphoto_relay4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sure_tv:
                if (businessLicense == null) {
                    ToastUtils.Toast_short("请上传营业执照");
                    return;
                }

                if (qualifications == null) {
                    ToastUtils.Toast_short("请上传维修资质");
                    return;
                }

                if (technicalCertificate == null) {
                    ToastUtils.Toast_short("请上传技师技术证照");
                    return;
                }

                if (picture == null) {
                    ToastUtils.Toast_short("请上传店铺照片");
                    return;
                }

                if (tempBean != null) {
                    Map<String, String> map = new HashMap<>();
                    map.put("city", tempBean.getCity());
                    map.put("name", tempBean.getName());
                    map.put("contact", tempBean.getContact());
                    map.put("contactTel", tempBean.getContactTel());
                    map.put("shopType", tempBean.getShopType());
                    map.put("businessLicense", StringUtil.checkNull(businessLicense));
                    map.put("qualifications", StringUtil.checkNull(qualifications));
                    map.put("technicalCertificate", StringUtil.checkNull(technicalCertificate));
                    map.put("picture", StringUtil.checkNull(picture));
                    mLoadingDialog.show();
                    mBeMerchantPresenter.saveRepairShop(map);
                }

                break;
            case R.id.select_iv:
                if (isSelect) {
                    select_iv.setImageResource(R.drawable.small_icon_radio_default);
                    isSelect = false;
                } else {
                    select_iv.setImageResource(R.drawable.small_icon_radio_selected);
                    isSelect = true;
                }
                break;
            case R.id.addphoto_relay1:
                takePhoto();
//                DialogUtils.initPictureMethodDialog(getActivity()).show();
                tempAction = 1;
                break;
            case R.id.addphoto_relay2:
//                DialogUtils.initPictureMethodDialog(getActivity()).show();
                takePhoto();
                tempAction = 2;
                break;
            case R.id.addphoto_relay3:
//                DialogUtils.initPictureMethodDialog(getActivity()).show();
                takePhoto();
                tempAction = 3;
                break;
            case R.id.addphoto_relay4:
//                DialogUtils.initPictureMethodDialog(getActivity()).show();
                takePhoto();
                tempAction = 4;
                break;
        }
    }

    private void takePhoto() {
        new TakePhotoUtils(getActivity()) {
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(UpBitmapBean busBean) {
        if (busBean.getEventStr().equals(Const.refresh)) {
            setBitmap(busBean.getBitmap());
        }
    }

    private AplyMerchantBean tempBean;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(AplyMerchantBean busBean) {
        if (busBean != null) {
            tempBean = busBean;
        }
    }

    private void setBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            switch (tempAction) {
                case 1:
                    addphoto_lay1.setVisibility(View.GONE);
                    addphoto_iv1.setImageBitmap(bitmap);
                    break;
                case 2:
                    addphoto_lay2.setVisibility(View.GONE);
                    addphoto_iv2.setImageBitmap(bitmap);
                    break;
                case 3:
                    addphoto_lay3.setVisibility(View.GONE);
                    addphoto_iv3.setImageBitmap(bitmap);
                    break;
                case 4:
                    addphoto_lay4.setVisibility(View.GONE);
                    addphoto_iv4.setImageBitmap(bitmap);
                    break;
            }
        }
    }


    //takePhoto

    @Override
    public void takeSuccess(TResult result) {
        ArrayList<TImage> images = result.getImages();
        String compressPath = images.get(images.size() - 1).getCompressPath();
        Log.e("tag", "compressPath=" + compressPath);
        if (compressPath != null) {
//            File mFile = new File(compressPath);
            setBitmap(BitmapFactory.decodeFile(compressPath));
            mLoadingDialog.show();
            mDriverUserInfoPresenter.uploadPic(compressPath);
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showData(EmptyBean dataBean) {
        AFRefreshBean afRefreshBean = new AFRefreshBean();
        afRefreshBean.setEventStr(Const.ok);
        EventBus.getDefault().post(afRefreshBean);
        resultDialog.show();
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void onSuccess(String path) {
        switch (tempAction) {
            case 1:
                businessLicense = path;
                break;
            case 2:
                qualifications = path;
                break;
            case 3:
                technicalCertificate = path;
                break;
            case 4:
                picture = path;
                break;
        }
    }
}
