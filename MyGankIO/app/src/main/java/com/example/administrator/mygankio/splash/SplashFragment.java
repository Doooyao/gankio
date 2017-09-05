package com.example.administrator.mygankio.splash;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.gankmain.GankMainActivity;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/7/20.
 */

public class SplashFragment extends Fragment implements SplashContract.View{
    ProgressBar checkVersionProgressBar;
    private SplashContract.Presenter splashPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.splash_fragment,container,false);
        checkVersionProgressBar = (ProgressBar) rootView.findViewById(R.id.progressbar_checkversion);
        return rootView;
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        splashPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        splashPresenter.start();
    }

    @Override
    public void showCheckVersionProgress() {
        checkVersionProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissCheckVersionProgress() {
        checkVersionProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUpdateWindow() {

    }

    @Override
    public void showLoginWindow() {

    }

    @Override
    public void showMainActivity() {
        Intent i = new Intent(getContext(), GankMainActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}
