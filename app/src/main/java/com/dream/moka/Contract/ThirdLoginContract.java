package com.dream.moka.Contract;




import com.dream.moka.Base.BasePVContract;

import cn.sharesdk.framework.Platform;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface ThirdLoginContract {

    interface View extends BasePVContract.BaseView {

        void authorizeComplete(String openID, String userIcon, String nickName, String unionId,String typeName);
        void showThirdLoginErro(Platform platform, int i, Throwable throwable);
        void showThirdLoginCancle(Platform platform, int i);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void toAuthorize(String platName);
    }
}
