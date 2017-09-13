package com.example.administrator.mygankio.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.mygankio.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdfz on 2017/9/7. 暂时只有最多两片 以后再完善
 */

public class TangramView extends View {
    int width;
    int heigth;
    int circumference;
    int count;
    int cracksWidth;
    Point startPoint;
    int centerColor;
    List<Point> pointList;
    Bitmap centerBitmap;
    int centerCircleRadius;
    int angleRadius;
    List<PieceOfView> pieceList;
    public TangramView(Context context) {
        this(context,null);
    }

    public TangramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TangramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initdata(2);
    }

    private void initdata(int count) {
        this.count = count;
        cracksWidth = 8;
        centerColor = Color.parseColor("#330909");
        angleRadius = 4;
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        heigth = getMeasuredHeight();
        centerCircleRadius = heigth/4;
        circumference = width*2+heigth*2;
        startPoint.set(width/2,0);
        initPointList();
//        initPiece();
    }

    private void initPointList() {
        pointList= new ArrayList<>();
        if (count>1){
            int pointSpace = circumference/count;
            for (int i = 0;i<count;i++) {
                Point p = new Point();
                if (pointSpace*i>circumference-width/2){
                    p.set(width/2-(circumference-pointSpace*i),0);
                }else if (pointSpace*i>circumference/2+width/2){
                    p.set(0,heigth-(pointSpace*i-width*3/2-heigth));
                }else if (pointSpace*i>heigth+width/2){
                    p.set(width-(pointSpace*i-width/2-heigth),heigth);
                }else if (pointSpace*i>width/2){
                    p.set(width,pointSpace*i-width/2);
                }else {
                    p.set(width/2+pointSpace*i,0);
                }
                pointList.add(p);
            }
        }
    }

    private void initPiece(List<Point> pointList) {

        pieceList = new ArrayList<>();
        if (count==1){
            PieceOfView piece = new PieceOfView();
//            piece.path.moveTo();
        }else if (count == 2){
            PieceOfView piece = new PieceOfView();
            piece.path.moveTo(0,0);
            piece.path.lineTo(width/2,0);
            piece.path.lineTo(width/2,heigth);
            piece.path.lineTo(0,heigth);
            piece.path.close();
            piece.color = Color.parseColor("#998800");
            pieceList.add(piece);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap rootBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas rootCanvas = new Canvas(rootBitmap);
        drawPiece(rootCanvas,pieceList);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        rootCanvas.drawCircle(width/2,heigth/2,centerCircleRadius+cracksWidth,p);
        drawCenterCircle(rootCanvas);
        canvas.drawBitmap(rootBitmap,0,0,new Paint());
        super.onDraw(canvas);
    }

    private void drawPiece(Canvas canvas,List<PieceOfView> pieceList) {
        for (int i = 0;i<pieceList.size();i++){
            Paint piecePaint = new Paint();
            piecePaint.setAntiAlias(true);
            piecePaint.setColor(Color.parseColor("#667722"));
            piecePaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(pieceList.get(i).path,piecePaint);
        }
    }

    private void drawCenterCircle(Canvas canvas) {
        canvas.save();
        canvas.translate(width/2,heigth/2);
        Paint circlePaint = new Paint();
        circlePaint.setColor(centerColor);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0,0,centerCircleRadius,circlePaint);
        circlePaint.setColor(Color.parseColor("#ffffff"));
        centerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_todaypush_white);
        canvas.drawBitmap(centerBitmap,new Rect(0,0,centerBitmap.getWidth(),centerBitmap.getHeight()),
                new Rect(-centerCircleRadius,-centerCircleRadius,centerCircleRadius,centerCircleRadius),circlePaint);
        canvas.restore();
    }

    public class PieceOfView{
        Path path = new Path();
        int color;
        String pieceText;
    }
}
