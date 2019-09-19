package com.android.myview.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * author : zf
 * date   : 2019/9/16
 * You are the best.
 */
public class MyTextView1 extends AppCompatTextView {


    private Paint paint;

    public MyTextView1(Context context) {
        super(context);
    }

    public MyTextView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3);
        paint.setTextSize(60);
        paint.setColor(Color.BLUE);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,getWidth()+100,getHeight()+50,paint);
        super.onDraw(canvas);
    }

}
