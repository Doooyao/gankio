package com.example.administrator.mygankio.gankmain.gankwebpage;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.administrator.mygankio.R;

public class WebViewActivity extends AppCompatActivity {

    private WebViewFragment webViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent i = getIntent();
        String url = i.getStringExtra("GankUrl");
        addFragment(url);
    }

    private void addFragment(String s) {
        webViewFragment = new WebViewFragment();
        WebViewPresenter webViewPresenter = new WebViewPresenter(webViewFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("GankUrl", s);
        webViewFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.rl_web_view_fragment_container, webViewFragment);
        fragmentTransaction.commit();
    }

    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(webViewFragment.webView.canGoBack())
            {
                webViewFragment.webView.goBack();//返回上一页面
                return true;
            }
            else
            {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
