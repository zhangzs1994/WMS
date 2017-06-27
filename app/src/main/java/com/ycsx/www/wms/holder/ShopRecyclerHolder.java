package com.ycsx.www.wms.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycsx.www.wms.R;

/**
 * Created by ZZS_PC on 2017/5/11.
 */
public class ShopRecyclerHolder extends RecyclerView.ViewHolder {
    public TextView shop_name,shop_price,shop_stock,shop_describ;
    public ImageView shop_image;

    public ShopRecyclerHolder(View itemView) {
        super(itemView);
        shop_name= (TextView) itemView.findViewById(R.id.shop_name);
        shop_price= (TextView) itemView.findViewById(R.id.shop_price);
        shop_describ= (TextView) itemView.findViewById(R.id.shop_describ);
        shop_stock= (TextView) itemView.findViewById(R.id.shop_stock);
        shop_image= (ImageView) itemView.findViewById(R.id.shop_image);
    }
}
