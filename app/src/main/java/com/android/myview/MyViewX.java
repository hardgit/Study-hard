package com.android.myview;

import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : zf
 * date   : 2019/9/16
 * You are the best.
 * ObjectAnimator动画
 */
public class MyViewX extends View {

    private Paint paint;
    private RectF rectF;
    private float progress = 0;
    private ObjectAnimator animator;
    private Paint paint1;

    public MyViewX(Context context) {
        super(context);
    }

    public MyViewX(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyViewX(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float getProgress() {
        return progress;
    }

    @SuppressLint("ObjectAnimatorBinding")
    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    //初始化
    public void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(30);

        Keyframe aFloat1 = Keyframe.ofFloat(0, 0);//在 0% 处开始
        Keyframe aFloat2 = Keyframe.ofFloat(0.5f, 100); // 时间经过 50% 的时候，动画完成度 100%
        Keyframe aFloat3 = Keyframe.ofFloat(1, 90);// 时间经过 100% 的时候，动画完成度倒退到 90%，即反弹 10%
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress", aFloat1, aFloat2, aFloat3);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(this, holder);
        animator.setDuration(1500);
        animator.start();
        //animator = ObjectAnimator.ofFloat(this, "progress", 0, 360);
       // this.animator.setDuration(1500);
//        this.animator.start();


        rectF = new RectF(300, 300, 800, 800);
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setTextSize(60);
        paint1.setColor(Color.BLACK);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeCap(Paint.Cap.ROUND);//圆角
        canvas.drawArc(rectF, 135, progress * 3.6f, false, paint);//画圆
        canvas.drawText((int) progress + "%", 300 + 200, 300 + 260, paint1);//文字
    }
}
