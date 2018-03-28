package com.besieged.ktreader.viewInterface;

import com.besieged.ktreader.model.entity.ZhihuDetail;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/28.
 */

public interface ZhihuDetailView {
    void onStartGetData();

    void onGetDetailSuccess(ZhihuDetail zhihuDetail);

    void onGetDetailFailed(String error);
}
