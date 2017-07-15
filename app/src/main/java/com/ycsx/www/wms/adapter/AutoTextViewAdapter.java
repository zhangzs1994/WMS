package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZS_PC on 2017/7/12.
 */
public class AutoTextViewAdapter extends BaseAdapter implements Filterable {
    private List<UserInfo> listName;
    private Context context;
    private ArrayFilter mFilter;
    private ArrayList<UserInfo> mList;

    public AutoTextViewAdapter(List<UserInfo> listName, Context context) {
        this.listName = listName;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listName==null ? 0:listName.size();
    }

    @Override
    public Object getItem(int position) {
        return listName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        MyHolder holder = null;
        if (view == null) {
            holder=new MyHolder();
            view = View.inflate(context, R.layout.auto_textview_item, null);
            holder.likeName = (TextView) view.findViewById(R.id.likeName);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        holder.likeName.setText(listName.get(position).getUserName());
        return view;
    }

    class MyHolder {
        TextView likeName;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    private class ArrayFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            if (mList == null) {
                mList = new ArrayList<UserInfo>(listName);
            }

            if (prefix == null || prefix.length() == 0) {
                ArrayList<UserInfo> list = mList;
                results.values = list;
                results.count = list.size();
            } else {
                String prefixString = prefix.toString().toLowerCase();
                ArrayList<UserInfo> unfilteredValues = mList;
                int count = unfilteredValues.size();
                ArrayList<UserInfo> newValues = new ArrayList<UserInfo>(count);
                for (int i = 0; i < count; i++) {
                    UserInfo user = unfilteredValues.get(i);
                    if (user != null) {
                        if(user.getUserName()!=null && user.getUserName().startsWith(prefixString)){
                            newValues.add(user);
                        }
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            listName = (List<UserInfo>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
