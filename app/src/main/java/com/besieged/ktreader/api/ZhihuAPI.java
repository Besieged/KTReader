package com.besieged.ktreader.api;

import com.besieged.ktreader.model.entity.ZhihuDetail;
import com.besieged.ktreader.model.entity.ZhihuLatest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/22.
 */

public interface ZhihuAPI {
    @GET("latest")
    Observable<ZhihuLatest> getZhihuLatest();

    @GET("before/{date}")
    Observable<ZhihuLatest> getBefore(@Path("date") String date);

    @GET("{id}")
    Observable<ZhihuDetail> getDetail(@Path("id") String id);

}
