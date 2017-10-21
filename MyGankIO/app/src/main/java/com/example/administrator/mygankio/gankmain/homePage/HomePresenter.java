package com.example.administrator.mygankio.gankmain.homePage;

import android.support.annotation.NonNull;

import com.example.administrator.mygankio.data.ApiService;
import com.example.administrator.mygankio.data.GankDateDataBean;
import com.example.administrator.mygankio.data.GankPushDate;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tdfz on 2017/9/5.
 */

public class HomePresenter implements HomeContract.presenter {
    HomeContract.view homeFragment;
    HomePageListAdapter homePageListAdapter;
    private int loadingMoreCount = 10;

    public HomePresenter (@NonNull HomeContract.view homeFragment){
        this.homeFragment = checkNotNull(homeFragment,"homeFragment cannot b null");
        homeFragment.setPresenter(this);

    }

    @Override
    public void start() {
        addAdapter();
        addListener();
    }

    private void addListener() {
        homePageListAdapter.setOnLoadingMoreFinishListener(new HomePageListAdapter.OnLoadingMoreFinishListener() {
            @Override
            public void onLoadingFinish() {
                homeFragment.showLoadingMoreBar();
            }

            @Override
            public void noMoreData() {
                homeFragment.dismissLoadingMoreBar();
            }

            @Override
            public void onLoadingStart() {
            }
        });
        homePageListAdapter.setOnRefreshingFinishListener(new HomePageListAdapter.OnRefreshingFinishListener() {
            @Override
            public void onRefreshingFinish() {
                homeFragment.dismissRefreshingBar();
                homeFragment.showLoadingMoreBar();
                homeFragment.showFirstData();
            }

            @Override
            public void noMoreData() {
                homeFragment.dismissLoadingMoreBar();
            }

            @Override
            public void onRefreshingStart() {
                homeFragment.showRefreshingBar();
            }
        });
    }

    private void addAdapter() {
        homePageListAdapter = new HomePageListAdapter(homeFragment,loadingMoreCount);
        homeFragment.setRecycleAdapter(homePageListAdapter);
    }


    @Override
    public void refreshData() {
        homePageListAdapter.initListData();
    }

    @Override
    public void loadingMoreData() {
        int startPosition = homePageListAdapter.gankDateDataBeanList.size();
        int endPosition = startPosition+ loadingMoreCount -1;
        homePageListAdapter.loadingMoreGankRange(startPosition,endPosition);
    }

}
