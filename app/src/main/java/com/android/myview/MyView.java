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
 *  基础
 */
public class MyView extends View {

    private Paint paint; //圆形
    private Paint paint2; //矩形
    private Paint paint3;


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



    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(300,110,100,paint);

        /**   RectF设置矩形的位置
         *  left 子view左上角距离父view左边的距离
         *  top  子view左上角距离父view左上方距离
         *  right 子view右下角距父view左边的距离  （决定画出的矩形宽度  矩形的宽 = left（子View距父view左边的距离）+ 你想要的宽度）
         *  bottom 子view底部距父view顶部的距离    （决定画出矩形的高度  同上   top+你想要的高度   ）
         *
         */
        RectF rectF = new RectF(100, 320, (100+400), (320+400) );
        //绘制圆角矩形
        canvas.drawRoundRect(rectF,10,10,paint2);



    }
}
