package com.besieged.ktreader.model;

import com.besieged.ktreader.presenter.listener.OnDoubanBookListener;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public interface DoubanBookModel {
    void loadSearch(String id,OnDoubanBookListener listener);
}
