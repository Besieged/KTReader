package com.besieged.ktreader;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/21.
 */

public abstract class MVPBaseActivity<V,T extends BasePresenter<V>> extends BaseActivity {
    protected T mPresenter;

    protected abstract T createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

}
