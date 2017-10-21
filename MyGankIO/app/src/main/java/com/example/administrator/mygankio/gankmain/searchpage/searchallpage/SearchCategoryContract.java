package com.example.administrator.mygankio.gankmain.searchpage.searchallpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.mygankio.BaseMvpPresenter;
import com.example.administrator.mygankio.BaseMvpView;

/**
 * Created by tdfz on 2017/10/17.
 */

public interface SearchCategoryContract {
    interface View extends BaseMvpView<SearchCategoryContract.Presenter> {
        void setAdapter(RecyclerView.Adapter adapter);
        void showBlankBackground(int backgroundType);
        void showList();
        String getCategory();
        Context takeContext();
    }

    interface Presenter extends BaseMvpPresenter {
        void searchGank(String query);
        void loadMoreGank();
    }
}