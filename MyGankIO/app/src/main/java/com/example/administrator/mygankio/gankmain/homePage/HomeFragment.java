package com.example.administrator.mygankio.gankmain.homePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.adapter.HomePageListAdapter;
import com.example.administrator.mygankio.adapter.SearchListAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/7/20.
 */

public class HomeFragment extends Fragment implements HomeContract.view{

    HomeContract.presenter homePresenter;

    RecyclerView homeRecycleView;

    View root;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_fragment,container,false);
        initview();
        return root;
    }

    private void initview() {
        homeRecycleView = (RecyclerView) root.findViewById(R.id.home_recyclerview);
        initRecycleView();
    }

    private void initRecycleView() {
        homeRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
//设置Item增加、移除动画
        homeRecycleView.setItemAnimator(new DefaultItemAnimator());
//添加分割线
        homeRecycleView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        HomePageListAdapter homePageListAdapter= new HomePageListAdapter(getContext());
        homeRecycleView.setAdapter(homePageListAdapter);
    }

    @Override
    public void setPresenter(HomeContract.presenter presenter) {
        homePresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        homePresenter.start();
    }

    /**
     * 显示和隐藏刷新
     */
    @Override
    public void showRefreshRecycleView() {

    }

    @Override
    public void hideRefreshRecycleView() {

    }

    @Override
    public void refreshRecycleview() {

    }
}
