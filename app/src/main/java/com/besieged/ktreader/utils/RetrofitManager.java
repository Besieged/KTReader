package com.besieged.ktreader.utils;

import com.besieged.ktreader.app.MyApplication;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/22.
 */

public class RetrofitManager {

    private static RetrofitManager retrofitManager;

    private RetrofitManager(){}

    //一直读取缓存，(有时间限制) 把拦截器设置到addNetworkOnterceptor
    private static Interceptor netInterceptor1 = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            int maxAge = 60;//缓存有效时间60s。超过60秒去网络重新获取
            return response
                    .newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .addHeader("Cache-Control","public,max-age="+maxAge)
                    .build();
        }
    };

    //有网络读取网络数据，没有网络读取缓存
    private static class netInterceptor2 implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //没有网络时强制使用缓存数据
            if (!NetWorkUtil.isNetWorkAvailable(MyApplication.getAPPContext())) {
                request = request.newBuilder()
                        //强制使用缓存数据
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (true) {
                return originalResponse .newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public,max-age=" + 0) //0为不进行缓存
                        .build();
            } else {
                int maxAge =  4 * 24 * 60 * 60; //缓存保存时间
                return originalResponse .newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-age=" + maxAge)
                        .build();
            }
        }
    }
    //缓存位置
    private static File cacheFile = new File(MyApplication.getAPPCacheDir(),"cacheData_zhihu");
    //设置缓存大小
    private static int DEFATLT_DIR_CACHE = 10*1024*1024;
    private static Cache cache = new Cache(cacheFile,DEFATLT_DIR_CACHE);
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new netInterceptor2())
            .addNetworkInterceptor(new netInterceptor2())
            .cache(cache)
            .build();

    public static RetrofitManager getInstence(){
        if (retrofitManager == null){
            synchronized (RetrofitManager.class){
                if (retrofitManager == null){
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    private Retrofit retrofit;

    public Retrofit getRetrofit(String url){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
