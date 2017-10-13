package com.example.administrator.mygankio.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tdfz on 2017/10/10.
 */

public class DateShowCircleView extends View {
    int width;
    //yyyy-mm-dd
    String date;
    int bcColor;
    public DateShowCircleView(Context context) {
        super(context);
        init();
    }

    public DateShowCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DateShowCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        date = "2017/09/24";
        bcColor = Color.parseColor("#887711");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = Math.min(getMeasuredHeight(),getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint bcPaint = new Paint();
        bcPaint.setColor(bcColor);
        bcPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width/2,width/2,width/2,bcPaint);
        Paint numPaint= new Paint();
        numPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawLine(0,2/3*width,4/3*width,0,numPaint);
        if (date!=null){
            if (date.length()==10){





            }
        }












        super.onDraw(canvas);
    }
}
