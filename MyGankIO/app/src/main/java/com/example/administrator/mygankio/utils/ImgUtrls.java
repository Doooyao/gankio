package com.example.administrator.mygankio.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

import com.example.administrator.mygankio.data.GankSearchBean;
import com.tinify.Options;
import com.tinify.Source;
import com.tinify.Tinify;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/7/22.
 */

public class ImgUtrls {

    public List<String> getImgUrlListFromResultBean(GankSearchBean.ResultsBean resultsBean){
        String s = resultsBean.getReadability();
        List<String> imgs = new ArrayList<>();
        if (s!=null){
            // 按指定模式在字符串查找
            String pattern = "http.*?\\.gif";

            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);

            // 现在创建 matcher 对象
            Matcher m = r.matcher(s);
            while (m.find()) {
                Log.d(TAG, "getImgUrlListFromResultBean:"+m.group());
                imgs.add(m.group());
            }
            return imgs;
        }else {
            return null;
        }

    }

    public int getImgSizeFromUrl(String s) throws IOException {
        int size;
        URL url = new URL(s);
        URLConnection conn = url.openConnection();
        size = conn.getContentLength();
        if (size < 0){
            conn.getInputStream().close();
            Log.d(TAG, "getImgSizeFromUrl: "+size);
            return 0;
        }
        else{
            conn.getInputStream().close();
            Log.d(TAG, "getImgSizeFromUrl: "+size);
            return size;
        }
    }
    public List<String> getMattchSizeImgList(List<String> imgs,int size,int maxCount) throws IOException {
        List<String> mattchImgs = new ArrayList<>();
        if (imgs!=null){
            if (imgs.size()>0){
                for (String imgurl:imgs){
                    if (mattchImgs.size()<maxCount){
                        int imgsize = getImgSizeFromUrl(imgurl);
                        if (imgsize>=size){
                            mattchImgs.add(imgurl);
                        }
                    }else {
                        return mattchImgs;
                    }
                }
            }
        }
        return mattchImgs;
    }

    public void getImgMiniFromResultBean(final GankSearchBean.ResultsBean resultsBean, final OnBitmapGetSuccess onBitmapGetSuccess){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> imgs = getImgUrlListFromResultBean(resultsBean);
                try {
                    List<String> mattchImgs = getMattchSizeImgList(imgs,1000,1);
                    if (mattchImgs.size()>0){
                        for (final String imgurl:mattchImgs){

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println(imgurl);
                                    onBitmapGetSuccess.OnSuccess(imgurl);
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public interface OnBitmapGetSuccess{
        void OnSuccess(String bitmapurl);
    }
}
