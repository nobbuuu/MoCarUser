package com.dream.moka.UI.Activity.set;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.Message.MessageDetailBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.Message.MessageDetailContract;
import com.dream.moka.Contract.UserXieYiContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.Message.MessageDetailPresenter;
import com.dream.moka.Presenter.UserXieYiPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.MainActivity;
import com.dream.moka.UI.Activity.Message.MessageRvActivity;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.StringUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class UserXieYiActivity extends BaseCommonActivity implements UserXieYiContract,MessageDetailContract.View{

    @Inject
    UserXieYiPresenter mUserXieYiPresenter;
    @Inject
    MessageDetailPresenter mMessageDetailPresenter;
    @Bind(R.id.txt)
    TextView mTxt;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    @Bind(R.id.web)
    WebView mWeb;

    private String mFrom;
    private Dialog mLoadingDialog;

    public static void openAct(Context context, String from) {
        Intent intent = new Intent(context, UserXieYiActivity.class);
        intent.putExtra("from", from);
        context.startActivity(intent);
    }
    public static void openAct(Context context, String from,String id) {
        Intent intent = new Intent(context, UserXieYiActivity.class);
        intent.putExtra("from", from);
        intent.putExtra(Const.intentTag, id);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mUserXieYiPresenter.attachView(this);
        mMessageDetailPresenter.attachView(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);

                        /* 设置支持Js,必须设置的,不然网页基本上不能看 */
        mWeb.getSettings().setJavaScriptEnabled(true);
/* 设置缓存模式,我这里使用的默认,不做多讲解 */
        mWeb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
/* 设置为true表示支持使用js打开新的窗口 */
        mWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    /* 大部分网页需要自己保存一些数据,这个时候就的设置下面这个属性 */
        mWeb.getSettings().setDomStorageEnabled(true);
 /* 设置为使用webview推荐的窗口 */
        mWeb.getSettings().setUseWideViewPort(true);
    /* 设置网页自适应屏幕大小 ---这个属性应该是跟上面一个属性一起用 */
        mWeb.getSettings().setLoadWithOverviewMode(true);
/* HTML5的地理位置服务,设置为true,启用地理定位 */
        mWeb.getSettings().setGeolocationEnabled(false);
/* 设置是否允许webview使用缩放的功能,我这里设为false,不允许 */
        mWeb.getSettings().setBuiltInZoomControls(false);
/* 提高网页渲染的优先级 */
        mWeb.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
/* 设置显示水平滚动条,就是网页右边的滚动条.我这里设置的不显示 */
        mWeb.setHorizontalScrollBarEnabled(false);
/* 指定垂直滚动条是否有叠加样式 */
        mWeb.setVerticalScrollbarOverlay(true);
//        mProgressDialog = ProgressDialog.show(mActivity, null, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWeb.getSettings().setMixedContentMode(mWeb.getSettings().MIXED_CONTENT_ALWAYS_ALLOW);  //注意安卓5.0以上的权限
        }
//        mLoadingDialog = new LoadingDialog(mActivity);
    }

    @Override
    public String initTitleText() {
        mFrom = getIntent().getStringExtra("from");
        if (mFrom.equals("user")) {
            return "用户协议";
        } else if (mFrom.equals("driver")) {
            return "司机协议";
        } else if (mFrom.equals("message")){
            return "消息详情";
        }else if (mFrom.equals(Const.insurance)){
            return "资料信息";
        } else {
            return "协议";
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

//        mLoadingDialog.show();
        if (mFrom.equals("user")) {
//            mTxt.setText(xieyi);
            mUserXieYiPresenter.getData("1");
        } else if (mFrom.equals("driver")) {
//            mTxt.setText("咨询热线 0755－23213376");
            mUserXieYiPresenter.getData("2");
        }else if (mFrom.equals("message")){
            String id = getIntent().getStringExtra(Const.intentTag);
            if (StringUtil.NoNullOrEmpty(id)){
                mMessageDetailPresenter.getMyMessageDetail(CommonAction.getToken(),id);
            }
        } else if (mFrom.equals(Const.insurance)){
            mUserXieYiPresenter.getData("5");
        }
        else {
            mUserXieYiPresenter.getData("1");

        }

    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finishActivity();
                break;
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
    public void getXiyiSuccess(String content) {
//        String escapedUsername = TextUtils.htmlEncode(content);
//        Resources res = getResources();
//        String text = String.format(res.getString(R.string.welcome_messages), escapedUsername,mailCount);
//        CharSequence styledText = Html.fromHtml(text);

        mTxt.setText(Html.fromHtml(content));
//        mWeb.loadDataWithBaseURL("",content, "text/html", "UTF-8", null);
    }


    @Override
    public void showMsgIfoData(MessageDetailBean dataBean) {
        mTxt.setText(Html.fromHtml(dataBean.getContent()));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finishActivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void finishActivity() {
        if (!AppManager.getAppManager().isInStack(MainActivity.class)){
            Intent intent = new Intent(mContext, MainActivity.class);
            Intent intent1 = new Intent(mContext, MessageRvActivity.class);
            startActivities(new Intent[]{intent,intent1});
        }
        finish();
    }
}
