package com.example.administrator.mygankio;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface BaseMvpView<T> {

    void setPresenter(T presenter);
    void showRefreshingBar();
    void dismissRefreshingBar();
    void showLoadingMoreBar();
    void dismissLoadingMoreBar();

}
