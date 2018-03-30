package com.besieged.ktreader.model.impl;

import com.besieged.ktreader.api.ZhihuAPI;
import com.besieged.ktreader.model.ZhihuDetailModel;
import com.besieged.ktreader.model.entity.ZhihuDetail;
import com.besieged.ktreader.presenter.listener.OnZhihuDetailListener;
import com.besieged.ktreader.utils.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/28.
 */

public class ZhihuDetailModelImpl implements ZhihuDetailModel {

    private ZhihuAPI mZhihuAPIService;
    private ZhihuDetail mZhihuDetail;

    public ZhihuDetailModelImpl() {
        mZhihuDetail = new ZhihuDetail();
        mZhihuAPIService = RetrofitManager
                .getInstence().getZhihuAPIService("http://news-at.zhihu.com/api/4/news/");
    }
    public ZhihuDetail getDetail(){
        return mZhihuDetail;
    }

    @Override
    public void loadDetail(String id, final OnZhihuDetailListener listener) {
        if (mZhihuAPIService!=null){
            mZhihuAPIService.getDetail(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ZhihuDetail>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ZhihuDetail zhihuDetail) {
                            mZhihuDetail = zhihuDetail;
                            listener.onLoadZhihuDetailSuccess(zhihuDetail);
                        }

                        @Override
                        public void onError(Throwable e) {
                            listener.onLoadDataError(e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
