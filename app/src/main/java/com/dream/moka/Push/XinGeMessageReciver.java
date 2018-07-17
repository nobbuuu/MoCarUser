package com.dream.moka.Push;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.dream.moka.Bean.EventBusBean.RefreshMsgBean;
import com.dream.moka.Bean.EventBusBean.RefreshOrderBean;
import com.dream.moka.Bean.EventBusBean.StopLocationBus;
import com.dream.moka.Bean.MessageBean;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.Message.MessageRvActivity;
import com.dream.moka.UI.Activity.Message.MessageSpecialActivity;
import com.dream.moka.UI.Activity.Message.OrderAddMaintainActivity;
import com.dream.moka.UI.Activity.drivercenter.DriverCenterActivity;
import com.dream.moka.UI.Activity.set.UserXieYiActivity;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.LogUtils;
import com.dream.moka.Utils.NotifyUtil;
import com.dream.moka.Utils.StringUtil;
import com.google.gson.Gson;
import com.tencent.android.tpush.XGCustomPushNotificationBuilder;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class XinGeMessageReciver extends XGPushBaseReceiver {
    //    public static final String EXTRA_BUNDLE = "launchBundle";
    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {

    }

    @Override
    public void onUnregisterResult(Context context, int i) {

    }

    @Override
    public void onSetTagResult(Context context, int i, String s) {

    }

    @Override
    public void onDeleteTagResult(Context context, int i, String s) {

    }

    /**
     * 消息透传
     *
     * @param context
     * @param xgPushTextMessage
     */
    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {
        // APP自主处理消息的过程...
        MessageBean messageBean = new Gson().fromJson(xgPushTextMessage.getCustomContent(), MessageBean.class);
        Log.e("onTextMessage", "messageBean=" + messageBean.toString());
        String type = messageBean.getType();
        if (StringUtil.NoNullOrEmpty(type)){
            switch (type){
                case "-2":
                    EventBus.getDefault().post(new StopLocationBus("stop"));
                    break;
                case "2":
                    Intent intent = new Intent(context, MessageRvActivity.class);
                    intent.putExtra(Const.intentTag,"user");
                    NotifyUtil.PushNotify(messageBean.getTitle(),messageBean.getTitle(),intent);
                    if (messageBean.getIsitnew().equals("1")) {//需要新增消息
                        messageBean.setTitle(messageBean.getTitle());
                        messageBean.setEventStr(Const.refresh);
                        EventBus.getDefault().post(messageBean);
                    } else {
                        if( StringUtil.NoNullOrEmpty(messageBean.getStatus()) && Integer.valueOf(messageBean.getStatus()) > -1){
                            //司机收到订单
                        }else {
                            EventBus.getDefault().post(new RefreshMsgBean(Const.refresh));
                        }
                    }
                    break;
            }
        }
    }

    /**
     * 通知点击回调
     *
     * @param context
     * @param xgPushClickedResult
     */
    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {
        Log.e(getClass().getName(), "message=" + xgPushClickedResult.toString());
        // APP自主处理消息的过程...
        MessageBean messageBean = new Gson().fromJson(xgPushClickedResult.getCustomContent(), MessageBean.class);
        Log.e("push", "messageBean=" + messageBean.toString());
        String type = messageBean.getType();
        if (StringUtil.NoNullOrEmpty(type)){
            switch (type){
                case  "1"://订单
                    if( StringUtil.NoNullOrEmpty(messageBean.getStatus()) && Integer.valueOf(messageBean.getStatus()) > -1){//司机收到订单
                        //跳转司机中心页面
                        context.startActivity(new Intent(context,DriverCenterActivity.class));

                    }else{
                        //跳转消息中心页面
                        String id = messageBean.getTagId();
                        if (StringUtil.NoNullOrEmpty(id)){
                            String messageType = messageBean.getMessageType();
                            if (StringUtil.NoNullOrEmpty(messageType)){
                                Intent intent = null;
                                switch (messageType) {
                                    case "1": //推单消息
                                        intent = new Intent(context,OrderAddMaintainActivity.class);
                                        intent.putExtra(Const.intentTag, id);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        break;
                                    case "2"://系统消息
                                        intent = new Intent(context, UserXieYiActivity.class);
                                        intent.putExtra("from", "message");
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra(Const.intentTag, id);
                                        break;
                                    case "3"://增项订单消息
                                        intent = new Intent(context, MessageSpecialActivity.class);
                                        intent.putExtra(Const.intentTag, id);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra("from", "message");
                                        break;
                                }
                                if (intent!=null){
                                    context.startActivity(intent);
                                }
                            }
                        }
                    }
                    break;
                case "2"://消息体

                    break;
            }
        }
    }

    /**
     * 收到通知内容
     *
     * @param context
     * @param message
     */
    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult message) {
        Log.e(getClass().getName(), "message=" + message.toString());
        // APP自主处理消息的过程...
        MessageBean messageBean = new Gson().fromJson(message.getCustomContent(), MessageBean.class);
        Log.e("push", "messageBean=" + messageBean.toString());

        if (messageBean.getType().equals("2")) {//消息体
            if (messageBean.getIsitnew().equals("1")) {//需要新增消息
                messageBean.setTitle(message.getTitle());
                messageBean.setEventStr(Const.refresh);
                EventBus.getDefault().post(messageBean);
            } else {
                if( StringUtil.NoNullOrEmpty(messageBean.getStatus()) && Integer.valueOf(messageBean.getStatus()) > -1){
                    //司机收到订单
                }else {
                    EventBus.getDefault().post(new RefreshMsgBean(Const.refresh));
                }
            }
        } else {
            EventBus.getDefault().post(new RefreshOrderBean(Const.refresh));
        }

    }

    public void setSound(Context context) {
        XGCustomPushNotificationBuilder build = new XGCustomPushNotificationBuilder();
        build.setSound(
                RingtoneManager.getActualDefaultRingtoneUri(
                        context, RingtoneManager.TYPE_ALARM)) // 设置声音
                .setSound(
                        Uri.parse("android.resource://" + context.getPackageName()
                                + "/" + R.raw.order_music)) //设定Raw下指定声音文件
                .setDefaults(Notification.DEFAULT_VIBRATE) // 响铃
                .setFlags(Notification.FLAG_NO_CLEAR); // 是否可清除
    }
}
