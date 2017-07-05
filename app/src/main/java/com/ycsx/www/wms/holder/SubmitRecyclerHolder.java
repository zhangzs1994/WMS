package com.ycsx.www.wms.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ycsx.www.wms.R;

/**
 * Created by ZZS_PC on 2017/5/11.
 */
public class SubmitRecyclerHolder extends RecyclerView.ViewHolder {
    public TextView order_id,order_status,order_price,order_time;

    public SubmitRecyclerHolder(View itemView) {
        super(itemView);
        order_id= (TextView) itemView.findViewById(R.id.order_id);
        order_price= (TextView) itemView.findViewById(R.id.order_price);
        order_time= (TextView) itemView.findViewById(R.id.order_time);
        order_status= (TextView) itemView.findViewById(R.id.order_status);
    }
}
