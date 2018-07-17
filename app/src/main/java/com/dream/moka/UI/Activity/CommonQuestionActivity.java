package com.dream.moka.UI.Activity;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.NomalQuestionBean;
import com.dream.moka.Common.AnimaCommonUtil;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.CommonQuestionContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Presenter.CommonQuestionPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.RvUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
public class CommonQuestionActivity extends BaseCommonActivity implements CommonQuestionContract{

    @Inject
    CommonQuestionPresenter mCommonQuestionPresenter;
    private QuestionAdapter mAdapter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;

    private List<NomalQuestionBean> listData = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_commonrv;
    }

    @Override
    public void initView() {
        mCommonQuestionPresenter.attachView(this);
        RvUtils.setOptionnoLine(common_rv,mActivity);
        common_rv.setBackgroundColor(ResourcesUtils.getColor(R.color.white));
        mAdapter = new QuestionAdapter(this,listData,R.layout.rvitem_questionone);
        common_rv.setAdapter(mAdapter);
    }

    @Override
    public String initTitleText() {
        return "常见问题";
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

    }

    @Override
    public void initDatas() {
        mCommonQuestionPresenter.getQuesitionData();
       /* listData.clear();
        for (int i = 0; i <2 ; i++) {
            QuestionBean bean = new QuestionBean();
            bean.setQuestion("我是问题"+(i+1));
            listData.add(bean);
        }
        mAdapter.notifyDataSetChanged();*/

    }

    @Override
    public void eventListener() {

    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void setQuestionData(List<NomalQuestionBean> list) {
        listData.clear();
        listData.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    private class QuestionAdapter extends RVBaseAdapter<NomalQuestionBean>{

        public QuestionAdapter(Activity context, List<NomalQuestionBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, NomalQuestionBean questionBean, int position) {
            NomalQuestionBean.QuestionTypeBean questionType = questionBean.getQuestionType();
            if (questionType!=null){
                holder.setText(R.id.quesOneName_tv, questionType.getName());
            }
            List<NomalQuestionBean.QuestionBean> questionBeans = questionBean.getQuestion();
            final RecyclerView quesTwoRv = holder.getView(R.id.quesTwo_rv);
            final ImageView quesone_riv = holder.getView(R.id.quesone_riv);
            final boolean[] isExpand = {true};
            if (questionBeans.size()>0){
                showDataAnim(!isExpand[0],quesone_riv,quesTwoRv);
            }
            RvUtils.setOption_noparam(quesTwoRv,mActivity);
            quesTwoRv.setAdapter(new QuestionTwoAdapter(mActivity,questionBeans,R.layout.rvitem_quesanswer));
            holder.setOnClickListener(R.id.quesOne_relay, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isExpand[0]){
                        showDataAnim(isExpand[0],quesone_riv,quesTwoRv);
                        isExpand[0] = false;
                    }else {
                        showDataAnim(isExpand[0],quesone_riv,quesTwoRv);
                        isExpand[0] = true;
                    }
                }
            });
        }
    }

    @OnClick({R.id.back_relay})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_relay:
                finish();
                break;
        }
    }

    private void showDataAnim(boolean isExpand,ImageView riv,RecyclerView rv) {
        if (!isExpand){
            rv.setVisibility(View.VISIBLE);
            riv.startAnimation(AnimaCommonUtil.getShunRotate());
        }else {
            rv.setVisibility(View.GONE);
            riv.startAnimation(AnimaCommonUtil.getNiRotate());
        }
    }

    private class QuestionTwoAdapter extends RVBaseAdapter<NomalQuestionBean.QuestionBean>{

        public QuestionTwoAdapter(Activity context, List<NomalQuestionBean.QuestionBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, NomalQuestionBean.QuestionBean questionBean, int position) {
            holder.setText(R.id.ques_tv,questionBean.getQuestion());
            holder.setText(R.id.answer_tv,questionBean.getAnswer());
        }
    }
}
