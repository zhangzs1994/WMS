package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.activity.ShopDetailsActivity;
import com.ycsx.www.wms.holder.BottomViewHolder;
import com.ycsx.www.wms.holder.HeaderViewHolder;
import com.ycsx.www.wms.holder.ShopRecyclerHolder;

import java.util.List;
import java.util.Map;

/**
 * Created by ZZS_PC on 2017/5/11.
 */
public class ShopRecyclerAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<Map<String,Object>> list;
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    private int mHeaderCount=0;//头部View个数
    private int mBottomCount=0;//底部View个数

    public ShopRecyclerAdapter(Context context, List<Map<String,Object>> list) {
        this.context = context;
        this.list = list;
    }

    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }
    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (mHeaderCount + getContentItemCount());
    }

    //内容长度
    public int getContentItemCount(){
        return list.size();
    }


    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mHeaderCount != 0 && position < mHeaderCount) {
            //头部View
            return ITEM_TYPE_HEADER;
        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType ==ITEM_TYPE_HEADER) {
            //获取头部布局
            return new HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.header_view, parent, false));
        } else if (viewType == ITEM_TYPE_CONTENT) {
            //获取内容布局
            return new ShopRecyclerHolder(LayoutInflater.from(context).inflate(R.layout.shop_recycler_item, parent, false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            //获取底部布局
            return new BottomViewHolder(LayoutInflater.from(context).inflate(R.layout.footer_view, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            //处理头部数据
            ((HeaderViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "这是头部", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof ShopRecyclerHolder) {
            //处理内容数据
            ((ShopRecyclerHolder)holder).shop_name.setText(list.get(position).get("name").toString());
            ((ShopRecyclerHolder)holder).shop_price.setText("价格："+list.get(position).get("price").toString());
            ((ShopRecyclerHolder)holder).shop_describ.setText(list.get(position).get("describ").toString());
            ((ShopRecyclerHolder)holder).shop_stock.setText("库存："+list.get(position).get("stock").toString());
            ((ShopRecyclerHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ShopDetailsActivity.class);
                    intent.putExtra("name",list.get(position).get("name").toString());
                    intent.putExtra("category",list.get(position).get("category").toString());
                    intent.putExtra("instockTime",list.get(position).get("instockTime").toString());
                    intent.putExtra("outstockTime",list.get(position).get("outstockTime").toString());
                    intent.putExtra("stock",list.get(position).get("stock").toString());
                    intent.putExtra("price",list.get(position).get("price").toString());
                    intent.putExtra("spec",list.get(position).get("spec").toString());
                    intent.putExtra("manufactureTime",list.get(position).get("manufactureTime").toString());
                    intent.putExtra("qualityTime",list.get(position).get("qualityTime").toString());
                    intent.putExtra("describ",list.get(position).get("describ").toString());
                    intent.putExtra("transactor",list.get(position).get("transactor").toString());
                    intent.putExtra("goodsStatus",list.get(position).get("goodsStatus").toString());
                    intent.putExtra("acceptedGoods",list.get(position).get("acceptedGoods").toString());
                    context.startActivity(intent);
                }
            });
        } else if (holder instanceof BottomViewHolder) {
            //处理底部数据
            ((BottomViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "这是底部", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //获取总条目数（包括头部和底部）
    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }

}
