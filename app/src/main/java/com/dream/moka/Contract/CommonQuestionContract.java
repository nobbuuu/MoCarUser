package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.NomalQuestionBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public interface CommonQuestionContract extends BaseContract {
    void setQuestionData(List<NomalQuestionBean> list);
}
