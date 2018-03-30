package com.besieged.ktreader.model.impl;

import com.besieged.ktreader.api.DoubanAPI;
import com.besieged.ktreader.app.Constant;
import com.besieged.ktreader.model.DoubanBookModel;
import com.besieged.ktreader.model.entity.DoubanBook;
import com.besieged.ktreader.presenter.listener.OnDoubanBookListener;
import com.besieged.ktreader.utils.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public class DoubanBookModelImpl implements DoubanBookModel {

    private DoubanAPI doubanAPI;

    public DoubanBookModelImpl() {
        doubanAPI = RetrofitManager.getInstence().getDoubanService(Constant.DOUBAN_BASE_URL);
    }

    @Override
    public void loadSearch(String id, final OnDoubanBookListener listener) {
        if (doubanAPI!=null){
            doubanAPI.getSearchBookByName(id,"50")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<DoubanBook>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(DoubanBook doubanBook) {
                            listener.onLoadSearchSuccess(doubanBook);
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
}
