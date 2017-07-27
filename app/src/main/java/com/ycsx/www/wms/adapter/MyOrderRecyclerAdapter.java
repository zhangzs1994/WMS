package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.activity.OrderDetailsActivity;
import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.holder.BottomViewHolder;
import com.ycsx.www.wms.holder.HeaderViewHolder;
import com.ycsx.www.wms.holder.SubmitRecyclerHolder;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ZZS_PC on 2017/5/11.
 */
public class MyOrderRecyclerAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<Map<String,Object>> list;
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    private int mHeaderCount=0;//头部View个数
    private int mBottomCount=0;//底部View个数

    public MyOrderRecyclerAdapter(Context context, List<Map<String,Object>> list) {
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
            return new SubmitRecyclerHolder(LayoutInflater.from(context).inflate(R.layout.submit_recycler_item, parent, false));
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
        } else if (holder instanceof SubmitRecyclerHolder) {
            //处理内容数据
            ((SubmitRecyclerHolder)holder).order_id.setText("订单号："+list.get(position).get("oid"));
            ((SubmitRecyclerHolder)holder).order_price.setText("总额："+new DecimalFormat("######0.00").format(list.get(position).get("ocost")));
            ((SubmitRecyclerHolder)holder).order_time.setText("时间："+list.get(position).get("octime"));
            ((SubmitRecyclerHolder)holder).order_status.setText("订单状态："+list.get(position).get("dvalue"));
            ((SubmitRecyclerHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("order_id",list.get(position).get("oid").toString());
                    intent.putExtra("uid",list.get(position).get("uid").toString());
                    intent.putExtra("dvalue",list.get(position).get("dvalue").toString());
                    intent.putExtra("value",list.get(position).get("value").toString());
                    intent.putExtra("title",list.get(position).get("title").toString());
                    intent.putExtra("classify",list.get(position).get("classify").toString());
                    context.startActivity(intent);
                }
            });
            ((SubmitRecyclerHolder)holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(list.get(position).get("dvalue").toString().equals("已失效")){
                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("确认删除该订单？")    //对话框显示内容
                                //设置按钮
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialog, int which) {
                                        Map<String, String> params = new HashMap<>();
                                        params.put("oid", list.get(position).get("oid").toString());
                                        SharedPreferences pref = context.getSharedPreferences("login", context.MODE_PRIVATE);
                                        params.put("operator", pref.getString("username", ""));
                                        Call<Common> call = RetrofitUtil.getInstance(API.URL).deleteUserOrder(params);
                                        call.enqueue(new Callback<Common>() {
                                            @Override
                                            public void onResponse(Call<Common> call, Response<Common> response) {
                                                if (response.isSuccessful()) {
                                                    Common info = response.body();
                                                    if (("10200").equals(info.getStatus())) {
                                                        Toast.makeText(context, "删除成功！", Toast.LENGTH_SHORT).show();
                                                        dialog.dismiss();
                                                        list.remove(position);
                                                        notifyDataSetChanged();
                                                    } else {
                                                        Log.e("getStatus==", info.getStatus());
                                                        Toast.makeText(context, "删除失败1！", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(context, "删除失败2！", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Common> call, Throwable t) {
                                                Toast.makeText(context, "删除失败3！", Toast.LENGTH_SHORT).show();
                                            }

                                        });
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .create()
                                .show();
                    }
                    return true;
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
