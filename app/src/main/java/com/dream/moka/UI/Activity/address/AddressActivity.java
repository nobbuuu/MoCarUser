package com.dream.moka.UI.Activity.address;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.AddressListResultBean;
import com.dream.moka.Bean.EventBusBean.RefreshAddress;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.AddressContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Presenter.AddressPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.SureOrderActivity;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.RvUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class AddressActivity extends BaseActivity implements AddressContract {

    @Inject
    AddressPresenter mAddressPresenter;
    @Bind(R.id.address_rv)
    RecyclerView address_rv;
    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.add_address)
    TextView mAddAddress;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    private List<AddressListResultBean> dataList = new ArrayList<>();
    private AddressAdapter mAdapter;
    private Dialog mLoadingDialog;
    private int mCode = 0;
    public static final int BACKCODE = 200;

    public static void openAct(Activity activity, int code) {
        Intent intent = new Intent(activity, AddressActivity.class);
        intent.putExtra("code", code);
        activity.startActivityForResult(intent, code);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_addressmanage;
    }

    @Override
    public void initView() {
        mAddressPresenter.attachView(this);
        RvUtils.setOption_noparam(address_rv, this);
        mAdapter = new AddressAdapter(this, dataList, R.layout.rvitem_addressmanage);
        address_rv.setAdapter(mAdapter);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        EventBus.getDefault().register(this);
        mCode = getIntent().getIntExtra("code", 0);
    }

    @Subscribe
    public void onEvent(RefreshAddress refreshAddress) {
        mAddressPresenter.getAddressListData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {
        mLoadingDialog.show();
        mAddressPresenter.getAddressListData();
    }

    @Override
    public void eventListener() {

    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @OnClick({R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
        }
    }

    @OnClick(R.id.add_address)
    public void onViewClicked() {
        AddAndEditAddressActivity.openAct(mContext, "add", new AddressListResultBean(), "1");
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
    public void getDataSuccess(List<AddressListResultBean> results) {
        if (dataList.size() != 0) {
            dataList.clear();
        }
        dataList.addAll(results);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setDefultAddressSuccess(int position) {
        for (int i = 0; i < dataList.size(); i++) {
            if (i == position) {
                dataList.get(i).setStatus(1);
            } else {
                dataList.get(i).setStatus(0);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteAddressSuccess(int position) {
        dataList.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    public class AddressAdapter extends RVBaseAdapter<AddressListResultBean> {

        private Dialog mDeleteDialog;

        public AddressAdapter(Activity context, List<AddressListResultBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final AddressListResultBean addressListResultBean, final int position) {

            String recipientsName = addressListResultBean.getRecipientsName();
            String telephone = addressListResultBean.getTelephone();
            String province = addressListResultBean.getProvince();
            String city = addressListResultBean.getCity();
            String area = addressListResultBean.getArea();
            String detailAddr = addressListResultBean.getDetailAddr();
            if (recipientsName != null) {
                holder.setText(R.id.name, recipientsName);
            }
            if (telephone != null) {
                holder.setText(R.id.phone, telephone);
            }
            holder.setText(R.id.address, province + city + area + detailAddr);
            final int status = addressListResultBean.getStatus();
            if (status == 1) {//默认
                holder.setImageResource(R.id.defult_img, R.mipmap.icon_radio_selected);
            } else {
                holder.setImageResource(R.id.defult_img, R.mipmap.icon_radio_default);
                holder.setOnClickListener(R.id.defult_layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = addressListResultBean.getId();
                        if (!TextUtils.isEmpty(id)) {
                            mAddressPresenter.setDefault(id, position);
                        }
                    }
                });
            }
            holder.setOnClickListener(R.id.delete, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDeleteDialog = DialogUtils.showDeleteDialog(mActivity, new NoDoubleClickListener() {
                        @Override
                        public void onNoDoubleClick(View view) {
                            String id = addressListResultBean.getId();
                            if (!TextUtils.isEmpty(id)) {
                                mAddressPresenter.deleteAddress(id, position);
                            }
                            if (mDeleteDialog != null) {
                                mDeleteDialog.dismiss();
                            }
                        }
                    });
                    mDeleteDialog.show();
                }
            });
            holder.setOnClickListener(R.id.edit, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddAndEditAddressActivity.openAct(mContext, "edit", addressListResultBean, String.valueOf(status));
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCode == SureOrderActivity.JIECHE_CODE || mCode == SureOrderActivity.SONGCHE_CODE) {
                        Intent intent = new Intent();
                        intent.putExtra("data", addressListResultBean);
                        setResult(BACKCODE, intent);
                        finish();
                    }
                }
            });
        }
    }
}
