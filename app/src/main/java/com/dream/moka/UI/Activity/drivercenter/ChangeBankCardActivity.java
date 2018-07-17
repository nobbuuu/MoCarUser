package com.dream.moka.UI.Activity.drivercenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.BankCardBean;
import com.dream.moka.Bean.BankInfoBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.ChangeBankContract;
import com.dream.moka.CumstomView.PayPsdInputView;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.ChangeBankPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.set.SetIDcardActivity;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.PopWindowUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class ChangeBankCardActivity extends BaseCommonActivity implements ChangeBankContract {

    @Bind(R.id.bankName)
    TextView mBankName;
    @Bind(R.id.bankCardType)
    TextView mBankCardType;
    @Bind(R.id.bankCardNo)
    TextView mBankCardNo;
    @Bind(R.id.changecard_lay)
    LinearLayout mChangecardLay;
    @Inject
    ChangeBankPresenter mChangeBankPresenter;
    @Bind(R.id.cardLayout)
    LinearLayout mCardLayout;
    @Bind(R.id.change_txt)
    TextView mChangeTxt;
    private Dialog mLoadingDialog;
    private boolean mHasBankCard = false;
    private BankInfoBean mBankInfoBean = new BankInfoBean();
    ;

    /**
     * @param context
     * @param isSaveBack 表示是从添加或者修改中back
     */
    public static void openAct(Context context, boolean isSaveBack) {
        Intent intent = new Intent(context, ChangeBankCardActivity.class);
        if (isSaveBack) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_driver_bankcard;
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
        mChangeBankPresenter.attachView(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        mLoadingDialog.show();
        mChangeBankPresenter.getBankData();
    }

    @Override
    public String initTitleText() {
        return "我的银行卡";
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

    @OnClick({R.id.changecard_lay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changecard_lay:
                mLoadingDialog.show();
                mChangeBankPresenter.checkHasPwd();
                break;
            case R.id.back_relay:
                finish();
                break;
        }
    }

    public void showPayPwdDialog(final Activity activity) {
        final Dialog dialog_tip = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View login = LayoutInflater.from(activity).inflate(R.layout.dialog_paypassword_input, null);
        PayPsdInputView payPsdInputView = (PayPsdInputView) login.findViewById(R.id.paypwd_view);
        TextView forgotpwd_tv = (TextView) login.findViewById(R.id.forgotpwd_tv);
        ImageView close_iv = (ImageView) login.findViewById(R.id.close_iv);
        dialog_tip.requestWindowFeature(Window.FEATURE_NO_TITLE);//（这句设置没有title）
        dialog_tip.setContentView(login);
        dialog_tip.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog_tip.setCancelable(true);
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        WindowManager.LayoutParams p = dialog_tip.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.75); //宽度设置为屏幕的0.75
//        p.height = (int) (displayHeight * 0.45); //高度设置为屏幕的0.45
        dialog_tip.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog不消失
        dialog_tip.getWindow().setAttributes(p);  //设置生效
        dialog_tip.show();
        PopWindowUtil.backgroundAlpaha(activity, 0.5f);
        payPsdInputView.setInputCompleteListener(new PayPsdInputView.inputCompleteListener() {
            @Override
            public void onInputComplete(String pwd) {
                mLoadingDialog.show();
                mChangeBankPresenter.CheckPwd(pwd);
            }
        });
        close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_tip.dismiss();
            }
        });

        dialog_tip.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                PopWindowUtil.backgroundAlpaha(activity, 1f);
            }
        });
        forgotpwd_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_tip.dismiss();
                SetIDcardActivity.openAct(mContext,"","forget");

            }
        });

    }

    @Override
    public void showError() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * 获取银行卡信息
     *
     * @param results
     */
    @Override
    public void getBankDataSuccess(BankCardBean results) {
        if (results != null && results.getId() != null && !results.getId().equals("")) {
            mHasBankCard = true;
            mChangeTxt.setText("更换银行卡");
            mCardLayout.setVisibility(View.VISIBLE);
            String bankName = results.getBankName();
            String accountNo = results.getAccountNo();
            if (accountNo != null) {
                String substring = accountNo.substring(accountNo.length() - 4, accountNo.length());
                mBankCardNo.setText("**** **** **** " + substring);
            }
            mBankName.setText(bankName == null ? "" : bankName);
            mBankInfoBean.setBankId(results.getBankId() != null ? results.getBankId() : "");
            mBankInfoBean.setBankName(results.getBankName() != null ? results.getBankName() : "");
            mBankInfoBean.setCardid(results.getId() != null ? results.getId() : "");
            mBankInfoBean.setCardNumber(results.getAccountNo() != null ? results.getAccountNo() : "");
            mBankInfoBean.setUsername(results.getUsername() != null ? results.getUsername() : "");


        } else {
            mHasBankCard = false;
            mChangeTxt.setText("新增银行卡");
            mCardLayout.setVisibility(View.GONE);
            mLoadingDialog.show();
            mChangeBankPresenter.checkHasPwd();
        }
    }

    /**
     * 是否设置过交易密码的检测结果
     *
     * @param has
     */
    @Override
    public void hasPayCode(boolean has) {
        if (has) {
            showPayPwdDialog(mActivity);
        } else {
            SetIDcardActivity.openAct(mContext, "1","");
        }
    }

    @Override
    public void checkPwdSuccess() {
        if (mHasBankCard) {
            AddBankCardActivity.openAct(mContext, "edit", mBankInfoBean);
        } else {
            AddBankCardActivity.openAct(mContext, "add", new BankInfoBean());
        }

    }
}
