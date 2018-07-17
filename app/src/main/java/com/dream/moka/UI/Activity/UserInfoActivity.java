package com.dream.moka.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.moka.Base.BaseCommonTakePhotoActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.MineContract;
import com.dream.moka.Contract.UserInfoContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.MinePresenter;
import com.dream.moka.Presenter.UserInfoPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.CustomHelper;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.GlideCircleTransform;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.SexPopwindowUtils;
import com.dream.moka.Utils.TakePhotoUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class UserInfoActivity extends BaseCommonTakePhotoActivity implements UserInfoContract,MineContract,TakePhoto.TakeResultListener, InvokeListener {
    @Bind(R.id.sex_relay)
    RelativeLayout sex_relay;
    @Bind(R.id.sex_tv)
    TextView sex_tv;
    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.head_icon)
    ImageView mHeadIcon;
    @Bind(R.id.nick_name)
    TextView mNickName;
    @Bind(R.id.nick_name_relay)
    RelativeLayout mNickNameRelay;
    @Bind(R.id.phone_tv)
    TextView mPhoneTv;
    @Bind(R.id.myphone_relay)
    RelativeLayout mMyphoneRelay;
    @Bind(R.id.qqnum_tv)
    TextView mQqnumTv;
    @Bind(R.id.qq_relay)
    RelativeLayout mQqRelay;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    @Inject
    UserInfoPresenter mUserInfoPresenter;
    @Inject
    MinePresenter mMinePresenter;

    public static void openAct(Context context) {
        context.startActivity(new Intent(context, UserInfoActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_userinfo;
    }

    @Override
    public void initView() {
        mUserInfoPresenter.attachView(this);
        mMinePresenter.attachView(this);

    }

    @Override
    public String initTitleText() {
        return "个人资料";
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
        mMinePresenter.getUserData();
    }

    @Override
    public void eventListener() {

    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @OnClick({R.id.back_relay, R.id.head_icon, R.id.nick_name_relay, R.id.sex_relay, R.id.myphone_relay, R.id.qq_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.head_icon:
//                DialogUtils.initPictureMethodDialog(mActivity).show();
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
                break;
            case R.id.nick_name_relay:
                IntentUtils.toActivityWithTag(ChangeUserInfoActivity.class,mActivity,"NickName",58);
                break;
            case R.id.sex_relay:
                new SexPopwindowUtils(sex_relay, mActivity) {
                    @Override
                    public void selectNanListener() {
                        sex_tv.setText("男");
                        Map<String,String> map = new HashMap<>();
                        map.put("token",CommonAction.getToken());
                        map.put("sex","1");
                        mUserInfoPresenter.saveInfo(map);
                    }

                    @Override
                    public void selectNvListener() {
                        sex_tv.setText("女");
                        Map<String,String> map = new HashMap<>();
                        map.put("token",CommonAction.getToken());
                        map.put("sex","0");
                        mLoadingDialog.show();
                        mUserInfoPresenter.saveInfo(map);
                    }
                };
                break;
            case R.id.myphone_relay:
//                Intent intent = new Intent(mContext,ChangephoneActivity.class);
//                intent.putExtra(Const.intentTag,"phone");
//                startActivity(intent);
                break;
            case R.id.qq_relay:
                IntentUtils.toActivityWithTag(ChangeUserInfoActivity.class,mActivity,"QQ",56);

                break;
        }
    }


    @Override
    public void takeSuccess(TResult result) {
        ArrayList<TImage> images = result.getImages();
        String compressPath = images.get(images.size() - 1).getCompressPath();
        mLoadingDialog.show();
        mUserInfoPresenter.uploadPic(compressPath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 56){
            mLoadingDialog.show();
            mMinePresenter.getUserData();
        }
        if (requestCode == 58){
            mLoadingDialog.show();
            mMinePresenter.getUserData();
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

    /**
     * 上传头像成功
     */
    @Override
    public void onPicSuccess(String filePath) {
//        GlidUtils.LoadFromFileLocation(mContext,filePath,mHeadIcon,R.mipmap.img_defaultavatar);
        Log.e("tag","headUrl="+Const.BannerUrl+filePath);
        Glide.with(mActivity).load(Const.BannerUrl+filePath).placeholder(R.mipmap.img_defaultavatar).transform(new GlideCircleTransform(mContext)).into(mHeadIcon);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void getDriverInfoSuccess(String status) {

    }

    @Override
    public void getUserDataSuccess(String userType) {
        String userHeadUrl = CommonAction.getUserHeadUrl();
        String userName = CommonAction.getUserName();
        String userSex = CommonAction.getUserSex();
        String userPhone = CommonAction.getUserPhone();
        String userQQ = CommonAction.getUserQQ();

        if (userHeadUrl.contains("http")){
            GlidUtils.LoadCircleImg(mContext, userHeadUrl == null ? "" : userHeadUrl, mHeadIcon, R.mipmap.img_defaultavatar);
        }else {
            GlidUtils.LoadCircleImg(mContext, userHeadUrl == null ? "" : Const.BannerUrl+userHeadUrl, mHeadIcon, R.mipmap.img_defaultavatar);
        }
        mNickName.setText(userName == null ? "" : userName);
        sex_tv.setText(userSex.equals("1") ? "男" : "女");
        mPhoneTv.setText(userPhone == null ? "" : userPhone);
        mQqnumTv.setText(userQQ == null ? "" : userQQ);
    }
}
