package com.dream.moka.UI.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.MultiLayoutsBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.ChatBean;
import com.dream.moka.Bean.Online.OnlinerBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.ChatContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.Online.ChatPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.BitmapUtils;
import com.dream.moka.Utils.IMUtil.EmoticonUtil;
import com.dream.moka.Utils.IMUtil.FileUtil;
import com.dream.moka.Utils.IMUtil.IMAcountUtils;
import com.dream.moka.Utils.IMUtil.RecorderUtil;
import com.dream.moka.Utils.IMUtil.View.VoiceSendingView;
import com.dream.moka.Utils.ImmUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;
import com.tencent.imcore.SoundElem;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMFaceElem;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMSoundElem;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.message.TIMConversationExt;
import com.tencent.imsdk.ext.message.TIMMessageLocator;
import com.tencent.imsdk.ext.message.TIMMessageRevokedListener;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class ChatMyActivity extends BaseCommonActivity implements ChatContract.View, TIMMessageListener, TIMMessageRevokedListener {

    @Bind(R.id.chat_rv)
    RecyclerView chat_rv;
    @Bind(R.id.botfile_lay)
    LinearLayout botfile_lay;

    @Inject
    ChatPresenter mChatPresenter;
    @Bind(R.id.addfile_iv)
    ImageView mAddfileIv;
    @Bind(R.id.voice_iv)
    ImageView voice_iv;
    @Bind(R.id.send_tv)
    TextView mSendTv;
    @Bind(R.id.picture_iv)
    ImageView mPictureIv;
    @Bind(R.id.camare_iv)
    ImageView mCamareIv;
    @Bind(R.id.input_edt)
    EditText mInputEdt;
    @Bind(R.id.input_tv)
    TextView mInputTv;
    @Bind(R.id.voice_sending)
    VoiceSendingView voiceSendingView;

    private List<ChatBean> dataList = new ArrayList<>();
    private ChatAdpter chatAdpter;


    //IM
    private TIMMessage message;
    private TIMConversation mConversation;
    private RecorderUtil recorder = new RecorderUtil();

    @Override
    public int getLayoutId() {
        return R.layout.activity_chatmy;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mChatPresenter.attachView(this);
        message = new TIMMessage();
        RvUtils.setOptionnoLine(chat_rv, this);
        chatAdpter = new ChatAdpter(this, dataList, new int[]{R.layout.chatitem_leftcommom, R.layout.chatitem_leftorder,
                R.layout.chatitem_leftevalue, R.layout.chatitem_right, R.layout.chatitem_rightimg,R.layout.rvitem_chat_leftvoice,R.layout.rvitem_chat_rightvoice});
        chat_rv.setAdapter(chatAdpter);
        //注册消息监听器
        TIMManager.getInstance().addMessageListener(this);
    }

    @Override
    public String initTitleText() {
        return "专家在线";
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
        IMAcountUtils.loginSig(CommonAction.getUserId(), CommonAction.getUserSign());
    }

    @Override
    public void initDatas() {
        for (int i = 0; i < 1; i++) {
            ChatBean bean = new ChatBean();
            bean.setContent("您好，有什么可以帮助到您?");
            bean.setType("0");
            dataList.add(bean);
        }
        chatAdpter.notifyDataSetChanged();
        String carId = getIntent().getStringExtra("carId");
        String type = getIntent().getStringExtra("type");
        if (StringUtil.NoNullOrEmpty(carId)) {
            mChatPresenter.getOnliner(carId, type);
        }

    }

    @Override
    public void eventListener() {
        right_tv.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                IntentUtils.call_Dall(Const.Phone, mActivity);
            }
        });

        //发送消息
        mSendTv.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                final String inputStr = mInputEdt.getText().toString();
                if (StringUtil.NoNullOrEmpty(inputStr)) {
                    getTextMessage(mInputEdt.getText());
                    sendMessage(inputStr);
                }
            }
        });

        mInputEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    botfile_lay.setVisibility(View.GONE);
                    isOpen = false;
                }
            }
        });

        mInputTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isHoldVoiceBtn = true;
                        updateVoiceView();
                        break;
                    case MotionEvent.ACTION_UP:
                        isHoldVoiceBtn = false;
                        updateVoiceView();
                        break;
                }
                return true;
            }
        });
    }

    private void sendMessage(final String inputStr) {
        if (mConversation != null) {
            mConversation.sendMessage(message, new TIMValueCallBack<TIMMessage>() {
                @Override
                public void onError(int code, String desc) {//发送消息失败
                    //错误码code和错误描述desc，可用于定位请求失败原因
                    //错误码code含义请参见错误码表
                    logFailMsg("tag", "code=" + code, "desc=" + desc);
                }

                @Override
                public void onSuccess(TIMMessage msg) {
                    //发送消息成功,消息状态已在sdk中修改，此时只需更新界面
                    Log.e("tag", "发送成功!");
                    ChatBean bean = new ChatBean();
                    bean.setType("3");
                    bean.setContent(inputStr);
                    dataList.add(bean);
                    chatAdpter.notifyDataSetChanged();
                    mInputEdt.setText("");
                    ImmUtils.hideImm(mActivity);
                    chat_rv.smoothScrollToPosition(dataList.size());
                }
            });
            //message对象为发送中状态
        }
    }

    private void logFailMsg(String tag, String msg, String msg2) {
        Log.e(tag, msg);
        Log.e(tag, msg2);
    }

    private void updateVoiceView(){
        if (isHoldVoiceBtn){
            mInputTv.setText("松开 结束");
            mInputTv.setBackgroundResource(R.drawable.shape_stroke_voice_pre);
            startSendVoice();
        }else{
            mInputTv.setText("按住 说话");
            mInputTv.setBackgroundResource(R.drawable.shape_stroke_voice);
            endSendVoice();
        }
    }

    /**
     * 开始发送语音消息
     */
    public void startSendVoice() {
        voiceSendingView.setVisibility(View.VISIBLE);
        voiceSendingView.showRecording();
        recorder.startRecording();

    }

    /**
     * 结束发送语音消息
     */
    public void endSendVoice() {
        voiceSendingView.release();
        voiceSendingView.setVisibility(View.GONE);
        recorder.stopRecording();
        if (recorder.getTimeInterval() < 1) {
            ToastUtils.Toast_short("录音过短，请重试");
        } else if (recorder.getTimeInterval() > 60) {
            ToastUtils.Toast_short("录音过长，请重试");
        } else {
            message = new TIMMessage();
            TIMSoundElem elem = new TIMSoundElem();
            elem.setPath(recorder.getFilePath());
            elem.setDuration(recorder.getTimeInterval());  //填写语音时长
            message.addElement(elem);
            mConversation.sendMessage(message, new TIMValueCallBack<TIMMessage>() {
                @Override
                public void onError(int code, String desc) {//发送消息失败
                    //错误码code和错误描述desc，可用于定位请求失败原因
                    //错误码code含义请参见错误码表
                    logFailMsg("tag",code+"",desc);
                }

                @Override
                public void onSuccess(TIMMessage msg) {
                    //发送消息成功,消息状态已在sdk中修改，此时只需更新界面
                    Log.e("tag","语音发送成功");
                }
            });
        }
    }

    private boolean isOpen;
    private boolean isVoice;
    private boolean isHoldVoiceBtn;

    @OnClick({R.id.back_relay, R.id.addfile_iv, R.id.picture_iv, R.id.camare_iv, R.id.voice_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.addfile_iv:
                if (isOpen) {
                    botfile_lay.setVisibility(View.GONE);
                    isOpen = false;
                } else {
                    getCurrentFocus().clearFocus();
                    ImmUtils.hideImm(mActivity);
                    botfile_lay.setVisibility(View.VISIBLE);
                    isOpen = true;
                }
                break;
            case R.id.picture_iv://相册
                IntentUtils.toPicture(mActivity);
                break;
            case R.id.camare_iv://拍照
                IntentUtils.toCamare(mActivity);
                break;
            case R.id.voice_iv:
                if (isVoice) {
                    voice_iv.setBackgroundResource(R.drawable.ic_voice_input);
                    mInputEdt.setVisibility(View.VISIBLE);
                    mInputTv.setVisibility(View.GONE);
                    mInputEdt.requestFocus();
                    ImmUtils.showImm(mActivity, mInputEdt);
                    isVoice = false;
                } else {
                    voice_iv.setBackgroundResource(R.drawable.ic_keyboard_input);
                    mInputEdt.setVisibility(View.GONE);
                    mInputTv.setVisibility(View.VISIBLE);
                    ImmUtils.hideImm(mActivity);
                    isVoice = true;
                }
                break;
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    private OnlinerBean tempOnlineBean;

    @Override
    public void showOnlinerData(OnlinerBean dataBean) {

        tempOnlineBean = dataBean;
//        String userId = dataBean.getId();
//        String userId = "0f028d3ad6714d56b7adf32160141084";//铨兄
//        String userId = "997eec4aab534ff2a6041d00c4012c7e";//王勇
        String userId = "2822dc9782b345c597fcbd5ca5173284";//张雷技师
//        String userId = "aa99e5c9a9cb489fb784471c231d67e3";//张雷用户端
//        String userId = "d11a589d81d643b48969fa01371f7118";//条子
        String id = CommonAction.getUserId();
        Log.e("tag", "id=" + id);
        //TIMConversationType.C2C：单聊模式
        mConversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, userId);

        TIMConversationExt timConversationExt = new TIMConversationExt(mConversation);
        timConversationExt.getMessage(20, message, new TIMValueCallBack<List<TIMMessage>>() {
            @Override
            public void onError(int i, String s) {
                logFailMsg(getLocalClassName(), "errorCode" + i, "get message error" + s);
            }

            @Override
            public void onSuccess(List<TIMMessage> timMessages) {
                Log.e("tag", "timMessages.size()=" + timMessages.size());
                if (timMessages.size() > 0) {
                    Collections.reverse(timMessages);
                    onNewMessages(timMessages);
                }
            }
        });
    }


    //显示消息
    @Override
    public boolean onNewMessages(List<TIMMessage> list) {
        for (int i = 0; i < list.size(); i++) {
            TIMMessage timMessage = list.get(i);
            if (timMessage != null) {
                long elementCount = timMessage.getElementCount();
                if (elementCount > 0) {
                    TIMElem elem = timMessage.getElement((int) (elementCount - 1));
                    ChatBean bean = new ChatBean();
                    switch (elem.getType()){
                        case Text://文字
                            TIMTextElem textElem = (TIMTextElem) elem;
                            String peer = timMessage.getConversation().getPeer();
                            Log.e("tag", "peer=" + peer);
                            if (timMessage.isSelf()) {
                                bean.setType("3");
                            } else {
                                bean.setType("0");
                            }
                            bean.setContent(textElem.getText());
                            Log.e("tag", "content=" + textElem.getText());
                            dataList.add(bean);
                            break;
                        case Sound://语音
                            TIMSoundElem soundElem = (TIMSoundElem) elem;
                            if (timMessage.isSelf()){
                                bean.setType("6");
                            }else {
                                bean.setType("5");
                            }
                            bean.setVoiceDuration(soundElem.getDuration()+"'");
                            bean.setVoicePath(soundElem.getPath());
                            dataList.add(bean);
                            break;
                    }
                }
            }
        }
        chatAdpter.notifyDataSetChanged();
        chat_rv.smoothScrollToPosition(dataList.size());
        return false;
    }

    @Override
    public void onMessageRevoked(TIMMessageLocator timMessageLocator) {

    }


    private class ChatAdpter extends MultiLayoutsBaseAdapter<ChatBean> {

        public ChatAdpter(Activity context, List<ChatBean> data, int[] layoutIds) {
            super(context, data, layoutIds);
        }

        @Override
        public int getItemType(int position) {
            int type = 0;
            String content_type = dataList.get(position).getType();
            if (content_type.equals("img")) {
                type = 4;
            } else {
                switch (content_type) {
                    case "0":
                        type = 0;
                        break;
                    case "3":
                        type = 3;
                        break;
                    case "5":
                        type = 5;
                        break;
                    case "6":
                        type = 6;
                        break;
                }
               /* switch (position) {
                    case 0:
                        type = 0;
                        break;
                    case 1:
                        type = 1;
                        break;
                    case 2:
                        type = 2;
                        break;
                    case 3:
                        type = 3;
                        break;
                    case 4:
                        type = 0;
                        break;
                }*/
            }

            return type;
        }

        @Override
        public void onBinds(RVBaseHolder holder, ChatBean baseBean, int position, int itemType) {
            if (baseBean.getType().equals("img") && baseBean.getBitmap() != null) {
                holder.setImageBitmap(R.id.upfile_iv, baseBean.getBitmap());
            }

            String content = baseBean.getContent();
            switch (itemType) {
                case 0:
                    if (StringUtil.NoNullOrEmpty(content)) {
                        holder.setText(R.id.lcontent_tv, content);
                    }
                    if (tempOnlineBean != null) {
                        holder.setText(R.id.leftName_tv, tempOnlineBean.getCode());
                    }
                    break;
                case 3:
                    if (StringUtil.NoNullOrEmpty(content)) {
                        holder.setText(R.id.rcontent_tv, content);
                    }
                    holder.setText(R.id.rname_tv, CommonAction.getUserName());
                    break;
                case 5:
                    holder.setText(R.id.leftv_tv,baseBean.getVoiceDuration());
                    holder.setOnClickListener(R.id.leftv_voice, new NoDoubleClickListener() {
                        @Override
                        public void onNoDoubleClick(View view) {
                            final File tempAudio = FileUtil.getTempFile(FileUtil.FileType.AUDIO);
                        }
                    });
                    break;
                case 6:

                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Const.CAMERA) {
            IntentUtils.toCamare(mActivity);
        } else {
            IntentUtils.toPicture(mActivity);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.CAMERA && data != null) {
            upBitmap(data, Const.CAMERA);
        }

        if (requestCode == Const.PICTURE && data != null) {
            upBitmap(data, Const.PICTURE);
        }
    }

    private void upBitmap(Intent data, int method) {
        Map<Bitmap, File> map = BitmapUtils.parseData(data, method, mActivity);
        for (Map.Entry<Bitmap, File> mFile : map.entrySet()) {
            Bitmap bitmap = mFile.getKey();
            if (bitmap != null) {
                ChatBean bean = new ChatBean();
                bean.setType("img");
                bean.setBitmap(bitmap);
                dataList.add(bean);
                chatAdpter.notifyDataSetChanged();
                chat_rv.smoothScrollToPosition(dataList.size());
            }
        }
    }

    public void getTextMessage(Editable s) {
        ImageSpan[] spans = s.getSpans(0, s.length(), ImageSpan.class);
        List<ImageSpan> listSpans = sortByIndex(s, spans);
        int currentIndex = 0;
        for (ImageSpan span : listSpans) {
            int startIndex = s.getSpanStart(span);
            int endIndex = s.getSpanEnd(span);
            if (currentIndex < startIndex) {
                TIMTextElem textElem = new TIMTextElem();
                textElem.setText(s.subSequence(currentIndex, startIndex).toString());
                message.addElement(textElem);
            }
            TIMFaceElem faceElem = new TIMFaceElem();
            int index = Integer.parseInt(s.subSequence(startIndex, endIndex).toString());
            faceElem.setIndex(index);
            if (index < EmoticonUtil.emoticonData.length) {
                faceElem.setData(EmoticonUtil.emoticonData[index].getBytes(Charset.forName("UTF-8")));
            }
            message.addElement(faceElem);
            currentIndex = endIndex;
        }
        if (currentIndex < s.length()) {
            TIMTextElem textElem = new TIMTextElem();
            textElem.setText(s.subSequence(currentIndex, s.length()).toString());
            message.addElement(textElem);
        }
    }

    private List<ImageSpan> sortByIndex(final Editable editInput, ImageSpan[] array) {
        ArrayList<ImageSpan> sortList = new ArrayList<>();
        for (ImageSpan span : array) {
            sortList.add(span);
        }
        Collections.sort(sortList, new Comparator<ImageSpan>() {
            @Override
            public int compare(ImageSpan lhs, ImageSpan rhs) {
                return editInput.getSpanStart(lhs) - editInput.getSpanStart(rhs);
            }
        });

        return sortList;
    }

}
