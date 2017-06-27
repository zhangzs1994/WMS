package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ycsx.www.wms.R;

/**
 * Created by ZZS_PC on 2017/5/13.
 */
public class GridViewAdapter extends BaseAdapter {
    private String[] mTitles;
    private Context context;

    public GridViewAdapter(String[] mTitles, Context context) {
        this.mTitles = mTitles;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public Object getItem(int position) {
        return mTitles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        GridViewHolder holder=null;
        if(view==null){
            holder=new GridViewHolder();
            view= View.inflate(context, R.layout.gridview_item,null);
            holder.textView= (TextView) view.findViewById(R.id.textView);
            view.setTag(holder);
        }else {
            holder= (GridViewHolder) view.getTag();
        }
        holder.textView.setText(mTitles[position]);
        return view;
    }

    class GridViewHolder{
        private TextView textView;
    }
}
