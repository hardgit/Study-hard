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
 * 扇形图简陋版本
 */
public class MyView2 extends View {

    private Paint paint;
    private RectF rectF;
    private RectF rectF2;
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
        rectF = new RectF(180,180,800,800);
        rectF2 = new RectF(200,200,800,800);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(rectF,180,130,true,paint);
        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF2,79,100,true,paint);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(rectF2,310,50,true,paint);
        paint.setColor(Color.parseColor("#663366"));
        canvas.drawArc(rectF2,360,7,true,paint);
        paint.setColor(Color.GRAY);
        canvas.drawArc(rectF2,-354,7,true,paint);
        paint.setColor(Color.GREEN);
        canvas.drawArc(rectF2,-347,66,true,paint);


    }
}
