package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.GlideUtils;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ZZS_PC on 2017/7/4.
 */
public class MyOrderListAdapter extends BaseAdapter {
    private List<Map<String, Object>> list;
    private Context context;
    private SharedPreferences pref;
    private UpdateMyShop updateMyShop;

    public MyOrderListAdapter(List<Map<String, Object>> list, Context context, UpdateMyShop updateMyShop) {
        this.list = list;
        this.context = context;
        this.updateMyShop = updateMyShop;
    }

    public interface UpdateMyShop {
        void onclick(View view, int position);
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
        MyHolder holder = null;
        if (view == null) {
            holder = new MyHolder();
            view = View.inflate(context, R.layout.mineorder_listview_item, null);
            holder.shop_name = (TextView) view.findViewById(R.id.shop_name);
            holder.shop_num = (TextView) view.findViewById(R.id.shop_num);
            holder.shop_price = (TextView) view.findViewById(R.id.shop_price);
            holder.delete = (Button) view.findViewById(R.id.delete);
            holder.update = (Button) view.findViewById(R.id.update);
            holder.shop_image = (ImageView) view.findViewById(R.id.shop_image);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        holder.shop_name.setText(list.get(position).get("pname") + "");
        holder.shop_num.setText("数量：" + list.get(position).get("num"));
        holder.shop_price.setText("单价：" + new DecimalFormat("######0.00").format(list.get(position).get("price")));
        GlideUtils.loadImage(context, list.get(position).get("pictureUrl") + "", holder.shop_image);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("确认移除该商品？")    //对话框显示内容
                        //设置按钮
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                pref = context.getSharedPreferences("login", context.MODE_PRIVATE);
                                Map<String, String> params = new HashMap<>();
                                params.put("uid", pref.getInt("id", 0) + "");
                                params.put("pid", list.get(position).get("pid").toString());
                                Call<Common> call = RetrofitUtil.getInstance(API.URL).deleteMyorde(params);
                                call.enqueue(new Callback<Common>() {
                                    @Override
                                    public void onResponse(Call<Common> call, Response<Common> response) {
                                        if (response.isSuccessful()) {
                                            Common info = response.body();
                                            if (("10200").equals(info.getStatus())) {
                                                Toast.makeText(context, "移除成功！", Toast.LENGTH_SHORT).show();
                                                list.remove(position);
                                                notifyDataSetChanged();
                                            } else {
                                                Log.e("getStatus==", info.getStatus());
                                                Toast.makeText(context, "移除失败1！", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Log.e("code==", response.code() + "");
                                            Toast.makeText(context, "移除失败2！", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Common> call, Throwable t) {
                                        Toast.makeText(context, "移除失败3！", Toast.LENGTH_SHORT).show();
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
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateMyShop != null) {
                    updateMyShop.onclick(v, position);
                }
            }
        });
        return view;
    }

    class MyHolder {
        Button delete, update;
        TextView shop_name, shop_num, shop_price;
        ImageView shop_image;
    }
}
