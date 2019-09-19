package com.android.myview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * author : zf
 * date   : 2019/9/12
 * You are the best.
 * Canvas 对绘制的辅助 clipXXX() 和 Matrix
 */
public class MyView7 extends View {

    private Paint paint;
    private Matrix matrix;
    private float[] floatsSrc,floatsDes;
    private Bitmap bitmap;
    private Float left,top,right,bottom;
    private Camera camera;
    private int rotateX;
    private ObjectAnimator animator;


    public MyView7(Context context) {
        super(context);
    }

    public MyView7(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView7(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //初始化
    public void initView(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.maps);
        left = getWidth()/3*1.0f;
        top  = getHeight()/3*1.0f;
        right = left+bitmap.getWidth();
        bottom = top+bitmap.getHeight();
        matrix = new Matrix();//几何任意变化
        floatsSrc = new float[]{left,top,   //左上
                                right,top,  //右上
                                right,bottom,//左下
                                left,bottom //右下
                               };
        floatsDes = new float[]{left,top,//左上
                                right,top, //右上
                                right+100,bottom+100,//左下
                                left,bottom//右下
                               };
        camera = new Camera();//三维变换
        //动画   propertyName 需定义好方法  取方法名做参数                            起始值，目标值
        animator = ObjectAnimator.ofInt(this, "rotateX", 0, 360);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());//插值器
        animator.setRepeatCount(ValueAnimator.INFINITE);//无限循环 或  设定次数
//        animator.setRepeatMode(ValueAnimator.REVERSE);//循环后返回
        //动画的效果由camera.rotateY决定
    }

    //作为属性动画和图片的桥梁rotateX
    public void setRotateX(int rotateX){
        this.rotateX = rotateX;
        invalidate();
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        matrix.reset();
        matrix.setPolyToPoly(floatsSrc,0,floatsDes,0,2);
        canvas.concat(matrix);//放入canvas

        canvas.save();//保存状态
        camera.save();//保存状态

        /*

         */
//        camera.setLocation(0,0,300); //设置虚拟相机的位置  虚拟相机在Z轴上
        camera.rotateY(rotateX);//旋转 Camera 的三维空间
//        camera.rotateY(30);  //Y轴
//        camera.rotateZ(30);//Z轴
        //旋转之后再移动回来   （反着写）
        canvas.translate(getWidth()/3+bitmap.getWidth()/2,getHeight()/3+bitmap.getHeight()/2);
        camera.applyToCanvas(canvas);
        // 旋转之前把绘制内容移动到轴心（原点）（反着写）
        canvas.translate(-getWidth()/3-bitmap.getWidth()/2,-getHeight()/3-bitmap.getHeight()/2);
        camera.restore();//恢复状态

        canvas.drawBitmap(bitmap,getWidth()/3,getHeight()/3,paint);

        canvas.restore();//恢复状态


    }
}
