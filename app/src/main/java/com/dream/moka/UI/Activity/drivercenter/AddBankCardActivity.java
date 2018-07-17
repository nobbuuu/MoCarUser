package com.dream.moka.UI.Activity.drivercenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.BankInfoBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.AddBankCardContract;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.AddBankCardPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class AddBankCardActivity extends BaseCommonActivity implements AddBankCardContract {
    @Inject
    AddBankCardPresenter mAddBankCardPresenter;
    @Bind(R.id.username_edt)
    EditText mUsernameEdt;
    @Bind(R.id.bank_choserelay)
    RelativeLayout mBankChoserelay;
    @Bind(R.id.cardNum)
    EditText mCardNum;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.bankName)
    TextView mBankName;
    private String bankId = "";
    private Dialog mLoadingDialog;
    private String mType;
    private String mCardId="";
    private BankInfoBean mData;

    public static void openAct(Context context, String type, Serializable data) {
        Intent intent = new Intent(context, AddBankCardActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_addbankcard;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {
        DaggerBaseComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
//                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
        mAddBankCardPresenter.attachView(this);
        if (mType.equals("edit")) {
            String username = mData.getUsername();
            String bankName = mData.getBankName();
            String cardNumber = mData.getCardNumber();
            mUsernameEdt.setText(username==null?"":username);
            mBankName.setText(bankName==null?"":bankName);
            mCardNum.setText(cardNumber==null?"":cardNumber);
        }
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
    }

    @Override
    public String initTitleText() {
        mType = getIntent().getStringExtra("type");
        mData = (BankInfoBean) getIntent().getSerializableExtra("data");
        if (mType.equals("add")) {
            return "新增银行卡";
        } else {
            mCardId = mData.getCardid();
            return "编辑银行卡";
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

    @OnClick({R.id.bank_choserelay, R.id.sure_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bank_choserelay:
                BanksRvActivity.openAct(mActivity);
                break;
            case R.id.sure_tv:
                if (checkEmpty()){
                    mLoadingDialog.show();
                    mAddBankCardPresenter.saveBankData(mCardId,mUsernameEdt.getText().toString().trim(),bankId,mCardNum.getText().toString().trim());
                }
                break;
            case R.id.back_relay:
                finish();
                break;
        }
    }

    private boolean checkEmpty() {
        String name = mUsernameEdt.getText().toString().trim();
        String bank = mBankName.getText().toString().trim();
        String cardNum = mCardNum.getText().toString().trim();
        if (!StringUtil.NoNullOrEmpty(name)){
            ToastUtils.Toast_short("户名不能为空");
            return false;
        }
        if (!StringUtil.NoNullOrEmpty(bank)){
            ToastUtils.Toast_short("开户行不能为空");
            return false;
        }
        if (!StringUtil.NoNullOrEmpty(cardNum)){
            ToastUtils.Toast_short("银行卡号不能为空");
            return false;
        }
        if (cardNum.length()<15){
            ToastUtils.Toast_short("银行卡号有误");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x20 && resultCode == 0x21) {
            if (data != null) {
                String name = data.getStringExtra("name");
                String id = data.getStringExtra("id");
                if (id != null) {
                    bankId = id;
                }
                mBankName.setText(name == null ? "" : name);
            }
        }

    }

    @Override
    public void showError() {
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }

    @Override
    public void onSuccessData() {
        ChangeBankCardActivity.openAct(mContext, true);
    }
}
