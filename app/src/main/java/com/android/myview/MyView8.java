package com.android.myview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;


/**
 * author : zf
 * date   : 2019/9/16
 * You are the best.
 */
public class MyView8 extends AppCompatImageView {


    private Paint paint;


    public MyView8(Context context) {
        super(context);
    }

    public MyView8(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView8(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(60);
        paint.setStrokeWidth(5);

    }

    /**
     * 绘制顺序：
     * 背景
     * 主体（onDraw()）
     * 子 View（dispatchDraw()）
     * 滑动边缘渐变和滑动条
     * 前景
     *
     *  先绘制的 会被 后绘制的 覆盖
     * @param canvas
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//    }
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
//        canvas.drawCircle(200, 100, 50, paint);
//        canvas.drawCircle(500, 600, 100, paint);
//        canvas.drawCircle(200, 200, 100, paint);
//        canvas.drawCircle(800, 900, 150, paint);

    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        paint.setColor(Color.RED);
        canvas.drawRect(80,160,400,300,paint);
        paint.setColor(Color.WHITE);
        canvas.drawText("New",240-50,230+20,paint);
    }
}
