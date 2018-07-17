package com.dream.moka.CumstomView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonContextAdapter;
import com.dream.moka.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class CarNoFirstKeyboad extends LinearLayout {

    @Bind(R.id.carNoFirst_gv)
    GridView mCarNoFirstGv;

    private Context mContext;
    private TextAdapter mTextAdapter;
    private String[] topTexts = new String[]{"京", "沪", "浙", "苏","粤", "鲁", "晋","冀", "豫", "川","渝", "辽", "吉", "黑", "皖", "鄂", "湘", "赣", "闽", "陕", "甘", "宁","蒙", "津", "贵", "云", "桂", "琼", "青", "新", "藏", "港", "澳"};
    private List<String> topList = new ArrayList<>();

    public CarNoFirstKeyboad(Context context) {
        super(context);
        init(context);
    }

    public CarNoFirstKeyboad(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View view = inflate(context, R.layout.view_carno_first, this);
        ButterKnife.bind(view, this);
        initData();
        initEvent();
    }


    private void initData() {
        topList.clear();
        for (int i = 0; i < topTexts.length; i++) {
            topList.add(topTexts[i]);
        }
        mTextAdapter = new TextAdapter(mContext, topList, R.layout.rvitem_onlytext_carboard);
        mCarNoFirstGv.setAdapter(mTextAdapter);
    }

    private void initEvent() {
        mCarNoFirstGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tempStr = topList.get(position);
                mKeyListener.onClick(2, tempStr);
            }
        });
    }

    @OnClick({R.id.cancle_relay})
    public void onViewClicked(View view) {
        mKeyListener.onClick(1, "");
    }

    public interface KeyListener {
        void onClick(int type, String inputStr);
    }

    private KeyListener mKeyListener;

    public void onKeyboardListener(KeyListener mkeyListener) {
        this.mKeyListener = mkeyListener;
    }

    private class TextAdapter extends CommonContextAdapter<String> {

        public TextAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, String dataBean, int position) {
            TextView tv = holder.getView(R.id.only_tv);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
            tv.setText(dataBean);
        }
    }
}
