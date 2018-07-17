package com.dream.moka.Adapter.Orders;

import android.app.Activity;
import android.view.View;

import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.TestBaseBean;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.UI.Activity.Message.MessageOrderCompleteActivity;
import com.dream.moka.Utils.IntentUtils;

import java.util.List;

public class MessageDriverAdapter extends RVBaseAdapter<TestBaseBean> {

        public MessageDriverAdapter(Activity context, List<TestBaseBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, TestBaseBean testBaseBean, final int position) {
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    IntentUtils.toActivity(MessageOrderCompleteActivity.class, mActivity);
                }
            });

        }
    }