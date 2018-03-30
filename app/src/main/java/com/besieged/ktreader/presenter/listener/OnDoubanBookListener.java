package com.besieged.ktreader.presenter.listener;

import com.besieged.ktreader.model.entity.DoubanBook;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public interface OnDoubanBookListener {
    void onLoadSearchSuccess(DoubanBook doubanBook);

    void onLoadDataError(String error);
}
