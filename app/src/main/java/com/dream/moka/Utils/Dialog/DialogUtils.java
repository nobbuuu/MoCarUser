package com.dream.moka.Utils.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.dream.moka.Bean.EventBusBean.FinishBus;
import com.dream.moka.CumstomView.CarNoKeyboad;
import com.dream.moka.CumstomView.PayPsdInputView;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Listener.PictureListener;
import com.dream.moka.Listener.onReturnClickListener;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.login_register.LoginActivity;
import com.dream.moka.UI.Activity.QrCodeActivtiy;
import com.dream.moka.Utils.FileUtil;
import com.dream.moka.Utils.HttpUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.NetUtil;
import com.dream.moka.Utils.PopWindowUtil;
import com.dream.moka.Utils.ToSettingPageUtil;
import com.dream.moka.Utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Administrator on 2017/8/7 0007.
 */
public class DialogUtils {

    public static Dialog actionResultDialog(Activity activity, final String tipStr, String summary) {

        final Dialog tipDialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View headView = LayoutInflater.from(activity).inflate(R.layout.dialog_actionresult, null);
        TextView tip_tv = (TextView) headView.findViewById(R.id.dialogtip_tv);
        TextView dialog_suretv = (TextView) headView.findViewById(R.id.dialog_suretv);

        tip_tv.setText(tipStr);
        dialog_suretv.setText(summary);
        if (tipStr.equals("申请成功")) {
            tip_tv.setTextColor(Color.BLACK);
            dialog_suretv.setTextColor(Color.BLACK);
        }
        tipDialog.setContentView(headView);
        //获取当前Activity所在的窗体
       /* Window dialogWindow1 = tipDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity( Gravity.BOTTOM);*/
        tipDialog.setCancelable(true);
        dialog_suretv.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (tipDialog.isShowing()) {
                    tipDialog.dismiss();
                }
                if (tipStr.equals("重置密码成功!")) {
                    EventBus.getDefault().post("finish");
                }
                if (tipStr.equals("修改密码成功!")) {
                    EventBus.getDefault().post(new FinishBus("finish"));
                }
                if (tipStr.equals("绑定成功!")) {
                    EventBus.getDefault().post("finish");
                }
            }
        });
        return tipDialog;
    }


    public static void setBespreadWindow(Dialog mDialog, Activity context) {
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        mDialog.getWindow().setAttributes(lp);
    }

    public static void show(Activity activity, final Dialog dialog) {

        if ("main".equals(Thread.currentThread().getName())) {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        } else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!dialog.isShowing()) {
                        dialog.show();
                    }
                }
            });
        }
    }

    public static Dialog initLoadingDialog(Activity activity) {
        Dialog dialog = new Dialog(activity, R.style.progress_dialog);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) dialog.findViewById(R.id.id_tv_loadingmsg);
        final ImageView imageView = (ImageView) dialog.findViewById(R.id.loading_cricle);
        final Animation circle_anim = AnimationUtils.loadAnimation(activity, R.anim.loading_rotate);
        LinearInterpolator interpolator = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
        circle_anim.setInterpolator(interpolator);
        msg.setText("LOADING...");
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (circle_anim != null) {
                    circle_anim.cancel();
                }
                imageView.clearAnimation();
            }
        });
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                if (circle_anim != null) {
                    circle_anim.start();
                    imageView.startAnimation(circle_anim);  //开始动画
                }
            }
        });
        return dialog;
    }

    public static void dismiss(Activity activity, final Dialog dialog) {
        if ("main".equals(Thread.currentThread().getName())) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        } else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            });
        }
    }

    public static Dialog initPictureMethodDialog(final Activity activity) {
        final Dialog headDialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View headView = LayoutInflater.from(activity).inflate(R.layout.dialog_changeheadicon, null);
        TextView bitmap_tv = (TextView) headView.findViewById(R.id.bitmap_tv);
        TextView takephoto_tv = (TextView) headView.findViewById(R.id.takephoto_tv);
        RelativeLayout cancel_relay = (RelativeLayout) headView.findViewById(R.id.cancel_relay);
        headDialog.setContentView(headView);
        //获取当前Activity所在的窗体
        Window dialogWindow1 = headDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity(Gravity.BOTTOM);
        headDialog.setCancelable(true);


        //拍照上传
        takephoto_tv.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //相机
                headDialog.dismiss();

                IntentUtils.toCamare(activity);
            }
        });

        //从相册上传图片
        bitmap_tv.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                headDialog.dismiss();
                IntentUtils.toPicture(activity);
            }
        });

        cancel_relay.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (headDialog.isShowing()) {
                    headDialog.dismiss();
                }
            }
        });
        return headDialog;
    }

    public static Dialog initPictureMethodDialog(final Activity activity, final PictureListener pictureListener) {
        final Dialog headDialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View headView = LayoutInflater.from(activity).inflate(R.layout.dialog_changeheadicon, null);
        TextView bitmap_tv = (TextView) headView.findViewById(R.id.bitmap_tv);
        TextView takephoto_tv = (TextView) headView.findViewById(R.id.takephoto_tv);
        RelativeLayout cancel_relay = (RelativeLayout) headView.findViewById(R.id.cancel_relay);
        headDialog.setContentView(headView);
        //获取当前Activity所在的窗体
        Window dialogWindow1 = headDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity(Gravity.BOTTOM);
        headDialog.setCancelable(true);

        //拍照上传
        takephoto_tv.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //相机
                headDialog.dismiss();
                pictureListener.onCamareClick();
//                IntentUtils.toCamare(activity);
            }
        });

        //从相册上传图片
        bitmap_tv.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                headDialog.dismiss();
                pictureListener.onPictureClick();
