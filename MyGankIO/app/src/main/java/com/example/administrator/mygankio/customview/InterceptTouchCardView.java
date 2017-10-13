package com.example.administrator.mygankio.customview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by tdfz on 2017/10/12.
 */

public class InterceptTouchCardView extends CardView {

    private int mx;
    private int my;
    private int lastx;
    private int lasty;

    public InterceptTouchCardView(Context context) {
        super(context);
    }

    public InterceptTouchCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptTouchCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Boolean intercept=false;
        //获取坐标点：
        int x= (int) ev.getX();
        int y= (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mx =x;
                my =y;
                intercept=false;
                break;
            case MotionEvent.ACTION_MOVE:
                int deletx=x- mx;
                int delety=y- my;
                if(Math.abs(deletx)<20&&Math.abs(delety)<20)
                {
                    intercept=false;
                }
                else {
                    intercept=true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept=false;
                break;
            default:
                break;
        }
        //这里尤其重要，解决了拦截MOVE事件却没有拦截DOWN事件没有坐标的问题
        return intercept;
    }
}

