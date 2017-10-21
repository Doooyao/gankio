package com.example.administrator.mygankio.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.utils.ActivityUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        init();
    }

    private void init() {
        SplashFragment splashFragment = (SplashFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_layout);
        if (splashFragment == null) {
            splashFragment = new SplashFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    splashFragment, R.id.content_layout);
        }

        SplashPresenter splashPresenter = new SplashPresenter(splashFragment);


    }

}
