package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.PicUploadResultBean;
import com.dream.moka.Contract.UserInfoContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Utils.MyUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/13 0013.
 */

public class UserInfoPresenter extends RxPresenter<UserInfoContract> {
    private ApiService mApiService;

    @Inject
    public UserInfoPresenter(ApiService apiService) {
        mApiService = apiService;
    }
    public void saveInfo(Map map){
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.onSuccess();
            }
        };
        addObserver(mApiService.updateUser(map),baseObserver);

    }
    /**
     * 上传图片
     *
     */
    public void uploadPic(final String path) {
        File file = new File(path);
        BaseObserver<PicUploadResultBean> baseObserver=new BaseObserver<PicUploadResultBean>(mView) {
            @Override
            public void onSuccess(PicUploadResultBean results) {
                if (results!=null){
                    String filePath = results.getFilePath();
                    mView.onPicSuccess(filePath);
                    Map<String,String> map=new HashMap<>();
                    map.put("token", CommonAction.getToken());
                    map.put("photo",filePath);
                    saveInfo(map);

                }
            }
        };
        addObserver(mApiService.uploadImg(MyUtils.getBody("user"),MyUtils.getRequestBodyParts("file",file)),baseObserver);

    }
}
