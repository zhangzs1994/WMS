package com.ycsx.www.wms.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ycsx.www.wms.R;

/**
 * Created by ZZS_PC on 2017/5/11.
 */
public class OrderRecyclerHolder extends RecyclerView.ViewHolder {
    public TextView order_id,order_time,order_price,order_status;

    public OrderRecyclerHolder(View itemView) {
        super(itemView);
        order_id= (TextView) itemView.findViewById(R.id.order_id);
        order_time= (TextView) itemView.findViewById(R.id.order_time);
        order_price= (TextView) itemView.findViewById(R.id.order_price);
        order_status= (TextView) itemView.findViewById(R.id.order_status);
    }
}
