package com.besieged.ktreader.presenter.impl;

import com.besieged.ktreader.BasePresenter;
import com.besieged.ktreader.model.impl.SplashModelImpl;
import com.besieged.ktreader.presenter.SplashPresenter;
import com.besieged.ktreader.presenter.listener.OnSplashListener;
import com.besieged.ktreader.viewInterface.SplashView;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/21.
 */

public class SplashPresenterImpl extends BasePresenter<SplashView> implements SplashPresenter,OnSplashListener {

    /*Presenter作为中间层，持有View和Model的引用*/
    private SplashView mSplashView;
    private SplashModelImpl splashModelImpl;

    public SplashPresenterImpl(SplashView splashView) {
        mSplashView = splashView;
        splashModelImpl = new SplashModelImpl();
    }

    @Override
    public void loadSaying() {
        splashModelImpl.loadSaying(this);
    }

    @Override
    public void onSuccess(String saying) {
        mSplashView.onGetSayingSuccess(saying);
    }

    @Override
    public void onError() {
        mSplashView.onGetSayingFailed();
    }

}
