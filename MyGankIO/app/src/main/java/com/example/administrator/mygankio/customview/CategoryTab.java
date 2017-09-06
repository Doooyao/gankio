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

public class CategoryTab extends android.support.v7.widget.AppCompatImageView {
    /**
     * 文本
     */
    private String mText;
    /**
     * 文本的颜色
     */
    private int mTextColor;
    /**
     * 文本的大小
     */
    private int mTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;
    private int mPaintStokeWidth;
    private Path outPath;
    private Path inPath;
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
        mTextSize = textSize;
        mText = s;
        mTextColor = color;
        mPaintStokeWidth = 4;
        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mPaint.setStrokeWidth(mPaintStokeWidth);
        mPaint.setAntiAlias(true);
        mBound = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mBound);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else
        {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            //这里添加边缘
            width = desired+2*mPaintStokeWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            //这里添加边缘
            height = desired+2*mPaintStokeWidth;
        }
        setMeasuredDimension(width, height);
        setPath();
    }

    private void setPath() {
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        outPath = new Path();
        outPath.moveTo(height/2,height);
        RectF rectf = new RectF(0,0,height,height);
        outPath.arcTo(rectf,90,180);
        outPath.lineTo(width-height/2,0);
        RectF rectf2 = new RectF(width-height,0,width,height);
        outPath.arcTo(rectf2,270,180);
        outPath.close();

        inPath = new Path();
        inPath.moveTo(height/2,height-mPaintStokeWidth);
        RectF rectf3 = new RectF(mPaintStokeWidth,mPaintStokeWidth,height-mPaintStokeWidth,height-mPaintStokeWidth);
        inPath.arcTo(rectf3,90,180);
        inPath.lineTo(width-height/2,mPaintStokeWidth);
        RectF rectf4 = new RectF(width-height+mPaintStokeWidth,mPaintStokeWidth,width-mPaintStokeWidth,height-mPaintStokeWidth);
        inPath.arcTo(rectf4,270,180);
        inPath.close();;

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //// TODO: 2017/9/6 下次优化
        Bitmap tabBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        System.out.println(getWidth()+"+"+getHeight());
        Canvas tabcanvas = new Canvas(tabBitmap);
        beCheckedDrawBitmap(tabcanvas);
        canvas.drawBitmap(tabBitmap,0,0,new Paint());
    }
//实心图标
    void beCheckedDrawBitmap(Canvas canvas){
        canvas.drawPath(outPath,mPaint);
        mPaint.setXfermode(new
                PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
        mPaint.setXfermode(null);
    }
    //空心图标
    void unCheckedDrawBitmap(Canvas canvas){
        canvas.drawPath(outPath,mPaint);
        mPaint.setXfermode(new
                PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawPath(inPath,mPaint);
        mPaint.setXfermode(null);
        canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
        mPaint.setXfermode(new
                PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawPath(outPath,mPaint);
        mPaint.setXfermode(null);
    }

}