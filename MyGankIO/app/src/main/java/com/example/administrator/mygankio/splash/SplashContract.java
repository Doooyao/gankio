package com.example.administrator.mygankio.splash;

import android.support.annotation.NonNull;

import com.example.administrator.mygankio.BaseMvpPresenter;
import com.example.administrator.mygankio.BaseMvpView;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface SplashContract {
    interface View extends BaseMvpView<Presenter>{
        void showCheckVersionProgress();
        void dismissCheckVersionProgress();
        void showUpdateWindow();
        void showLoginWindow();
        void showMainActivity();


    }
    interface Presenter extends BaseMvpPresenter {
        void gotoMainActivity();
        void checkversion();


    }
}
