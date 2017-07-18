package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.util.GlideUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by ZZS_PC on 2017/7/4.
 */
public class AddOrderListAdapter extends BaseAdapter{
    private List<Map<String,Object>> list;
    private Context context;
    private SharedPreferences pref;

    public AddOrderListAdapter(List<Map<String,Object>> list, Context context) {
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
        MyHolder holder=null;
        if(view==null){
            holder=new MyHolder();
            view=View.inflate(context, R.layout.addorder_listview_item,null);
            holder.shop_name= (TextView) view.findViewById(R.id.shop_name);
            holder.shop_num= (TextView) view.findViewById(R.id.shop_num);
            holder.shop_price= (TextView) view.findViewById(R.id.shop_price);
            holder.shop_allPrice= (TextView) view.findViewById(R.id.shop_allPrice);
            holder.shop_image= (ImageView) view.findViewById(R.id.shop_image);
            view.setTag(holder);
        }else{
            holder= (MyHolder) view.getTag();
        }
        holder.shop_name.setText(list.get(position).get("pname")+"");
        holder.shop_num.setText("数量："+list.get(position).get("num"));
        holder.shop_price.setText("单价："+new DecimalFormat("######0.00").format(list.get(position).get("price")));
        holder.shop_allPrice.setText("总价："+new DecimalFormat("######0.00").format(Double.parseDouble(list.get(position).get("shop_allPrice")+"")));
        GlideUtils.loadImage(context,list.get(position).get("pictureUrl")+"",holder.shop_image);
        return view;
   }

    class MyHolder{
        TextView shop_name,shop_num,shop_price,shop_allPrice;
        ImageView shop_image;
    }
}
