package com.besieged.ktreader.model;

import com.besieged.ktreader.presenter.listener.OnZhihuDetailListener;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/28.
 */

public interface ZhihuDetailModel {
    void loadDetail(String id,OnZhihuDetailListener listener);
}
