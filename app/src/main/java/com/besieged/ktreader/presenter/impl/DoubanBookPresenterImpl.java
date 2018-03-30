package com.besieged.ktreader.presenter.impl;

import com.besieged.ktreader.BasePresenter;
import com.besieged.ktreader.model.entity.DoubanBook;
import com.besieged.ktreader.model.impl.DoubanBookModelImpl;
import com.besieged.ktreader.presenter.DoubanBookPresenter;
import com.besieged.ktreader.presenter.listener.OnDoubanBookListener;
import com.besieged.ktreader.viewInterface.DoubanBookView;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public class DoubanBookPresenterImpl extends BasePresenter<DoubanBookView> implements DoubanBookPresenter,OnDoubanBookListener {
    private DoubanBookView mDoubanBookView;
    private DoubanBookModelImpl mDoubanBookModelImpl;

    public DoubanBookPresenterImpl(DoubanBookView mDoubanBookView) {
        this.mDoubanBookView = mDoubanBookView;
        mDoubanBookModelImpl = new DoubanBookModelImpl();
    }

    @Override
    public void getSearch(String id) {
        mDoubanBookView.onStartGetData();
        mDoubanBookModelImpl.loadSearch(id,this);
    }


    @Override
    public void onLoadSearchSuccess(DoubanBook doubanBook) {
        mDoubanBookView.onGetSearchSuccess(doubanBook);
    }

    @Override
    public void onLoadDataError(String error) {
        mDoubanBookView.onGetDataFailed(error);
    }
}
