package com.example.administrator.mygankio.utils;

import android.util.Log;

import com.example.administrator.mygankio.R;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * Created by tdfz on 2017/9/6.
 */

public class StringUtils {
    public List<String> getTitleFromContent(String htmlSource){
        List<String> list = new ArrayList<String>();
        String s = "";
        //Pattern pa = Pattern.compile("<title>.*?</title>", Pattern.CANON_EQ);也可以
        Pattern pa = Pattern.compile("\"title\": \".\"");//源码中标题正则表达式
        Matcher ma = pa.matcher(htmlSource);
        while (ma.find())//寻找符合el的字串
        {
            s = ma.group();
        }
        String[] parts = s.split("/");
        for(int i=0,j=parts.length;i<j;i++){
            list.add(parts[i]);
        }
        return list;
    }
    public static String formatUrl(String url){
        if (url.startsWith("http://")||url.startsWith("https://")){
            return url.trim();
        }else if (url.indexOf("://")!=-1){
            Log.w(TAG, "formatUrl: wrongurl");
            return url.trim();
        }else {
            return "http://"+url.trim();
        }
    }
    public static int getImageResIdByCategoryString(String category){
        switch (category){
            case "iOS":
                return R.drawable.ic_apple_apple;
            case "Android":
                return R.drawable.ic_android_black_24dp;
            case "App":
                return R.drawable.ic_apps_black_24dp;
            case "瞎推荐":
                return R.drawable.ic_star_black_24dp;
            case "拓展资源":
                return R.drawable.ic_explore_black_24dp;
            case "休息视频":
                return R.drawable.ic_video_library_black_24dp;
            case "前端":
                return R.drawable.ic_html5;
            case "福利":
                return R.drawable.ic_directions_car_black_24dp;
            default:
                return 0;
        }
    }
}
