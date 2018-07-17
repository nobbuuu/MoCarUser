package com.dream.moka.IM.im.imUtils;

import android.util.Log;

import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.sns.TIMAddFriendRequest;
import com.tencent.imsdk.ext.sns.TIMFriendResult;
import com.tencent.imsdk.ext.sns.TIMFriendshipManagerExt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */

public class FriendUtils {

    public static void addFriends(String identify){
        //创建请求列表
        List<TIMAddFriendRequest> reqList = new ArrayList<TIMAddFriendRequest>();

//添加好友请求
        TIMAddFriendRequest req = new TIMAddFriendRequest(identify);
        req.setAddrSource("DemoApp");
        req.setAddWording("add me");
        req.setRemark("Cat");

        reqList.add(req);

//申请添加好友
        TIMFriendshipManagerExt.getInstance().addFriend(reqList, new TIMValueCallBack<List<TIMFriendResult>>() {
            @Override
            public void onError(int code, String desc){
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e(getClass().getName(), "addFriend failed: " + code + " desc");
            }

            @Override
            public void onSuccess(List<TIMFriendResult> result){
                Log.e(getClass().getName(), "addFriend succ");
                for(TIMFriendResult res : result){
                    Log.e(getClass().getName(), "identifier: " + res.getIdentifer() + " status: " + res.getStatus());
                }
            }
        });
    }
}
