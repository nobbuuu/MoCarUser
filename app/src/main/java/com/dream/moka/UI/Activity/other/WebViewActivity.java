package com.dream.moka.UI.Activity.other;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.HotActivityBean;
import com.dream.moka.Bean.Maintain.CarconfigureBean;
import com.dream.moka.Bean.NewsDetailBean;
import com.dream.moka.Bean.ProductDetailBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.FreeMaitain.CarconfigureContract;
import com.dream.moka.Contract.Message.ContentContract;
import com.dream.moka.Contract.ProductDetailContract;
import com.dream.moka.Contract.UserXieYiContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.Maintain.CarconfigurePresenter;
import com.dream.moka.Presenter.Message.ContentPresenter;
import com.dream.moka.Presenter.ProductDetailPresenter;
import com.dream.moka.Presenter.UserXieYiPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import java.io.FileNotFoundException;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/14 0014.
 */
public class WebViewActivity extends BaseCommonActivity implements UserXieYiContract, ContentContract.View, ProductDetailContract.View, CarconfigureContract.View {

    @Bind(R.id.web_view)
    WebView web_view;
    @Bind(R.id.bedriver_tv)
    TextView bedriver_tv;
    WebSettings webSettings;

    private String type, contentId;
    @Inject
    UserXieYiPresenter mUserXieYiPresenter;

    @Inject
    ContentPresenter mContentPresenter;

    @Inject
    ProductDetailPresenter mProductDetailPresenter;

    @Inject
    CarconfigurePresenter mCarconfigurePresenter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String picFile = (String) msg.obj;
            String[] split = picFile.split("/");
            String fileName = split[split.length - 1];
            try {
                MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), picFile, fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + picFile)));
            ToastUtils.Toast_short("保存成功");
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {
        mUserXieYiPresenter.attachView(this);
        mContentPresenter.attachView(this);
        mProductDetailPresenter.attachView(this);
        mCarconfigurePresenter.attachView(this);
    }

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public String initTitleText() {
        String tag = getIntent().getStringExtra("tag");
        type = getIntent().getStringExtra(Const.intentTag);
        String title = "";
        if (tag != null) {
            title = tag;
        } else if (null != type) {
            switch (type) {
                case "2":
                    title = "活动详情";
                    break;
                case "4":
                    title = "使用规则";
                    break;
                case "6":
                    title = "积分规则";
                    break;
                case "8":
                    title = "动态详情";
                    break;
                case "9":
                    title = "配件详情";
                    break;
                case "10":
                    title = "原厂配件参数";
                    break;
            }
            bedriver_tv.setVisibility(View.GONE);
        }
        return title;
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
        web_view.addJavascriptInterface(new JsOperation(), "Android");//JS与java的交互
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
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        String type = getIntent().getStringExtra(Const.intentTag);
        if (type != null) {
            contentId = getIntent().getStringExtra(Const.webUrl);
            if (StringUtil.NoNullOrEmpty(contentId)) {
                mLoadingDialog.show();
                switch (type) {
                    case "2":
                        mContentPresenter.getContent(contentId);
                        break;
                    case "9":
                        mProductDetailPresenter.getProductDetail(contentId);
                        break;
                    case "10":
                        mCarconfigurePresenter.getCarConfigure(contentId);
                        break;
                    default:
                        mUserXieYiPresenter.getData(type);
                        break;
                }
            }
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
    public void getXiyiSuccess(String content) {
//        web_view.loadUrl(weburl)；
//        String tempStr = StringSpLitUtil.replaceAll(StringEscapeUtils.unescapeHtml4(content));
//        Log.e("tag","tempStr="+tempStr);
        web_view.loadUrl(Const.API_BASE_URL + "repair/getContent?content=" + content);
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
    public void showContentData(HotActivityBean dataBean) {
        web_view.loadUrl(Const.API_BASE_URL + "repair/getContent?content=" + StringUtil.checkNull(dataBean.getContent()));
    }

    @Override
    public void showProductDetailData(ProductDetailBean dataBean) {
        web_view.loadUrl(Const.API_BASE_URL + "repair/getContent?content=" + StringUtil.checkNull(dataBean.getContent()));
    }

    @Override
    public void showCarconfigureData(CarconfigureBean dataBean) {
        web_view.loadUrl(Const.API_BASE_URL + "repair/getContent?content=" + StringUtil.checkNull(dataBean.getCarConfigure()));
    }

    class JsOperation {


        public JsOperation() {
        }

        @JavascriptInterface
        public void saveImg(final String imgUrl) {
            // 下载图片到本地(最好做一个避免重复下载的问题)
            Log.e("tag", "imgUrl=" + imgUrl);
           /* for (String url : MyApplication.getImgList()){
                if (url.equals(imgUrl)){
                    ToastUtils.Toast_short("保存成功");
                    return;
                }
            }*/
           /* DownPicUtil.downPic(imgUrl, new DownPicUtil.DownFinishListener() {
                @Override
                public void getDownPath(String s) {
                    Message msg = Message.obtain();
                    msg.obj = s;
                    handler.sendMessage(msg);
                }
            });*/
        }
    }

    @Override
    public void finish() {
        // 当我们对Activity进行finish的时候，webview持有的页面并不会立即释放，如果页面中有在执行js等其他操作，仅仅进行finish是完全不够的。
        web_view.loadUrl("about:blank");
        super.finish();
    }
}
