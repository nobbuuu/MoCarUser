package com.dream.moka.UI.Activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.UserInfoContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.UserInfoPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.ToastUtils;

import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class ChangeUserInfoActivity extends BaseCommonActivity implements UserInfoContract {

    @Inject
    UserInfoPresenter mUserInfoPresenter;
    @Bind(R.id.info_edt)
    EditText mInfoEdt;
    @Bind(R.id.save_tv)
    TextView mSaveTv;

    private String edtTag;
    @Override
    public int getLayoutId() {
        return R.layout.activity_changeinfo;
    }

    @Override
    public void initView() {
        mUserInfoPresenter.attachView(this);
    }

    @Override
    public String initTitleText() {
        String tag = getIntent().getStringExtra(Const.intentTag);
        if (tag!=null){
            edtTag = tag;
            if (tag.equals("QQ")){
                mInfoEdt.setHint("请输入QQ号");
                return "修改QQ号";
            }else {
                mInfoEdt.setHint("请输入昵称");
                return "修改昵称";
            }
        }else {
            return "";
        }
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

    }

    @Override
    public void eventListener() {

    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @OnClick({R.id.back_relay, R.id.save_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.save_tv:
                String edtInfo = mInfoEdt.getText().toString();
                if (!edtInfo.isEmpty()){
                    Map<String, String> singleMap = MapUtils.getSingleMap();
                    singleMap.put("token", CommonAction.getToken());
                    if (edtTag.equals("QQ")){
                        singleMap.put("qq",edtInfo);
                    }else {
                        singleMap.put("name",edtInfo);
                    }
                    mLoadingDialog.show();
                    mUserInfoPresenter.saveInfo(singleMap);
                }else {
                    ToastUtils.Toast_short("请输入要修改的信息");
                }
                break;
        }
    }

    @Override
    public void onPicSuccess(String filePath) {

    }

    @Override
    public void onSuccess() {
        ToastUtils.Toast_short("修改成功");
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

}
