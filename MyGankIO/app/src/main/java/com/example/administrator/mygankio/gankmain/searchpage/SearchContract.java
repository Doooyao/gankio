package com.example.administrator.mygankio.gankmain.searchpage;

import android.os.Bundle;

import com.example.administrator.mygankio.BaseMvpPresenter;
import com.example.administrator.mygankio.BaseMvpView;
import com.example.administrator.mygankio.data.GankSearchBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public interface SearchContract {
    interface View extends BaseMvpView<SearchContract.Presenter> {
        int getCurrentPage();
        String getQuery();
        void searchByAllPage(String query);
        void searchByCollectionPage(String query);
    }
    interface Presenter extends BaseMvpPresenter {
        void searchGank();
    }
}
