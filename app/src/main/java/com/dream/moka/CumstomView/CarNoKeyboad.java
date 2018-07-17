package com.dream.moka.CumstomView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
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

public class CarNoKeyboad extends LinearLayout {

    @Bind(R.id.cancle_relay)
    RelativeLayout mCancleRelay;
    @Bind(R.id.topText_gv)
    GridView mTopTextGv;
    @Bind(R.id.albNum_gv)
    GridView mAlbNumGv;
    @Bind(R.id.zm_gv)
    GridView mZmGv;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.delete_iv)
    ImageView mDeleteIv;

    private EditText mEditText;
    private Context mContext;
    private TextAdapter mTextAdapter;
    private String[] topTexts = new String[]{"港", "澳", "学", "警", "领"};
    private String[] numTexts = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    private String[] zmTexts = new String[]{"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M"};
    private List<String> topList = new ArrayList<>();
    private List<String> numList = new ArrayList<>();
    private List<String> zmList = new ArrayList<>();

    private StringBuffer tempInputStr = new StringBuffer();
    public CarNoKeyboad(Context context,EditText mEditText) {
        super(context);
        this.mEditText = mEditText;
        init(context);
    }

    public CarNoKeyboad(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View view = inflate(context, R.layout.view_keyboard_carno, this);
        ButterKnife.bind(view, this);
        initData();
        initEvent();
    }


    private void initData() {
        topList.clear();
        numList.clear();
        zmList.clear();
        String s = mEditText.getText().toString();
        for (int i = 0; i < s.length(); i++) {
            tempInputStr.append(s.charAt(i));
        }
        for (int i = 0; i < topTexts.length; i++) {
            topList.add(topTexts[i]);
        }
        for (int i = 0; i < numTexts.length; i++) {
            numList.add(numTexts[i]);
        }
        for (int i = 0; i < zmTexts.length; i++) {
            zmList.add(zmTexts[i]);
        }
        mTextAdapter = new TextAdapter(mContext, topList, R.layout.rvitem_onlytext_carboard);
        mTopTextGv.setAdapter(mTextAdapter);
        mTextAdapter = new TextAdapter(mContext, numList, R.layout.rvitem_onlytext_carboard);
        mAlbNumGv.setAdapter(mTextAdapter);
        mTextAdapter = new TextAdapter(mContext, zmList, R.layout.rvitem_onlytext_carboard);
        mZmGv.setAdapter(mTextAdapter);
    }
    private void initEvent() {
        mTopTextGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tempInputStr.toString().length()<7){
                    tempInputStr.append(topList.get(position));
                    mKeyListener.onClick(4,tempInputStr.toString());
                }
            }
        });
        mAlbNumGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tempInputStr.toString().length()<7){
                    tempInputStr.append(numList.get(position));
                    mKeyListener.onClick(4,tempInputStr.toString());
                }
            }
        });
        mZmGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tempInputStr.toString().length()<7){
                    tempInputStr.append(zmList.get(position));
                    mKeyListener.onClick(4,tempInputStr.toString());
                }
            }
        });
    }

    @OnClick({R.id.cancle_relay, R.id.sure_tv, R.id.delete_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancle_relay:
                mKeyListener.onClick(1,tempInputStr.toString());
                break;
            case R.id.sure_tv:
                mKeyListener.onClick(2,tempInputStr.toString());
                break;
            case R.id.delete_iv:
                int index = mEditText.getSelectionStart();
                if(index>0){
                    tempInputStr = tempInputStr.delete(index-1, index);
                    mKeyListener.onClick(3,tempInputStr.toString());
                }
                break;
        }
    }
    public interface KeyListener {
        void onClick(int type,String inputStr);
    }
    private KeyListener mKeyListener;
    public void onKeyboardListener(KeyListener mkeyListener){
        this.mKeyListener = mkeyListener;
    }
    private class TextAdapter extends CommonContextAdapter<String> {

        public TextAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, String dataBean, int position) {
            TextView tv = holder.getView(R.id.only_tv);
            tv.setText(dataBean);
        }
    }
}
