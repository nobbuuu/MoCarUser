package com.dream.moka.Adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.R;
import com.dream.moka.Utils.DensityUtil;

import java.util.List;

public class DescribpicAdapter extends CommonAdapter<String> {
    private int itemWith = DensityUtil.getScreenWidth();
    private int mWidth = (itemWith - DensityUtil.dip2px(MyApplication.getInstance(), 54)) / 4;

    public DescribpicAdapter(Activity context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(BaseViewHolder holder, String s, int position) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mWidth, mWidth);
        ImageView only_iv = holder.getView(R.id.only_iv);
        only_iv.setLayoutParams(params);
        holder.setImageByUrl(R.id.only_iv, Const.BannerUrl+s,false);

        holder.getConvertView().setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
            }
        });
    }
}