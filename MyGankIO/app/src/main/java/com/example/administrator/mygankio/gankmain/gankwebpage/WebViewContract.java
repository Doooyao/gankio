package com.example.administrator.mygankio.gankmain.gankwebpage;

import android.webkit.WebView;

import com.example.administrator.mygankio.BaseMvpPresenter;
import com.example.administrator.mygankio.BaseMvpView;
import com.example.administrator.mygankio.gankmain.homePage.HomeContract;
import com.example.administrator.mygankio.gankmain.homePage.HomePageListAdapter;

/**
 * Created by tdfz on 2017/9/19.
 */

public interface WebViewContract {

    interface view extends BaseMvpView<WebViewContract.presenter> {
        float getWebViewScale();
        void loadUrl();
    }

    interface presenter extends BaseMvpPresenter {
//        void openUrl(WebView webView);
//        void markDownNow(WebView webView);
//        void gotoLastMark(WebView webView);
    }
}
