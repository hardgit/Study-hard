package com.android.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * author : zf
 * date   : 2019/9/10
 * You are the best.
 * 饼状图优化版
 */
public class MyView3 extends View {

    private Paint paintTextLine;
    private Paint[] paints ;//画笔集
    private String[] colors ;//颜色集
    private Float[] radian ;//扫过的角度
    private RectF rectMove;
    private RectF rectCommon;
    private Float maxNumber = Float.MIN_VALUE;
    private Path path;

    public MyView3(Context context) {
        super(context);
    }

    public MyView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();//初始化
    }

    public MyView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //初始化
    public void initView(){
        /**
         * 绘制饼状图所用
         */
        paints = new Paint[7];
        colors = new String[]{"#ff0000","#fff000","#000fff","#7cfc00","#00ffff","#ffa500","#800080"};
        for (int i = 0; i < colors.length; i++) {
            paints[i] = new Paint();//画笔
            paints[i].setColor(Color.parseColor(colors[i]));//上色
            paints[i].setAntiAlias(true);//抗锯齿
            paints[i].setStyle(Paint.Style.FILL);//线条
        }

        /**
         *   绘制字体和线条的笔
         */
        paintTextLine = new Paint();
        paintTextLine.setColor(Color.BLACK);
        paintTextLine.setTextSize(26);//字体大小
        paintTextLine.setAntiAlias(true);//抗锯齿
        paintTextLine.setStyle(Paint.Style.STROKE);//线条
         //移动的rectF
        rectMove = new RectF(-320,-320,300,300);
        //不移动的rectF
        rectCommon = new RectF(-300,-300,300,300);
         //扫过的角度
        radian = new Float[]{7f,7f, 10f, 56f, 100f, 130f, 50f};

        //取扫过的角度最大值
        for (int i = 0; i < radian.length; i++) {
            maxNumber = Math.max(maxNumber,radian[i]);
        }
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float startAngle = 0; //扇形的起始角度
        float sweepAngle = 0; //扫过的角度
        float startAngleText = 0;

        canvas.translate(getWidth()/2,getHeight()/2);

        if(paints.length!=0){
            for (int i = 0; i < paints.length; i++) {
                sweepAngle = radian[i];
                startAngleText = ((radian[i]/2)+startAngle)+1;
                Log.e("startAngleText",startAngleText+"");

                if(radian[i].equals(maxNumber)){
                    canvas.drawArc(rectMove,startAngle+2,sweepAngle-2,true,paints[i]);
                }else{
                    canvas.drawArc(rectCommon,startAngle+2,sweepAngle-2,true,paints[i]);
                }

                /**
                 *  (300,300)
                 * pointX = x + radius * Math.cos(Math.toRadians(angle))
                 * pointY = y + radius * Math.sin(Math.toRadians(angle))
                 */

                //点的位置     300 为  半径
                float pointX = (float) (Math.cos(startAngleText * Math.PI / 180)* 300);
                float pointY = (float) (Math.sin(startAngleText * Math.PI / 180) * 300);
                //线的位置
                float lineX = (float)( Math.cos(startAngleText * Math.PI / 180) * 350);
                float lineY = (float)( Math.sin(startAngleText * Math.PI / 180) * 350);

               //凸出的部分
                if(radian[i] .equals(maxNumber)){
                    path.reset();//左右两边用的同一个path避免混淆
                    canvas.translate(-19,-19);
                    path.moveTo(pointX,pointY);
                    path.lineTo(lineX,lineY);//第一条斜线
                   float lineXX = lineX < 0 ? (lineX-100) :lineX+100;
                    path.lineTo(lineXX ,lineY);//第二条直线
                    canvas.drawPath(path,paintTextLine);//绘制
                    //绘制文字  调整文字的位置
                    canvas.drawText((i+1)+"%",lineX<0?lineXX-50:lineXX+20,lineY+5,paintTextLine);
                    canvas.translate(19,19);

                }else{
                    path.reset();//左右两边用的同一个path避免混淆
                    path.moveTo(pointX,pointY);
                    path.lineTo(lineX,lineY);//第一条斜线
                    float lineXX = lineX < 0 ? lineX-100 :lineX+100;
                    path.lineTo(lineXX ,lineY);//第二条直线
                    canvas.drawPath(path,paintTextLine);//绘制
                    //绘制文字   调整文字的位置
                    canvas.drawText((i+1)+"%",lineX<0?lineXX-50:lineXX+20 ,lineY+5,paintTextLine);
                }
                // 初始化每个饼的起始角度
                startAngle = startAngle + sweepAngle;

            }

        }


    }
}
