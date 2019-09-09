package com.android.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : zf
 * date   : 2019/9/9
 * You are the best.
 */
public class MyView2 extends View {

    private Paint paint;
    private RectF rectF;
    private RectF rectF2;
    private RectF rectF3;
    private RectF rectF4;
    private RectF rectF5;
    private RectF rectF6;

    public MyView2(Context context) {
        super(context);
    }

    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        rectF = new RectF(200,200,800,800);
        rectF2 = new RectF(200,200,800,800);
        rectF3 = new RectF(200,200,800,800);
        rectF4 = new RectF(200,200,800,800);
        rectF5 = new RectF(200,200,800,800);
        rectF6 = new RectF(200,200,800,800);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(rectF,180,130,true,paint);
        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF2,79,100,true,paint);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(rectF3,310,50,true,paint);
        paint.setColor(Color.parseColor("#663366"));
        canvas.drawArc(rectF4,360,7,true,paint);
        paint.setColor(Color.GRAY);
        canvas.drawArc(rectF5,-354,7,true,paint);
        paint.setColor(Color.GREEN);
        canvas.drawArc(rectF6,-347,66,true,paint);
    }
}
