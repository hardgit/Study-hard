package com.android.myview.practice;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.android.myview.R;

/**
 * author : zf
 * date   : 2019/9/19
 * You are the best.
 *  绘制手指+动画
 */
public class LikeView extends View {

    private Paint paint;// 画小手
    private Paint paintCircle;//画圆
    private Bitmap selectedBitmap;//选中
    private Bitmap unSelectedBitmap;//未选中
    private Bitmap shiningBitmap;//头上4小点
    private boolean isLike;//是否选中
    private int shiningWidth;
    private int shiningHeight;
    private int y;//小手top值
    private int widthBitmap;
    private int heightBitmap;
    private float handScale,handBackScale,circleScale,circleAlpha,shiningScale;

    //动画参数
    private static final Float SCALE_START = 0.8f;
    private static final Float SCALE_END = 1f;
    private static final Float SCALE_CIRCLE_START = 0.5f;
    private static final Float SCALE_CIRCLE_END = 1f;
    private static final Float ALPHA_CIRCLE_START = 0.35f;
    private static final Float ALPHA_CIRCLE_END = 0f;
    private static final Float SCALE_SHINING_START = 0.65f;

    private ObjectAnimator handScaleAnimator;
    private ObjectAnimator handBackScaleAnimator;
    private ObjectAnimator circleScaleAnimator;
    private ObjectAnimator circleAlphaAnimator;
    private ObjectAnimator shiningScaleAnimator;
    private AnimatorSet animatorSet;


    private int viewHieght;//父view的高度
    private int flag; //标识




    public LikeView(Context context) {
        super(context);
    }

    public LikeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LikeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeWidth(5);
        paintCircle.setColor(Color.parseColor("#e24d3d"));
        selectedBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.jike_like_selected);
        unSelectedBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.jike_like_unselected);
        shiningBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.jike_like_selected_shining);
        widthBitmap = selectedBitmap.getWidth(); //图片宽度
        heightBitmap = selectedBitmap.getHeight();//图片高度
        shiningWidth = shiningBitmap.getWidth();//小点的宽度
        shiningHeight = shiningBitmap.getHeight();//小点的高度
        Animator();//动画
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 根据图片的宽高 + 偏移量 计算出 父view的宽高
         */
        int measureSpecWidth = MeasureSpec.makeMeasureSpec(widthBitmap, MeasureSpec.EXACTLY);
        //减去35的多余空间   view高度
        viewHieght = heightBitmap + shiningHeight -10;
        int measureSpecHeight = MeasureSpec.makeMeasureSpec(viewHieght, MeasureSpec.EXACTLY);
        setMeasuredDimension(measureSpecWidth,measureSpecHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //减去35的原因是为了拉近手指与小点之间的距离
        y = shiningHeight-23;
        if(isLike){//点赞
            //小手绘制
            canvas.save();//保存状态
            canvas.scale(handScale,handScale,widthBitmap/2,viewHieght/2);
            canvas.drawBitmap(selectedBitmap,0,y,paint);
            canvas.restore();//恢复状态

            //小点向右偏移7px 让大拇指在小点中间
            canvas.save();//保存状态
            canvas.scale(shiningScale,shiningScale,widthBitmap/2,viewHieght/2);
            canvas.drawBitmap(shiningBitmap,4,0,paint);
            canvas.restore();//恢复状态

//            if(flag != 0){ //第一次进来不绘制圆
//               canvas.save();
//               paintCircle.setAlpha((int) (255*circleAlpha));
//               canvas.drawCircle(widthBitmap/2,viewHieght-(y),(selectedBitmap.getHeight()/2+5)*circleScale,paintCircle);
//               canvas.restore();
//               paintCircle.setAlpha(255);
//            }
        }else{//取消
            if(flag != 0){ //第一次进来不加动画
                //绘制小手
                canvas.save();
                canvas.scale(handBackScale,handBackScale,widthBitmap/2,viewHieght/2);
                canvas.drawBitmap(unSelectedBitmap,0,y,paint);
                canvas.restore();//恢复状态
            }else{//默认未点赞
                canvas.drawBitmap(unSelectedBitmap,0,y,paint);
            }

        }
      flag = 1;
    }


    public void openAnimator(){
        if(!isLike){ //  默认状态
           like();
        }else{ //点赞状态
           unLike();
        }
        if(animatorSet.isRunning()){
           animatorSet.cancel();
        }else{
            animatorSet.start();
        }
        invalidate();//刷新View
    }

    private void like(){
        isLike = true;
    }

    private void unLike(){
        isLike = false;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        selectedBitmap.recycle();
        unSelectedBitmap.recycle();
        shiningBitmap.recycle();
        animatorSet.cancel();
    }

    //属性动画
    private void Animator(){
        handScaleAnimator = ObjectAnimator.ofFloat(this,"handScale",SCALE_END,SCALE_START,SCALE_END);//1f,0.8f,1f
        handBackScaleAnimator = ObjectAnimator.ofFloat(this,"handBackScale",SCALE_START,SCALE_END);//0.8f,1f
        circleScaleAnimator = ObjectAnimator.ofFloat(this,"circleScale",SCALE_CIRCLE_START,SCALE_CIRCLE_END);//0.5f,1f
        circleAlphaAnimator = ObjectAnimator.ofFloat(this,"circleAlpha",ALPHA_CIRCLE_START,ALPHA_CIRCLE_END);//0.35f,0f
        shiningScaleAnimator = ObjectAnimator.ofFloat(this,"shiningScale",SCALE_SHINING_START,SCALE_END);//0.65f,1f
        circleScaleAnimator.setInterpolator(new BounceInterpolator());
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(handScaleAnimator,handBackScaleAnimator,circleScaleAnimator,circleAlphaAnimator,shiningScaleAnimator);
    }

    public float getHandScale() {
        return handScale;
    }

    public void setHandScale(float handScale) {
        this.handScale = handScale;
        invalidate();
    }

    public float getHandBackScale() {
        return handBackScale;
    }

    public void setHandBackScale(float handBackScale) {
        this.handBackScale = handBackScale;
        invalidate();
    }

    public float getCircleScale() {
        return circleScale;
    }

    public void setCircleScale(float circleScale) {
        this.circleScale = circleScale;
        invalidate();
    }

    public float getCircleAlpha() {
        return circleAlpha;
    }

    public void setCircleAlpha(float circleAlpha) {
        this.circleAlpha = circleAlpha;
        invalidate();
    }

    public float getShiningScale() {
        return shiningScale;
    }

    public void setShiningScale(float shiningScale) {
        this.shiningScale = shiningScale;
        invalidate();
    }
}
