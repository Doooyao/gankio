package com.example.administrator.mygankio.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdfz on 2017/10/11.
 */

public class TxxDraggableCardContainer extends RelativeLayout{
    int touchStartX = 0;
    int touchStartY = 0;
    float centerX = 0;
    float minHeight = 0;
    float secondPageScale = 0.8f;
    float turnPercent = 0.5f;
    float showNextWholeCardPercent = 0.1f;
    float centerY = 0;
    int topChildIndex = 0;
    List<View> childViews;
    public TxxDraggableCardContainer(Context context) {
        super(context);
        init();
    }

    public TxxDraggableCardContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TxxDraggableCardContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        childViews = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
        super.onDraw(canvas);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        childViews.add(child);
        initChilds();
    }


    private void initChilds() {
        View topPageView = childViews.get(topChildIndex);
        final View nextPageView = childViews.get((topChildIndex+1)%childViews.size());
        topPageView.bringToFront();
        topPageView.setOnTouchListener(new OnTouchListener() {
            @Override
                public boolean onTouch(final View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            centerX = v.getX()+v.getMeasuredWidth()/2;
                            centerY = v.getY()+v.getMeasuredHeight()/2;
                            touchStartX = (int) event.getRawX();
                            touchStartY = (int) event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (touchStartX==touchStartY&&touchStartX==0){
                                centerX = v.getX()+v.getMeasuredWidth()/2;
                                centerY = v.getY()+v.getMeasuredHeight()/2;
                                touchStartX = (int) event.getRawX();
                                touchStartY = (int) event.getRawY();
                            }else {
                                ViewCompat.setTranslationX(v,event.getRawX()-touchStartX);
                                ViewCompat.setTranslationY(v,event.getRawY()-touchStartY);
                                float radius = 0;
                                if (touchStartY>=centerY){
                                    radius = -(event.getRawX()-touchStartX)/v.getMeasuredWidth()*30;
                                }else {
                                    radius = (event.getRawX()-touchStartX)/v.getMeasuredWidth()*30;
                                }
                                ViewCompat.setRotation(v,radius);
                                if (nextPageView.getVisibility()==GONE){
                                    nextPageView.setVisibility(VISIBLE);
                                }
                                if (Math.abs(event.getRawX()-touchStartX)/v.getMeasuredWidth()<=showNextWholeCardPercent){

                                    ViewCompat.setScaleX(nextPageView,
                                            Math.abs(event.getRawX()-touchStartX)/(v.getMeasuredWidth()*showNextWholeCardPercent)*(1-secondPageScale)+secondPageScale);
                                    if (nextPageView.getMeasuredHeight()>v.getMeasuredHeight()){

                                    }else {
                                        ViewCompat.setScaleY(nextPageView,
                                                Math.abs(event.getRawX()-touchStartX)/(v.getMeasuredWidth()*showNextWholeCardPercent)*(1-secondPageScale)+secondPageScale);

                                    }
                                }else {
                                    ViewCompat.setScaleX(nextPageView,1);
                                    ViewCompat.setScaleY(nextPageView,1);
                                }
//                                //滑动超过一定距离 翻页
//                                if (Math.abs(event.getRawX()-touchStartX)/v.getMeasuredWidth()>turnPercent){
//                                    nextPageView.bringToFront();
//                                }else {
//                                    v.bringToFront();
//                                }
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            if (Math.abs(event.getRawX()-touchStartX)/v.getMeasuredWidth()>turnPercent){
                                if (topChildIndex==childViews.size()-1){
                                    topChildIndex = 0;
                                }else {
                                    topChildIndex ++;
                                }
                                initChilds();
                            }else {

                            }
                            ValueAnimator valueAnimator = ValueAnimator.ofFloat(1,0);
                            valueAnimator.setDuration(250);
                            final float startX = v.getTranslationX();
                            final float startY = v.getTranslationY();
                            final float startRadius = v.getRotation();
                            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    ViewCompat.setTranslationX(v,startX*(float)animation.getAnimatedValue());
                                    ViewCompat.setTranslationY(v,startY*(float)animation.getAnimatedValue());
                                    ViewCompat.setRotation(v,startRadius*(float)animation.getAnimatedValue());
                                }
                            });
                            valueAnimator.start();
                            touchStartX = 0;
                            touchStartY = 0;
                            break;
                    }
                    return true;
                }

        });
    }
}
