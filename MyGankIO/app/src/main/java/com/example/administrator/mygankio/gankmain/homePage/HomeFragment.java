package com.example.administrator.mygankio.gankmain.homePage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.customview.gggswiperefresh.GGGSwipeRefreshLayout;
import com.example.administrator.mygankio.gankmain.gankwebpage.WebViewActivity;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/7/20.
 */

public class HomeFragment extends Fragment implements HomeContract.view{

    HomeContract.presenter homePresenter;
    GGGSwipeRefreshLayout gggSwipeRefreshLayout;
    RecyclerView homeRecycleView;
    View root;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_fragment,container,false);
        initview();
        return root;
    }

    private void initview() {
        homeRecycleView = (RecyclerView) root.findViewById(R.id.home_recyclerview);
        gggSwipeRefreshLayout = (GGGSwipeRefreshLayout) root.findViewById(R.id.home_swiperefreshlayout);
        initRecycleView();
        homePresenter.start();
        addListener();
    }

    private void addListener() {
        gggSwipeRefreshLayout.setOnLoadingMoreListener(new GGGSwipeRefreshLayout.OnLoadingMoreListener() {
            @Override
            public void onLoadingMore() {
                homePresenter.loadingMoreData();
            }
        });
        gggSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (gggSwipeRefreshLayout.getIsLoadingMore()){
                    dismissRefreshingBar();
                }else {
                    homePresenter.refreshData();
                }
            }
        });

    }

    private void initRecycleView() {
        homeRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
//设置Item增加、移除动画
//        homeRecycleView.setItemAnimator(new HomePageListItemAnimator());
    }

    @Override
    public void setPresenter(HomeContract.presenter presenter) {
        homePresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showRefreshingBar() {
        gggSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void dismissRefreshingBar() {
        gggSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 显示和隐藏刷新
     */


    @Override
    public void showLoadingMoreBar() {
        gggSwipeRefreshLayout.showLoadingMore();
    }

    @Override
    public void dismissLoadingMoreBar() {
        gggSwipeRefreshLayout.dismissLoadingMore();
    }

    @Override
    public void showFirstData() {
        homeRecycleView.smoothScrollToPosition(0);
    }

    @Override
    public Context takeContext() {
        return getContext();
    }

    @Override
    public void setRecycleAdapter(RecyclerView.Adapter adapter) {
        homeRecycleView.setAdapter(adapter);
    }
}
