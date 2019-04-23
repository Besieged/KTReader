package com.besieged.ktreader.presenter.listener;

import com.besieged.ktreader.model.entity.DoubanMovieDetail;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/4/11.
 */

public interface OnDoubanMovieListener {
    void onLoadSearchSuccess();//电影搜索
    void onLoadInTheatersSuccess(DoubanMovieDetail doubanMovieDetail);//正在热映
    void onLoadComingSoonSuccess();//即将上映
    void onLoadTop250Success(DoubanMovieDetail doubanMovieDetail);//Top250
    void onLoadWeeklySuccess();// 口碑榜
    void onLoadNewMoviesSuccess();//新片榜

    void onLoadDataError(String error);
}
