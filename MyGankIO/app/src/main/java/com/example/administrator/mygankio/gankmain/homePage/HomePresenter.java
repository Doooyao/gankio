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

    public HomePresenter (@NonNull HomeContract.view homeFragment){
        this.homeFragment = checkNotNull(homeFragment,"homeFragment cannot b null");
        homeFragment.setPresenter(this);

    }

    @Override
    public void start() {
        getListAdapter();
        getGankPushData();
    }

    private void getListAdapter() {
        homePageListAdapter = homeFragment.getHomePageAdapter();
    }

    public void getGankPushData() {

    }

}
