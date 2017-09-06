package com.example.administrator.mygankio.gankmain.homePage;

import com.example.administrator.mygankio.BaseMvpPresenter;
import com.example.administrator.mygankio.BaseMvpView;
import com.example.administrator.mygankio.adapter.HomePageListAdapter;

import java.util.List;

/**
 * Created by tdfz on 2017/9/5.
 */

public interface HomeContract {
    interface view extends BaseMvpView<HomeContract.presenter>{
        void showRefreshRecycleView();
        void hideRefreshRecycleView();
        void refreshRecycleview();
        HomePageListAdapter getHomePageAdapter();

    }




    interface presenter extends BaseMvpPresenter{
        void getGankPushData();
        void addGankDateDataBean(List<String> strings, int startIndex, int endIndex);
    }
}
