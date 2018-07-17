package com.dream.moka.Base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
public class BasePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    public BasePagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        if (fragmentList!=null){
            fragments.clear();
            fragments.addAll(fragmentList);        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
