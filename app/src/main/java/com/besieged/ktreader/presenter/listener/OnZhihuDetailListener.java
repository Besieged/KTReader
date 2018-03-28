package com.besieged.ktreader.presenter.listener;

import com.besieged.ktreader.model.entity.ZhihuDetail;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/28.
 */

public interface OnZhihuDetailListener {
    void onLoadZhihuDetailSuccess(ZhihuDetail zhihuDetail);

    void onLoadDataError(String error);
}
