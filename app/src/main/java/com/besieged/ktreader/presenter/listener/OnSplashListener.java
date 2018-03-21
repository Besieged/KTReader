package com.besieged.ktreader.presenter.listener;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/21.
 */

public interface OnSplashListener {
    /**
     * 成功时回调
     * @param saying
     */
    void onSuccess(String saying);
    /**
     * 失败时回调
     */
    void onError();
}
