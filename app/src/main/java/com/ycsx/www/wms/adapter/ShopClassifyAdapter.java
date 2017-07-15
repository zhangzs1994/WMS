package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ZZS_PC on 2017/6/22.
 */
public class ShopClassifyAdapter extends BaseAdapter {
    private List<Map<String,Object>> list;
    private Context context;

    public ShopClassifyAdapter(List<Map<String,Object>> list, Context context) {
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
    public View getView(final int position, View view, ViewGroup parent) {
        MyHolder holder = null;
        if (view == null) {
            holder = new MyHolder();
            view = View.inflate(context, R.layout.classify_item, null);
            holder.classify_name= (TextView) view.findViewById(R.id.classify_name);
            holder.classify_delete= (Button) view.findViewById(R.id.classify_delete);
            view.setTag(holder);
        }else {
            holder= (MyHolder) view.getTag();
        }
        holder.classify_name.setText(list.get(position).get("value")+"");
        holder.classify_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder .setMessage("确认删除该分类？")    //对话框显示内容
                        //设置按钮
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                Map<String, String> params = new HashMap<>();
                                params.put("colName", "goodsCategory");
                                params.put("code", list.get(position).get("code")+"");
                                Call<Common> call = RetrofitUtil.getInstance(API.URL).deleteDropdown(params);
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
                                            }else {
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
        });
        return view;
    }

    class MyHolder {
        TextView classify_name;
        Button classify_update,classify_delete;
    }
}
