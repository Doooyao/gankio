package com.example.administrator.mygankio.gankmain.searchpage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.mygankio.R;

/**
 * Created by Administrator on 2017/7/21.
 */

public class SearchCategoryTabView extends android.support.v7.widget.AppCompatTextView{
    float height;
    float width;
    int color;
    public SearchCategoryTabView(Context context) {
        super(context);
        init(context, null);
    }

    public SearchCategoryTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SearchCategoryTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context c,AttributeSet attr) {
        TypedArray t = c.obtainStyledAttributes(attr, R.styleable.SearchCategoryTabView);
        color = t.getColor(R.styleable.SearchCategoryTabView_color,Color.parseColor("#00ff00"));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
        height = getHeight();
        width = getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.moveTo(height/2,height);
        RectF rectf = new RectF(0,0,height,height);
        path.arcTo(rectf,90,180);
        path.lineTo(width-height/2,0);
        RectF rectf2 = new RectF(width-height,0,width,height);
        path.arcTo(rectf2,270,180);
        path.close();
        Paint p = new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
        p.setAntiAlias(true);
        canvas.drawPath(path,p);
        super.onDraw(canvas);
    }
}
