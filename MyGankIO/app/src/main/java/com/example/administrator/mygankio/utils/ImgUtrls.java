package com.example.administrator.mygankio.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

import com.example.administrator.mygankio.data.ApiService;
import com.example.administrator.mygankio.data.GankSearchBean;
import com.tinify.Options;
import com.tinify.Source;
import com.tinify.Tinify;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public byte[] Bitmap2Bytes(Bitmap bitmap){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return b;
    }
    public Bitmap Bytes2Bitmap(byte[] b){
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
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


    public static String getImgUrlFromUrl(final String url, final GetImgUrlListener getImgUrlListener){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url+"/")
                .client(new OkHttpClient())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.getResponseBodyByUrl("");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (url.indexOf("bilibili")!=-1){
                  if (response.code()==200){
                      String source = null;
                      try {
                          source = response.body().string();
                      String pattern = "(?<=<img\\ssrc=\"//).*?(?=\" style=\"display:none;\"\\sclass=\"cover_image\"/>)";
                      // 创建 Pattern 对象
                      Pattern r = Pattern.compile(pattern);
                      // 现在创建 matcher 对象
                      Matcher m = r.matcher(source);
                      if (m.find( )) {
                          getImgUrlListener.getImgUrl(m.group(0));
                      }
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
        return null;
    }
    //// TODO: 2017/10/1 防止直接加载omm 暂时不用 
    private Bitmap decodeBitmap(String path){
//        BitmapFactory.Options op = new BitmapFactory.Options();
//        //inJustDecodeBounds 
//        //If set to true, the decoder will return null (no bitmap), but the out…
//        op.inJustDecodeBounds = true;
//        Bitmap bmp = BitmapFactory.decodeFile(path, op); //获取尺寸信息
//        //获取比例大小
//        int wRatio = (int)Math.ceil(op.outWidth/DISPLAY_WIDTH);
//        int hRatio = (int)Math.ceil(op.outHeight/DISPLAY_HEIGHT);
//        //如果超出指定大小，则缩小相应的比例
//        if(wRatio > 1 && hRatio > 1){
//            if(wRatio > hRatio){
//                op.inSampleSize = wRatio;
//            }else{
//                op.inSampleSize = hRatio;
//            }
//        }
//        op.inJustDecodeBounds = false;
//        bmp = BitmapFactory.decodeFile(path, op);
//        return bmp;
        return null;
    }

}
