package com.dream.moka.Adapter.Main;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.HotActivityBean;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.WebActivity;
import com.dream.moka.UI.Activity.other.WebViewActivity;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.StringUtil;

import java.util.List;

public class HothdAdapter extends RVBaseAdapter<HotActivityBean> {

        public HothdAdapter(Activity context, List<HotActivityBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(final RVBaseHolder holder, final HotActivityBean hotActivityBean, int position) {
            String pic = hotActivityBean.getPic();
            ImageView imageView = holder.getView(R.id.img);
            GlidUtils.LoadImgForUrl(mActivity, Const.BannerUrl + pic, imageView, R.drawable.banner_zhanweitu_9);
            String title = hotActivityBean.getTitle();
            holder.setText(R.id.title, title);
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {

                    if (hotActivityBean.getType()==0){
                        if (StringUtil.NoNullOrEmpty(hotActivityBean.getLinkAddr())){
                            IntentUtils.toActivityWithUrl(WebActivity.class, mActivity, hotActivityBean.getLinkAddr(), "活动详情");
                        }
                    }else if (StringUtil.NoNullOrEmpty(hotActivityBean.getId())){
                        IntentUtils.toActivityWithUrl(WebViewActivity.class,mActivity,hotActivityBean.getId(),"2");
                    }
                }
            });
        }
    }