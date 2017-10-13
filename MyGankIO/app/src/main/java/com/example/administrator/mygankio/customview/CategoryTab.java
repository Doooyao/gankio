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
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by tdfz on 2017/9/6.
 */

public class CategoryTab extends View {
    /**
     * 文本
     */
    private String categoryText;
    /**
     * 文本的颜色
     */
    private int categoryColor;
    /**
     * 文本的大小
     */
    private int categoryTextSize;
    /**
     * 文本的颜色
     */
    private int categoryHeight;
    /**
     * 文本的大小
     */
    private int categoryWidth;


    public CategoryTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CategoryTab(Context context) {
        this(context, null);
    }

    /**
     * 获得我自定义的样式属性
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CategoryTab(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
        setattr(64,"Android",Color.parseColor("#ff00ff"));
    }



    public void setattr(int textSize,String s,int color){
        categoryTextSize = textSize;
        categoryText = s;
        categoryColor = color;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightSize!=0){
            categoryHeight = heightSize;
            if (widthMode == MeasureSpec.EXACTLY)
            {
                categoryWidth = widthSize;
            } else
            {
                categoryWidth = categoryHeight*5/2;
            }
            setMeasuredDimension(categoryWidth, categoryHeight);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private void initPath(Path path) {
        path.moveTo(categoryHeight/2,0);
        RectF rectf = new RectF(0,0,categoryHeight,categoryHeight);
        path.arcTo(rectf,-90,-180);
        path.lineTo(categoryWidth-categoryHeight/2,categoryHeight);
        RectF rectf2 = new RectF(categoryWidth-categoryHeight,0,categoryWidth,categoryHeight);
        path.arcTo(rectf2,-270,-180);
        path.close();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Path path = new Path();
        initPath(path);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
        p.setColor(categoryColor);
        canvas.drawPath(path,p);

    }

}