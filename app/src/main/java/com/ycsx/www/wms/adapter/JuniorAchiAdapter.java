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
 * Created by ZZS_PC on 2017/6/22.
 */
public class JuniorAchiAdapter extends BaseAdapter {
    private List<Map<String,String>> list;
    private Context context;

    public JuniorAchiAdapter(List<Map<String,String>> list, Context context) {
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
        MyHolder holder = null;
        if (view == null) {
            holder = new MyHolder();
            view = View.inflate(context, R.layout.junior_item, null);
            holder.junior_name= (TextView) view.findViewById(R.id.junior_name);
            view.setTag(holder);
        }else {
            holder= (MyHolder) view.getTag();
        }
        holder.junior_name.setText(list.get(position).get("name")+"的业绩");
        return view;
    }

    class MyHolder {
        TextView junior_name;
    }
}
