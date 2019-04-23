package com.besieged.ktreader.model.impl;

import android.util.Log;

import com.besieged.ktreader.api.DoubanAPI;
import com.besieged.ktreader.app.Constant;
import com.besieged.ktreader.model.DoubanMovieModel;
import com.besieged.ktreader.model.entity.DoubanMovieDetail;
import com.besieged.ktreader.presenter.listener.OnDoubanMovieListener;
import com.besieged.ktreader.utils.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/4/11.
 */

public class DoubanMovieModelImpl implements DoubanMovieModel {

    public DoubanMovieModelImpl() {
        mDoubanAPIService = RetrofitManager.getInstence().getDoubanService(Constant.DOUBAN_BASE_URL);
    }

    DoubanAPI mDoubanAPIService;

    @Override
    public void loadSearch(String id, OnDoubanMovieListener listener) {

    }

    @Override
    public void loadInTheaters(final OnDoubanMovieListener listener) {
        Log.d("testTime", "开始下载数据， 时间： " + String.valueOf(System.currentTimeMillis()));
        if (mDoubanAPIService !=null){
            mDoubanAPIService.getInTheaters()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<DoubanMovieDetail>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(DoubanMovieDetail doubanMovieDetail) {
                            Log.d("testTime", "数据下载完成 时间： " + String.valueOf(System.currentTimeMillis()));
                            listener.onLoadInTheatersSuccess(doubanMovieDetail);
                        }

                        @Override
                        public void onError(Throwable e) {
                            listener.onLoadDataError(e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    public void loadComingSoon(OnDoubanMovieListener listener) {

    }

    @Override
    public void loadTop250(final OnDoubanMovieListener listener) {
        if (mDoubanAPIService!=null){
            mDoubanAPIService.getTop250(String.valueOf(0))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<DoubanMovieDetail>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(DoubanMovieDetail doubanMovieDetail) {
                            listener.onLoadTop250Success(doubanMovieDetail);
                        }

                        @Override
                        public void onError(Throwable e) {
                            listener.onLoadDataError(e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    public void loadWeekly(OnDoubanMovieListener listener) {

    }

    @Override
    public void loadNewMovies(OnDoubanMovieListener listener) {

    }


}
