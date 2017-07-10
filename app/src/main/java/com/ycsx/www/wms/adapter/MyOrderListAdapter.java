package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.common.API;
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
public class MyOrderListAdapter extends BaseAdapter{
    private List<Map<String,Object>> list;
    private Context context;
    private SharedPreferences pref;

    public MyOrderListAdapter(List<Map<String,Object>> list, Context context) {
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
        MyHolder holder=null;
        if(view==null){
            holder=new MyHolder();
            view=View.inflate(context, R.layout.mineorder_listview_item,null);
            holder.shop_name= (TextView) view.findViewById(R.id.shop_name);
            holder.shop_num= (TextView) view.findViewById(R.id.shop_num);
            holder.shop_price= (TextView) view.findViewById(R.id.shop_price);
            holder.delete= (LinearLayout) view.findViewById(R.id.delete);
            view.setTag(holder);
        }else{
            holder= (MyHolder) view.getTag();
        }
        holder.shop_name.setText(list.get(position).get("pname")+"");
        holder.shop_num.setText("数量："+list.get(position).get("num"));
        holder.shop_price.setText("单价："+new DecimalFormat("######0.00").format(list.get(position).get("price")));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                Toast.makeText(context, "删除成功！", Toast.LENGTH_SHORT).show();
                                list.remove(position);
                                notifyDataSetChanged();
                            } else {
                                Log.e("getStatus==", info.getStatus());
                                Toast.makeText(context, "删除失败1！", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e("code==", response.code()+"");
                            Toast.makeText(context, "删除失败2！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Common> call, Throwable t) {
                        Toast.makeText(context, "删除失败3！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
   }

    class MyHolder{
        LinearLayout delete;
        TextView shop_name,shop_num,shop_price;
    }
}
