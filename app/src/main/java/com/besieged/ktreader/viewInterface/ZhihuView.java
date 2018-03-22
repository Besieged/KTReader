package com.besieged.ktreader.viewInterface;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/22.
 */

public interface ZhihuView {
    void onStartGetData();

    void onGetZhihuLatestSuccess();

    void onGetMoreSuccess();

    void onGetDataFailed(String errMsg);
}
