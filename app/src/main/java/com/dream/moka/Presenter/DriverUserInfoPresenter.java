package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.PicUploadResultBean;
import com.dream.moka.Contract.DriverUserInfContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Utils.MyUtils;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class DriverUserInfoPresenter extends RxPresenter<DriverUserInfContract> {
    private ApiService mApiService;

    @Inject
    public DriverUserInfoPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 修改头像
     *
     * @param driverId
     */
    public void saveHeadImg(String driverId, String path, final String showpath) {
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.onSuccess(showpath);
            }
        };
        addObserver(mApiService.updateDriver(token,driverId,path),baseObserver);

    }

    /**
     * 上传图片
     *
     * @param driverId
     */
    public void uploadPic(final String driverId, final String path) {
        File file = new File(path);
        BaseObserver<PicUploadResultBean> baseObserver=new BaseObserver<PicUploadResultBean>(mView) {
            @Override
            public void onSuccess(PicUploadResultBean results) {
                if (results!=null){
                    String filePath = results.getFilePath();
                    saveHeadImg(driverId,filePath,path);
                }
            }
        };
        addObserver(mApiService.uploadImg(MyUtils.getBody("driver"),MyUtils.getRequestBodyParts("file",file)),baseObserver);

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
                    mView.onSuccess(filePath);
                }
            }
        };
        addObserver(mApiService.uploadImg(MyUtils.getBody("driver"),MyUtils.getRequestBodyParts("file",file)),baseObserver);
    }
    /**
     * 上传图片
     *
     */
    public void uploadPicforType(final String path,String type) {
        File file = new File(path);
        BaseObserver<PicUploadResultBean> baseObserver=new BaseObserver<PicUploadResultBean>(mView) {
            @Override
            public void onSuccess(PicUploadResultBean results) {
                if (results!=null){
                    String filePath = results.getFilePath();
                    mView.onSuccess(filePath);
                }
            }
        };
        addObserver(mApiService.uploadImg(MyUtils.getBody(type),MyUtils.getRequestBodyParts("file",file)),baseObserver);
    }
}
