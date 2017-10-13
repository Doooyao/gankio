package com.example.administrator.mygankio.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.administrator.mygankio.gankmain.gankwebpage.WebViewActivity;

/**
 * Created by tdfz on 2017/9/28.
 */

public class WebUtils {
    public static void gotoWebActivity(Context context,String url,View view){
        Intent i = new Intent(context, WebViewActivity.class);
        i.putExtra("GankUrl",url);
        context.startActivity(i);
    }
}
