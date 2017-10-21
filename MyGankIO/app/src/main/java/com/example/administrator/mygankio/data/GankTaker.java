package com.example.administrator.mygankio.data;

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

/**
 * Created by tdfz on 2017/10/18.
 */

public class GankTaker implements IGankTaker{
    Retrofit retrofit;

    public static GankTaker create(){
        GankTaker gankTaker = new GankTaker();
        return gankTaker;
    }
    public GankTaker(){
         retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Override
    public void searchGank(String query, int count, int page, String category, final OnSearchFinishListener onSearchFinishListener) {
        ApiService apiService = retrofit.create(ApiService.class);
        if (!query.trim().equals("")&&query.trim()!=null){
            Observable<GankSearchBean> observable = apiService.searchGank(query,category,count,page);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()) //这两行代码需要省了
                    .subscribe(new Observer<GankSearchBean>() {
                        @Override
                        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                            onSearchFinishListener.onstart();
                        }

                        @Override
                        public void onNext(@io.reactivex.annotations.NonNull GankSearchBean gankSearchBean) {
                            List<GankBean> resultsBeen = gankSearchBean.getResults();
                            onSearchFinishListener.onfinish(resultsBeen);
                        }

                        @Override
                        public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                            System.out.println(e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else {

            Observable<GankSearchBean> observable = apiService.searchCategoryGank(category,count,page);
            System.out.println(category+count+page);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()) //这两行代码需要省了
                    .subscribe(new Observer<GankSearchBean>() {
                        @Override
                        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                            onSearchFinishListener.onstart();
                        }

                        @Override
                        public void onNext(@io.reactivex.annotations.NonNull GankSearchBean gankSearchBean) {
                            List<GankBean> resultsBeen = gankSearchBean.getResults();
                            onSearchFinishListener.onfinish(resultsBeen);
                        }

                        @Override
                        public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                            System.out.println(e+"cuowucuowu_________");
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
