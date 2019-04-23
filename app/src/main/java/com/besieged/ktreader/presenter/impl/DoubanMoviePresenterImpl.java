package com.besieged.ktreader.presenter.impl;

import android.util.Log;

import com.besieged.ktreader.BasePresenter;
import com.besieged.ktreader.model.entity.DoubanMovieDetail;
import com.besieged.ktreader.model.impl.DoubanMovieModelImpl;
import com.besieged.ktreader.presenter.DoubanMoviePresenter;
import com.besieged.ktreader.presenter.listener.OnDoubanMovieListener;
import com.besieged.ktreader.viewInterface.DoubanMovieView;


/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/4/12.
 */

public class DoubanMoviePresenterImpl extends BasePresenter<DoubanMovieView> implements DoubanMoviePresenter,OnDoubanMovieListener {

    private DoubanMovieView mDoubanMovieView;
    private DoubanMovieModelImpl mDoubanMovieModelImpl;
    private DoubanMovieDetail mDoubanMovieDetail;
    private DoubanMovieDetail mDoubanMovieTopDetail;

    public DoubanMoviePresenterImpl(DoubanMovieView mDoubanMovieView) {
        this.mDoubanMovieView = mDoubanMovieView;
        mDoubanMovieModelImpl = new DoubanMovieModelImpl();
    }

    public DoubanMovieDetail getInTheatersData() {
        if (mDoubanMovieDetail == null) {
            mDoubanMovieDetail = new DoubanMovieDetail();
        }
        return mDoubanMovieDetail;
    }

    public DoubanMovieDetail getTopData() {
        if (mDoubanMovieTopDetail == null) {
            mDoubanMovieTopDetail = new DoubanMovieDetail();
        }
        return mDoubanMovieTopDetail;
    }

    @Override
    public void getSearch() {

    }

    @Override
    public void getInTheaters() {
        mDoubanMovieModelImpl.loadInTheaters(this);
    }

    @Override
    public void getComingSoon() {

    }

    @Override
    public void getTop250() {
        mDoubanMovieModelImpl.loadTop250(this);
    }

    @Override
    public void getWeekly() {

    }

    @Override
    public void getNewMovies() {

    }

    @Override
    public void onLoadSearchSuccess() {

    }

    @Override
    public void onLoadInTheatersSuccess(DoubanMovieDetail doubanMovieDetail) {
        mDoubanMovieDetail = doubanMovieDetail;
        Log.d("testTime", "数据下载完成 返回到p层接口 时间： " + String.valueOf(System.currentTimeMillis()));
        mDoubanMovieView.onGetInTheatersSuccess();
    }

    @Override
    public void onLoadComingSoonSuccess() {

    }

    @Override
    public void onLoadTop250Success(DoubanMovieDetail doubanMovieDetail) {
        mDoubanMovieTopDetail = doubanMovieDetail;
        mDoubanMovieView.onGgetTop250Success();
    }

    @Override
    public void onLoadWeeklySuccess() {

    }

    @Override
    public void onLoadNewMoviesSuccess() {

    }

    @Override
    public void onLoadDataError(String error) {
        Log.d("ppadpp",error);
    }
}
