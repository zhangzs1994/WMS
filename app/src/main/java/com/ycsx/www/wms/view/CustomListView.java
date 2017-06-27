package com.ycsx.www.wms.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 *  GridView显示不完整,GridView去掉滚动条,自定义
 * @author hefeng
 *
 */
public class CustomListView extends ListView {

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

