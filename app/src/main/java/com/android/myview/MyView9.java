package com.android.myview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;

import androidx.interpolator.view.animation.FastOutLinearInInterpolator;

/**
 * author : zf
 * date   : 2019/9/16
 * You are the best.
 */
public class MyView9 extends View {

    private Paint paint;
    private Bitmap bitmap;
    private float Horizontal;
    private ObjectAnimator animator;

    public MyView9(Context context) {
        super(context);
    }

    public MyView9(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView9(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        animator = ObjectAnimator.ofFloat(this, "horizontal", 0, 500);
        animator.setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
    }

    public void startAnimator(int position) {
        if (animator == null) {
            return;
        }

        switch (position) {
            case 1:
                animator.setInterpolator(new LinearInterpolator());
                break;
            case 2:
                animator.setInterpolator(new AnticipateInterpolator());
                break;
            case 3:
                animator.setInterpolator(new AccelerateInterpolator());
                break;
            case 4:
                animator.setInterpolator(new AnticipateOvershootInterpolator());
                break;
            case 5:
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                break;
            case 6:
                animator.setInterpolator(new DecelerateInterpolator());
                break;
            case 7:
                animator.setInterpolator(new BounceInterpolator());
                break;
            case 8:
                animator.setInterpolator(new CycleInterpolator(2));
                break;
            case 9:
                animator.setInterpolator(new OvershootInterpolator());
                break;
            case 10:
                animator.setInterpolator(new FastOutLinearInInterpolator());
                break;
            case 11:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    animator.setInterpolator(new PathInterpolator(100,0));
                }
                break;
        }
        animator.start();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    public float getHorizontal() {
        return Horizontal;
    }

    public void setHorizontal(float horizontal) {
        Horizontal = horizontal;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.music);
        canvas.translate(Horizontal, 100);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

}
