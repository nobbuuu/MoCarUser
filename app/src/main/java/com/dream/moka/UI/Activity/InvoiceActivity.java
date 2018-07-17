package com.dream.moka.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.dream.moka.UI.Activity.address.AddressActivity;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class InvoiceActivity extends BaseCommonActivity implements SaveInvoiceContract.View {

    @Bind(R.id.common_invoice)
    TextView common_invoice;
    @Bind(R.id.zzinvoice_tv)
    TextView zzinvoice_tv;
    @Bind(R.id.zztip_tv)
    TextView zztip_tv;
    @Bind(R.id.people_edt)
    EditText people_edt;
    @Bind(R.id.common_invoicelay)
    LinearLayout common_invoicelay;
    @Bind(R.id.zzticket_lay)
    LinearLayout zzticket_lay;
    @Bind(R.id.geren_img)
    ImageView geren_img;
    @Bind(R.id.companey_img)
    ImageView companey_img;
    @Bind(R.id.phone_edt)
    EditText mPhoneEdt;
    @Bind(R.id.email_edt)
    EditText mEmailEdt;
    @Bind(R.id.address_iv)
    ImageView mAddressIv;
    @Bind(R.id.address_tv)
    TextView mAddressTv;
    @Bind(R.id.address_lay)
    LinearLayout mAddressLay;
    @Bind(R.id.invoiceinfo_relay)
    RelativeLayout mInvoiceinfoRelay;
    @Bind(R.id.company_tv)
    TextView mCompanyTv;
    @Bind(R.id.taxpayer_tv)
    TextView mTaxpayerTv;
    @Bind(R.id.regiPhone_tv)
    TextView mRegiPhoneTv;
    @Bind(R.id.regiAddress_tv)
    TextView mRegiAddressTv;
    @Bind(R.id.bankName_tv)
    TextView mBankNameTv;
    @Bind(R.id.bankNum_tv)
    TextView mBankNumTv;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.content_tv)
    TextView content_tv;
    @Bind(R.id.nexttip_tv)
    TextView nexttip_tv;

    @Inject
    InvoicePresenter mInvoicePresenter;
    @Bind(R.id.taxpayer_tv_nomal)
    EditText mTaxpayerTvNomal;
    @Bind(R.id.name_tv)
    TextView mNameTv;
    @Bind(R.id.phone_tv)
    TextView mPhoneTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invoice;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mInvoicePresenter.attachView(this);
        InvoiceBean invoiceBean = (InvoiceBean) getIntent().getSerializableExtra("data");
        if (invoiceBean != null) {
            mNameTv.setText(invoiceBean.getName());
            mPhoneTv.setText(invoiceBean.getTelphone());
            mAddressTv.setText(invoiceBean.getRecipientsAddr());
            String Type = invoiceBean.getInvoiceType();
            if (Type != null) {
                if (Type.equals("1")) {//电子发票
                    common_invoice.setTextColor(ResourcesUtils.getColor(R.color.fengexian4));
                    zzinvoice_tv.setTextColor(ResourcesUtils.getColor(R.color.color666));
                    common_invoice.setBackgroundResource(R.drawable.shape_stroke_chonzhi);
                    zzinvoice_tv.setBackgroundResource(R.drawable.shape_stroke_a0);
                    zzticket_lay.setVisibility(View.GONE);
                    common_invoicelay.setVisibility(View.VISIBLE);
                    zztip_tv.setVisibility(View.GONE);
                    invoiceType = "1";

                    String name = invoiceBean.getName();
                    String identificationNo = invoiceBean.getIdentificationNo();
                    String recipientsTel = invoiceBean.getRecipientsTel();
                    String recipientsEmail = invoiceBean.getRecipientsEmail();
                    people_edt.setText(name);
                    mPhoneEdt.setText(recipientsTel);
                    mEmailEdt.setText(recipientsEmail);
                    String type = invoiceBean.getNameType();
                    if (type.equals("0")) {
                        mTaxpayerTvNomal.setVisibility(View.GONE);
                    } else {
                        mTaxpayerTvNomal.setVisibility(View.VISIBLE);
                    }
                    mTaxpayerTvNomal.setText(identificationNo);//电子发票中的识别码

                } else {//增值税发票
                    zzinvoice_tv.setTextColor(ResourcesUtils.getColor(R.color.fengexian4));
                    common_invoice.setTextColor(ResourcesUtils.getColor(R.color.color666));
                    zzinvoice_tv.setBackgroundResource(R.drawable.shape_stroke_chonzhi);
                    common_invoice.setBackgroundResource(R.drawable.shape_stroke_a0);
                    zzticket_lay.setVisibility(View.VISIBLE);
                    zztip_tv.setVisibility(View.VISIBLE);
                    common_invoicelay.setVisibility(View.GONE);
                    invoiceType = "0";

                }
            }

        }
    }

    @Override
    public String initTitleText() {
        return "开具发票";
    }

    @Override
    public String initRightTv() {
        return "发票问题";
    }

    @Override
    public boolean isRighttv() {
        return false;
    }

    @Override
    public void loadResum() {
        mInvoicePresenter.getUserCompInvoice(CommonAction.getToken());
    }

    @Override
    public void initDatas() {
    }

    @Override
    public void eventListener() {

    }

    private String invoiceType = "1";//1:普通发票；0：增值税发票
    private String nameType = "0";//0:个人，1：公司；

    @OnClick({R.id.back_relay, R.id.common_invoice, R.id.zzinvoice_tv, R.id.right_tv, R.id.geren_img, R.id.companey_img, R.id.address_lay, R.id.invoiceinfo_relay, R.id.sure_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.common_invoice:
                common_invoice.setTextColor(ResourcesUtils.getColor(R.color.fengexian4));
                zzinvoice_tv.setTextColor(ResourcesUtils.getColor(R.color.color666));
                common_invoice.setBackgroundResource(R.drawable.shape_stroke_chonzhi);
                zzinvoice_tv.setBackgroundResource(R.drawable.shape_stroke_a0);
                zzticket_lay.setVisibility(View.GONE);
                common_invoicelay.setVisibility(View.VISIBLE);
                zztip_tv.setVisibility(View.GONE);
                invoiceType = "1";
                break;
            case R.id.zzinvoice_tv:
                zzinvoice_tv.setTextColor(ResourcesUtils.getColor(R.color.fengexian4));
                common_invoice.setTextColor(ResourcesUtils.getColor(R.color.color666));
                zzinvoice_tv.setBackgroundResource(R.drawable.shape_stroke_chonzhi);
                common_invoice.setBackgroundResource(R.drawable.shape_stroke_a0);
                zzticket_lay.setVisibility(View.VISIBLE);
                zztip_tv.setVisibility(View.VISIBLE);
                common_invoicelay.setVisibility(View.GONE);
                invoiceType = "0";
                break;
            case R.id.right_tv:
                IntentUtils.toActivityWithTag(BeDriverWebActivity.class, mActivity, "发票问题");
                break;
            case R.id.geren_img:
                people_edt.setHint("请填写个人姓名");
                geren_img.setImageResource(R.mipmap.icon_radio_selected);
                companey_img.setImageResource(R.mipmap.icon_radio_default);
                nameType = "0";
                mTaxpayerTvNomal.setVisibility(View.GONE);
                break;
            case R.id.companey_img:
                people_edt.setHint("请填写公司名称");
                companey_img.setImageResource(R.mipmap.icon_radio_selected);
                geren_img.setImageResource(R.mipmap.icon_radio_default);
                nameType = "1";
                mTaxpayerTvNomal.setVisibility(View.VISIBLE);
                break;
            case R.id.address_lay:
                IntentUtils.toActivity_result(AddressActivity.class, mActivity, 180);
                break;
            case R.id.invoiceinfo_relay:
                IntentUtils.toActivity_result(AddTicketsZZActivity.class, mActivity, 160);
                break;
            case R.id.sure_tv:
                Map<String, String> map = new HashMap<>();
                map.put("invoiceType", invoiceType);
                if (invoiceType.equals("1")) {//电子发票
                    String name = people_edt.getText().toString();
                    String phone = mPhoneEdt.getText().toString();
                    String email = mEmailEdt.getText().toString();
                    String cotent = content_tv.getText().toString();
                    String trim = mTaxpayerTvNomal.getText().toString().trim();//电子发票中的识别码
                    if (email.isEmpty()) {
                        ToastUtils.Toast_short("请输入邮箱地址");
                    } else if (name.isEmpty()) {
                        ToastUtils.Toast_short("请输入发票抬头信息");
                    } else {
                        map.put("nameType", nameType);
                        map.put("name", name);
                        map.put("recipientsTel", phone);
                        map.put("recipientsEmail", email);
                        map.put("content", cotent);
                        if (nameType.equals("1")) {
                            map.put("identificationNo", trim);
                        }
                        mLoadingDialog.show();
                        mInvoicePresenter.saveOrderInvoice(CommonAction.getToken(), map);
                    }
                } else {//增值税发票
                    //收件人地址
                    String recipientsAddr = mAddressTv.getText().toString();
                    //收件人名称
                    String recipientsName = mNameTv.getText().toString();
                    //收件人电话
                    String mPhone = mPhoneTv.getText().toString();

                    if (recipientsAddr.isEmpty()) {
                        ToastUtils.Toast_long("请选择收件人地址");
                        break;
                    }
                    if (recipientsName.isEmpty()) {
                        ToastUtils.Toast_long("请选择收件人姓名");
                        break;
                    }
                    if (mPhone.isEmpty()) {
                        ToastUtils.Toast_long("请选择收件人电话");
                        break;
                    }

                    if (null == tempBean || tempBean.getId() == null) {
                        ToastUtils.Toast_long("请先录入发票详情");
                        break;
                    }
                    map.put("recipientsName", tempBean.getName());
                    map.put("recipientsAddr", recipientsAddr);
                    map.put("recipientsTel", mPhone);

                    //单位名称
                    map.put("name", tempBean.getName());
                    map.put("identificationNo", tempBean.getIdentificationNo());
                    map.put("telphone", tempBean.getTelphone());
                    map.put("address", tempBean.getAddress());
                    map.put("bankName", tempBean.getBankName());
                    map.put("account", tempBean.getAccount());
                    mLoadingDialog.show();
                    mInvoicePresenter.saveOrderInvoice(CommonAction.getToken(), map);
                }
                break;
        }
    }

    @Override
    public void showData(InvoiceBean dataBean) {
        String id = dataBean.getId();
        Intent intent = getIntent();
        if (id != null) {
            intent.putExtra("invoiceId", id);
            intent.putExtra("invoiceType", invoiceType);
            intent.putExtra("invoiceBean", dataBean);
            setResult(104, intent);
            finish();
        }
    }

    @Override
    public void showUpdateInvoice(EmptyBean dataBean) {

    }

    private InvoiceBaseBean tempBean;

    @Override
    public void showBaseData(InvoiceBaseBean dataBean) {
        tempBean = dataBean;
        mCompanyTv.setText(StringUtil.checkNull(dataBean.getName()));
        mTaxpayerTv.setText(StringUtil.checkNull(dataBean.getIdentificationNo()));
        mRegiPhoneTv.setText(StringUtil.checkNull(dataBean.getTelphone()));
        mRegiAddressTv.setText(StringUtil.checkNull(dataBean.getAddress()));
        mBankNameTv.setText(StringUtil.checkNull(dataBean.getBankName()));
        mBankNumTv.setText(StringUtil.checkNull(dataBean.getAccount()));
        nexttip_tv.setVisibility(View.GONE);

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
