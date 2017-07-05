package com.ycsx.www.wms.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.viewbadger.BadgeView;
import com.ycsx.www.wms.R;
import com.ycsx.www.wms.activity.AddOrderActivity;
import com.ycsx.www.wms.activity.ShopDetailsActivity;
import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.bean.OrderShop;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.holder.BottomViewHolder;
import com.ycsx.www.wms.holder.HeaderViewHolder;
import com.ycsx.www.wms.holder.ShopAddHolder;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ZZS_PC on 2017/5/11.
 */
public class ShopAddAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Map<String, Object>> list;
    private PopupWindow popupWindow;
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    private int mHeaderCount = 0;//头部View个数
    private int mBottomCount = 0;//底部View个数
    private int count = 0;
    private BadgeView badgeView;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private TextView text_finish;
    private Button btn_finish;

    public ShopAddAdapter(Context context, List<Map<String, Object>> list) {
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
            ((ShopAddHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShopDetailsActivity.class);
                    intent.putExtra("name", list.get(position).get("name").toString());
                    intent.putExtra("category", list.get(position).get("category").toString());
                    intent.putExtra("instockTime", list.get(position).get("instockTime").toString());
                    intent.putExtra("outstockTime", list.get(position).get("outstockTime").toString());
                    intent.putExtra("stock", list.get(position).get("stock").toString());
                    intent.putExtra("price", list.get(position).get("price").toString());
                    intent.putExtra("spec", list.get(position).get("spec").toString());
                    intent.putExtra("manufactureTime", list.get(position).get("manufactureTime").toString());
                    intent.putExtra("qualityTime", list.get(position).get("qualityTime").toString());
                    intent.putExtra("describ", list.get(position).get("describ").toString());
                    intent.putExtra("transactor", list.get(position).get("transactor").toString());
                    intent.putExtra("goodsStatus", list.get(position).get("goodsStatus").toString());
                    intent.putExtra("acceptedGoods", list.get(position).get("acceptedGoods").toString());
                    context.startActivity(intent);
                }
            });
            popupWindow = new PopupWindow(context);
            View view = LayoutInflater.from(context).inflate(R.layout.shop_finish_popup, null);
            LinearLayout shop_card = (LinearLayout) view.findViewById(R.id.shop_card);
            badgeView = new BadgeView(context, shop_card);
            badgeView.setBackgroundResource(R.drawable.update_round);
            badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT); //默认值
            badgeView.setBadgeMargin(5);
            badgeView.setTextSize(12);
            text_finish = (TextView) view.findViewById(R.id.text_finish);
            btn_finish = (Button) view.findViewById(R.id.btn_finish);
            popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setHeight(100);
            popupWindow.setContentView(view);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            select();
            btn_finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,AddOrderActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新
                    context.startActivity(intent);
                }
            });
            ((ShopAddHolder) holder).shop_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow = new PopupWindow(context);
                    popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                    popupWindow.setFocusable(true);
                    View view = LayoutInflater.from(context).inflate(R.layout.shop_add_popup, null);
                    TextView name = (TextView) view.findViewById(R.id.name);
                    name.setText(list.get(position).get("name").toString());
                    final TextView stock = (TextView) view.findViewById(R.id.stock);
                    stock.setText(list.get(position).get("stock").toString());
                    final EditText num = (EditText) view.findViewById(R.id.num);
                    num.setText("1");
                    final TextView price = (TextView) view.findViewById(R.id.price);
                    price.setText(list.get(position).get("price").toString());
                    final TextView all_price = (TextView) view.findViewById(R.id.all_price);
                    if (num.getText().toString().equals("")) {
                        num.setText("1");
                    }
                    all_price.setText((Integer.parseInt(num.getText() + "")) * Double.parseDouble(price.getText() + "") + "");
                    TextView lessen = (TextView) view.findViewById(R.id.lessen);
                    TextView add = (TextView) view.findViewById(R.id.add);
                    final Button cancel = (Button) view.findViewById(R.id.cancel);
                    Button confirm = (Button) view.findViewById(R.id.confirm);
                    popupWindow.setContentView(view);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                    num.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!num.getText().toString().equals("")) {
                                if (Integer.parseInt(num.getText() + "") <= Integer.parseInt(stock.getText() + "")) {
                                    all_price.setText((Integer.parseInt(num.getText() + "")) * Double.parseDouble(price.getText() + "") + "");
                                } else {
                                    Toast.makeText(context, "输入数量大于库存数量，请重新输入！", Toast.LENGTH_SHORT).show();
                                    num.setText(stock.getText() + "");
                                }
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });
                    lessen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (num.getText().toString().equals("")) {
                                num.setText("1");
                                all_price.setText((Integer.parseInt(num.getText() + "")) * Double.parseDouble(price.getText() + "") + "");
                            }
                            if (Integer.parseInt(num.getText() + "") > 1) {
                                num.setText((Integer.parseInt(num.getText() + "") - 1) + "");
                                all_price.setText((Integer.parseInt(num.getText() + "")) * Double.parseDouble(price.getText() + "") + "");
                            } else {
                                num.setText("1");
                                all_price.setText((Integer.parseInt(num.getText() + "")) * Double.parseDouble(price.getText() + "") + "");
                            }
                        }
                    });
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (num.getText().toString().equals("")) {
                                num.setText("1");
                                all_price.setText((Integer.parseInt(num.getText() + "")) * Double.parseDouble(price.getText() + "") + "");
                            }
                            if (Integer.parseInt(num.getText() + "") < Integer.parseInt(stock.getText() + "")) {
                                num.setText((Integer.parseInt(num.getText() + "") + 1) + "");
                                all_price.setText((Integer.parseInt(num.getText() + "")) * Double.parseDouble(price.getText() + "") + "");
                            } else {
                                num.setText(Integer.parseInt(stock.getText() + "") + "");
                                all_price.setText((Integer.parseInt(num.getText() + "")) * Double.parseDouble(price.getText() + "") + "");
                            }
                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (all_price.getText().toString().equals("") || all_price.getText().toString().equals("0.0")) {
                                Toast.makeText(context, "已移除商品！", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.e("name", list.get(position).get("name").toString());
                                Log.e("id", list.get(position).get("id").toString());
                                pref = context.getSharedPreferences("login", context.MODE_PRIVATE);
                                Map<String, String> params = new HashMap<>();
                                params.put("uid", pref.getInt("id", 0) + "");
                                params.put("pid", list.get(position).get("id").toString());
                                params.put("pname", list.get(position).get("name").toString());
                                params.put("num", num.getText() + "");
                                params.put("price", price.getText() + "");
                                Call<Common> call = RetrofitUtil.getInstance(API.URL).addMyorder(params);
                                call.enqueue(new Callback<Common>() {
                                    @Override
                                    public void onResponse(Call<Common> call, Response<Common> response) {
                                        if (response.isSuccessful()) {
                                            Common info = response.body();
                                            if (("10200").equals(info.getStatus())) {
                                                Toast.makeText(context, "添加成功！", Toast.LENGTH_SHORT).show();
                                                select();
                                            } else {
                                                Log.e("getStatus==", info.getStatus());
                                                Toast.makeText(context, "添加失败1！", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Log.e("code==", response.code()+"");
                                            Toast.makeText(context, "添加失败2！", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Common> call, Throwable t) {
                                        Toast.makeText(context, "添加失败3！", Toast.LENGTH_SHORT).show();
                                    }

                                });
                                popupWindow.dismiss();
                            }
                        }
                    });
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

    private void select() {
        pref = context.getSharedPreferences("login", context.MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("uid", pref.getInt("id", 0) + "");
        Call<OrderShop> call1 = RetrofitUtil.getInstance(API.URL).selectMyorde(params);
        call1.enqueue(new Callback<OrderShop>() {
            @Override
            public void onResponse(Call<OrderShop> call, Response<OrderShop> response) {
                if (response.isSuccessful()) {
                    OrderShop info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        count = info.getData().size();
                    }else  if(("10365").equals(info.getStatus())) {
                        count=0;
                    } else {
                        Toast.makeText(context, "查询失败1！", Toast.LENGTH_SHORT).show();
                    }
                    if(count>0){
                        badgeView.setText(count+"");
                        badgeView.show();
                        text_finish.setVisibility(View.GONE);
                        btn_finish.setVisibility(View.VISIBLE);
                    }else {
                        badgeView.hide();
                        text_finish.setVisibility(View.VISIBLE);
                        btn_finish.setVisibility(View.GONE);
                    }
                    Log.e("count", "==" + count);
                } else {
                    Toast.makeText(context, "查询失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderShop> call, Throwable t) {
                Log.e("getMessage", "===" + t.getMessage());
                Toast.makeText(context, "查询失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取总条目数（包括头部和底部）
    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }

}
