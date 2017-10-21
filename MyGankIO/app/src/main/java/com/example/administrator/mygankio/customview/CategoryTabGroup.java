package com.example.administrator.mygankio.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.utils.Densityutils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdfz on 2017/9/6.
 */

public class CategoryTabGroup extends RelativeLayout {
    Context context;
    private View rootView;
    List<TextView> textViews;
    private LinearLayout ll;
    private ImageView ivSeekBar;
    OnTabClickListener onTabClickListener;

    public CategoryTabGroup(Context context) {
        super(context);
        initview(context);
    }

    public CategoryTabGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }

    public CategoryTabGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context);
    }

    private void initview(Context context) {
        this.context  = context;
        rootView = LayoutInflater.from(context).inflate(R.layout.search_category_tab_layout,
                this,false);
        removeAllViews();
        addView(rootView);

        ll = (LinearLayout) rootView.findViewById(R.id.categorytab_parent);
        textViews = new ArrayList<>();
        ivSeekBar = (ImageView) rootView.findViewById(R.id.categorytab_seekbar);
        resetTab();
    }

    public void resetTab() {
        for (TextView tv:textViews){
            ll.addView(tv);
        }
    }

    public void addTab(String tabName){
        TextView textView = new TextView(context);
        textView.setText(tabName);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) context.getResources().getDimension(R.dimen.search_bar_tab_width), ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(12);
        textView.setLayoutParams(params);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textViews.add(textView);
        final int position = textViews.size()-1;
        textView.setOnTouchListener(new OnTouchListener() {

            private float startY;
            private float startX;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getRawX();
                        startY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        if (Math.abs(startX-event.getRawX())<10&&Math.abs(startY-event.getRawY())<10){
                            if (onTabClickListener!=null){
                                onTabClickListener.onTabClick(v,position,event);
                            }
                            ivSeekBar.setTranslationX(v.getX());
                            lightTextViewtext(position);
                            ((TextView)v).setTextColor(Color.parseColor("#ffffff"));
                        }
                        break;
                }
                return false;
            }
        });
    }
    public interface OnTabClickListener{
        void onTabClick(View v, int position, MotionEvent event);
    }
    public void setOnTabClickListener(OnTabClickListener onTabClickListener){
        this.onTabClickListener = onTabClickListener;
    }

    void lightTextViewtext(int position){
        for (TextView tv:textViews){
          tv.setTextColor(Color.parseColor("#88ffffff"));
        }
    }
   public void setCurrentPosition(int position){
        lightTextViewtext(position);
        textViews.get(position).setTextColor(Color.parseColor("#ffffff"));
        ivSeekBar.setTranslationX(textViews.get(position).getX());
    }

}