package com.android.myview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : zf
 * date   : 2019/9/11
 * You are the best.
 * //绘制文字  Paint 使用
 */
public class MyTextView5 extends View {

    private TextPaint paint; //画笔
    private String str = "这是一段很长的文字，这里写这些文字的作用就是为了达到自动换行的效果，这么多字应该差不多够了吧！算了不够的话一会再补。";
    private String str2 = "这是一段文字";

    public MyTextView5(Context context) {
        super(context);
    }

    public MyTextView5(Context context, AttributeSet attrs) {
        super(context, attrs);
      initView();
    }

    public MyTextView5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(){
         paint  = new TextPaint(Paint.ANTI_ALIAS_FLAG);
         paint.setStrokeWidth(3);
         paint.setTextSize(60);
         paint.setColor(Color.BLACK);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0,getHeight()/4);
        paint.setFakeBoldText(true);//文字伪粗体
        paint.setStrikeThruText(true);//文字中间添加删除线
        paint.setUnderlineText(true);//文字底部添加删除线
        paint.setTextSkewX(-0.5f);//文字倾斜
        paint.setTextScaleX(0.8f);//文字的缩放：胖瘦
        paint.setLetterSpacing(0.2f);//文字间距 默认为0
        Rect bounds = new Rect();
        StaticLayout staticLayout = new StaticLayout(str,paint,1080, Layout.Alignment.ALIGN_NORMAL,1,0,true);
//        staticLayout.draw(canvas);//绘制可以换行的文字

        /**
         * 文字外面绘制边框
         */
        canvas.drawText(str2,100,100,paint);
        paint.getTextBounds(str2,0,str2.length(),bounds);
        bounds.left += 90;
        bounds.top += 90;
        bounds.right += 110;
        bounds.bottom += 110;
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(bounds,paint);//绘制不可以换行的文字


        /**
         *
         */
        paint.reset();//重置画笔
        paint.setTextSize(88f);
        paint.setAntiAlias(true);
        canvas.drawText(str2,getWidth()/4,getHeight()/4,paint);
        paint.setStyle(Paint.Style.FILL);
        float measureText = paint.measureText(str2);//测量文字的宽度并返回
        paint.setColor(Color.RED);
        canvas.drawLine(getWidth()/4,getHeight()/4,getHeight()/6+measureText,getHeight()/4,paint);
    }
}
