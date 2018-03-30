package com.besieged.ktreader.presenter.listener;

import com.besieged.ktreader.model.entity.DoubanBookDetail;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public interface OnDoubanBookDetailListener {
    void onLoadDOubanBookDetailSuccess(DoubanBookDetail doubanBookDetail);
    void onLoadDataError(String error);
}
