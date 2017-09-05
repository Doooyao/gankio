package com.example.administrator.mygankio.splash;

import android.os.Handler;
import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/7/20.
 */

public class SplashPresenter implements SplashContract.Presenter {
    private SplashContract.View mSplashFragment;
    Handler handler = new Handler();
    @Override
    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mSplashFragment.showCheckVersionProgress();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mSplashFragment.dismissCheckVersionProgress();
                        gotoMainActivity();
                    }
                });
            }
        }).start();

    }
    public SplashPresenter(@NonNull SplashContract.View splashFragment) {
        mSplashFragment = checkNotNull(splashFragment, "splashFragment cannot be null!");
        mSplashFragment.setPresenter(this);
    }

    @Override
    public void gotoMainActivity() {
        mSplashFragment.showMainActivity();
    }

    @Override
    public void checkversion() {

    }
}
