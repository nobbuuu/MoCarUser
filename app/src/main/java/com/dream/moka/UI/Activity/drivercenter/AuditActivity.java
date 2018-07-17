package com.dream.moka.UI.Activity.drivercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.R;
import com.dream.moka.Utils.IntentUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class AuditActivity extends BaseCommonActivity {

    @Bind(R.id.img)
    ImageView mImg;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.icCard)
    TextView mIcCard;
    @Bind(R.id.content)
    TextView mContent;
    @Bind(R.id.read)
    CheckBox mRead;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.call_service)
    TextView mCallService;
    private String mFrom;

    public static void openAct(Context context, String from, String status) {
        Intent intent = new Intent(context, AuditActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("status", status);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_driveraudit;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    //-1：初审中 -2 ：已驳回 -3：终审中 1： 通过
    @Override
    public void initView() {
        if (mFrom.equals("mine")) {
            String status = getIntent().getStringExtra("status");
            switch (status) {
                case "-1":
                    mImg.setImageResource(R.mipmap.icon_audit);
                    mTitle.setText("正在审核中");
                    mContent.setText("您提交申请资料后，审核时间预计3-7个工作日");
                    break;
                case "-2":
                    String driverReject = CommonAction.getDriverReject();
                    mImg.setImageResource(R.mipmap.icon_audit);
                    mTitle.setText("审核已驳回");
                    mContent.setText(driverReject == null ? "" : driverReject);
                    break;
                case "-3":
                    String driverName = CommonAction.getDriverName();
                    String driverIdCard = CommonAction.getDriverIdCard();
                    mImg.setImageResource(R.mipmap.icon_audit01);
                    mTitle.setText("资料审核通过");
                    mContent.setText("审核通过后工作人员会联系您参加面试，具体信息以工作人员电话通知为准");
                    mName.setVisibility(View.VISIBLE);
                    mIcCard.setVisibility(View.VISIBLE);
                    mName.setText(driverName == null ? "" : "申请人：" + driverName);
                    mIcCard.setText(driverIdCard == null ? "" : "身份证号：" + driverIdCard);
                    break;
            }

        } else if (mFrom.equals("withDraw")) {
            mImg.setImageResource(R.mipmap.icon_audit);
            mTitle.setText("正在审核中");
            mContent.setText("您提交申请后，1-3个工作日会受理提现申请订单，具体到账日期以银行为准");
        }
    }

    @Override
    public String initTitleText() {
        mFrom = getIntent().getStringExtra("from");
        return mFrom.equals("mine") ? "司机中心" : "提现审核";
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
        mCallService.setText("联系电话："+CommonAction.getPlatPhone());
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay, R.id.call_service})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.call_service:
                IntentUtils.call_Dall(CommonAction.getPlatPhone(), mActivity);
                break;
        }
    }

}
