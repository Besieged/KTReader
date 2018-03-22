package com.besieged.ktreader.presenter.impl;

import com.besieged.ktreader.BasePresenter;
import com.besieged.ktreader.model.entity.ZhihuLatest;
import com.besieged.ktreader.model.impl.ZhihuLatestModelImpl;
import com.besieged.ktreader.presenter.ZhihuPresenter;
import com.besieged.ktreader.presenter.listener.OnZhihuLatestListener;
import com.besieged.ktreader.viewInterface.ZhihuView;

import java.util.List;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/22.
 */

public class ZhihuPresenterImpl extends BasePresenter<ZhihuView> implements ZhihuPresenter,OnZhihuLatestListener {

    private ZhihuView mZhihuView;
    private ZhihuLatestModelImpl zhihuLatestModelImpl;

    public ZhihuPresenterImpl(ZhihuView mZhihuView) {
        this.mZhihuView = mZhihuView;
        this.zhihuLatestModelImpl = new ZhihuLatestModelImpl();
    }

    public List<ZhihuLatest.StoriesEntity> getmZhihuLatestList() {
        return zhihuLatestModelImpl.getmZhihuLatestList();
    }

    @Override
    public void getLatest() {
        mZhihuView.onStartGetData();
        zhihuLatestModelImpl.loadZhihuLatest(this);
    }

    @Override
    public void getMore() {
        mZhihuView.onStartGetData();
        zhihuLatestModelImpl.loadMore(this);
    }

    @Override
    public void onLoadZhihuLatestSuccess() {
        mZhihuView.onGetZhihuLatestSuccess();
    }

    @Override
    public void onLoadMoreSuccess() {
        mZhihuView.onGetMoreSuccess();
    }

    @Override
    public void onLoadDataError(String str) {
        mZhihuView.onGetDataFailed(str);
    }
}
