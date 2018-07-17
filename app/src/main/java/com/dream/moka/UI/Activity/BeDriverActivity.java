package com.dream.moka.UI.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.DriverUserInfContract;
import com.dream.moka.Listener.PictureListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.DriverUserInfoPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.CustomHelper;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.SpUtils;
import com.dream.moka.Utils.ToastUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class BeDriverActivity extends BaseCommonActivity implements DriverUserInfContract, TakePhoto.TakeResultListener, InvokeListener {

    @Bind(R.id.root_lay)
    LinearLayout root_lay;
    @Bind(R.id.web_view)
    WebView mWeb;
    @Bind(R.id.progress)
    ProgressBar mProgress;
    private Dialog mLoadingDialog;
    @Inject
    DriverUserInfoPresenter mDriverUserInfoPresenter;
    private CustomHelper mCustomHelper;
    private String mType;

    public static void openAct(Context context) {
        context.startActivity(new Intent(context, BeDriverActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bedriverinput;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void initView() {
        DaggerBaseComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
//                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
        mDriverUserInfoPresenter.attachView(this);
        mWeb.addJavascriptInterface(BeDriverActivity.this, "android");
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
    }

    @JavascriptInterface
    public void onFunctionSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.Toast_short("已提交申请");
                SpUtils.savaUserInfo(Const.userType,"2");
                finish();
            }
        });
    }

    @JavascriptInterface
    public void updataPic(String type) {
        mType = type;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtils.initPictureMethodDialog(mActivity, new PictureListener() {
                    @Override
                    public void onCamareClick() {
                        mCustomHelper = CustomHelper.INSTANCE(getTakePhoto(), "600", "600", false, "600", "600");
                        mCustomHelper.onClick(true);
                    }

                    @Override
                    public void onPictureClick() {
                        mCustomHelper = CustomHelper.INSTANCE(getTakePhoto(), "600", "600", false, "600", "600");
                        mCustomHelper.onClick(false);
                    }
                }).show();
            }
        });
    }


    @Override
    public String initTitleText() {
        return "申请资料填写";
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
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        String url = Const.API_BASE_URL + "index/applyDriver?token=" + CommonAction.getToken();
        mWeb.loadUrl(url);
        mProgress.setMax(100);
        mWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgress.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgress.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
//        mWeb.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                handler.proceed();
//            }
//
////                　　//handler.cancel(); 默认的处理方式，WebView变成空白页
////                　　//handler.process();接受证书
////                　　handleMessage(Message msg);
//        });
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        ArrayList<TImage> images = result.getImages();
        String compressPath = images.get(images.size() - 1).getCompressPath();
        mLoadingDialog.show();
        mDriverUserInfoPresenter.uploadPic(compressPath);
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    private InvokeParam invokeParam;

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    private TakePhoto takePhoto;

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void onSuccess(String path) {
        String s = path + mType;
//        mWeb.loadUrl("javascript:updataHtml(" + s + ")");
        mWeb.loadUrl("javascript:updataHtml('" + s + "')");

//        mWeb.loadUrl("javascript:updataHtml("+path+"+"+mType+")");
//        mWeb.loadUrl("javascript:updataHtml(" + path + ",'" +mType + "')");
//        mWeb.loadUrl("javascript:updataHtml(" + "'http://blog.csdn.net/Leejizhou'" + ")");

    }
}
