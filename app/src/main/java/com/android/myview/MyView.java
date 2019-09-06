package com.android.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : zf
 * date   : 2019/9/6
 * You are the best.
 */
public class MyView extends View {

    private Paint paint; //圆形
    private Paint paint2; //矩形
    private Paint paint3;
    private int color = 0xaaff0000;


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void initView(){
        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);//抗锯齿
        paint.setStyle(Paint.Style.FILL);

        paint2 = new Paint();
        paint2.setColor(0xfff000);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);//抗锯齿

        paint3 = new Paint();



    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(300,110,100,paint);

        /**   RectF设置矩形的位置
         *  left 子view左上角距离父控件左边的距离
         *  top  子view左上角距离父控件左上方距离
         *  right 子view右下角距父控件左边的距离  （决定画出的图形矩形宽度）
         *  bottom 子view底部距父控件顶部的距离    （决定画出图形的高度）
         */
        RectF rectF = new RectF(100, 320, 120, 325);

        canvas.drawRoundRect(rectF,10,10,paint);



    }
}
