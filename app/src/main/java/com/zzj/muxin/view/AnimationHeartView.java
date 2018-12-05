package com.zzj.muxin.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zzj.muxin.R;
import com.zzj.muxin.evaluator.BezierEvaluator;

import java.util.Random;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/12/4 9:40
 * @desc :  心形点赞动画
 * @version: 1.0
 */
public class AnimationHeartView extends RelativeLayout {
    //加载图片的drawable数组
    private Drawable[] drawable;
    //插入器
    private Interpolator[] interpolators;
    //图片的宽
    private int ivWidth = 0;
    //图片的高
    private int ivHeight = 0;
    //父容器的宽高
    private int pWidth = 0;
    private int pHeight = 0;

    private LayoutParams lp ;

    private Random random ;
    public AnimationHeartView(Context context) {
        this(context,null);
    }

    public AnimationHeartView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AnimationHeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        drawable = new Drawable[5];
        //加载图片的drawable
        drawable[0] = ContextCompat.getDrawable(context, R.mipmap.ic_heart_blue);
        drawable[1] = ContextCompat.getDrawable(context, R.mipmap.ic_heart_red);
        drawable[2] = ContextCompat.getDrawable(context, R.mipmap.ic_heart_yellow);
        drawable[3] = ContextCompat.getDrawable(context, R.mipmap.ic_heart_green);
        drawable[4] = ContextCompat.getDrawable(context, R.mipmap.ic_heart_red);

        interpolators = new Interpolator[4];
        interpolators[0] = new AccelerateInterpolator();
        interpolators[1] = new DecelerateInterpolator();
        interpolators[2] = new AccelerateDecelerateInterpolator();
        interpolators[3] = new LinearInterpolator();
        //获取心形图片的宽高，每个图片的宽高大小要一致
        ivWidth = drawable[0].getIntrinsicWidth();
        ivHeight = drawable[0].getIntrinsicHeight();

        lp = new LayoutParams(ivWidth,ivHeight);
        //设置将图片添加到父容器底部并水平居中
        lp.addRule(ALIGN_PARENT_BOTTOM);
        lp.addRule(CENTER_HORIZONTAL);

        random = new Random();
        addHeartImage(context).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
    }

    private ImageView addHeartImage(Context context){
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(drawable[random.nextInt(5)]);
        imageView.setLayoutParams(lp);
        addView(imageView);
        return imageView;
    }

    /**
     * 设置添加到容器中的心形图片的动画 有小到大 有透明到清晰
     * @param imageView
     */
    private AnimatorSet setIvHeartAnimation(ImageView imageView){
        //设置透明度动画由0.4到1
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView,"alpha",0.4f,1f);
        //在x轴缩放
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageView,"scaleX",0.4f,1f);
        //在Y轴缩放
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageView,"scaleY",0.4f,1f);
        //将动画添加到执行动画集中  一起执行
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaAnimator,scaleXAnimator,scaleYAnimator);
        animatorSet.setDuration(500);
        return animatorSet;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //再此处才能准确获取到控件的宽高
        pWidth = getMeasuredWidth();
        pHeight = getMeasuredHeight();
    }

    private PointF getPointF(int scale) {
        PointF pointF = new PointF();
        pointF.x = random.nextInt((pWidth - 100));//减去100 是为了控制 x轴活动范围,看效果
        //再Y轴上 为了确保第二个点 在第一个点之上,我把Y分成了上下两半 这样动画效果好一些  也可以用其他方法
        pointF.y = random.nextInt((pHeight - 100))/scale;
        return pointF;
    }

    /**
     * 获取执行贝塞尔曲线的动画
     * @param imageView
     * @return
     */
    private AnimatorSet getAnimatorSet(final ImageView imageView){
        AnimatorSet animatorSet = new AnimatorSet();
        PointF point0 = new PointF((pWidth-ivWidth)/2,pHeight-ivHeight);
        //终止点
        PointF point3 = new PointF(random.nextInt(getWidth()),0);
        /**
         * 开始执行贝塞尔曲线动画
         */
        TypeEvaluator typeEvaluator = new BezierEvaluator(getPointF(2),getPointF(1));
        ValueAnimator valueAnimator = ValueAnimator.ofObject(typeEvaluator,point0,point3);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //这里获取到贝塞尔曲线计算出来的的x y值 赋值给view 这样就能让爱心随着曲线走啦
                PointF pointF = (PointF) animation.getAnimatedValue();
                imageView.setX(pointF.x);
                imageView.setY(pointF.y);
                imageView.setAlpha(1-animation.getAnimatedFraction());
            }
        });
        animatorSet.setDuration(3000);
        animatorSet.play(valueAnimator);
        return animatorSet;
    }

    /**
     * 合并两个动画
     */
    private void mergeAnimation(final ImageView imageView){
        AnimatorSet finalAnimatorSet = new AnimatorSet();
        //实现随机加速
        finalAnimatorSet.setInterpolator(interpolators[random.nextInt(4)]);
//        执行动画
        finalAnimatorSet.playTogether(getAnimatorSet(imageView),setIvHeartAnimation(imageView));
        finalAnimatorSet.setTarget(imageView);
        finalAnimatorSet.addListener(new Animator.AnimatorListener() {


            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //结束动画时，移除imageView
                removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }


        });
        //启动动画
        finalAnimatorSet.start();
    }

    /**
     * 启动动画
     */
    public void startAnimation(){

        mergeAnimation(addHeartImage(getContext()));
    }

}
