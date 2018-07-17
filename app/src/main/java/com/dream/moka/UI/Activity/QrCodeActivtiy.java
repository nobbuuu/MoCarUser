package com.dream.moka.UI.Activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.Utils.DensityUtil;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.ToastUtils;
import com.dream.moka.Utils.ZXingUtils;
import com.google.zxing.WriterException;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by Administrator on 2017/11/29 0029.
 */
public class QrCodeActivtiy extends BaseActivity {

    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.right_iv)
    ImageView mRightIv;
    @Bind(R.id.titlebar_relay)
    RelativeLayout mTitlebarRelay;
    @Bind(R.id.img)
    ImageView mImg;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    private String mShareUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_qrcode;
    }

    @Override
    public void initView() {
        String driverId = CommonAction.getDriverId();
//        String registDriverUrl = CommonAction.getRegistDriverUrl();
        mShareUrl = Const.API_BASE_URL + "login/driverRegister?recomUserId=" + driverId;
        try {
            Bitmap qrCode = ZXingUtils.createQRCode(mShareUrl, DensityUtil.dip2px(QrCodeActivtiy.this, 100));
            mImg.setImageBitmap(qrCode);
        } catch (WriterException e) {
            e.printStackTrace();
        }
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

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @OnClick({R.id.back_relay, R.id.right_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.right_iv:
                DialogUtils.showDriverShareDialog(mActivity
                        , new QqClicklistener() {
                            @Override
                            public void onClick() {
                                shareQQ();
//                                shareQQTest();
                            }
                        }, new WeChatClicklistener() {
                            @Override
                            public void onClick() {
                                shareWechat();
                            }
                        }, new WeChatQuanClicklistener() {
                            @Override
                            public void onClick() {
                                shareWechatQuan();
                            }
                        }, new CopyClicklistener() {
                            @Override
                            public void onClick() {
                                copyUrl();
                            }
                        });
                break;
        }
    }

    /**
     * 复制
     */
    private void copyUrl() {
        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(mShareUrl);
        ToastUtils.Toast_short("复制成功");
    }

    /**
     * 分享到朋友圈
     */
    private void shareWechatQuan() {
        Platform.ShareParams sp = new Platform.ShareParams();
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setImageData(logo);
        sp.setTitle(ResourcesUtils.getString(R.string.share_title));
        sp.setText(ResourcesUtils.getString(R.string.share_click));
        sp.setUrl(mShareUrl);
        Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);
        platform.share(sp);
    }

    /**
     * 分享到微信
     */
    private void shareWechat() {
        Platform.ShareParams sp = new Platform.ShareParams();
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setImageData(logo);
        sp.setTitle(ResourcesUtils.getString(R.string.share_title));
        sp.setText(ResourcesUtils.getString(R.string.share_click));
        sp.setUrl(mShareUrl);
        Platform platform = ShareSDK.getPlatform(Wechat.NAME);
        platform.share(sp);
    }

    /**
     * 分享到qq
     */
    private void shareQQ() {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitleUrl(mShareUrl);// 标题的超链接
        sp.setTitle(ResourcesUtils.getString(R.string.share_title));
        sp.setText(ResourcesUtils.getString(R.string.share_click));
        sp.setShareType(Platform.SHARE_WEBPAGE);
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
        sp.setImageData(logo);
//        sp.setImageUrl("http://www.someserver.com/测试图片网络地址.jpg");
        sp.setSite("发布分享的网站名称");
        sp.setSiteUrl("发布分享网站的地址");

        Platform qq = ShareSDK.getPlatform(QQ.NAME);
//Platform qzone = ShareSDK.getPlatform (QZone.NAME);

// 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        qq.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
// 执行图文分享
        qq.share(sp);
//qzone.share(sp);
    }

    /**
     * 分享到qq
     */
    private void shareQQTest() {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle("标题");
        sp.setTitleUrl("http://sharesdk.cn");
        sp.setText("我是共用的参数，这几个平台都有text参数要求，提取出来啦");
        sp.setImageUrl("https://hmls.hfbank.com.cn/hfapp-api/9.png");
        ToastUtils.Toast_long("分享到qq");
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.share(sp);
//qzone.share(sp);
    }


    public interface QqClicklistener {
        void onClick();
    }

    public interface WeChatClicklistener {
        void onClick();
    }

    public interface WeChatQuanClicklistener {
        void onClick();
    }

    public interface CopyClicklistener {
        void onClick();
    }


}
