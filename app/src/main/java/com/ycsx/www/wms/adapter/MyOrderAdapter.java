package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ycsx.www.wms.R;

import java.util.List;
import java.util.Map;

/**
 * Created by ZZS_PC on 2017/7/1.
 */
public class MyOrderAdapter extends BaseAdapter{
    private List<Map<String,String>> list;
    private Context context;

    public MyOrderAdapter(List<Map<String, String>> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        MyHolder holder=null;
        if(view==null){
            holder=new MyHolder();
            view=View.inflate(context,R.layout.myorder_listview_item,null);
            holder.shop_name= (TextView) view.findViewById(R.id.shop_name);
            holder.shop_num= (TextView) view.findViewById(R.id.shop_num);
            holder.shop_price= (TextView) view.findViewById(R.id.shop_price);
            view.setTag(holder);
        }else{
            holder= (MyHolder) view.getTag();
        }
        holder.shop_name.setText(list.get(position).get("name"));
        holder.shop_num.setText(list.get(position).get("num"));
        holder.shop_price.setText(list.get(position).get("price"));
        return view;
    }

    class MyHolder{
        TextView shop_name,shop_num,shop_price;
    }
}
