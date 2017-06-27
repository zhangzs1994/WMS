package com.ycsx.www.wms.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycsx.www.wms.R;

/**
 * Created by ZZS_PC on 2017/5/11.
 */
public class OutStockRecyclerHolder extends RecyclerView.ViewHolder {
    public TextView shop_name,shop_describ,shop_outstockTime,shop_stock;
    public ImageView shop_image;

    public OutStockRecyclerHolder(View itemView) {
        super(itemView);
        shop_name= (TextView) itemView.findViewById(R.id.shop_name);
        shop_outstockTime= (TextView) itemView.findViewById(R.id.shop_outstockTime);
        shop_describ= (TextView) itemView.findViewById(R.id.shop_describ);
        //shop_stock= (TextView) itemView.findViewById(R.id.shop_stock);
        shop_image= (ImageView) itemView.findViewById(R.id.shop_image);
    }
}
