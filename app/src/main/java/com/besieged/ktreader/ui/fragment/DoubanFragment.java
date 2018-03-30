package com.besieged.ktreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.besieged.ktreader.R;
import com.besieged.ktreader.adapter.DoubanAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public class DoubanFragment extends Fragment {

    DoubanAdapter adapter;
    Unbinder unbinder;
    @BindView(R.id.tablay_douban)
    TabLayout tablayDouban;
    @BindView(R.id.vp_douban)
    ViewPager vpDouban;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_douban, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new DoubanAdapter(getActivity().getSupportFragmentManager());
        vpDouban.setAdapter(adapter);
        tablayDouban.setupWithViewPager(vpDouban);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
