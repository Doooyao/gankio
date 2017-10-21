package com.example.administrator.mygankio.customview.gggswiperefresh;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by tdfz on 2017/10/16.
 */

public class LoadingViewController implements ILoadingViewController{
    Context context;

    public LoadingViewController(Context context){
        this.context = context;
    }

    @Override
    public void reset() {

    }

    @Override
    public View create() {
        ImageView imageView = new ImageView(context);
//        ProgressBar progressBar = new ProgressBar(context);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(200,200);
        imageView.setBackgroundColor(Color.parseColor("#000000"));
        params.setMargins(0,20,0,20);
        imageView.setLayoutParams(params);
        return imageView;
    }

    @Override
    public int getDefaultHeight() {
        return 0;
    }

    @Override
    public int getCurrentHeight() {
        return 0;
    }

    @Override
    public int move(int height) {
        return 0;
    }

    @Override
    public int finishPullRefresh(float totalDistance) {
        return 0;
    }

    @Override
    public void setRefreshListener(GGGSwipeRefreshLayout.OnRefreshListener mListener) {

    }

    @Override
    public void showNoMore(boolean show) {

    }

    @Override
    public View getDefaultView() {
        return null;
    }

    @Override
    public void setLoadMore(boolean loading) {

    }

    @Override
    public boolean isLoading() {
        return false;
    }
}
