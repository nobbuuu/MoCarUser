package com.dream.moka.UI.ChildFragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Bean.EventBusBean.AFRefreshBean;
import com.dream.moka.Bean.EventBusBean.BusCityBean;
import com.dream.moka.Bean.Merchant.AplyMerchantBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.CityChooseActivity;
import com.dream.moka.Utils.CheckForAllUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class BeMerchant1Fragment extends BaseFragmentV4 {

    @Bind(R.id.citychoose_relay)
    RelativeLayout citychoose_relay;
    @Bind(R.id.city_tv)
    TextView city_tv;
    @Bind(R.id.forths_iv)
    ImageView forths_iv;
    @Bind(R.id.second_iv)
    ImageView second_iv;
    @Bind(R.id.left_tv)
    TextView mLeftTv;
    @Bind(R.id.merchant_edt)
    EditText mMerchantEdt;
    @Bind(R.id.contacter_edt)
    EditText mContacterEdt;
    @Bind(R.id.contact_edt)
    EditText mContactEdt;
    @Bind(R.id.address_edt)
    EditText mAddressEdt;
    @Bind(R.id.sure_tv)
    TextView mSureTv;

    @Override
    public void initDaggerView(BaseComponent build) {
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bemerchant1;
    }

    @Override
    public void initResume() {

    }

    @Override
    public void initEvents() {

    }

    @Override
    public void initDta() {

    }

    private String shopType = "1";
    @OnClick({R.id.sure_tv, R.id.citychoose_relay, R.id.second_iv, R.id.forths_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sure_tv:
                String address = mAddressEdt.getText().toString();
                String contact = mContactEdt.getText().toString();
                String contacter = mContacterEdt.getText().toString();
                String merchantName = mMerchantEdt.getText().toString();
                String cityName = city_tv.getText().toString();
                if (checkEmpty()){
                    AplyMerchantBean afRefreshBean = new AplyMerchantBean();
                    afRefreshBean.setAddress(address);
                    afRefreshBean.setCity(cityName);
                    afRefreshBean.setContact(contacter);
                    afRefreshBean.setName(merchantName);
                    afRefreshBean.setContactTel(contact);
                    afRefreshBean.setShopType(shopType);
                    EventBus.getDefault().post(afRefreshBean);
                    AFRefreshBean bean = new AFRefreshBean();
                    bean.setEventStr(Const.refresh);
                    EventBus.getDefault().post(bean);
                }
                break;
            case R.id.citychoose_relay:
                Intent intent = new Intent(getContext(), CityChooseActivity.class);
                startActivityForResult(intent, 110);
                break;
            case R.id.forths_iv:
                shopType = "1";
                forths_iv.setImageResource(R.mipmap.information_icon_radio_selected);
                second_iv.setImageResource(R.mipmap.information_icon_radio_default);
                break;
            case R.id.second_iv:
                shopType = "0";
                second_iv.setImageResource(R.mipmap.information_icon_radio_selected);
                forths_iv.setImageResource(R.mipmap.information_icon_radio_default);
                break;
        }
    }

    private boolean checkEmpty() {
        if (TextUtils.isEmpty(city_tv.getText().toString())) {
            ToastUtils.Toast_short("请选择城市");
            return false;
        }
        if (TextUtils.isEmpty(mMerchantEdt.getText().toString())) {
            ToastUtils.Toast_short("请输入商家名");
            return false;
        }
        if (TextUtils.isEmpty(mContacterEdt.getText().toString())) {
            ToastUtils.Toast_short("请输入联系人姓名");
            return false;
        }
        if (TextUtils.isEmpty(mContactEdt.getText().toString())) {
            ToastUtils.Toast_short("请输入联系电话");
            return false;
        }else if (!CheckForAllUtils.isMobileNO(mContactEdt.getText().toString().trim())){
            ToastUtils.Toast_short("手机号不正确");
            return false;
        }
        if (TextUtils.isEmpty(mAddressEdt.getText().toString())) {
            ToastUtils.Toast_short("请输入地址");
            return false;
        }

        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(BusCityBean afRefreshBean) {
        if (afRefreshBean.getEventStr().equals(Const.refresh)) {
            city_tv.setTextColor(ResourcesUtils.getColor(R.color.color333));
            city_tv.setText(afRefreshBean.getCity());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
