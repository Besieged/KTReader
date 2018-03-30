package com.besieged.ktreader.factory;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.besieged.ktreader.BaseFragment;
import com.besieged.ktreader.R;
import com.besieged.ktreader.ui.fragment.DoubanFragment;
import com.besieged.ktreader.ui.fragment.ZhihuFragment;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public class FragmentFactory {

    private static FragmentFactory mFragmentFactory;

    private BaseFragment mZHihuFragment;
    private Fragment mDoubanFragment;
    private BaseFragment mQiwenFragment;
    private BaseFragment mTupianFragment;

    public static FragmentFactory getInstance(){
        if (mFragmentFactory==null){
            synchronized (FragmentFactory.class){
                if (mFragmentFactory == null){
                    mFragmentFactory = new FragmentFactory();
                }
            }
        }
        return mFragmentFactory;
    }

    public Fragment getFragment(int id){
        switch (id){
            case R.id.nav_zhihu:
                return getZhihuFragment();
            case R.id.nav_douban:
                return getDoubanFragment();
            case R.id.nav_qiwen:
                return getQiwenFragment();
            case R.id.nav_tupian:
                return getTupianFragment();
        }
        return null;
    }

    private BaseFragment getZhihuFragment(){
        if (mZHihuFragment==null){
            mZHihuFragment = new ZhihuFragment();
        }
        return mZHihuFragment;
    }

    private Fragment getDoubanFragment() {
        if (mDoubanFragment == null) {
            mDoubanFragment = new DoubanFragment();
            Log.d("ppapp","new DoubanFragment()");
        }
        Log.d("ppapp","getDoubanFragment");
        return mDoubanFragment;
    }
    private BaseFragment getQiwenFragment() {
        if (mQiwenFragment == null) {
            mQiwenFragment = new ZhihuFragment();
        }
        return mQiwenFragment;
    }
    private BaseFragment getTupianFragment() {
        if (mTupianFragment == null) {
            mTupianFragment = new ZhihuFragment();
        }
        return mTupianFragment;
    }

}
