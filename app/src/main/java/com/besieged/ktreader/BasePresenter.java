package com.besieged.ktreader;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/21.
 */

public class BasePresenter<T> {
    protected Reference<T> mViewRef;//弱引用

    public void attachView(T view){
        mViewRef = new WeakReference<T>(view);//简历弱关联
    }

    protected T getView(){
        return mViewRef.get();
    }

    public boolean isViewAttach(){
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView(){
        if (mViewRef!=null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
