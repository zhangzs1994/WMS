package com.ycsx.www.wms.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 横向滚动条通用类
 */

public class SimpleViewPagerIndicator extends LinearLayout {

    private static final int COLOR_TEXT_NORMAL = 0xFF000000;
    //设置滚动条颜色
    private static final int COLOR_INDICATOR_COLOR = Color.BLACK;

    private String[] mTitles;
    private int mTabCount;
    private int mIndicatorColor = COLOR_INDICATOR_COLOR;
    private float mTranslationX;
    private Paint mPaint = new Paint();
    private int mTabWidth;

    public SimpleViewPagerIndicator(Context context) {
        this(context, null);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(mIndicatorColor);
        mPaint.setStrokeWidth(9.0F);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTabWidth = w / mTabCount;
    }

    //设置滚动条个数
    public void setTitles(String[] titles) {
        mTitles = titles;
        mTabCount = titles.length;
        generateTitleView();
    }

    ////设置滚动条颜色
    public void setIndicatorColor(int indicatorColor) {
        this.mIndicatorColor = indicatorColor;
    }

    //画线条
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mTranslationX, getHeight() - 2);
        //设置线条的长度（线条距离左右50dp）
        //canvas.drawLine(50, 0, mTabWidth-50, 0, mPaint);
        //设置线条的长度（线条铺满）
        canvas.drawLine(0, 0, mTabWidth, 0, mPaint);
        canvas.restore();
    }

    //设置线条滑动的变化
    public void scroll(int position, float offset) {
        /**
         * <pre>
         *  0-1:position=0 ;1-0:postion=0;
         * </pre>
         */
        mTranslationX = getWidth() / mTabCount * (position + offset);
        invalidate();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private void generateTitleView() {
        if (getChildCount() > 0)
            this.removeAllViews();
        int count = mTitles.length;
        setWeightSum(count);
    }

}
