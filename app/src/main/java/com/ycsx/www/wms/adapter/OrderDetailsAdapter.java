package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycsx.www.wms.R;

import java.util.List;
import java.util.Map;

/**
 * Created by ZZS_PC on 2017/6/19.
 */
public class OrderDetailsAdapter extends BaseAdapter {
    private List<Map<String, Object>> list;
    private Context context;
    private View viewHead;

    public OrderDetailsAdapter(View viewHead, List<Map<String, Object>> list, Context context) {
        this.viewHead = viewHead;
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
        if (holder == null) {
            holder = new MyHolder();
            view = View.inflate(context, R.layout.order_shopinfo_item, null);
            holder.shop_image = (ImageView) view.findViewById(R.id.shop_image);
            holder.shop_name = (TextView) view.findViewById(R.id.shop_name);
            holder.shop_price = (TextView) view.findViewById(R.id.shop_price);
            holder.shop_num = (TextView) view.findViewById(R.id.shop_num);
            holder.shop_totalPrice = (TextView) view.findViewById(R.id.shop_totalPrice);
            holder.order_id = (TextView) viewHead.findViewById(R.id.order_id);
            holder.order_price = (TextView) viewHead.findViewById(R.id.order_price);
            holder.order_status = (TextView) viewHead.findViewById(R.id.order_status);
            holder.order_time = (TextView) viewHead.findViewById(R.id.order_time);
            holder.order_address = (TextView) viewHead.findViewById(R.id.order_address);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        holder.shop_name.setText(list.get(position).get("name").toString());
        holder.shop_price.setText("单价："+list.get(position).get("price").toString());
        holder.shop_num.setText("数量："+list.get(position).get("freightamount").toString());
        holder.shop_totalPrice.setText("总价："+list.get(position).get("iocost").toString());
        holder.order_id.setText(list.get(position).get("oid").toString());
        holder.order_price.setText(list.get(position).get("ocost").toString());
        if(list.get(position).get("ostatus").equals("0")){
            holder.order_status.setText("待审核");
        }else if(list.get(position).get("ostatus").equals("1")){
            holder.order_status.setText("审核已通过");
        }else if(list.get(position).get("ostatus").equals("2")){
            holder.order_status.setText("审核未通过");
        }else if(list.get(position).get("ostatus").equals("3")){
            holder.order_status.setText("发货");
        }
        holder.order_time.setText(list.get(position).get("inventime").toString());
        holder.order_address.setText(list.get(position).get("ouaddress").toString());
        return view;
    }

    class MyHolder {
        TextView shop_name, shop_price, shop_num, shop_totalPrice, order_id,
                order_price, order_status, order_time, order_address;
        ImageView shop_image;
    }
}
