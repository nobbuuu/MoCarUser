package com.dream.moka.Utils;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class FragmentUtils {

    public static List<Fragment> getFragments(Fragment childFragment){

        List<Fragment> fraList = new ArrayList<>();
        for (int i = 0; i < 3 ; i++) {
            fraList.add(childFragment);
        }
        return fraList;
    }
}
