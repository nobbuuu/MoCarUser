package com.dream.moka.IM;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.dream.moka.Bean.Online.OnlinerBean;
import com.dream.moka.IM.im.activity.ChatActivity;
import com.dream.moka.IM.im.bean.Message;
import com.dream.moka.IM.im.bean.MessageFactory;
import com.dream.moka.IM.im.event.MessageEvent;
import com.dream.moka.IM.im.ui.CustomMessage;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.R;
import com.dream.moka.Utils.AppManager;
import com.google.gson.Gson;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMGroupReceiveMessageOpt;
import com.tencent.imsdk.TIMMessage;

import java.util.Observable;
import java.util.Observer;

/**
 * 在线消息通知展示
 */
public class PushUtil implements Observer {

    private static final String TAG = PushUtil.class.getSimpleName();

    private static int pushNum=0;

    private final int pushId=1;

    private static PushUtil instance = new PushUtil();

    private PushUtil() {
        MessageEvent.getInstance().addObserver(this);
    }

    public static PushUtil getInstance(){
        return instance;
    }



    private void PushNotify(TIMMessage msg){
        //系统消息，自己发的消息，程序在前台的时候不通知
        if (msg==null||AppManager.getAppManager().isInStack(ChatActivity.class)||
                (msg.getConversation().getType()!= TIMConversationType.Group&&
                        msg.getConversation().getType()!= TIMConversationType.C2C)||
                msg.isSelf()||
                msg.getRecvFlag() == TIMGroupReceiveMessageOpt.ReceiveNotNotify ||
                MessageFactory.getMessage(msg) instanceof CustomMessage) return;

        String senderStr,contentStr;
        Message message = MessageFactory.getMessage(msg);
        if (message == null) return;
        senderStr = message.getSender();
        contentStr = message.getSummary();
        Log.e(TAG, "recv senderStr " + senderStr);
        Log.e(TAG, "recv contentStr " + contentStr);
        NotificationManager mNotificationManager = (NotificationManager) MyApplication.getInstance().getSystemService(MyApplication.getInstance().NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MyApplication.getInstance());
        Intent notificationIntent = new Intent(MyApplication.getInstance(), ChatActivity.class);
        notificationIntent.putExtra("identify",senderStr);
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(MyApplication.getInstance(), 0, notificationIntent, 0);
        mBuilder
//                .setContentTitle(senderStr)//设置通知栏标题
                .setContentText(contentStr)
                .setContentIntent(intent) //设置通知栏点击意图
//                .setNumber(++pushNum) //设置通知集合的数量
                .setTicker(senderStr+":"+contentStr) //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setDefaults(Notification.DEFAULT_ALL)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                .setSmallIcon(R.mipmap.logo);//设置通知小ICON
        Notification notify = mBuilder.build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(pushId, notify);
    }

    public static void resetPushNum(){
        pushNum=0;
    }

    public void reset(){
        NotificationManager notificationManager = (NotificationManager)MyApplication.getInstance().getSystemService(MyApplication.getInstance().NOTIFICATION_SERVICE);
        notificationManager.cancel(pushId);
    }

    /**
     * This method is called if the specified {@code Observable} object's
     * {@code notifyObservers} method is called (because the {@code Observable}
     * object has been updated.
     *
     * @param observable the {@link Observable} object.
     * @param data       the data passed to {@link Observable#notifyObservers(Object)}.
     */
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof MessageEvent){
            if (data instanceof TIMMessage) {
                TIMMessage msg = (TIMMessage) data;
                if (msg != null){
                    PushNotify(msg);
                }
            }
        }
    }
}
