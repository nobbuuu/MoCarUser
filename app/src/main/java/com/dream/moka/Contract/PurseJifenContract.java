package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.IntegralBean;
import com.dream.moka.Bean.MyAccountBean;
import com.dream.moka.Bean.WalletBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public interface PurseJifenContract extends BaseContract {

    void getAccount(WalletBean walletBean);
    void getQianBaoData(MyAccountBean results,boolean isMore);
    void isLoadAll(boolean isAll);
    void showScoreList(List<IntegralBean> dataList);
}
