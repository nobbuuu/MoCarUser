package com.dream.moka.UI.Activity;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.TextView;

import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Bean.SprayResultBean;
import com.dream.moka.Common.CommonActivity;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.FreeMaitain.ConfirmMainTainContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.FreeMaintain.ConfirmSprayPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;
import com.google.gson.Gson;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/14 0014.
 */
public class WebActivity extends CommonActivity implements ConfirmMainTainContract.View{

    @Bind(R.id.web_view)
    WebView web_view;
    @Bind(R.id.bedriver_tv)
    TextView bedriver_tv;
    WebSettings webSettings;

    @Inject
    ConfirmSprayPresenter mConfirmSprayPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {
        bedriver_tv.setVisibility(View.GONE);
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void initDatas() {

        String weburl = getIntent().getStringExtra(Const.webUrl);
        Log.e("tag", "weburl=" + weburl);
        if (null!=weburl&&weburl.contains(Const.token)&&weburl.contains(Const.latitude)){
            DaggerBaseComponent.builder()
                    .appComponent(MyApplication.getInstance().getAppComponent())
//                .mainActivityModule(new MainActivityModule(this))
                    .build().inject(this);
            mConfirmSprayPresenter.attachView(this);
            titlebar_relay.setVisibility(View.GONE);
        }
        webSettings = web_view.getSettings();
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        web_view.loadUrl("http://api.niufa.cn:9080/app/apiDownloadPic.do?url=21234.jpg");
        //设置不用系统浏览器打开,直接显示在当前Webview
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        web_view.addJavascriptInterface(WebActivity.this, "android");//JS与java的交互
// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        //关闭webview中缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        deleteDatabase("WebView.db");
        deleteDatabase("WebViewCache.db");
        web_view.clearHistory();
        getCacheDir().delete();

        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        if (StringUtil.NoNullOrEmpty(weburl)){
            if (!weburl.contains("http")){
                weburl = "http://"+weburl;
            }
            web_view.clearCache(true);
            web_view.clearFormData();
            web_view.loadUrl(weburl);
        }else {
            ToastUtils.Toast_short("链接有误");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        web_view.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        web_view.onPause();
    }

    @Override
    public void eventListener() {


    }

    @OnClick({R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                if (web_view.canGoBack()) {
                    web_view.goBack();
                } else {
                    finish();
                }
                break;
        }
    }

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && web_view.canGoBack()) {
            web_view.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (web_view != null) {
            web_view.loadUrl("about:blank");
            web_view.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            web_view.clearHistory();

            ((ViewGroup) web_view.getParent()).removeView(web_view);
            web_view.destroy();
            web_view = null;
        }
        super.onDestroy();
    }

    @JavascriptInterface
    public void toComitOrder(final String mJson) {
        Log.e("tag","imgUrl="+mJson);
        if (StringUtil.NoNullOrEmpty(mJson)){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    sprayResultBean = new Gson().fromJson(mJson, SprayResultBean.class);
                    if (sprayResultBean!=null){
                        Map<String, String> map = MapUtils.getSingleMap();
                        map.put(Const.token, CommonAction.getToken());
                        if (StringUtil.NoNullOrEmpty(sprayResultBean.getCarId())&&StringUtil.NoNullOrEmpty(sprayResultBean.getRepairId())){
                            map.put("carId",sprayResultBean.getCarId());
                            map.put("repairShopId",sprayResultBean.getRepairId());
                            mLoadingDialog.show();
                            mConfirmSprayPresenter.confirmMaintainOrder(map);
                        }else {
                            ToastUtils.Toast_short("数据异常");
                        }
                    }
                }
            });
        }
    }
    @JavascriptInterface
    public void finishActivity(String mstr) {
        Log.e("tag","mstr="+mstr);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
    }
    @Override
    public void finish() {
        // 当我们对Activity进行finish的时候，webview持有的页面并不会立即释放，如果页面中有在执行js等其他操作，仅仅进行finish是完全不够的。
        web_view.loadUrl("about:blank");
        super.finish();
    }

    @Override
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }

    private SprayResultBean sprayResultBean;
    @Override
    public void showConfirmData(ConfirmOrderResultBean dataBean) {
        SureOrderActivity.openAct(mContext,Const.coinSpray,sprayResultBean,dataBean);
    }
}
