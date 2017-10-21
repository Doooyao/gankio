package com.example.administrator.mygankio.gankmain.gankwebpage;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.administrator.mygankio.R;
import com.github.clans.fab.FloatingActionMenu;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tdfz on 2017/9/19.
 */

public class WebViewFragment extends Fragment implements WebViewContract.view ,View.OnClickListener {
//    Long collectionInfoId;
    SwipeRefreshLayout swipeRefreshLayout;
    WebView webView;
    View rootView;
//    FloatingActionMenu fabMenu;
    float scaleThisTime;
    WebViewContract.presenter webViewPresenter;
    com.github.clans.fab.FloatingActionButton fabSun1;
    com.github.clans.fab.FloatingActionButton fabSun2;
    com.github.clans.fab.FloatingActionButton fabSun3;
    com.github.clans.fab.FloatingActionButton fabSun4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.web_view_fragment,container,false);
        initview();
        setlistener();
        return rootView;
    }


    private void initview() {
        webView = (WebView) rootView.findViewById(R.id.webview_collectioncontent);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        configWebView(webView);
        webViewPresenter.start();
//        initFabMenu();
    }

    private void configWebView(final WebView webView) {
        //设置进度条
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress > 90) {
                    //隐藏进度条
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    if (!swipeRefreshLayout.isRefreshing())
                        swipeRefreshLayout.setRefreshing(true);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        WebSettings webSettings = webView.getSettings();
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存路径
//        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);

        webSettings.setLoadWithOverviewMode(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
        webSettings.setDefaultFontSize(12);
        //设置是否可缩放，会出现缩放工具（若为true则上面的设值也默认为true）
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //设置webview可以看视频
        webSettings.setPluginState(WebSettings.PluginState.ON);
//        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView wv, String url) {
                if(url == null) return true;
                if(!(url.startsWith("http://")||url.startsWith("https://"))) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                        return true;
                    }
                }
                //处理http和https开头的url
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                System.out.println(request.getRequestHeaders().toString());
            }

            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                super.onScaleChanged(view, oldScale, newScale);
                scaleThisTime = newScale;
            }
            });
            // 支持使用localStorage(H5页面的支持)
        // 设置WebView的客户端
        webSettings.setDomStorageEnabled(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        WebView.setWebContentsDebuggingEnabled(true);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptThirdPartyCookies(webView,true);
        }
//        webSettings.setUserAgentString("Mozilla/5.0 (Linux; Android 7.1.1; ONEPLUS A3000 Build/NMF26F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.116 Mobile Safari/537.3");

    }


    private void setlistener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //重新刷新页面
                webView.loadUrl(webView.getUrl());
            }
        });
    }

//    private void initFabMenu() {
//        fabSun1 = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.menu_item_1);
//        fabSun2 = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.menu_item_2);
//        fabSun3 = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.menu_item_3);
//        fabSun4 = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.menu_item_4);
//        //// TODO: 2017/7/6 暂时不考虑性能问题
//        fabSun1.setOnClickListener(this);
//        fabSun2.setOnClickListener(this);
//        fabSun3.setOnClickListener(this);
//        fabSun4.setOnClickListener(this);
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_item_1:
//                webViewPresenter.markDownNow(webView);
                break;
            case R.id.menu_item_2:
//                webViewPresenter.gotoLastMark(webView);
                break;
        }
    }


    @Override
    public float getWebViewScale() {
//        暂时使用这个方式
        return webView.getScale();
    }

    @Override
    public void loadUrl() {
        Bundle b = getArguments();
        String url = b.getString("GankUrl");
        //// TODO: 2017/9/26 暂时这么解决
        //if bilibili——> change UA to windows
        if (url.indexOf("bilibili")!=-1){
            webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
//            String mUrl =  (url.substring(0,url.length()-1)+".html").replace("www","m");
//            webView.loadUrl(mUrl);
        }
// else {
            webView.loadUrl(url);
//        }
    }

    @Override
    public void setPresenter(WebViewContract.presenter presenter) {
        webViewPresenter =  checkNotNull(presenter);
    }

    @Override
    public void showRefreshingBar() {

    }

    @Override
    public void dismissRefreshingBar() {

    }

    @Override
    public void showLoadingMoreBar() {

    }

    @Override
    public void dismissLoadingMoreBar() {

    }
}
