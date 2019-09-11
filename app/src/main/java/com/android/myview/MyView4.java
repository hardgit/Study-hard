package com.android.myview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Process;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : zf
 * date   : 2019/9/11
 * You are the best.
 */
public class MyView4 extends View {

    private Paint paint;
    public MyView4(Context context) {
        super(context);
    }

    public MyView4(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //初始化
    public void initView(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         *   onDraw里面不建议创建对象因为会影响绘制速度、
         *   因为懒所以直接在这里创建了
         */
        /**
         *   线性扩散 linearGradient
         *   辐射性扩散 radialGradient
         *   扫射性扩散sweepGradient
         */
        LinearGradient linearGradient = new LinearGradient(100f, 400f, 600, 600
                ,Color.parseColor("#ff0000"),Color.parseColor("#00ffff"), Shader.TileMode.REPEAT);
        RadialGradient radialGradient = new RadialGradient(300,300,200,Color.parseColor("#ff0000"),Color.parseColor("#000fff"), Shader.TileMode.CLAMP);
        SweepGradient  sweepGradient = new SweepGradient(300,300,Color.parseColor("#000fff"),Color.parseColor("#ff3300"));
//       paint.setShader(sweepGradient);
//       canvas.drawCircle(300,300,200,paint);
        /**
         *   Bitmap着色器
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        Shader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
        Shader bitmapShader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
         //混合着色器
        ComposeShader composeShader = new ComposeShader(bitmapShader,bitmapShader2, PorterDuff.Mode.SRC_OVER);
        paint.setShader(composeShader);
//        paint.setShader(bitmapShader2);
        /**
         * 颜色过滤  R G B
         */
        LightingColorFilter lightingColorFilter = new LightingColorFilter(0xff0000, 0x000000);
        paint.setColorFilter(lightingColorFilter);
        paint.setDither(true);//抖动：让图片看起来更自然 相当于优化 降低色彩深度后更为明显
//        canvas.drawCircle(getWidth()/2,500,400,paint);
        //绘制文字加阴影
        paint.setTextSize(60);
        paint.setShadowLayer(10,0,0,Color.RED);
        canvas.drawText("跟党走，打胜仗！",200,200,paint);

        paint.reset();
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.what_the_fuck);
        /**
         *
             NORMAL: 内外都模糊绘制
            SOLID: 内部正常绘制，外部模糊
            INNER: 内部模糊，外部不绘制
           OUTER: 内部不绘制，外部模糊（什么鬼？）
         */
        paint.setMaskFilter(new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(bitmap1,20,100,paint);
    }

}
