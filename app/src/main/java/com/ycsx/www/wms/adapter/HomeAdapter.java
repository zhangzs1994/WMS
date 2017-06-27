package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * ViewPager的容器
 */

public class HomeAdapter extends PagerAdapter {
    private List<ImageView> mDataList;
    private Context context;

    public HomeAdapter(List<ImageView> mDataList, Context context) {
        this.mDataList = mDataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        //告诉容器我们的数据长度为Integer.MAX_VALUE，这样就可以一直滚动
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //若position超过mDataList.size()，会发生越界异常，所以这里每次超过size又从0开始计算位置
        position = position % mDataList.size();
        ImageView img = mDataList.get(position);
        ViewGroup parent = (ViewGroup) img.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position = position % mDataList.size();
        container.removeView((View) object);
    }
}