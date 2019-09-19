package com.android.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : zf
 * date   : 2019/9/18
 * You are the best.
 */
public class MyViewX3 extends View {


    public MyViewX3(Context context) {
        super(context);
    }

    public MyViewX3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewX3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
