package com.dream.moka.UI.Activity.other;

import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.NewsDetailBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.DynamicDetailContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.Message.DynamicDetailPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.StringUtil;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.apache.http.util.EncodingUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/14 0014.
 */
public class DynamicActivity extends BaseCommonActivity implements DynamicDetailContract.View {

    @Bind(R.id.web_view)
    WebView web_view;
    WebSettings webSettings;
    @Bind(R.id.dynamicTitle_tv)
    TextView mDynamicTitleTv;
    @Bind(R.id.dynamicTime_tv)
    TextView mDynamicTimeTv;
    @Bind(R.id.readNum_tv)
    TextView mReadNumTv;
    @Bind(R.id.topimg_iv)
    ImageView mTopimgIv;

    @Inject
    DynamicDetailPresenter mDynamicDetailPresenter;

    private String type, content_url;
    @Override
    public int getLayoutId() {
        return R.layout.activity_dynamic_detail;
    }

    @Override
    public void initView() {
        mDynamicDetailPresenter.attachView(this);
    }

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public String initTitleText() {
        return "动态详情";
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

        webSettings = web_view.getSettings();
        web_view.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
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
        //        web_view.addJavascriptInterface(new JsOperation(), "Android");//JS与java的交互
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //设置自适应屏幕，两者合用
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            webSettings.setDefaultFontSize(23);
            webSettings.setTextSize(WebSettings.TextSize.LARGEST);
        }
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        String type = getIntent().getStringExtra(Const.intentTag);
        if (type != null) {
            content_url = getIntent().getStringExtra(Const.webUrl);
            mLoadingDialog.show();
            mDynamicDetailPresenter.getDynamicContent(content_url);
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
            web_view.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            web_view.clearHistory();
            ((ViewGroup) web_view.getParent()).removeView(web_view);
            web_view.destroy();
            web_view = null;
        }
        super.onDestroy();
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
    public void showDynamicData(NewsDetailBean dataBean) {
        if (dataBean.getContent() != null) {
            mDynamicTitleTv.setText(dataBean.getTitle());
            mDynamicTimeTv.setText(DateFormatUtil.getTime(dataBean.getCreateDate(),Const.YMD_HMS,Const.MD_HM));
            if (dataBean.getHits()!=0){
                mReadNumTv.setText("阅读量："+dataBean.getHits());
            }
//            GlidUtils.LoadImgForUrl(mContext.getApplicationContext(),Const.BannerUrl+dataBean.getImage(),mTopimgIv);
            if (StringUtil.NoNullOrEmpty(dataBean.getContent())){
//                String newContent = HtmlFormat.getNewContent(dataBean.getContent());
////            paramUrl= paramUrl.replace("<img", "<img style='max-width:100%;height:auto;'");
//                Const.API_BASE_URL + "repair/getContent?content="
                web_view.loadDataWithBaseURL(Const.API_BASE_URL + "repair/getContent?content=", dataBean.getContent(),"text/html","utf-8",null);
//                web_view.loadUrl(Const.API_BASE_URL+"repair/getContent?content="+dataBean.getContent());
//                web_view.postUrl(Const.API_BASE_URL+"repair/getContent?content=", EncodingUtils.getBytes(dataBean.getContent(),"utf-8"));
            }
        }
    }

    @Override
    public void finish() {
        // 当我们对Activity进行finish的时候，webview持有的页面并不会立即释放，如果页面中有在执行js等其他操作，仅仅进行finish是完全不够的。
        web_view.loadUrl("about:blank");
        super.finish();
    }
}
