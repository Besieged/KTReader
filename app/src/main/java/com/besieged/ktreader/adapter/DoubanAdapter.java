package com.besieged.ktreader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.besieged.ktreader.ui.fragment.DoubanBookFragment;
import com.besieged.ktreader.ui.fragment.DoubanMovieFragment;
import com.besieged.ktreader.ui.fragment.DoubanMusicFragment;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public class DoubanAdapter extends FragmentPagerAdapter {

    private final String[] mTitles = new String[]{
            "图书","电影","音乐"
    };


    public DoubanAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new DoubanMovieFragment();
        } else if (position == 2) {
            return new DoubanMusicFragment();
        }
        return new DoubanBookFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
