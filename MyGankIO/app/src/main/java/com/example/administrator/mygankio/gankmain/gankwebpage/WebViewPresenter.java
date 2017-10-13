package com.example.administrator.mygankio.gankmain.gankwebpage;

import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.WebView;

import com.example.administrator.mygankio.gankmain.searchpage.SearchContract;

import static android.content.ContentValues.TAG;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tdfz on 2017/9/19.
 */

public class WebViewPresenter implements WebViewContract.presenter{
    WebViewContract.view webViewFragment;
    public WebViewPresenter (@NonNull WebViewContract.view webViewFragment){
        this.webViewFragment = checkNotNull(webViewFragment,"searchFragment cannot b null");
        webViewFragment.setPresenter(this);
    }

//    @Override
//    public void openUrl(WebView webView) {
//        webView.loadUrl(collectionInfoBean.getUrl());
//    }

//    @Override
//    public void markDownNow(WebView webView) {
//        //初始化当前的scale 如果网页不支持缩放 则初始化失败 scale为0.0
//        float scale = webViewActivity.getWebViewScale();
//        String url = webView.getUrl();
//        int scrollx = webView.getScrollX();
//        int scrolly = webView.getScrollY();
//
//        MarkerInfoBean mark = collectionInfoBean.getMarkerInfoBean();
//        if (mark==null){
//            mark = new MarkerInfoBean();
//            mark.setUrl(url);
//            mark.setScale(scale);
//            mark.setScrollX(scrollx);
//            mark.setScrollY(scrolly);
//            dbManager.insertMarkerInfo(mark);
//            collectionInfoBean.setMarkerInfoBean(mark);
//            dbManager.updateCollectionInfo(collectionInfoBean);
//        }else {
//            mark.setUrl(url);
//            mark.setScale(scale);
//            mark.setScrollX(scrollx);
//            mark.setScrollY(scrolly);
//            dbManager.updateMarkerInfo(mark);
//        }
//    }
//
//    @Override
//    public void gotoLastMark(WebView webView) {
//        if (collectionInfoBean.getMarkerInfoBean()!=null){
//            if (!collectionInfoBean.getMarkerInfoBean().getUrl().equals(webView.getUrl())){
//                webView.loadUrl(collectionInfoBean.getMarkerInfoBean().getUrl());
//            }
//            //初始化当前的scale进行一倍缩放 如果网页不支持缩放 则初始化失败 scale为0.0
//            if (webViewActivity.getWebViewScale()!=0){
//                webView.zoomBy(collectionInfoBean.getMarkerInfoBean().getScale()/webViewActivity.getWebViewScale());
//            }
//            webView.scrollTo(collectionInfoBean.getMarkerInfoBean().getScrollX(),
//                    collectionInfoBean.getMarkerInfoBean().getScrollY());
//        }else {
//            Log.d(TAG, "gotoLastMark: 没有书签"+collectionInfoBean.getMid());
//        }
//
//
//    }

    @Override
    public void start() {
        webViewFragment.loadUrl();
    }
}
