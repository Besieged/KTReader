package com.besieged.ktreader.viewInterface;

import com.besieged.ktreader.model.entity.DoubanBook;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public interface DoubanBookView {
    void onStartGetData();

    void onGetSearchSuccess(DoubanBook doubanBook);

    void onGetDataFailed(String error);
}
