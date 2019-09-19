package com.android.myview;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : zf
 * date   : 2019/9/16
 * You are the best.
 *   ObjectAnimator动画
 */
public class MyViewX1 extends View {


    private Paint paint;
    private int color = Color.RED;
    private ObjectAnimator animator;

    public MyViewX1(Context context) {
        super(context);
    }

    public MyViewX1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyViewX1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    //初始化
    public void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animator = ObjectAnimator.ofArgb(this, "color", 0xffff0000, 0xff000000);
        }
//         animator = ObjectAnimator.ofArgb()解决闪烁问题
        animator.setEvaluator(new HsvEvaluator());//解决闪烁问题  和  体验效果
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setDuration(1500);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(color);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 200, paint);
    }



    class HsvEvaluator implements TypeEvaluator<Integer> {
        float[] startHsv = new float[3];
        float[] endHsv = new float[3];
        float[] outHsv = new float[3];

        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            // 把 ARGB 转换成 HSV
            Color.colorToHSV(startValue, startHsv);
            Color.colorToHSV(endValue, endHsv);

            // 计算当前动画完成度（fraction）所对应的颜色值
            if (endHsv[0] - startHsv[0] > 180) {
                endHsv[0] -= 360;
            } else if (endHsv[0] - startHsv[0] < -180) {
                endHsv[0] += 360;
            }
            outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
            if (outHsv[0] > 360) {
                outHsv[0] -= 360;
            } else if (outHsv[0] < 0) {
                outHsv[0] += 360;
            }
            outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
            outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;

            // 计算当前动画完成度（fraction）所对应的透明度
            int alpha = startValue >> 24 + (int) ((endValue >> 24 - startValue >> 24) * fraction);

            // 把 HSV 转换回 ARGB 返回
            return Color.HSVToColor(alpha, outHsv);
        }
    }
}