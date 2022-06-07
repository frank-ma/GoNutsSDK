package com.nutsplay.nopagesdk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.nutsplay.nopagesdk.R;

/**
 * Created by smaug on 2017/6/1.
 */

public class ColorBallLoading extends LinearLayout {
    private CradleBall cradleBallOne;
    private CradleBall cradleBallTwo;
    private CradleBall cradleBallThree;


    ScaleAnimation mScaleAnimation1;//大小
    ScaleAnimation mScaleAnimation2;//大小
    ScaleAnimation mScaleAnimation3;


    private boolean isStart = false;

    public ColorBallLoading(Context context) {
        super(context);
        initView(context);
    }

    public ColorBallLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ColorBallLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.animation_color_ball, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        cradleBallOne = (CradleBall) findViewById(R.id.ball_one);
        cradleBallTwo = (CradleBall) findViewById(R.id.ball_two);
        cradleBallThree = (CradleBall) findViewById(R.id.ball_three);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 设置颜色
        cradleBallOne.setLoadingColor(getResources().getColor(R.color.dotRed));
        cradleBallTwo.setLoadingColor(getResources().getColor(R.color.dotYellow));
        cradleBallThree.setLoadingColor(getResources().getColor(R.color.dotBlue));

        //变大变小
        startAni();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }


    public void start() {
        if (!isStart) {
            isStart = true;
            startAni();//开始动画
        }
    }

    public void stop() {
        isStart = false;
        cradleBallOne.clearAnimation();
        cradleBallTwo.clearAnimation();
        cradleBallThree.clearAnimation();
    }

    public boolean isStart() {
        return isStart;
    }


    public void setLoadingColor(int color) {
        cradleBallOne.setLoadingColor(color);
        cradleBallTwo.setLoadingColor(color);
        cradleBallThree.setLoadingColor(color);
    }

    public void startAni() {
        mScaleAnimation1 = new ScaleAnimation(1, 0, 1, 0, 1, 0.5f, 1, 0.5f);
        mScaleAnimation1.setDuration(700);
        mScaleAnimation1.setStartOffset(20);
        mScaleAnimation1.setInterpolator(new LinearInterpolator());
        mScaleAnimation1.setRepeatCount(-1);
        mScaleAnimation1.setRepeatMode(Animation.REVERSE);

        mScaleAnimation2 = new ScaleAnimation(1, 0, 1, 0, 1, 0.5f, 1, 0.5f);
        mScaleAnimation2.setDuration(700);
        mScaleAnimation2.setStartOffset(40);
        mScaleAnimation2.setInterpolator(new LinearInterpolator());
        mScaleAnimation2.setRepeatCount(-1);
        mScaleAnimation2.setRepeatMode(Animation.REVERSE);

        mScaleAnimation3 = new ScaleAnimation(1, 0, 1, 0, 1, 0.5f, 1, 0.5f);
        mScaleAnimation3.setDuration(700);
        mScaleAnimation3.setStartOffset(60);
        mScaleAnimation3.setInterpolator(new LinearInterpolator());
        mScaleAnimation3.setRepeatCount(-1);
        mScaleAnimation3.setRepeatMode(Animation.REVERSE);


        cradleBallOne.startAnimation(mScaleAnimation1);
        cradleBallTwo.startAnimation(mScaleAnimation2);
        cradleBallThree.startAnimation(mScaleAnimation3);


    }
}