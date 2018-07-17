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
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.DriverEquityContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.DriverEquityPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.PopWindowUtil;
import com.dream.moka.Utils.ToastUtils;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class DriverEquityActivity extends BaseCommonActivity implements DriverEquityContract {

    @Inject
    DriverEquityPresenter mDriverEquityPresenter;

    public static void openAct(Context context) {
        context.startActivity(new Intent(context, DriverEquityActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_driver_equity;
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
        mDriverEquityPresenter.attachView(this);
    }

    @Override
    public String initTitleText() {
        return "司机权益";
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

    @OnClick({R.id.back_relay, R.id.sure_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.sure_tv:
//                ToastUtils.Toast_short("开发中，敬请期待");
                showDialogTip(mActivity);
                break;
        }
    }

    public void showDialogTip(final Activity activity) {
        final Dialog dialog_tip = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View login = LayoutInflater.from(activity).inflate(R.layout.dialog_exitmoney, null);
        TextView know_tv = (TextView) login.findViewById(R.id.tip_positive);
        TextView tip_nav = (TextView) login.findViewById(R.id.tip_nav);
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
        dialog_tip.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                PopWindowUtil.backgroundAlpaha(activity, 1f);
            }
        });
        know_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_tip.dismiss();

            }
        });
        tip_nav.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                dialog_tip.dismiss();
                mDriverEquityPresenter.tuiKuanData();

            }
        });

    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onBackMoneySuccess() {
        ToastUtils.Toast_short("退款成功，请注意查收");
        finish();
    }
}
