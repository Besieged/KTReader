package com.besieged.ktreader.model;

import com.besieged.ktreader.presenter.listener.OnDoubanBookDetailListener;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public interface DoubanBookDetailModel {
    void loadSearch(String id, OnDoubanBookDetailListener listener);//图书搜索
}
