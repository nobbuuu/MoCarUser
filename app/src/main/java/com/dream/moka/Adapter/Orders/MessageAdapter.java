package com.dream.moka.Adapter.Orders;

import android.app.Activity;
import android.view.View;

import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.Message.MessageListBean;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.Message.MessageSpecialActivity;
import com.dream.moka.UI.Activity.Message.OrderAddMaintainActivity;
import com.dream.moka.UI.Activity.set.UserXieYiActivity;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.StringUtil;

import java.util.List;

public class MessageAdapter extends RVBaseAdapter<MessageListBean.ListBean> {

        public MessageAdapter(Activity context, List<MessageListBean.ListBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final MessageListBean.ListBean testBaseBean, final int position) {
            String status = testBaseBean.getStatus();
            if (StringUtil.NoNullOrEmpty(status)){
                View haveMsg_v = holder.getView(R.id.haveMsg_v);
                if (status.equals("0")){//消息未读
                    haveMsg_v.setBackgroundResource(R.drawable.shape_circle_blue);
                }else {
                    haveMsg_v.setBackgroundResource(R.drawable.shape_circle_white);
                }
            }
            final String messageType = testBaseBean.getMessageType();
            switch (messageType){
                case "1"://推单消息
                    holder.setText(R.id.msgType_tv,"推单消息");
                    break;
                case "2"://系统消息
                    holder.setText(R.id.msgType_tv,"系统消息");
                    break;
                case "3"://增项订单消息
                    holder.setText(R.id.msgType_tv,"增项推单消息");
                    break;
            }
            holder.setText(R.id.msgContent_tv,testBaseBean.getMessageTitle());
            holder.setText(R.id.time_tv, DateFormatUtil.getTime(testBaseBean.getCreateDate(), Const.YMD_HMS,Const.YMD_HM));
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    String id = testBaseBean.getId();
                    if (StringUtil.NoNullOrEmpty(id)){
                        switch (messageType) {
                            case "1": //推单消息
                                IntentUtils.toActivityWithTag(OrderAddMaintainActivity.class,mActivity,id);
                                break;
                            case "2"://系统消息
                                UserXieYiActivity.openAct(mActivity,"message", id);
                                break;
                            case "3"://增项订单消息
                                MessageSpecialActivity.openAct(mActivity,id,"message");
                                break;
                        }
                    }
                }
            });

        }
    }