//                IntentUtils.toPicture(activity);
            }
        });

        cancel_relay.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (headDialog.isShowing()) {
                    headDialog.dismiss();
                }
            }
        });
        return headDialog;
    }

    public static Dialog showCarNoDialog(final Activity activity, EditText mEdit) {
        final Dialog headDialog = new Dialog(activity, R.style.ActionSheetDialogStyle1);
        CarNoKeyboad headView = new CarNoKeyboad(activity, mEdit);
        headDialog.setContentView(headView);
        //获取当前Activity所在的窗体
        Window dialogWindow1 = headDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity(Gravity.BOTTOM);
        headDialog.setCancelable(true);
        headView.onKeyboardListener(new CarNoKeyboad.KeyListener() {
            @Override
            public void onClick(int type, String inputStr) {
                switch (type) {
                    case 1:
                        headDialog.dismiss();
                        break;
                    case 2:
                        headDialog.dismiss();

                        break;
                    case 3:

                        break;
                    default:

                        break;
                }
            }
        });
        headDialog.show();
        return headDialog;
    }

    public static Dialog showDriverShareDialog(final Activity activity, final QrCodeActivtiy.QqClicklistener qqClicklistener
            , final QrCodeActivtiy.WeChatClicklistener weChatClicklistener, final QrCodeActivtiy.WeChatQuanClicklistener quanClicklistener
            , final QrCodeActivtiy.CopyClicklistener copyClicklistener) {
        final Dialog headDialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View headView = LayoutInflater.from(activity).inflate(R.layout.dialog_drivershare, null);
        RelativeLayout cancel_relay = (RelativeLayout) headView.findViewById(R.id.cancel_relay);
        LinearLayout wx = (LinearLayout) headView.findViewById(R.id.wechat);
        LinearLayout wechatQuan = (LinearLayout) headView.findViewById(R.id.wechatQuan);
        LinearLayout tengxun = (LinearLayout) headView.findViewById(R.id.tengxun);
        LinearLayout copy = (LinearLayout) headView.findViewById(R.id.copy);
        headDialog.setContentView(headView);
        //获取当前Activity所在的窗体
        Window dialogWindow1 = headDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity(Gravity.BOTTOM);
        headDialog.setCancelable(true);
        PopWindowUtil.backgroundAlpaha(activity, 0.95f);
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        WindowManager.LayoutParams p = headDialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.95); //宽度设置为屏幕的0.75
//        p.height = (int) (displayHeight * 0.45); //高度设置为屏幕的0.45
        headDialog.getWindow().setAttributes(p);  //设置生效
        headDialog.show();
        headDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                PopWindowUtil.backgroundAlpaha(activity, 1);
            }
        });
        cancel_relay.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (headDialog.isShowing()) {
                    headDialog.dismiss();
                }
            }
        });
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weChatClicklistener.onClick();
                if (headDialog.isShowing()) {
                    headDialog.dismiss();
                }
            }
        });
        wechatQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quanClicklistener.onClick();
                if (headDialog.isShowing()) {
                    headDialog.dismiss();
                }
            }
        });
        tengxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qqClicklistener.onClick();
                if (headDialog.isShowing()) {
                    headDialog.dismiss();
                }
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyClicklistener.onClick();
                if (headDialog.isShowing()) {
                    headDialog.dismiss();
                }
            }
        });
        return headDialog;
    }

    public static AlertDialog getLoginTip(final Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("温馨提示");
        View login = LayoutInflater.from(activity).inflate(R.layout.view_logintip, null);
        TextView tv = (TextView) login.findViewById(R.id.ttttt);
        tv.setText("您还未登录");
        builder.setView(login);
        AlertDialog alertDialog = builder.setPositiveButton("去登录", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoginActivity.openAct(activity);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();
        return alertDialog;
    }

    public static void getVersionDialog(String webVersionName, final String apkUrl, final Context activity) {
        final Dialog dialog_tip = new Dialog(activity);
        View login = LayoutInflater.from(activity).inflate(R.layout.dialog_versionupdate, null);
        TextView tip_title = (TextView) login.findViewById(R.id.tip_title);
        TextView version_tv = (TextView) login.findViewById(R.id.version_tv);
        TextPaint tp = tip_title.getPaint();
        tp.setFakeBoldText(true);//加粗字体
        tip_title.setText("发现新版本");
        version_tv.setText(webVersionName);
        TextView cancle = (TextView) login.findViewById(R.id.tip_nav);
        TextView sure = (TextView) login.findViewById(R.id.tip_positive);
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
        dialog_tip.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog_tip.getWindow().setAttributes(p);  //设置生效

      /*  // TODO: 2016/12/23  //去掉蓝线
        int dividerID=activity.getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider=dialog_tip.findViewById(dividerID);
        divider.setBackgroundColor(Color.TRANSPARENT);(这句在5.1上会报空指针)*/

        dialog_tip.show();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog_tip.isShowing()) {
                    dialog_tip.dismiss();
                }
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog_tip.isShowing()) {
                    dialog_tip.dismiss();
                }
                String netTypeName = NetUtil.getNetTypeName(activity);
                Log.e("tag", "netTypeName=" + netTypeName);

                if (netTypeName.equals("无网络")) {
                    ToastUtils.Toast_short("网络异常");
                    return;
                }
                if (!netTypeName.equals("WIFI")) {
                    downloadTip(activity, apkUrl);
                } else {
                    //下载安装包、
                    EventBus.getDefault().post("downloadApk");
                }
            }
        });
    }

    public static void downloadTip(final Context activity, final String apkUrl) {
        final Dialog dialog_tip = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View login = LayoutInflater.from(activity).inflate(R.layout.dialog_networktip, null);
        TextView tip_title = (TextView) login.findViewById(R.id.tip_title);
        TextView version_tv = (TextView) login.findViewById(R.id.version_tv);
        TextPaint tp = tip_title.getPaint();
        tp.setFakeBoldText(true);//加粗字体
        TextView cancle = (TextView) login.findViewById(R.id.tip_nav);
        TextView sure = (TextView) login.findViewById(R.id.tip_positive);
        dialog_tip.requestWindowFeature(Window.FEATURE_NO_TITLE);//（这句设置没有title）
        dialog_tip.setContentView(login);
        //获取当前Activity所在的窗体
        Window dialogWindow1 = dialog_tip.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity(Gravity.BOTTOM);
        dialog_tip.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog_tip.setCancelable(true);
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        WindowManager.LayoutParams p = dialog_tip.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.75); //宽度设置为屏幕的0.75
//        p.height = (int) (displayHeight * 0.45); //高度设置为屏幕的0.45
        dialog_tip.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog_tip.getWindow().setAttributes(p);  //设置生效

      /*  // TODO: 2016/12/23  //去掉蓝线
        int dividerID=activity.getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider=dialog_tip.findViewById(dividerID);
        divider.setBackgroundColor(Color.TRANSPARENT);(这句在5.1上会报空指针)*/

        dialog_tip.show();
        //使用流量下载
        cancle.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (dialog_tip.isShowing()) {
                    dialog_tip.dismiss();
                }
                //下载安装包、
                EventBus.getDefault().post("downloadApk");
            }
        });

        sure.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (dialog_tip.isShowing()) {
                    dialog_tip.dismiss();
                }
            }
        });

    }

    public static void showCaredTip(final Context activity) {
        final Dialog dialog_tip = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View login = LayoutInflater.from(activity).inflate(R.layout.dialog_getcared, null);
        TextView know_tv = (TextView) login.findViewById(R.id.know_tv);
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
        know_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_tip.dismiss();
                EventBus.getDefault().post(new FinishBus("getCared"));
            }
        });

    }

    public static Dialog showPermissionTip(final Context activity) {
        final Dialog dialog_tip = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View login = LayoutInflater.from(activity).inflate(R.layout.dialog_permission, null);
        TextView tip_title = (TextView) login.findViewById(R.id.tip_title);
        TextView version_tv = (TextView) login.findViewById(R.id.version_tv);
        TextPaint tp = tip_title.getPaint();
        tp.setFakeBoldText(true);//加粗字体
        TextView cancle = (TextView) login.findViewById(R.id.tip_nav);
        TextView sure = (TextView) login.findViewById(R.id.tip_positive);
        dialog_tip.requestWindowFeature(Window.FEATURE_NO_TITLE);//（这句设置没有title）
        dialog_tip.setContentView(login);
      /*  //获取当前Activity所在的窗体
        Window dialogWindow1 = dialog_tip.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity(Gravity.BOTTOM);*/
        dialog_tip.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog_tip.setCancelable(true);
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        WindowManager.LayoutParams p = dialog_tip.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.75); //宽度设置为屏幕的0.75
//        p.height = (int) (displayHeight * 0.45); //高度设置为屏幕的0.45
        dialog_tip.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog_tip.getWindow().setAttributes(p);  //设置生效

      /*  // TODO: 2016/12/23  //去掉蓝线
        int dividerID=activity.getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider=dialog_tip.findViewById(dividerID);
        divider.setBackgroundColor(Color.TRANSPARENT);(这句在5.1上会报空指针)*/

        //取消
        cancle.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (dialog_tip.isShowing()) {
                    dialog_tip.dismiss();
                }
            }
        });

        sure.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                ToSettingPageUtil.gotoPermissionSetting();
            }
        });

        return dialog_tip;
    }

    public static Dialog showDeleteDialog(final Context activity, final NoDoubleClickListener mSureClicklistener) {
        final Dialog dialog_tip = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View login = LayoutInflater.from(activity).inflate(R.layout.dialog_permission, null);
        TextView tip_title = (TextView) login.findViewById(R.id.tip_title);
        TextView version_tv = (TextView) login.findViewById(R.id.version_tv);
        version_tv.setText("确认删除？");
        TextPaint tp = tip_title.getPaint();
        tp.setFakeBoldText(true);//加粗字体
        TextView cancle = (TextView) login.findViewById(R.id.tip_nav);
        cancle.setText("取消");
        TextView sure = (TextView) login.findViewById(R.id.tip_positive);
        sure.setText("确定");
        dialog_tip.requestWindowFeature(Window.FEATURE_NO_TITLE);//（这句设置没有title）
        dialog_tip.setContentView(login);
      /*  //获取当前Activity所在的窗体
        Window dialogWindow1 = dialog_tip.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity(Gravity.BOTTOM);*/
        dialog_tip.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog_tip.setCancelable(true);
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        WindowManager.LayoutParams p = dialog_tip.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.75); //宽度设置为屏幕的0.75
//        p.height = (int) (displayHeight * 0.45); //高度设置为屏幕的0.45
        dialog_tip.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog_tip.getWindow().setAttributes(p);  //设置生效

      /*  // TODO: 2016/12/23  //去掉蓝线
        int dividerID=activity.getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider=dialog_tip.findViewById(dividerID);
        divider.setBackgroundColor(Color.TRANSPARENT);(这句在5.1上会报空指针)*/

        //取消
        cancle.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if (dialog_tip.isShowing()) {
                    dialog_tip.dismiss();
                }
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSureClicklistener.onNoDoubleClick(view);
                dialog_tip.dismiss();
            }
        });
        return dialog_tip;
    }

    static String reasonString = "";

    public static Dialog returnMoneyDialog(String title, Activity activity, final onReturnClickListener returnClickListener) {
        final Dialog dialog = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.return_money_layout, null);
        TextView tt = (TextView) inflate.findViewById(R.id.title);
        tt.setText(title);
        final RadioGroup radioGroup = (RadioGroup) inflate.findViewById(R.id.group);
        Button buttonYes = (Button) inflate.findViewById(R.id.yes);
        Button buttonCancle = (Button) inflate.findViewById(R.id.cancle);
        final EditText reason = (EditText) inflate.findViewById(R.id.reason);
        dialog.setContentView(inflate);

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.75); //宽度设置为屏幕的0.75
//        p.height = (int) (displayHeight * 0.45); //高度设置为屏幕的0.45
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);  //设置生效
        reasonString = "";
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio1:
                        reasonString = "等待时间太久";
                        reason.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.radio2:
                        reasonString = "价格太贵了";
                        reason.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.radio3:
                        reason.setVisibility(View.VISIBLE);
                        break;

                }
            }
        });
        reason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                reasonString = "" + reason.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnClickListener.onLiftClickListener(reasonString);
                dialog.dismiss();
            }
        });
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnClickListener.onRightClickListener();
                dialog.dismiss();
            }
        });

        return dialog;
    }

    /**
     * 套餐保养提示弹窗
     *
     * @param activity
     */
    public static void openMessageDialog(Activity activity) {
        Dialog dialog = new Dialog(activity);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.baoyang_msg_layout, null);
//        dialog.setContentView();
    }

    public static void showDialog(Dialog mDialog) {
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }
}
