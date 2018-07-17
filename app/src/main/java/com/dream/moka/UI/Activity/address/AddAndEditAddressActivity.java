package com.dream.moka.UI.Activity.address;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Bean.AddressListResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.AddAndEditAddressContract;
import com.dream.moka.Presenter.AddAndEditAddressPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.ToastUtils;
import com.dream.moka.Bean.EventBusBean.RefreshAddress;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class AddAndEditAddressActivity extends BaseActivity implements AddAndEditAddressContract {

    @Inject
    AddAndEditAddressPresenter mAddAndEditAddressPresenter;
    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.area)
    TextView mArea;
    @Bind(R.id.address)
    EditText mAddress;
    @Bind(R.id.btn)
    TextView mBtn;
    private String mTypeFrom;
    private AddressListResultBean mData;
    private String mLat = "";
    private String mLong = "";
    private String mP = "";
    private String mC = "";
    private String mA = "";
    private String mNameAddress;
    private String mStatus="0";

    public static void openAct(Context context, String type, AddressListResultBean addressListResultBean,String status) {
        Intent intent = new Intent(context, AddAndEditAddressActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("data", addressListResultBean);
        intent.putExtra("status", status);
        context.startActivity(intent);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_and_edit;
    }

    @Override
    public void initView() {
        mAddAndEditAddressPresenter.attachView(this);
        mTypeFrom = getIntent().getStringExtra("type");
        if (mTypeFrom.equals("edit")) {
            mData = (AddressListResultBean) getIntent().getSerializableExtra("data");
            mStatus = getIntent().getStringExtra("status");
            mTitle.setText("编辑地址");
            mName.setText(mData.getRecipientsName());
            mName.setSelection(mData.getRecipientsName().length());
            mPhone.setText(mData.getTelephone());
            mP = mData.getProvince();
            mC = mData.getArea();
            mA = mData.getCity();
            mLat=mData.getLatitude();
            mLong=mData.getLongitude();
            mArea.setText(mP + mC + mA);
            mAddress.setText(mData.getDetailAddr());

        }

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

    @OnClick({R.id.back_relay, R.id.area, R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.area:
                ChoosePointForMapActivity.openAct(mActivity,0x10);
                break;
            case R.id.btn:
                if (mTypeFrom.equals("edit")) {//编辑
                    if (checkKeyEmpty()) {
                        mLoadingDialog.show();
                        mAddAndEditAddressPresenter.editAddressData(mData.getId(), mName.getText().toString(), mPhone.getText().toString()
                                , mP, mC, mA, mAddress.getText().toString(), mLong, mLat,mStatus);
                    }
                } else {//新增
                    if (checkKeyEmpty()) {
                        mLoadingDialog.show();
                        mAddAndEditAddressPresenter.addAddressData(mName.getText().toString(), mPhone.getText().toString()
                                , mP, mC, mA, mAddress.getText().toString(), mLong, mLat);
                    }
                }

                break;
        }
    }

    private boolean checkKeyEmpty() {
        if (TextUtils.isEmpty(mName.getText())) {
            ToastUtils.Toast_short("请输入姓名");
            return false;
        }
        if (TextUtils.isEmpty(mPhone.getText())) {
            ToastUtils.Toast_short("请输入手机号");
            return false;
        }
        if (TextUtils.isEmpty(mArea.getText())) {
            ToastUtils.Toast_short("请选择所在地区");
            return false;
        }
        if (TextUtils.isEmpty(mAddress.getText())) {
            ToastUtils.Toast_short("请输入详细地址");
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0x10&&resultCode == 0x20) {
            mLat = data.getStringExtra("lat");
            mLong = data.getStringExtra("Long");
            mP = data.getStringExtra("p");
            mC = data.getStringExtra("c");
            mA = data.getStringExtra("a");
            mNameAddress = data.getStringExtra("detail");
            mArea.setText(mP + mC + mA);
            mAddress.setText(mNameAddress);
            mAddress.setSelection(mNameAddress.length());
        }
    }

    @Override
    public void showError() {
        if (mLoadingDialog!=null){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void complete() {
        if (mLoadingDialog!=null){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void onSuccess() {
        EventBus.getDefault().post(new RefreshAddress());
        finish();
    }
}
