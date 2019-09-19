package com.android.myview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * author : zf
 * date   : 2019/9/17
 * You are the best.
 *   AnimatorSet组合动画
 */
public class MyViewX2 extends View {

    private Paint paint;
    private float moveY=200;
    private ObjectAnimator animatorX;
    private ObjectAnimator animatorY;
    private ObjectAnimator animatorZ;
    private Bitmap bitmap;
    private ObjectAnimator animatorYY;

    public MyViewX2(Context context) {
        super(context);
    }

    public MyViewX2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyViewX2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //初始化
    public void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        animatorX = ObjectAnimator.ofFloat(this, "alpha", 0, 1);
        animatorX.setInterpolator(new AccelerateInterpolator());
        animatorY = ObjectAnimator.ofFloat(this, "translationX", 0, 500);
        animatorY.setInterpolator(new AnticipateOvershootInterpolator());
        animatorYY = ObjectAnimator.ofFloat(this, "moveY", 200, 500);
        animatorYY.setInterpolator(new AnticipateOvershootInterpolator());
        animatorZ = ObjectAnimator.ofFloat(this, "rotation", 0, 360);
        animatorZ.setInterpolator(new DecelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.playSequentially(animatorX,animatorYY,animatorY,animatorZ);
        animatorSet.start();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.music);
        canvas.drawBitmap(bitmap,100,moveY,paint);
    }

    public float getMoveY() {
        return moveY;
    }

    public void setMoveY(float moveY) {
        this.moveY = moveY;
        invalidate();
    }
}
