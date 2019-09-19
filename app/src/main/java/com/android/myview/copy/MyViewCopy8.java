package com.android.myview.copy;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.android.myview.R;

/**
 * author : zf
 * date   : 2019/9/12
 * You are the best.
 */
public class MyViewCopy8 extends View {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap bitmap;
        Camera camera = new Camera();
        int degree;
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "degree", 0, 360);

        public MyViewCopy8(Context context) {
            super(context);
        }

        public MyViewCopy8(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public MyViewCopy8(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.maps);

            animator.setDuration(2500);
            animator.setInterpolator(new LinearInterpolator());
            animator.setRepeatCount(ValueAnimator.INFINITE);
//            animator.setRepeatMode(ValueAnimator.REVERSE);
        }

        @Override
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            animator.start();
        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            animator.end();
        }

        @SuppressWarnings("unused")
        public void setDegree(int degree) {
            this.degree = degree;
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int bitmapWidth = bitmap.getWidth();
            int bitmapHeight = bitmap.getHeight();
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int x = centerX - bitmapWidth / 2;
            int y = centerY - bitmapHeight / 2;

            canvas.save();

            camera.save();
            camera.rotateX(degree);
            canvas.translate(centerX, centerY);
            camera.applyToCanvas(canvas);
            canvas.translate(-centerX, -centerY);
            camera.restore();

            canvas.drawBitmap(bitmap, x, y, paint);
            canvas.restore();
        }
}
