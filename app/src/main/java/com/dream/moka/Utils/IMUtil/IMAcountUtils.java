package com.dream.moka.Utils.IMUtil;

import android.util.Log;

import com.dream.moka.IM.PushUtil;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.ToastUtils;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMManager;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/8 0008.
 */

public class IMAcountUtils {
    /**
     * 登陆IMSDK
     */
    public static void loginSig(String userId, final String userSig) {
        //发起登录请求
        TIMManager.getInstance().login(
                userId, //用户id
                userSig,//用户帐号签名，由私钥加密获得，具体请参考文档
                new TIMCallBack() {//回调接口
                    @Override
                    public void onSuccess() {//登录成功
//                        ToastUtil.showToast(context, "userSig登陆成功，用戶名：" + TIMManager.getInstance().getLoginUser());
                        Log.e(Const.IM,"LOGIN_onSuccess>>>"+"userSig登陆成功，用戶名：" + TIMManager.getInstance().getLoginUser());
//                        ToastUtils.Toast_short("userSig登陆成功，用戶名：" + TIMManager.getInstance().getLoginUser());
//                        AppManager.getAppManager().finishAllActivity();
                        //初始化程序后台后消息推送
                        PushUtil.getInstance();

                        TIMFriendshipManager.ModifyUserProfileParam param = new TIMFriendshipManager.ModifyUserProfileParam();
                        param.setSelfSignature("mocarUser");
                        param.setFaceUrl(CommonAction.getUserHeadUrl());
                        param.setNickname(CommonAction.getUserName());
                        //设置用户手机号
                        param.setLocation(CommonAction.getUserPhone());
                        TIMFriendshipManager.getInstance().modifyProfile(param, new TIMCallBack() {
                            @Override
                            public void onError(int code, String desc) {
                                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                                //错误码 code 列表请参见错误码表
                                Log.e(getClass().getName(), "modifyProfile failed: " + code + " desc" + desc);
                            }

                            @Override
                            public void onSuccess() {
                                Log.e(getClass().getName(), "modifyProfile succ");
                            }
                        });
                    }

                    @Override
                    public void onError(int code, String desc) {//登录失败
                        Log.e(Const.IM,"LOGIN_onError>>>"+"code=" +code);
                        Log.e(Const.IM,"LOGIN_onError>>>"+"desc=" +desc);
                        ToastUtils.Toast_long("IM账号异常，请重新登录");
                    }
                });
    }
}
