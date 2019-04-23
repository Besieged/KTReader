package com.besieged.ktreader.presenter;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/4/12.
 */

public interface DoubanMoviePresenter {
    void getSearch(); //电影搜索
    void getInTheaters(); //正在热映
    void getComingSoon(); //即将上映
    void getTop250(); //Top250
    void getWeekly(); // 口碑榜
    void getNewMovies(); //新片榜
}
