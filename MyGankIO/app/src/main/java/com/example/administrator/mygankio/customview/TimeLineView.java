package com.example.administrator.mygankio.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tdfz on 2017/10/9.
 */

public class TimeLineView extends View {
    int width;
    int heigth;
    int startposition = 0;
    int positionCounts = 20;
    int space;
    public TimeLineView(Context context) {
        super(context);
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        heigth = getMeasuredHeight();
        space = heigth/positionCounts;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.parseColor("#000000"));
        p.setStyle(Paint.Style.FILL);
        for (int i = 0;i<positionCounts;i++)
        {   if (i%5==0){
            canvas.drawLine(0,space/2+i*space,width,space/2+i*space,p);
        }
            canvas.drawLine(0,space/2+i*space,width/2,space/2+i*space,p);

        }
        super.onDraw(canvas);
    }
}
