package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.util.GlideUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by ZZS_PC on 2017/6/19.
 */
public class OrderDetailsAdapter extends BaseAdapter {
    private List<Map<String, Object>> list;
    private Context context;
    private View viewHead;
    private String[] image = null;

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
            holder.order_contact = (TextView) viewHead.findViewById(R.id.order_contact);
            holder.order_receiving = (TextView) viewHead.findViewById(R.id.order_receiving);
            holder.order_creater = (TextView) viewHead.findViewById(R.id.order_creater);
            holder.audit_explain = (TextView) viewHead.findViewById(R.id.audit_explain);
            holder.order_express = (TextView) viewHead.findViewById(R.id.order_express);
            holder.layout_audit_explain = (LinearLayout) viewHead.findViewById(R.id.layout_audit_explain);
            holder.layout_order_express = (LinearLayout) viewHead.findViewById(R.id.layout_order_express);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        holder.order_id.setText(list.get(position).get("oid").toString());
        holder.order_price.setText(new DecimalFormat("######0.00").format(list.get(position).get("ocost")));
        holder.order_status.setText(list.get(position).get("dvalue").toString());
        holder.order_time.setText(list.get(position).get("inventime").toString());
        holder.order_address.setText(list.get(position).get("ouaddress").toString());
        holder.order_contact.setText(list.get(position).get("contact")+"");
        holder.order_receiving.setText(list.get(position).get("receiving")+"");
        holder.order_creater.setText(list.get(position).get("uname")+"");
        holder.audit_explain.setText(list.get(position).get("criteria")+"");
        holder.order_express.setText(list.get(position).get("expressnumber")+"");
        holder.shop_name.setText(list.get(position).get("name").toString());
        holder.shop_price.setText("单价："+new DecimalFormat("######0.00").format(list.get(position).get("price")));
        holder.shop_num.setText("数量："+list.get(position).get("freightamount").toString());
        holder.shop_totalPrice.setText("总价："+new DecimalFormat("######0.00").format(list.get(position).get("iocost")));
        image = convertStrToArray(list.get(position).get("pictureUrl").toString());
        GlideUtils.loadImage(context, image[0], holder.shop_image);
        if(Integer.parseInt(list.get(position).get("ostatus")+"")==0){
            holder.layout_audit_explain.setVisibility(View.GONE);
            holder.layout_order_express.setVisibility(View.GONE);
        }else if(Integer.parseInt(list.get(position).get("ostatus")+"")==3){
            holder.layout_audit_explain.setVisibility(View.VISIBLE);
            holder.layout_order_express.setVisibility(View.VISIBLE);
        }else{
            holder.layout_audit_explain.setVisibility(View.VISIBLE);
            holder.layout_order_express.setVisibility(View.GONE);
        }
        return view;
    }

    public String[] convertStrToArray(String str) {
        //Log.e("image", "" + strArray.length);
        if (!str.contains(",")) {
            image = new String[1];
            image[0] = str;
        } else {
            image = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        }
        return image;
    }

    class MyHolder {
        TextView shop_name, shop_price, shop_num, shop_totalPrice, order_id,
                order_price, order_status, order_time, order_address,order_receiving,
                order_contact,order_creater,audit_explain,order_express;
        ImageView shop_image;
        LinearLayout layout_audit_explain,layout_order_express;
    }
}
