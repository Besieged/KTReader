package com.besieged.ktreader.presenter.impl;

import com.besieged.ktreader.BasePresenter;
import com.besieged.ktreader.model.entity.DoubanBookDetail;
import com.besieged.ktreader.model.impl.DoubanBookDetailModelImpl;
import com.besieged.ktreader.presenter.DoubanBookDetailPresenter;
import com.besieged.ktreader.presenter.listener.OnDoubanBookDetailListener;
import com.besieged.ktreader.viewInterface.DoubanBookDetailView;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public class DoubanBookDetailPresenterImpl extends BasePresenter<DoubanBookDetailView> implements OnDoubanBookDetailListener,DoubanBookDetailPresenter{
    private DoubanBookDetailView mDoubanBookDetailView;
    private DoubanBookDetailModelImpl mDoubanBookDetailModelImpl;

    public DoubanBookDetailPresenterImpl(DoubanBookDetailView mDoubanBookDetailView) {
        this.mDoubanBookDetailView = mDoubanBookDetailView;
        mDoubanBookDetailModelImpl = new DoubanBookDetailModelImpl();
    }

    @Override
    public void getDetail(String id) {
        mDoubanBookDetailView.onStartGetData();
        mDoubanBookDetailModelImpl.loadSearch(id, this);
    }


    @Override
    public void onLoadDOubanBookDetailSuccess(DoubanBookDetail doubanBookDetail) {
        mDoubanBookDetailView.onGetSearchSuccess(doubanBookDetail);
    }

    @Override
    public void onLoadDataError(String error) {
        mDoubanBookDetailView.onGetDataFailed(error);
    }
}
