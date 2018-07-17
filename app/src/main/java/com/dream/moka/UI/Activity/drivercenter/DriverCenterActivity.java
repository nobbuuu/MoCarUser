package com.dream.moka.UI.Activity.drivercenter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Base.BasePagerAdapter;
import com.dream.moka.Bean.EventBusBean.ChooseOrderBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.IM.im.activity.ChatActivity;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.MainActivity;
import com.dream.moka.UI.Activity.Message.MessageRvActivity;
import com.dream.moka.UI.Fragment.DriverCenterMainFragment;
import com.dream.moka.UI.Fragment.DriverCenterMineFragment;
import com.dream.moka.UI.Fragment.DriverCenterOrderFragment;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.SpUtils;
import com.dream.moka.Utils.StringUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
public class DriverCenterActivity extends BaseActivity {

    @Bind(R.id.drivercenter_vp)
    ViewPager drivercenter_vp;
    @Bind(R.id.dmain_iv)
    ImageView dmain_iv;
    @Bind(R.id.dorder_iv)
    ImageView dorder_iv;
    @Bind(R.id.dmine_iv)
    ImageView dmine_iv;
    @Bind(R.id.dmain_tv)
    TextView dmain_tv;
    @Bind(R.id.dorder_tv)
    TextView dorder_tv;
    @Bind(R.id.dmine_tv)
    TextView dmine_tv;
    @Bind(R.id.center_tv)
    TextView center_tv;
    @Bind(R.id.right_tv)
    TextView right_tv;
    @Bind(R.id.message_iv)
    ImageView message_iv;
    @Bind(R.id.title_relay)
    RelativeLayout title_relay;
    @Bind(R.id.zezao_relay)
    FrameLayout zezao_relay;
    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.orderNo)
    EditText mOrderNo;
    @Bind(R.id.startTime)
    TextView mStartTime;
    @Bind(R.id.startLayout)
    RelativeLayout mStartLayout;
    @Bind(R.id.endTime)
    TextView mEndTime;
    @Bind(R.id.endLayout)
    RelativeLayout mEndLayout;
    @Bind(R.id.radio1)
    RadioButton mRadio1;
    @Bind(R.id.radio2)
    RadioButton mRadio2;
    @Bind(R.id.statusGroup)
    RadioGroup mStatusGroup;
    @Bind(R.id.sure_sxtv)
    TextView mSureSxtv;
    @Bind(R.id.return_tv)
    TextView mReturnTv;
    @Bind(R.id.dmain_lay)
    LinearLayout mDmainLay;
    @Bind(R.id.dorder_lay)
    LinearLayout mDorderLay;
    @Bind(R.id.dmine_lay)
    LinearLayout mDmineLay;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    @Bind(R.id.popView)
    LinearLayout mPopView;

    //    private PopupWindow popupWindowpp;
    private List<Fragment> fragments = new ArrayList<>();
    ChooseOrderBean chooseOrderBean = new ChooseOrderBean();
    private boolean isopenPopview = false;
    private String mTag;

    @Override
    public int getLayoutId() {
        return R.layout.activity_drivercenter;
    }

    @Override
    public void initView() {
        mTag = getIntent().getStringExtra("tag");
        EventBus.getDefault().register(this);
//        initPopwindowPP();
    }

    private void initFragments() {
        fragments.clear();
        DriverCenterMainFragment driverCenterMainFragment = new DriverCenterMainFragment();
        fragments.add(driverCenterMainFragment);
        DriverCenterOrderFragment orderFragment = new DriverCenterOrderFragment();
        fragments.add(orderFragment);
        DriverCenterMineFragment mineFragment = new DriverCenterMineFragment();
        fragments.add(mineFragment);

    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {
        initFragments();
        drivercenter_vp.setAdapter(new BasePagerAdapter(getSupportFragmentManager(), fragments));
        drivercenter_vp.setOffscreenPageLimit(3);
    }

    @Override
    public void eventListener() {
        drivercenter_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        initBotItem();
                        refreshMain();
                        break;
                    case 1:
                        initBotItem();
                        refreshOrder();
                        break;
                    case 2:
                        initBotItem();
                        refreshMine();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mStatusGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radio1:
                        chooseOrderBean.setStatus("0");
                        break;
                    case R.id.radio2:
                        chooseOrderBean.setStatus("1");
                        break;
                }
            }
        });
        mStartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(0);
            }
        });
        mEndLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(1);
            }
        });
        mSureSxtv.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                chooseOrderBean.setOrderCode(mOrderNo.getText().toString().trim());
                chooseOrderBean.setBeginDate(mStartTime.getText().toString().trim());
                chooseOrderBean.setEndDate(mEndTime.getText().toString().trim());
                EventBus.getDefault().post(chooseOrderBean);
                openPopView(false);
            }
        });
        mReturnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOrderNo.setText("");
                mStartTime.setText("");
                mEndTime.setText("");
                mStatusGroup.clearCheck();
                chooseOrderBean.setOrderCode("");
                chooseOrderBean.setBeginDate("");
                chooseOrderBean.setEndDate("");
                chooseOrderBean.setStatus("");
            }
        });
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }


    private void initBotItem() {

        dmain_iv.setImageResource(R.mipmap.gray_icon_home);
        dmain_tv.setTextColor(ResourcesUtils.getColor(R.color.color666));
        dorder_iv.setImageResource(R.mipmap.gray_icon_indent);
        dorder_tv.setTextColor(ResourcesUtils.getColor(R.color.color666));
        dmine_iv.setImageResource(R.mipmap.icon_my);
        dmine_tv.setTextColor(ResourcesUtils.getColor(R.color.color666));
    }

    @OnClick({R.id.back_relay, R.id.dmain_lay, R.id.dorder_lay, R.id.dmine_lay, R.id.right_tv, R.id.message_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                backMethod();
                break;
            case R.id.dmain_lay:
                refreshMain();
                drivercenter_vp.setCurrentItem(0);
                break;
            case R.id.dorder_lay:
                refreshOrder();
                drivercenter_vp.setCurrentItem(1);

                break;
            case R.id.dmine_lay:
                refreshMine();
                drivercenter_vp.setCurrentItem(2);

                break;
            case R.id.right_tv:
                if (isopenPopview) {
                    openPopView(true);
                } else {
                    openPopView(false);
                }
                break;
            case R.id.message_iv://消息
                MessageRvActivity.openAct(mContext, "driver");
                break;
        }
    }

    public void openPopView(boolean open) {
        if (open) {
            zezao_relay.setVisibility(View.VISIBLE);
            zezao_relay.setAlpha(0.5f);
            right_tv.setText("取消");
            mPopView.setVisibility(View.VISIBLE);
        } else {
            right_tv.setText("筛选");
            zezao_relay.setVisibility(View.GONE);
            mPopView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        backMethod();
    }

    public void backMethod() {
        if (StringUtil.NoNullOrEmpty(mTag) && mTag.equals("backMain")) {
            Intent mainIntent = new Intent(mContext, MainActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
            finish();
        } else {
            finish();
        }
    }

//    TextView startTime;
//    TextView endTime;
//    private void initPopwindowPP() {
//        final ChooseOrderBean chooseOrderBean = new ChooseOrderBean();
//        //加载弹出框的布局
//        View contentView = LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.pop_order_saixuan, null);
//        popupWindowpp = PopWindowUtil.getPopupWindow(mActivity, contentView, R.style.top2botAnimation, ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        TextView sure_sxtv = (TextView) contentView.findViewById(R.id.sure_sxtv);
//        TextView return_tv = (TextView) contentView.findViewById(R.id.return_tv);
//        final TextView orderId = (TextView) contentView.findViewById(R.id.orderNo);
//        RelativeLayout startLayout = (RelativeLayout) contentView.findViewById(R.id.startLayout);
//        RelativeLayout endLayout = (RelativeLayout) contentView.findViewById(R.id.endLayout);
//        startTime = (TextView) contentView.findViewById(R.id.startTime);
//        endTime = (TextView) contentView.findViewById(R.id.endTime);
//        popupWindowpp.setFocusable(true);// 取得焦点
//        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
//        popupWindowpp.setBackgroundDrawable(new BitmapDrawable());
//        //点击外部消失
//        popupWindowpp.setOutsideTouchable(true);
//        final RadioGroup statusGroup = (RadioGroup) contentView.findViewById(R.id.statusGroup);
//        statusGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                switch (radioGroup.getCheckedRadioButtonId()) {
//                    case R.id.radio1:
//                        chooseOrderBean.setStatus("0");
//                        break;
//                    case R.id.radio2:
//                        chooseOrderBean.setStatus("1");
//                        break;
//                }
//            }
//        });
//        startLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openDialog(0);
//            }
//        });
//        endLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openDialog(1);
//            }
//        });
//        popupWindowpp.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                right_tv.setText("筛选");
//                zezao_relay.setVisibility(View.GONE);
//            }
//        });
//        sure_sxtv.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View view) {
//                chooseOrderBean.setOrderCode(orderId.getText().toString().trim());
//                chooseOrderBean.setBeginDate(startTime.getText().toString().trim());
//                chooseOrderBean.setEndDate(endTime.getText().toString().trim());
//                EventBus.getDefault().post(chooseOrderBean);
//                popupWindowpp.dismiss();
//            }
//        });
//        return_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                orderId.setText("");
//                startTime.setText("");
//                endTime.setText("");
//                statusGroup.clearCheck();
//                chooseOrderBean.setOrderCode("");
//                chooseOrderBean.setBeginDate("");
//                chooseOrderBean.setEndDate("");
//                chooseOrderBean.setStatus("");
////                EventBus.getDefault().post(chooseOrderBean);
////                popupWindowpp.dismiss();
//
//            }
//        });
//    }

    /**
     */
    private void openDialog(final int i) {
        TimePickerView build = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                long time = date.getTime();
                String dateStr = DateFormatUtil.getTime(time, Const.Y_M_D);
                if (dateStr != null) {
                    if (i == 0) {
                        mStartTime.setText(dateStr);
                    } else {
                        mEndTime.setText(dateStr);
                    }
                }
                Log.e("tag", "dateStr=" + dateStr);
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setContentSize(16)//滚轮文字大小
                .setTitleSize(13)//标题文字大小
                .setCancelText("取消")//取消按钮文字
                .setLabel(" 年", "月", "日", "时", "分", "秒")
                .isCyclic(true)//是否循环滚动
                .build();
        build.show();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(String eventStr) {
        if (eventStr.equals("sx")) {
//            right_tv.setVisibility(View.VISIBLE);
            right_tv.setVisibility(View.GONE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain1(String eventStr) {
        if (eventStr.equals("sb")) {
            right_tv.setVisibility(View.GONE);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain2(String eventStr) {
        if (eventStr.equals("Ordering")) {
            drivercenter_vp.setCurrentItem(1);
        }
    }

    private void refreshOrder() {
        initBotItem();
        dorder_iv.setImageResource(R.mipmap.blue_icon_indent);
        dorder_tv.setTextColor(ResourcesUtils.getColor(R.color.blue_them));
        center_tv.setText("订单查看");
        message_iv.setVisibility(View.GONE);
        String orderPager = (String) SpUtils.getParam(Const.OrderPage, "0");
        if (orderPager.equals("1")) {
            right_tv.setVisibility(View.VISIBLE);
        } else {
            right_tv.setVisibility(View.GONE);
        }

    }

    private void refreshMine() {
        initBotItem();
        dmine_iv.setImageResource(R.mipmap.biue_icon_biue_icon_my);
        dmine_tv.setTextColor(ResourcesUtils.getColor(R.color.blue_them));
        center_tv.setText("我的");
        message_iv.setVisibility(View.GONE);
        right_tv.setVisibility(View.GONE);

    }

    private void refreshMain() {
        dmain_iv.setImageResource(R.mipmap.blue_icon_home);
        dmain_tv.setTextColor(ResourcesUtils.getColor(R.color.blue_them));
        center_tv.setText("司机中心");
        message_iv.setVisibility(View.VISIBLE);
        right_tv.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (!AppManager.getAppManager().isInStack(MainActivity.class)){
                startActivity(new Intent(DriverCenterActivity.this,MainActivity.class));
            }
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}