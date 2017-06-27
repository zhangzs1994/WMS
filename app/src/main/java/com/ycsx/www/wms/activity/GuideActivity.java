package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.GuideAdapter;
import com.ycsx.www.wms.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity {
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private View mView;
    private List<ImageView> mDataList;
    private int diatance;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_guide);
        initView();
        initData();
        initEvent();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_points);
        mView = findViewById(R.id.v_redpoint);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        int[] image = new int[]{R.drawable.a, R.drawable.b, R.drawable.b, R.drawable.c};
        mDataList = new ArrayList<ImageView>();
        for (int i = 0; i < image.length; i++) {
            ImageView img = new ImageView(getApplicationContext());
            img.setBackgroundResource(image[i]);
            mDataList.add(img);
            //添加底部灰点
            View v = new View(getApplicationContext());
            v.setBackgroundResource(R.drawable.gray_round);
            //指定其大小
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if (i != 0) {
                params.leftMargin = 20;
            }
            v.setLayoutParams(params);
            mLinearLayout.addView(v);
        }
        preferences = getSharedPreferences("Guide", MODE_PRIVATE);
        boolean isFirst=preferences.getBoolean("isFirst", true);
        //判断是不是首次登录，
        if (isFirst) {
            mViewPager.setAdapter(new GuideAdapter(mDataList,this));
            mDataList.get(image.length-1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor = preferences.edit();
                    //将登录标志位设置为false，下次登录时不在显示首次登录界面
                    editor.putBoolean("isFirst", false);
                    editor.commit();
                    Intent intent=new Intent(GuideActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }else{
            Intent intent=new Intent(GuideActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        //设置每次加载时第一页在MAX_VALUE / 2 - Extra 页，造成用户无限轮播的错觉
//        int startPage = Integer.MAX_VALUE/2;
//        int extra = startPage % mDataList.size();
//        startPage = startPage - extra;
//        mViewPager.setCurrentItem(startPage);
        mViewPager.setCurrentItem(0);

    }

    private void initEvent() {
        /**
         * 当底部红色小圆点加载完成时测出两个小灰点的距离，便于计算后面小红点动态移动的距离
         */
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                diatance = mLinearLayout.getChildAt(1).getLeft() - mLinearLayout.getChildAt(0).getLeft();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //测出页面滚动时小红点移动的距离，并通过setLayoutParams(params)不断更新其位置
                position = position % mDataList.size();
                float leftMargin = diatance * (position + positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mView.getLayoutParams();
                params.leftMargin = Math.round(leftMargin);
                mView.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
