package com.dream.moka.Adapter.Main;

import android.app.Activity;

import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Bean.HomeGvBean;
import com.dream.moka.R;

import java.util.List;

public class HomeGvAdapter extends CommonAdapter<HomeGvBean> {

        public HomeGvAdapter(Activity context, List<HomeGvBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, HomeGvBean dataBean, int position) {
            holder.setImageResource(R.id.img_iv, dataBean.getResId());
            holder.setText(R.id.content_tv, dataBean.getName());
        }

    }