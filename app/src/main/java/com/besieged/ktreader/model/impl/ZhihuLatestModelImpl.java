package com.besieged.ktreader.model.impl;

import com.besieged.ktreader.api.ZhihuAPI;
import com.besieged.ktreader.model.ZhihuLatestModel;
import com.besieged.ktreader.model.entity.ZhihuLatest;
import com.besieged.ktreader.presenter.listener.OnZhihuLatestListener;
import com.besieged.ktreader.utils.RetrofitManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/22.
 * 获取知乎日报数据的Model实现
 */

public class ZhihuLatestModelImpl implements ZhihuLatestModel {

    private ZhihuAPI mZhihuService;
    private List<ZhihuLatest.StoriesEntity> mZhihuLatestList;
    private String date;//网络请求的url参数，首次加载数据获取，调用getmore方法时，date-1.

    public ZhihuLatestModelImpl(){
        mZhihuLatestList = new ArrayList<>();
        mZhihuService = RetrofitManager
                .getInstence()
                .getZhihuAPIService("http://news-at.zhihu.com/api/4/news/");
    }
    @Override
    public void loadZhihuLatest(final OnZhihuLatestListener listener) {
        mZhihuLatestList.clear();
        if (mZhihuService!=null){
            mZhihuService.getZhihuLatest()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ZhihuLatest>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(ZhihuLatest zhihuLatest) {
                            date = zhihuLatest.date;
                            for (int i=0;i<zhihuLatest.stories.size();i++){
                                mZhihuLatestList.add(zhihuLatest.stories.get(i));
                            }
                            listener.onLoadZhihuLatestSuccess();
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

    public List<ZhihuLatest.StoriesEntity> getmZhihuLatestList(){
        return mZhihuLatestList;
    }

    @Override
    public void loadMore(final OnZhihuLatestListener listener) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = new GregorianCalendar();
        try {
            calendar.setTime(simpleDateFormat.parse(date));
            calendar.add(Calendar.HOUR_OF_DAY,-1);
            date = simpleDateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //数据层的操作，网络请求数据
        if (mZhihuService!=null){
            mZhihuService.getBefore(date)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ZhihuLatest>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ZhihuLatest zhihuLatest) {
                            date = zhihuLatest.date;
                            for (int i=0;i<zhihuLatest.stories.size();i++){
                                mZhihuLatestList.add(zhihuLatest.stories.get(i));
                            }
                            listener.onLoadZhihuLatestSuccess();
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
