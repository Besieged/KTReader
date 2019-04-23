package com.besieged.ktreader.viewInterface;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/4/12.
 */

public interface DoubanMovieView {
    void onStartGetData();

    void onGetInTheatersSuccess();
    void onGetComingSoonSuccess();
    void onGgetTop250Success();
    void onGetWeeklySuccess();
    void onGetNewMoviesSuccess();

    void onGetDataFailed(String error);
}
