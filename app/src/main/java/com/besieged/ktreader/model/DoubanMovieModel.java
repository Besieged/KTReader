package com.besieged.ktreader.model;

import com.besieged.ktreader.presenter.listener.OnDoubanMovieListener;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/4/11.
 */

public interface DoubanMovieModel {
    void loadSearch(String id, OnDoubanMovieListener listener);//电影搜索
    void loadInTheaters(OnDoubanMovieListener listener);//正在热映
    void loadComingSoon(OnDoubanMovieListener listener);//即将上映
    void loadTop250(OnDoubanMovieListener listener);//Top250
    void loadWeekly(OnDoubanMovieListener listener);// 口碑榜
    void loadNewMovies(OnDoubanMovieListener listener);//新片榜
}
