package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ycsx.www.wms.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RolesListViewAdapter extends BaseExpandableListAdapter {
    public List<String> group;
    public List<List<String>> list;
    LayoutInflater mInflater;
    Context context;

    public RolesListViewAdapter(List<String> group, List<List<String>> list, Context context) {
        mInflater = LayoutInflater.from(context);
        this.group = group;
        this.list = list;
        this.context = context;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(R.layout.channel_expandablelistview_item, null);
            mViewChild.listView = (ListView) convertView.findViewById(R.id.channel_item_child_gridView);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(context, setGridViewData(list.get(groupPosition)), R.layout.channel_listview_item,
                new String[]{"child"}, new int[]{R.id.channel_listview_item});
        mViewChild.listView.setAdapter(mSimpleAdapter);
        setGridViewListener(mViewChild.listView);
        mViewChild.listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        hashMap.put(groupPosition + "", mViewChild.gridView);
        return convertView;
    }

    /**
     * 设置gridview点击事件监听
     *
     * @param listView
     */
    private void setGridViewListener(final ListView listView) {
        listView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view instanceof TextView) {
                    //如果想要获取到哪一行，则自定义gridview的adapter，item设置2个textview一个隐藏设置id，显示哪一行
                    TextView tv = (TextView) view;
//                    Toast.makeText(context, "position=" + position+"||"+tv.getText(), Toast.LENGTH_SHORT).show();
//                    Log.e("hefeng", "gridView listaner position=" + position + "||text="+tv.getText());
                }
            }
        });
    }


    /**
     * 设置gridview数据
     *
     * @param data
     * @return
     */
    private ArrayList<HashMap<String, Object>> setGridViewData(List data) {
        ArrayList<HashMap<String, Object>> gridItem = new ArrayList<HashMap<String, Object>>();
        if(data.size()==0){
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("child", "无");
            gridItem.add(hashMap);
        }else {
            for (int i = 0; i < data.size(); i++) {
                HashMap<String, Object> hashMap = new HashMap<String, Object>();
                hashMap.put("child", data.get(i));
                gridItem.add(hashMap);
            }
        }
        return gridItem;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public String getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(R.layout.channel_expandablelistview, null);
            mViewChild.textView = (TextView) convertView.findViewById(R.id.channel_group_name);
            mViewChild.imageView = (ImageView) convertView.findViewById(R.id.channel_imageview_orientation);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }

        if (isExpanded) {
            mViewChild.imageView.setImageResource(R.drawable.channel_expandablelistview_top_icon);
        } else {
            mViewChild.imageView.setImageResource(R.drawable.channel_expandablelistview_bottom_icon);
        }
        mViewChild.textView.setText(getGroup(groupPosition));
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    ViewChild mViewChild;

    static class ViewChild {
        ImageView imageView;
        TextView textView;
        ListView listView;
    }
}
