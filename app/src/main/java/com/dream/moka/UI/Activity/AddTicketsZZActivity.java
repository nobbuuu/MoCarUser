package com.dream.moka.UI.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.InvoiceBaseBean;
import com.dream.moka.Bean.InvoiceBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.SaveInvoiceContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Presenter.InvoicePresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.drivercenter.BanksRvActivity;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class AddTicketsZZActivity extends BaseCommonActivity implements SaveInvoiceContract.View {

    @Inject
    InvoicePresenter mInvoicePresenter;
    @Bind(R.id.unitName_edt)
    EditText mUnitNameEdt;
    @Bind(R.id.address_relay)
    LinearLayout mAddressRelay;
    @Bind(R.id.peopleCode_edt)
    EditText mPeopleCodeEdt;
    @Bind(R.id.regiAddress_edt)
    EditText mRegiAddressEdt;
    @Bind(R.id.regiPhone_edt)
    EditText mRegiPhoneEdt;
    @Bind(R.id.bankName_tv)
    TextView bankName_tv;
    @Bind(R.id.bankNum_edt)
    EditText mBankNumEdt;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_addticket;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mInvoicePresenter.attachView(this);
    }

    @Override
    public String initTitleText() {
        return "增票资质";
    }

    @Override
    public String initRightTv() {
        return "保存";
    }

    @Override
    public boolean isRighttv() {
        return true;
    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {
//        getCurrentFocus().clearFocus();
        mInvoicePresenter.getUserCompInvoice(CommonAction.getToken());
    }

    @Override
    public void eventListener() {

    }

    private String invoiceId = "";

    @OnClick({R.id.back_relay, R.id.right_tv,R.id.bankName_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.bankName_tv:
                BanksRvActivity.openAct(mActivity);
                break;
            case R.id.right_tv:

                String unitName = mUnitNameEdt.getText().toString();
                String unitNameHint = mUnitNameEdt.getHint().toString();
                String bankName = bankName_tv.getText().toString();
                String bankNameHint = bankName_tv.getHint().toString();
                String bankNo = mBankNumEdt.getText().toString();
                String bankNoHint = mBankNumEdt.getHint().toString();
                String peopleCode = mPeopleCodeEdt.getText().toString();
                String peopleCodeHint = mPeopleCodeEdt.getHint().toString();
                String regiAddress = mRegiAddressEdt.getText().toString();
                String regiAddressHint = mRegiAddressEdt.getHint().toString();
                String regiPhone = mRegiPhoneEdt.getText().toString();
                String regiPhoneHint = mRegiPhoneEdt.getHint().toString();

                if (unitName.isEmpty()) {
                    ToastUtils.Toast_long(unitNameHint);
                    break;
                }
                if (peopleCode.isEmpty()) {
                    ToastUtils.Toast_long(peopleCodeHint);
                    break;
                }
                if (regiAddress.isEmpty()) {
                    ToastUtils.Toast_long(regiAddressHint);
                    break;
                }

                if (regiPhone.isEmpty()) {
                    ToastUtils.Toast_long(regiPhoneHint);
                    break;
                }
                if (bankName.isEmpty()) {
                    ToastUtils.Toast_long(bankNameHint);
                    break;
                }

                if (bankNo.isEmpty()) {
                    ToastUtils.Toast_long(bankNoHint);
                    break;
                }

                Map<String, String> map = MapUtils.getSingleMap();
                map.put("id", invoiceId);
                map.put("name", unitName);
                map.put("identificationNo", peopleCode);
                map.put("address", regiAddress);
                map.put("telphone", regiPhone);
                map.put("bankName", bankName);
                map.put("account", bankNo);
                mLoadingDialog.show();
                mInvoicePresenter.updateInvoice(CommonAction.getToken(), map);

                break;
        }
    }

    @Override
    public void showData(InvoiceBean dataBean) {

    }

    @Override
    public void showUpdateInvoice(EmptyBean dataBean) {
        ToastUtils.Toast_long("保存成功");
        getCurrentFocus().clearFocus();
    }

    @Override
    public void showBaseData(InvoiceBaseBean dataBean) {

        mUnitNameEdt.setText(StringUtil.checkNull(dataBean.getName()));
        mPeopleCodeEdt.setText(StringUtil.checkNull(dataBean.getIdentificationNo()));
        mRegiAddressEdt.setText(StringUtil.checkNull(dataBean.getAddress()));
        mRegiPhoneEdt.setText(StringUtil.checkNull(dataBean.getTelphone()));
        bankName_tv.setText(StringUtil.checkNull(dataBean.getBankName()));
        mBankNumEdt.setText(StringUtil.checkNull(dataBean.getAccount()));
        invoiceId = StringUtil.checkNull(dataBean.getId());
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0x20&&resultCode==0x21&&data!=null){
            String name = data.getStringExtra("name");
            bankName_tv.setText(StringUtil.checkNull(name));
        }
    }
}
