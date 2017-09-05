package com.example.administrator.mygankio.gankmain.homePage;

import android.support.annotation.NonNull;

import com.example.administrator.mygankio.data.ApiService;
import com.example.administrator.mygankio.data.GankPushDate;
import com.example.administrator.mygankio.data.GankSearchBean;
import com.example.administrator.mygankio.data.GankType;
import com.example.administrator.mygankio.gankmain.searchpage.SearchContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
    GankPushDate gankPushDate;
    public static final int ONE_PAGE_COUNT = 10;
    public HomePresenter (@NonNull HomeContract.view homeFragment){
        this.homeFragment = checkNotNull(homeFragment,"homeFragment cannot b null");
        homeFragment.setPresenter(this);
        gankPushDate = new GankPushDate();
    }

    @Override
    public void start() {
        getGankPushData();
    }

    public void getGankPushData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<GankPushDate> observable = apiService.getGankPushDate();
        //// TODO: 2017/9/5  到底要不要省 以后再看
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //这两行代码需要省了
                .subscribe(new Observer<GankPushDate>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull GankPushDate gankPushDate2) {
                         gankPushDate = gankPushDate2;
                         for (String s:gankPushDate.getResults()){
                             System.out.println(s);
                         }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getGankDateDataBean(String s) {
        String year = s.substring(0,4);
        String month = s.substring(5,2);
        String day = s.substring(7,2);

    }

}
