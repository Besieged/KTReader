package com.besieged.ktreader.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.besieged.ktreader.BaseFragment;
import com.besieged.ktreader.R;
import com.besieged.ktreader.adapter.DoubanMovieAdapter;
import com.besieged.ktreader.presenter.impl.DoubanMoviePresenterImpl;
import com.besieged.ktreader.viewInterface.DoubanMovieView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public class DoubanMovieFragment extends BaseFragment<DoubanMovieView, DoubanMoviePresenterImpl> implements DoubanMovieView {
    @BindView(R.id.recycleview_douban)
    RecyclerView mRecycleviewDouban;
    @BindView(R.id.prograss)
    ProgressBar mPrograss;
    Unbinder unbinder;
    private DoubanMovieAdapter mDoubanMovieAdapter;

    @Override
    public void onStartGetData() {
        mPrograss.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetInTheatersSuccess() {
        Log.d("testTime", "数据下载完成 返回到V层接口 时间： " + String.valueOf(System.currentTimeMillis()));
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
        mDoubanMovieAdapter.setInTheatersData(mPresenter.getInTheatersData());
    }

    @Override
    public void onGetComingSoonSuccess() {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGgetTop250Success() {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
        mDoubanMovieAdapter.setTopData(mPresenter.getTopData());
    }

    @Override
    public void onGetWeeklySuccess() {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetNewMoviesSuccess() {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetDataFailed(String error) {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
    }

    @Override
    protected DoubanMoviePresenterImpl createPresenter() {
        return new DoubanMoviePresenterImpl(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_douban_movie;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        mPresenter.getInTheaters();
        mPresenter.getTop250();
        mRecycleviewDouban.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleviewDouban.setHasFixedSize(true);
        mRecycleviewDouban.setItemAnimator(new DefaultItemAnimator());
        mDoubanMovieAdapter = new DoubanMovieAdapter(getContext());
        mDoubanMovieAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecycleviewDouban.setAdapter(mDoubanMovieAdapter);


    }

    private DoubanMovieAdapter.OnItemClickListener mOnItemClickListener = new DoubanMovieAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(String id, int type) {
            //           Intent intent = new Intent(getContext(), DoubanMovieSubjectActivity.class);
            //            intent.putExtra("DOUBANMOVIEID",String.valueOf(id));
            //            startActivity(intent);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
