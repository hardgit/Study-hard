package com.android.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * author : zf
 * date   : 2019/9/6
 * You are the best.
 *  基础一 Paint and Canvas 的使用
 */
public class MyView extends View {

    private Paint paint; //圆形
    private Paint paint2; //矩形
    private Paint paint3;//文本
    private RectF roundRectF;
    private RectF arcRectF;
    private RectF arcRectF2;
    private Path path;
    private RectF rectF;
    private int[] number = new int[]{1,1,80,50,200,300,180};

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void initView(){
        paint = new Paint();
        paint.setColor(0xaaff0000);
        paint.setAntiAlias(true);//抗锯齿
        paint.setStyle(Paint.Style.FILL);

        paint2 = new Paint();
        paint2.setColor(0xaafff000);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);//抗锯齿

        paint3 = new Paint();
        paint3.setColor(0xaaDDF000);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(1);
        paint3.setTextSize(18);//字体大小
        paint3.setColor(Color.BLACK);
        paint3.setAntiAlias(true);//抗锯齿

        roundRectF = new RectF(100, 320, (100+400), (320+300) );
        arcRectF = new RectF(200,200,800,500);
        arcRectF2 = new RectF(400,400,600,800);
        path = new Path();
//        rectF = new RectF();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         *    X = 子View左上角距父View的距离
         *    Y =  子View右下角距父view顶部的距离
         *    radius = 半径
         */
        //绘制圆行
//        canvas.drawCircle(300,110,100,paint);

        /**   RectF设置矩形的位置
         *  left 子view左上角距离父view左边的距离
         *  top  子view左上角距离父view左上方距离
         *  right 子view右下角距父view左边的距离  （决定画出的矩形宽度  矩形的宽 = left（子View距父view左边的距离）+ 你想要的宽度）
         *  bottom 子view底部距父view顶部的距离    （决定画出矩形的高度  同上   top+你想要的高度   ）
         */
//        //绘制圆角矩形
//        canvas.drawRoundRect(roundRectF,10,10,paint2);
//        //绘制文本
//        canvas.drawText("跟党走，打胜仗！",100,300,paint3);
        //绘制弧形
        /**
         *  arcRectF : 摆放位置
         *  startAngle : 正数：顺时针 ， 负数：逆时针
         *  sweepAngle : 弧
         *  useCenter : true:扇形 ，  false： 弧形
         *  paint ： 画笔
         */
       canvas.drawArc(arcRectF,-110,100,true,paint);
       canvas.drawArc(arcRectF,20,140,false,paint);
       paint.setStyle(Paint.Style.STROKE);
       canvas.drawArc(arcRectF,180,60,false,paint);


        canvas.translate(200,getWidth()-200);//移动坐标系到中心点
        paint.setStrokeWidth(2);//线的粗细
        path.lineTo(0,400);//画线
        path.lineTo(800,400);
        canvas.drawPath(path,paint);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        int width = 100;
        for (int i = 1; i < number.length; i++) {
            canvas.drawRect(width,number[i],200*i,399,paint);
            canvas.drawText(number[i]+"",width+40,455,paint3);
            Log.e("TAG",width+50+"------"+455);
            width = width+200;
        }

    }
}
