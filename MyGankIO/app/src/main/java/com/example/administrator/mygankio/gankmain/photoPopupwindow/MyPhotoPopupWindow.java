package com.example.administrator.mygankio.gankmain.photoPopupwindow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.gankmain.searchpage.SearchContract;

/**
 * Created by tdfz on 2017/9/28.
 */

public class MyPhotoPopupWindow {
    PopupWindow mPopupWindow;
    public MyPhotoPopupWindow(Context context){
        View popupView = LayoutInflater.from(context).inflate(R.layout.photo_popupwindow,null);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
    }

    public void show(){
//        mPopupWindow.showAsDropDown();
    }
}
