package com.besieged.ktreader.model.impl;

import android.os.AsyncTask;

import com.besieged.ktreader.model.SplashModel;
import com.besieged.ktreader.presenter.listener.OnSplashListener;
import com.besieged.ktreader.utils.ShowApiUtils;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/21.
 */

public class SplashModelImpl implements SplashModel {

    private OnSplashListener listener; //回调接口

    @Override
    public void loadSaying(OnSplashListener listener) {
        this.listener = listener;
        new ShowAsyncTask().execute(ShowApiUtils.SAYING);
    }

    //使用基本的AsyncTask处理网络请求
    class ShowAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return ShowApiUtils.parseJsonFromSaying(ShowApiUtils.getData(params[0]));
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                listener.onSuccess(s); //网络请求数据不为空时 回调成功方法。
            } else {
                listener.onError(); //回调失败方法。
            }
        }
    }
}
