package com.besieged.ktreader.model;

import com.besieged.ktreader.presenter.listener.OnZhihuLatestListener;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/22.
 */

public interface ZhihuLatestModel {
    void loadZhihuLatest(OnZhihuLatestListener listener);

    void loadMore(OnZhihuLatestListener listener);
}
