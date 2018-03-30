package com.besieged.ktreader.viewInterface;

import com.besieged.ktreader.model.entity.DoubanBookDetail;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public interface DoubanBookDetailView {
    void onStartGetData();

    void onGetSearchSuccess(DoubanBookDetail doubanBookDetail);

    void onGetDataFailed(String error);
}
