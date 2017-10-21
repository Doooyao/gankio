package com.example.administrator.mygankio.gankmain.homePage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.mygankio.BaseMvpPresenter;
import com.example.administrator.mygankio.BaseMvpView;

/**
 * Created by tdfz on 2017/9/5.
 */

public interface HomeContract {
    interface view extends BaseMvpView<HomeContract.presenter>{

        void showFirstData();
        Context takeContext();
        void setRecycleAdapter(RecyclerView.Adapter adapter);

    }

    interface presenter extends BaseMvpPresenter{
        void refreshData();
        void loadingMoreData();
    }
}
