package com.example.administrator.mygankio.gankmain.searchpage;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.administrator.mygankio.data.ApiService;
import com.example.administrator.mygankio.data.GankSearchBean;
import com.example.administrator.mygankio.data.GankType;
import com.tinify.Tinify;

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
 * Created by Administrator on 2017/7/21.
 */

public class SearchPresenter implements SearchContract.Presenter {
    SearchContract.View searchFragment;
    public SearchPresenter (@NonNull SearchContract.View searchFragment){
        this.searchFragment = checkNotNull(searchFragment,"searchFragment cannot b null");
        searchFragment.setPresenter(this);
    }

    @Override
    public void start() {
        Tinify.setKey("39slKXXxXkyRKPC_Rvdm4rmX5JCry2jW");
    }

    @Override
    public void searchGank() {
        Bundle b = searchFragment.getSearchParam();
        String query = b.getString("query");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<GankSearchBean> observable = apiService.searchGank(query, GankType.ANDROID,5,1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //这两行代码需要省了
                .subscribe(new Observer<GankSearchBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        searchFragment.showRefreshRecycleView();
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull GankSearchBean gankSearchBean) {
                        List<GankSearchBean.ResultsBean> resultsBeen = gankSearchBean.getResults();
                        searchFragment.getResultsBeen().clear();
                        searchFragment.getResultsBeen().addAll(resultsBeen);
                        searchFragment.refreshRecycleview();
                        searchFragment.hideRefreshRecycleView();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println(22222);
                    }
                });

    }
}
