package com.android.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * author : zf
 * date   : 2019/9/12
 * You are the best.
 */
public class MyView6 extends View {


    private Paint paint;
    private Bitmap bitmap;
    private Path path;

    public MyView6(Context context) {
        super(context);
    }

    public MyView6(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView6(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //初始化
    public void initView(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.maps);

        Log.e("bitmap",bitmap.getHeight()+"    "+bitmap.getWidth());
        /**
         *    内容裁剪需要先将裁剪范围定位到需要裁剪的内容上
         *    否则空白
         */
        canvas.save();//保存状态
        canvas.clipRect(360,580,500,750);
        canvas.drawBitmap(bitmap,360,580,paint);
        canvas.restore();//回复状态，不然截下来绘制的内容也会导致裁剪


        /**
         *   canvas.translate(200,200);//位移
         *   degrees：旋转角度
         *   px : 圆的 X原点
         *   py : 圆的 Y原点
         */
        canvas.save();//保存状态
        paint.setStyle(Paint.Style.STROKE);
        canvas.translate(200,200);//位移
        path.addCircle(250,280,130, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,100,100,paint);
        canvas.restore();//恢复状态

        /**
         *    canvas.rotate(180,250,280);
         *   degrees：旋转角度
         *   px : 圆的 X原点
         *   py : 圆的 Y原点
         */
        canvas.save();//保存状态
        paint.setStyle(Paint.Style.STROKE);
        canvas.rotate(180,250,280);//旋转
        path.addCircle(250,280,130, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,100,100,paint);
        canvas.restore();//恢复状态

        /**
         *      canvas.scale(0.5f,0.5f,getWidth()/2+bitmap.getWidth()/2,getHeight()/2+bitmap.getHeight()/2);//缩放
         *      缩放：需找到图片的中心点进行放大或缩小
         */
        canvas.save();//保存状态
        canvas.scale(0.5f,0.5f,getWidth()/2+bitmap.getWidth()/2,getHeight()/2+bitmap.getHeight()/2);//缩放
        canvas.drawBitmap(bitmap,getWidth()/2,getHeight()/2,paint);
        canvas.restore();//恢复状态


        /**
         *  错切
         *  canvas.skew(0.8f,0.5f);
         */
        canvas.save();//保存状态
        canvas.skew(0.8f,0.5f);//错切
        canvas.drawBitmap(bitmap,getWidth()/2,0,paint);
        canvas.restore();//恢复状态
    }
}
