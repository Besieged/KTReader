package com.besieged.ktreader.model.impl;

import com.besieged.ktreader.api.DoubanAPI;
import com.besieged.ktreader.app.Constant;
import com.besieged.ktreader.model.DoubanBookDetailModel;
import com.besieged.ktreader.model.entity.DoubanBookDetail;
import com.besieged.ktreader.presenter.listener.OnDoubanBookDetailListener;
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

public class DoubanBookDetailModelImpl implements DoubanBookDetailModel {

    private DoubanAPI mDoubanApiService; //请求服务

    public DoubanBookDetailModelImpl() {
        mDoubanApiService = RetrofitManager.getInstence().getDoubanService(Constant.DOUBAN_BASE_URL);
    }

    @Override
    public void loadSearch(String id, final OnDoubanBookDetailListener listener) {
        if (mDoubanApiService != null) {
            mDoubanApiService.getSearchBookDetail(id, String.valueOf(0))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<DoubanBookDetail>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(DoubanBookDetail doubanBookDetail) {
                            listener.onLoadDOubanBookDetailSuccess(doubanBookDetail);
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
