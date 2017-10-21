package com.example.administrator.mygankio.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by tdfz on 2017/10/19.
 */

public class CircleSpreadBackGround extends AppBarLayout {
    float radius;
    int backGroundColor;
    int upGroundColor;
    float centerX = 0;
    int width;
    int height;
    float centerY = 0;
    private Paint p;
    private ImageView imageView;

    public CircleSpreadBackGround(Context context) {
        super(context);
        init();
    }

    public CircleSpreadBackGround(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);init();
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    private void init() {
        radius = 0;
        backGroundColor = Color.parseColor("#ffffff");
        upGroundColor = Color.parseColor("#ffffff");
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(backGroundColor);
        p = new Paint();
        p.setColor(upGroundColor);
        p.setStyle(Paint.Style.FILL);
        p.setAntiAlias(true);
        canvas.drawCircle(centerX,centerY,radius, p);
        super.onDraw(canvas);
    }

    public void setColor(int color){
        backGroundColor = color;
        invalidate();
    }
    public void changetoAnotherColor(final int color, float x, float y){
        final float Maxradius =Math.max(Math.max(Math.max(getlength(0,0,x,y),getlength(0,height,x,y)),getlength(width,0,x,y))
                ,getlength(width,height,x,y));
        centerX = x;
        centerY = y;
        upGroundColor = color;
        ValueAnimator animator = ValueAnimator.ofFloat(0,Maxradius);
        animator.setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius = (float) animation.getAnimatedValue();
                if (radius==Maxradius){
                    backGroundColor = color;
                    radius = 0;
                }
                invalidate();
            }
        });
        animator.start();

    }

    float getlength(float startx,float starty,float endx,float endy){
        float length = (float) Math.sqrt(Math.pow(startx-endx,2)+Math.pow(starty-endy,2));
        return length;
    }

}
