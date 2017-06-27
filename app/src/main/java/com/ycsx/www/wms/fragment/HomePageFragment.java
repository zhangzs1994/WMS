package com.ycsx.www.wms.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.demono.AutoScrollViewPager;
import com.ycsx.www.wms.R;
import com.ycsx.www.wms.activity.AchievementActivity;
import com.ycsx.www.wms.activity.OrderMangerActivity;
import com.ycsx.www.wms.activity.StockMangerActivity;
import com.ycsx.www.wms.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/6.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {
    private AutoScrollViewPager mViewPager;
    private LinearLayout mLinearLayout, layout_order, layout_achievement, layout_stock;
    private View mView;
    private List<ImageView> mDataList;
    private int diatance;
    private View view;
    private int[] image = new int[]{R.drawable.major_image, R.drawable.major_image, R.drawable.major_image, R.drawable.major_image};
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage, container, false);
        initView();
        initData();
        initEvent();
        return view;
    }

    @Override
    public void onStart() {
        //设置viewpager自动轮播并设置轮播方向
        mViewPager.startAutoScroll();
        mViewPager.setSlideInterval(2000);
        mViewPager.setCycle(true);
        super.onStart();
    }

    @Override
    public void onStop() {
        //设置viewpager停止轮播
        mViewPager.stopAutoScroll();
        super.onStop();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mViewPager = (AutoScrollViewPager) view.findViewById(R.id.viewpager);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.layout_points);
        layout_order = (LinearLayout) view.findViewById(R.id.layout_order);
        layout_achievement = (LinearLayout) view.findViewById(R.id.layout_achievement);
        layout_stock = (LinearLayout) view.findViewById(R.id.layout_stock);
        layout_order.setOnClickListener(this);
        layout_achievement.setOnClickListener(this);
        layout_stock.setOnClickListener(this);
        mView = view.findViewById(R.id.view_redpoint);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mDataList = new ArrayList<ImageView>();
        for (int i = 0; i < image.length; i++) {
            ImageView img = new ImageView(getContext());
            img.setBackgroundResource(image[i]);
            mDataList.add(img);
            //添加底部灰点
            View v = new View(getContext());
            v.setBackgroundResource(R.drawable.gray_round);
            //指定其大小
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if (i != 0) {
                params.leftMargin = 20;
            }
            v.setLayoutParams(params);
            mLinearLayout.addView(v);
        }
        mViewPager.setAdapter(new HomeAdapter(mDataList, getActivity()));
        //设置每次加载时第一页在MAX_VALUE / 2 - Extra 页，造成用户无限轮播的错觉
        int startPage = Integer.MAX_VALUE / 2;
        int extra = startPage % mDataList.size();
        startPage = startPage - extra;
        mViewPager.setCurrentItem(startPage);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_order:
                intent = new Intent(getActivity(), OrderMangerActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_achievement:
                intent = new Intent(getActivity(), AchievementActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_stock:
                intent = new Intent(getActivity(), StockMangerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
