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
import com.ycsx.www.wms.holder.ShopAddHolder;
import com.ycsx.www.wms.util.GlideUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by ZZS_PC on 2017/5/11.
 */
public class ShopAddAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Map<String, Object>> list;
    private String[] image = null;
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    private int mHeaderCount = 0;//头部View个数
    private int mBottomCount = 0;//底部View个数
    private ImageInterface imageInterface;

    public ShopAddAdapter(ImageInterface imageInterface, Context context, List<Map<String, Object>> list) {
        this.imageInterface = imageInterface;
        this.context = context;
        this.list = list;
    }

    public interface ImageInterface {
        void onclick(View view, int position);
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
    public int getContentItemCount() {
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
        if (viewType == ITEM_TYPE_HEADER) {
            //获取头部布局
            return new HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.header_view, parent, false));
        } else if (viewType == ITEM_TYPE_CONTENT) {
            //获取内容布局
            return new ShopAddHolder(LayoutInflater.from(context).inflate(R.layout.shop_add_item, parent, false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            //获取底部布局
            return new BottomViewHolder(LayoutInflater.from(context).inflate(R.layout.footer_view, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            //处理头部数据
            ((HeaderViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "这是头部", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof ShopAddHolder) {
            //处理内容数据
            ((ShopAddHolder) holder).shop_name.setText(list.get(position).get("name").toString());
            ((ShopAddHolder) holder).shop_price.setText("价格：" + list.get(position).get("price").toString());
            ((ShopAddHolder) holder).shop_describ.setText(list.get(position).get("describ").toString());
            ((ShopAddHolder) holder).shop_stock.setText("库存：" + list.get(position).get("stock").toString());
            image = convertStrToArray(list.get(position).get("pictureUrl").toString());
            GlideUtils.loadImage(context, image[0], ((ShopAddHolder)holder).shop_image);
            ((ShopAddHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShopDetailsActivity.class);
                    intent.putExtra("id", list.get(position).get("id").toString());
                    context.startActivity(intent);
                }
            });
            ((ShopAddHolder) holder).shop_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageInterface != null) {
                        imageInterface.onclick(v, position);
                    }
                }
            });
        } else if (holder instanceof BottomViewHolder) {
            //处理底部数据
            ((BottomViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "这是底部", Toast.LENGTH_SHORT).show();
                }
            });
        }
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

    //获取总条目数（包括头部和底部）
    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }

}
