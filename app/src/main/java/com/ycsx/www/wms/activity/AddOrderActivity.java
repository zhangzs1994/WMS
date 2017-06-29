package com.ycsx.www.wms.activity;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.AddOrderInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddOrderActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout shop1, shop2, shop3, shop4, shop5, shop6, shop7, shop8, shop9, shop10, shop11,
            shop12, shop13, shop14, shop15, shop16, shop17, shop18, shop19, shop20;
    private TextView add, lessen;
    private EditText id1, id2, id3, id4, id5, id6, id7, id8, id9, id10, id11, id12, id13, id14,
            id15, id16, id17, id18, id19, id20,
            num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12, num13,
            num14, num15, num16, num17, num18, num19, num20,
            price1, price2, price3, price4, price5, price6, price7, price8, price9, price10,
            price11, price12, price13, price14, price15, price16, price17, price18, price19, price20,
            ouaddress, ocost;
    private Button submit, keep;
    private int num = 1;
    private AddOrderInfo order=new AddOrderInfo();
    private List<AddOrderInfo.DataBean> datas=new ArrayList<>();
    private String orderDetial;
    private GsonBuilder builder;
    private Gson gson;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_add_order);
        initView();
        builder=new GsonBuilder();
        gson=builder.create();
    }

    private void initView() {
        add = (TextView) findViewById(R.id.add);
        lessen = (TextView) findViewById(R.id.lessen);
        add.setOnClickListener(this);
        lessen.setOnClickListener(this);
        submit = (Button) findViewById(R.id.submit);
        keep = (Button) findViewById(R.id.keep);
        submit.setOnClickListener(this);
        keep.setOnClickListener(this);
        ouaddress = (EditText) findViewById(R.id.ouaddress);
        ocost = (EditText) findViewById(R.id.ocost);
        shop1 = (LinearLayout) findViewById(R.id.shop1);
        shop2 = (LinearLayout) findViewById(R.id.shop2);
        shop3 = (LinearLayout) findViewById(R.id.shop3);
        shop4 = (LinearLayout) findViewById(R.id.shop4);
        shop5 = (LinearLayout) findViewById(R.id.shop5);
        shop6 = (LinearLayout) findViewById(R.id.shop6);
        shop7 = (LinearLayout) findViewById(R.id.shop7);
        shop8 = (LinearLayout) findViewById(R.id.shop8);
        shop9 = (LinearLayout) findViewById(R.id.shop9);
        shop10 = (LinearLayout) findViewById(R.id.shop10);
        shop11 = (LinearLayout) findViewById(R.id.shop11);
        shop12 = (LinearLayout) findViewById(R.id.shop12);
        shop13 = (LinearLayout) findViewById(R.id.shop13);
        shop14 = (LinearLayout) findViewById(R.id.shop14);
        shop15 = (LinearLayout) findViewById(R.id.shop15);
        shop16 = (LinearLayout) findViewById(R.id.shop16);
        shop17 = (LinearLayout) findViewById(R.id.shop17);
        shop18 = (LinearLayout) findViewById(R.id.shop18);
        shop19 = (LinearLayout) findViewById(R.id.shop19);
        shop20 = (LinearLayout) findViewById(R.id.shop20);
        id1 = (EditText) findViewById(R.id.id1);
        id2 = (EditText) findViewById(R.id.id2);
        id3 = (EditText) findViewById(R.id.id3);
        id4 = (EditText) findViewById(R.id.id4);
        id5 = (EditText) findViewById(R.id.id5);
        id6 = (EditText) findViewById(R.id.id6);
        id7 = (EditText) findViewById(R.id.id7);
        id8 = (EditText) findViewById(R.id.id8);
        id9 = (EditText) findViewById(R.id.id9);
        id10 = (EditText) findViewById(R.id.id10);
        id11 = (EditText) findViewById(R.id.id11);
        id12 = (EditText) findViewById(R.id.id12);
        id13 = (EditText) findViewById(R.id.id13);
        id14 = (EditText) findViewById(R.id.id14);
        id15 = (EditText) findViewById(R.id.id15);
        id16 = (EditText) findViewById(R.id.id16);
        id17 = (EditText) findViewById(R.id.id17);
        id18 = (EditText) findViewById(R.id.id18);
        id19 = (EditText) findViewById(R.id.id19);
        id20 = (EditText) findViewById(R.id.id20);
        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        num3 = (EditText) findViewById(R.id.num3);
        num4 = (EditText) findViewById(R.id.num4);
        num5 = (EditText) findViewById(R.id.num5);
        num6 = (EditText) findViewById(R.id.num6);
        num7 = (EditText) findViewById(R.id.num7);
        num8 = (EditText) findViewById(R.id.num8);
        num9 = (EditText) findViewById(R.id.num9);
        num10 = (EditText) findViewById(R.id.num10);
        num11 = (EditText) findViewById(R.id.num11);
        num12 = (EditText) findViewById(R.id.num12);
        num13 = (EditText) findViewById(R.id.num13);
        num14 = (EditText) findViewById(R.id.num14);
        num15 = (EditText) findViewById(R.id.num15);
        num16 = (EditText) findViewById(R.id.num16);
        num17 = (EditText) findViewById(R.id.num17);
        num18 = (EditText) findViewById(R.id.num18);
        num19 = (EditText) findViewById(R.id.num19);
        num20 = (EditText) findViewById(R.id.num20);
        price1 = (EditText) findViewById(R.id.price1);
        price2 = (EditText) findViewById(R.id.price2);
        price3 = (EditText) findViewById(R.id.price3);
        price4 = (EditText) findViewById(R.id.price4);
        price5 = (EditText) findViewById(R.id.price5);
        price6 = (EditText) findViewById(R.id.price6);
        price7 = (EditText) findViewById(R.id.price7);
        price8 = (EditText) findViewById(R.id.price8);
        price9 = (EditText) findViewById(R.id.price9);
        price10 = (EditText) findViewById(R.id.price10);
        price11 = (EditText) findViewById(R.id.price11);
        price12 = (EditText) findViewById(R.id.price12);
        price13 = (EditText) findViewById(R.id.price13);
        price14 = (EditText) findViewById(R.id.price14);
        price15 = (EditText) findViewById(R.id.price15);
        price16 = (EditText) findViewById(R.id.price16);
        price17 = (EditText) findViewById(R.id.price17);
        price18 = (EditText) findViewById(R.id.price18);
        price19 = (EditText) findViewById(R.id.price19);
        price20 = (EditText) findViewById(R.id.price20);
    }

    private void initData() {
        orderDetial=gson.toJson(order, AddOrderInfo.class);
        Log.e("orderDetial===", orderDetial);
//        Map<String, String> params = new HashMap<>();
//        params.put("orderDetial", "");
//        Call<Common> call = RetrofitUtil.getInstance(API.URL).addOrder(params);
//        call.enqueue(new Callback<Common>() {
//            @Override
//            public void onResponse(Call<Common> call, Response<Common> response) {
//                if (response.isSuccessful()) {
//                    Common info = response.body();
//                    if (("10200").equals(info.getStatus())) {
//                        Toast.makeText(AddOrderActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(AddOrderActivity.this, "访问失败1！", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(AddOrderActivity.this, "访问失败2！", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Common> call, Throwable t) {
//                Toast.makeText(AddOrderActivity.this, "访问失败3！", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void back(View view) {
        finish();
    }

    //返回当前时间-day
    public static String getTimeByMinute(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                if (num < 20) {
                    num++;
                } else {
                    num = 20;
                }
                if (num == 2) {
                    shop2.setVisibility(View.VISIBLE);
                } else if (num == 3) {
                    shop3.setVisibility(View.VISIBLE);
                } else if (num == 4) {
                    shop4.setVisibility(View.VISIBLE);
                } else if (num == 5) {
                    shop5.setVisibility(View.VISIBLE);
                } else if (num == 6) {
                    shop6.setVisibility(View.VISIBLE);
                } else if (num == 7) {
                    shop7.setVisibility(View.VISIBLE);
                } else if (num == 8) {
                    shop8.setVisibility(View.VISIBLE);
                } else if (num == 9) {
                    shop9.setVisibility(View.VISIBLE);
                } else if (num == 10) {
                    shop10.setVisibility(View.VISIBLE);
                } else if (num == 11) {
                    shop11.setVisibility(View.VISIBLE);
                } else if (num == 12) {
                    shop12.setVisibility(View.VISIBLE);
                } else if (num == 13) {
                    shop13.setVisibility(View.VISIBLE);
                } else if (num == 14) {
                    shop14.setVisibility(View.VISIBLE);
                } else if (num == 15) {
                    shop15.setVisibility(View.VISIBLE);
                } else if (num == 16) {
                    shop16.setVisibility(View.VISIBLE);
                } else if (num == 17) {
                    shop17.setVisibility(View.VISIBLE);
                } else if (num == 18) {
                    shop18.setVisibility(View.VISIBLE);
                } else if (num == 19) {
                    shop19.setVisibility(View.VISIBLE);
                } else if (num == 20) {
                    shop20.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(AddOrderActivity.this, "无法添加更多了！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.lessen:
                if (num == 2) {
                    shop2.setVisibility(View.GONE);
                } else if (num == 3) {
                    shop3.setVisibility(View.GONE);
                } else if (num == 4) {
                    shop4.setVisibility(View.GONE);
                } else if (num == 5) {
                    shop5.setVisibility(View.GONE);
                } else if (num == 6) {
                    shop6.setVisibility(View.GONE);
                } else if (num == 7) {
                    shop7.setVisibility(View.GONE);
                } else if (num == 8) {
                    shop8.setVisibility(View.GONE);
                } else if (num == 9) {
                    shop9.setVisibility(View.GONE);
                } else if (num == 10) {
                    shop10.setVisibility(View.GONE);
                } else if (num == 11) {
                    shop11.setVisibility(View.GONE);
                } else if (num == 12) {
                    shop12.setVisibility(View.GONE);
                } else if (num == 13) {
                    shop13.setVisibility(View.GONE);
                } else if (num == 14) {
                    shop14.setVisibility(View.GONE);
                } else if (num == 15) {
                    shop15.setVisibility(View.GONE);
                } else if (num == 16) {
                    shop16.setVisibility(View.GONE);
                } else if (num == 17) {
                    shop17.setVisibility(View.GONE);
                } else if (num == 18) {
                    shop18.setVisibility(View.GONE);
                } else if (num == 19) {
                    shop19.setVisibility(View.GONE);
                } else if (num == 20) {
                    shop20.setVisibility(View.GONE);
                } else {
                    Toast.makeText(AddOrderActivity.this, "无法再减少了！", Toast.LENGTH_SHORT).show();
                }
                if (num > 1) {
                    num--;
                } else {
                    num = 1;
                }
                break;
            case R.id.submit:
                SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                order.setId(pref.getInt("id",0));
                order.setName(pref.getString("name",""));
                order.setDateTime(getTimeByMinute(0));
                order.setOuaddress(ouaddress.getText()+"");
                order.setOcost(ocost.getText()+"");
                if(shop1.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id1.getText()+""));
                    data.setNum(Integer.parseInt(num1.getText()+""));
                    data.setPrice(Double.parseDouble(price1.getText()+""));
                    datas.add(data);
                }
                if(shop2.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id2.getText()+""));
                    data.setNum(Integer.parseInt(num2.getText()+""));
                    data.setPrice(Double.parseDouble(price2.getText()+""));
                    datas.add(data);
                }
                if(shop3.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id3.getText()+""));
                    data.setNum(Integer.parseInt(num3.getText()+""));
                    data.setPrice(Double.parseDouble(price3.getText()+""));
                    datas.add(data);
                }
                if(shop4.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id4.getText()+""));
                    data.setNum(Integer.parseInt(num4.getText()+""));
                    data.setPrice(Double.parseDouble(price4.getText()+""));
                    datas.add(data);
                }
                if(shop5.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id5.getText()+""));
                    data.setNum(Integer.parseInt(num5.getText()+""));
                    data.setPrice(Double.parseDouble(price5.getText()+""));
                    datas.add(data);
                }
                if(shop6.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id6.getText()+""));
                    data.setNum(Integer.parseInt(num6.getText()+""));
                    data.setPrice(Double.parseDouble(price6.getText()+""));
                    datas.add(data);
                }
                if(shop7.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id7.getText()+""));
                    data.setNum(Integer.parseInt(num7.getText()+""));
                    data.setPrice(Double.parseDouble(price7.getText()+""));
                    datas.add(data);
                }
                if(shop8.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id8.getText()+""));
                    data.setNum(Integer.parseInt(num8.getText()+""));
                    data.setPrice(Double.parseDouble(price8.getText()+""));
                    datas.add(data);
                }
                if(shop9.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id9.getText()+""));
                    data.setNum(Integer.parseInt(num9.getText()+""));
                    data.setPrice(Double.parseDouble(price9.getText()+""));
                    datas.add(data);
                }
                if(shop10.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id10.getText()+""));
                    data.setNum(Integer.parseInt(num10.getText()+""));
                    data.setPrice(Double.parseDouble(price10.getText()+""));
                    datas.add(data);
                }
                if(shop11.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id11.getText()+""));
                    data.setNum(Integer.parseInt(num11.getText()+""));
                    data.setPrice(Double.parseDouble(price11.getText()+""));
                    datas.add(data);
                }
                if(shop12.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id12.getText()+""));
                    data.setNum(Integer.parseInt(num12.getText()+""));
                    data.setPrice(Double.parseDouble(price12.getText()+""));
                    datas.add(data);
                }
                if(shop13.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id13.getText()+""));
                    data.setNum(Integer.parseInt(num13.getText()+""));
                    data.setPrice(Double.parseDouble(price13.getText()+""));
                    datas.add(data);
                }
                if(shop14.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id14.getText()+""));
                    data.setNum(Integer.parseInt(num14.getText()+""));
                    data.setPrice(Double.parseDouble(price14.getText()+""));
                    datas.add(data);
                }
                if(shop15.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id15.getText()+""));
                    data.setNum(Integer.parseInt(num15.getText()+""));
                    data.setPrice(Double.parseDouble(price15.getText()+""));
                    datas.add(data);
                }
                if(shop16.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id16.getText()+""));
                    data.setNum(Integer.parseInt(num16.getText()+""));
                    data.setPrice(Double.parseDouble(price16.getText()+""));
                    datas.add(data);
                }
                if(shop17.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id17.getText()+""));
                    data.setNum(Integer.parseInt(num17.getText()+""));
                    data.setPrice(Double.parseDouble(price17.getText()+""));
                    datas.add(data);
                }
                if(shop18.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id18.getText()+""));
                    data.setNum(Integer.parseInt(num18.getText()+""));
                    data.setPrice(Double.parseDouble(price18.getText()+""));
                    datas.add(data);
                }
                if(shop19.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id19.getText()+""));
                    data.setNum(Integer.parseInt(num19.getText()+""));
                    data.setPrice(Double.parseDouble(price19.getText()+""));
                    datas.add(data);
                }
                if(shop20.getVisibility()==View.VISIBLE){
                    AddOrderInfo.DataBean data=new AddOrderInfo.DataBean();
                    data.setPid(Integer.parseInt(id20.getText()+""));
                    data.setNum(Integer.parseInt(num20.getText()+""));
                    data.setPrice(Double.parseDouble(price20.getText()+""));
                    datas.add(data);
                }
                order.setData(datas);
                initData();
                break;
            case R.id.keep:
                break;
        }
    }
}
