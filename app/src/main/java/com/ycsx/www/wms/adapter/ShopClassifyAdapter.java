package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ycsx.www.wms.R;

import java.util.List;
import java.util.Map;

/**
 * Created by ZZS_PC on 2017/6/22.
 */
public class ShopClassifyAdapter extends BaseAdapter {
    private List<Map<String, Object>> list;
    private Context context;
    private SharedPreferences pref;

    public ShopClassifyAdapter(List<Map<String, Object>> list, Context context) {
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
    public View getView(final int position, View view, ViewGroup parent) {
        MyHolder holder = null;
        if (view == null) {
            holder = new MyHolder();
            view = View.inflate(context, R.layout.classify_item, null);
            holder.classify_name = (TextView) view.findViewById(R.id.classify_name);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        holder.classify_name.setText(list.get(position).get("value") + "");
        return view;
    }

    class MyHolder {
        TextView classify_name;
        Button classify_delete;
    }
}
