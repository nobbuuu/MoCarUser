package com.dream.moka.UI.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.EventBusBean.WXPaySuccessBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.AppraiseContract;
import com.dream.moka.Presenter.AppraisePresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.RatingBar;
import com.dream.moka.Utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class AppraiseActivity extends BaseCommonActivity implements AppraiseContract {

    @Inject
    AppraisePresenter mAppraisePresenter;
    @Bind(R.id.ptnum_tv)
    TextView mPtnumTv;
    @Bind(R.id.ptstar)
    RatingBar mPtstar;
    @Bind(R.id.jdrivernum_tv)
    TextView mJdrivernumTv;
    @Bind(R.id.jcstar)
    RatingBar mJcstar;
    @Bind(R.id.sdrivernum_tv)
    TextView mSdrivernumTv;
    @Bind(R.id.sdstar)
    RatingBar mSdstar;
    @Bind(R.id.wxservicenum_tv)
    TextView mWxservicenumTv;
    @Bind(R.id.wxstar)
    RatingBar mWxstar;
    @Bind(R.id.xicar_tv)
    TextView mXicarTv;
    @Bind(R.id.xistar)
    RatingBar mXistar;
    @Bind(R.id.fabu_tv)
    TextView mFabuTv;

    private String star1 = "3", star2 = "3", star3 = "3", star4 = "3", star5 = "3";
    private String mId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_appraise;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mAppraisePresenter.attachView(this);
        mId = getIntent().getStringExtra("id");
        mWxstar.setStar(3);
        mPtstar.setStar(3);
        mXistar.setStar(3);
        mSdstar.setStar(3);
        mJcstar.setStar(3);
    }

    @Override
    public String initTitleText() {
        return "评价";
    }

    @Override
    public String initRightTv() {
        return "投诉";
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
    }

    @Override
    public void eventListener() {
        mJcstar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(int var1) {
                star2 = String.valueOf(var1);
            }
        });
        mSdstar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(int var1) {
                star3 = String.valueOf(var1);
            }
        });
        mXistar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(int var1) {
                star5 = String.valueOf(var1);
            }
        });
        mPtstar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(int var1) {
                star1 = String.valueOf(var1);
            }
        });
        mWxstar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(int var1) {
                star4 = String.valueOf(var1);
            }
        });
    }

    @OnClick({R.id.back_relay, R.id.fabu_tv,R.id.right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.fabu_tv:
//                actionResultDialog(mActivity, "恭喜你评价成功，获得10积分").show();
                mLoadingDialog.show();
                mAppraisePresenter.PingLun(mId, star2, star3, star4, star1, star5);
                break;
            case R.id.right_tv:
                IntentUtils.toActivity(FeedBackServiceActivity.class, mActivity);
                break;
        }
    }

    //    (@Field("token") String token, @Field("orderId") String orderId, @Field("sendDriverScore") String sendDriverScore
//            , @Field("backDriverScore") String backDriverScore, @Field("repairShopScore") String repairShopScore, @Field("technicianScore") String technicianScore
//            , @Field("washCarScore") String washCarScore);
    public Dialog actionResultDialog(Activity activity, final String tipStr) {

        final Dialog tipDialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View headView = LayoutInflater.from(activity).inflate(R.layout.dialog_appraise, null);
        TextView fabu_result = (TextView) headView.findViewById(R.id.fabu_result);
        fabu_result.setText(tipStr);
        tipDialog.setContentView(headView);
        //获取当前Activity所在的窗体
       /* Window dialogWindow1 = tipDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity( Gravity.BOTTOM);*/
        tipDialog.setCancelable(true);
        tipDialog.setCanceledOnTouchOutside(true);
        return tipDialog;
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
    public void onSuccess() {
//        EventBus.getDefault().post(new WXPaySuccessBean());
        ToastUtils.Toast_short("评价成功");
        finish();
    }
}
