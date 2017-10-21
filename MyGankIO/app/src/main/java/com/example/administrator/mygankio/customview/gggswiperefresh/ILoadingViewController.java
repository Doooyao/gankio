package com.example.administrator.mygankio.customview.gggswiperefresh;

import android.view.View;

/**
 * Created by tdfz on 2017/10/16.
 */

public interface ILoadingViewController {
    void reset();

    View create();

    int getDefaultHeight();

    int getCurrentHeight();

    /**
     * 向下滑动
     * @param height 滑动距离
     * @return 父view需移动距离
     */
    int move(int height);

    /**
     * 滑动结束
     * @param totalDistance 手指离开屏幕时滑动总距离
     * @return 父view需移动距离
     */
    int finishPullRefresh(float totalDistance);

    void setRefreshListener(GGGSwipeRefreshLayout.OnRefreshListener mListener);

    void showNoMore(boolean show);

    View getDefaultView();

    void setLoadMore(boolean loading);

    boolean isLoading();
}
