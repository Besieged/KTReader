package com.besieged.ktreader.presenter.listener;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/22.
 */

public interface OnZhihuLatestListener {
    void onLoadZhihuLatestSuccess();

    void onLoadMoreSuccess();

    void onLoadDataError(String str);

}
