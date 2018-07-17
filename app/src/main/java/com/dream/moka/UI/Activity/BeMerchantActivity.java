package com.dream.moka.UI.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.BasePagerAdapter;
import com.dream.moka.Bean.EventBusBean.AFRefreshBean;
import com.dream.moka.Bean.EventBusBean.BusCityBean;
import com.dream.moka.Bean.EventBusBean.UpBitmapBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.UI.ChildFragment.BeMerchant1Fragment;
import com.dream.moka.UI.ChildFragment.BeMerchant2Fragment;
import com.dream.moka.Utils.BitmapUtils;
import com.dream.moka.Utils.ResourcesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class BeMerchantActivity extends BaseCommonActivity {

    @Bind(R.id.apply_provp)
    ViewPager apply_provp;
    @Bind(R.id.left_iv)
    ImageView left_iv;
    @Bind(R.id.center_iv)
    ImageView center_iv;
    @Bind(R.id.right_iv)
    ImageView right_iv;
    @Bind(R.id.view1)
    View view1;
    @Bind(R.id.view2)
    View view2;
    @Bind(R.id.view3)
    View view3;
    @Bind(R.id.view4)
    View view4;

    List<Fragment> fragments = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_bemerchant;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        initFragments();
        apply_provp.setAdapter(new BasePagerAdapter(getSupportFragmentManager(),fragments));
    }
    private void initFragments(){
        fragments.clear();
        BeMerchant1Fragment fragment1 = new BeMerchant1Fragment();
        fragments.add(fragment1);
        BeMerchant2Fragment fragment2 = new BeMerchant2Fragment();
        fragments.add(fragment2);
    }

    @Override
    public String initTitleText() {
        return "申请加盟";
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

    }

    @Override
    public void eventListener() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(AFRefreshBean afRefreshBean){
        if (afRefreshBean.getEventStr().equals(Const.refresh)){
            apply_provp.setCurrentItem(1);
            title_tv.setText("证件上传");
            center_iv.setBackgroundResource(R.drawable.shape_circle_icon);
            view2.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
            view3.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
        }else {
            view4.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
            right_iv.setBackgroundResource(R.drawable.shape_circle_icon);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("tag","onActivityResult="+getClass().getName());
        fragments.get(1).onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==110&&data!=null){
            String city = data.getStringExtra("city");
            if (city!=null){
                BusCityBean cityBean = new BusCityBean();
                cityBean.setEventStr(Const.refresh);
                cityBean.setCity(city);
                EventBus.getDefault().post(cityBean);
            }
        }

      /*  if (requestCode == Const.CAMERA && data != null) {
            upBitmap(data,Const.CAMERA);
        }

        if (requestCode == Const.PICTURE && data != null) {
            upBitmap(data,Const.PICTURE);
        }*/
    }

    private void upBitmap(Intent data,int method) {
        Map<Bitmap, File> map = BitmapUtils.parseData(data, method, mActivity);
        for (Map.Entry<Bitmap, File> mFile:map.entrySet()){
            UpBitmapBean upBitmapBean = new UpBitmapBean();
            upBitmapBean.setEventStr(Const.refresh);
            upBitmapBean.setBitmap(mFile.getKey());
            upBitmapBean.setFile(mFile.getValue());
            EventBus.getDefault().post(upBitmapBean);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
