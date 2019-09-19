package com.android.myview.practice;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.android.myview.R;

/**
 * author : zf
 * date   : 2019/9/17
 * You are the best.
 * 自定义点赞功能
 */
public class MyLikenum extends View {

    private Paint paint;
    private Paint paintText;
    private Bitmap like_unselected;
    private Bitmap like_selected;
    private Bitmap like_selected_shining;
    private int height;
    private int width;
    private int likeNum = 10,statusNum;
    public boolean status;
    private AnimatorSet animatorSet;
    private float pointScale,pointBackScale,fingerScale;
    private ObjectAnimator pointScaleA;
    private ObjectAnimator pointBackScaleA;
    private ObjectAnimator fingerScaleA;
    private float textWidth;
    private float view_width;


    public MyLikenum(Context context) {
        super(context);
    }

    public MyLikenum(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyLikenum(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //初始化
    public void initView() {
        //创建画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(60);

        /**
         *  handScaleA   手指上方4个点弹出动画
         *  handBackScaleA  手指上方4个点缩回动画
         */
        pointScaleA = ObjectAnimator.ofFloat(this, "pointScale", 0f, 1f);
        pointBackScaleA = ObjectAnimator.ofFloat(this, "pointBackScale", 1f, 0f);
//        pointBackScaleA.setDuration(100);

        /**
         *  fingerScaleA  手指动画
         */
        fingerScaleA = ObjectAnimator.ofFloat(this, "fingerScale", 1f, 0.8f, 1f);

        animatorSet = new AnimatorSet();

        animatorSet.playTogether(pointScaleA,pointBackScaleA,fingerScaleA);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //加载图片资源
        like_unselected = BitmapFactory.decodeResource(getResources(), R.mipmap.jike_like_unselected);
        like_selected = BitmapFactory.decodeResource(getResources(), R.mipmap.jike_like_selected);
        like_selected_shining = BitmapFactory.decodeResource(getResources(), R.mipmap.jike_like_selected_shining);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        like_unselected.recycle();
        like_selected.recycle();
        like_selected_shining.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //计算View的高度  上下追加20
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(like_unselected.getHeight() + 40, MeasureSpec.EXACTLY);
        //获取文字的宽度
        String likenum = String.valueOf(likeNum);
        textWidth = paintText.measureText(likenum, 0, likenum.length());
        //计算View的宽度  左右追加30
        view_width = (like_unselected.getWidth() + textWidth) + 60;
        widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) view_width, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画布中心位置
        width = getWidth() / 2;
        height = getHeight() / 2;
        //小手的位置Y
        int imgTop = getHeight() - like_selected_shining.getHeight() - 20;


        if (status) {
            //手指动画绘制
            canvas.save();//保存画布
            paint.setAlpha((int) (255*pointScale));
            canvas.scale(fingerScale,fingerScale,like_selected.getWidth()/2,imgTop);
            canvas.drawBitmap(like_selected, 0, imgTop, paint);
            canvas.restore();//恢复画布
            paint.setAlpha(255);

          //4个点动画绘制
            canvas.save();//保存画布
            canvas.scale(pointScale,pointScale,like_selected_shining.getWidth()/2+10,imgTop);
            canvas.drawBitmap(like_selected_shining, 5, 0, paint);
            canvas.restore();//恢复画布
            statusNum = 1;

            //绘制圆

        }else{

             if(statusNum != 0){//第一次进来的时候不执行
                 //未选中手指动画绘制
                 canvas.save();//保存画布
                 canvas.scale(fingerScale,fingerScale,like_selected.getWidth()/2,imgTop);
                 canvas.drawBitmap(like_unselected, 0, imgTop, paint);
                 canvas.restore();//恢复画布
//                //4个点动画绘制
//                 canvas.save();//保存画布
//                 paint.setAlpha((int) (255*pointScale));
//                 canvas.scale(pointBackScale,pointBackScale,like_selected_shining.getWidth()/2+10,imgTop);
//                 canvas.drawBitmap(like_selected_shining, 5, 0, paint);
//                 canvas.restore();//恢复画布
//                 paint.setAlpha(255);
             }else{
                 canvas.drawBitmap(like_unselected, 0, imgTop, paint);
             }
        }

        //文字的位置  上面4个点的高度+小手的高度+减去的20(由于处于中间位置所以还需要调整)
        int textTop = (like_selected.getHeight() + like_selected_shining.getHeight() + 20) / 2 + 10;
        canvas.drawText(String.valueOf(likeNum), like_selected.getWidth() + 20, textTop, paintText);


    }


    public void onClick() {
        //因为status默认为false  先走else
        if (status) {  //-1
            status = false;
            likeNum--;
        } else {      //+1
            status = true;
            likeNum++;
        }
        invalidate();
        if (animatorSet.isRunning()) {
            animatorSet.cancel();
        } else {
            animatorSet.start();
        }
    }

    public float getHandScale() {
        return pointScale;
    }

    public void setPointScale(float pointScale) {
        this.pointScale = pointScale;
        invalidate();
    }

    public float getPointBackScale() {
        return pointBackScale;
    }

    public void setPointBackScale(float pointBackScale) {
        this.pointBackScale = pointBackScale;
        invalidate();
    }

    public float getFingerScale() {
        return fingerScale;
    }

    public void setFingerScale(float fingerScale) {
        this.fingerScale = fingerScale;
        invalidate();
    }
}
