package com.ycsx.www.wms.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.CategoryInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderQueryActivity extends BaseActivity {
    private Spinner spinner2,spinner1;
    private List<String> spinnerValue2 = new ArrayList<>();
    private List<String> spinnerCode2 = new ArrayList<>();
    private List<String> spinnerValue1 = new ArrayList<>();
    private List<String> spinnerCode1 = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter1;
    private ArrayAdapter<String> arrayAdapter2;
    private LinearLayout start_dataSelect, end_dataSelect;
    private TextView start_dataTime, end_dataTime;
    private Button query;
    private DatePickerDialog dialog;
    private EditText order_id;
    private String status;
    private String classify;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_order_query);
        initView();
        queryDropdown1();
        queryDropdown2();
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerValue1.get(position).toString().equals("全部")){
                    classify = "";
                }else{
                    for (int i = 0; i < spinnerValue1.size(); i++) {
                        if (spinnerValue1.get(i).toString().equals(spinnerValue1.get(position).toString())) {
                            classify = spinnerCode1.get(i-1).toString();
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                status = "";
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerValue2.get(position).toString().equals("全部")){
                    status = "";
                }else{
                    for (int i = 0; i < spinnerValue2.size(); i++) {
                        if (spinnerValue2.get(i).toString().equals(spinnerValue2.get(position).toString())) {
                            status = spinnerCode2.get(i-1).toString();
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                status = "";
            }
        });
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start_dataTime.getText().equals("") && !end_dataTime.getText().equals("")) {
                    Toast.makeText(OrderQueryActivity.this, "请选择开始日期！", Toast.LENGTH_SHORT).show();
                } else if (!start_dataTime.getText().equals("") && end_dataTime.getText().equals("")) {
                    Toast.makeText(OrderQueryActivity.this, "请选择结束日期！", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(OrderQueryActivity.this, OrderListActivity.class);
                    intent.putExtra("title", "订单列表");
                    intent.putExtra("oid", order_id.getText() + "");
                    intent.putExtra("ostatus", status + "");
                    intent.putExtra("classify", classify + "");
                    intent.putExtra("starttime", start_dataTime.getText() + "");
                    intent.putExtra("endtime", end_dataTime.getText() + "");
                    startActivity(intent);
                }
            }
        });
    }

    private void queryDropdown1() {
        spinnerValue1.add("全部");
        Map<String, String> params = new HashMap<>();
        params.put("colName", "order1");
        Call<CategoryInfo> call = RetrofitUtil.getInstance(API.URL).queryDropdown(params);
        call.enqueue(new Callback<CategoryInfo>() {
            @Override
            public void onResponse(Call<CategoryInfo> call, Response<CategoryInfo> response) {
                if (response.isSuccessful()) {
                    CategoryInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            spinnerValue1.add(info.getData().get(i).getValue() + "");
                            spinnerCode1.add(info.getData().get(i).getCode() + "");
                        }
                        arrayAdapter1 = new ArrayAdapter<String>(OrderQueryActivity.this, R.layout.spinner_item, spinnerValue1);
                        arrayAdapter1.setDropDownViewResource(R.layout.dropdown_stytle);
                        spinner1.setAdapter(arrayAdapter1);
                    } else {
                        Toast.makeText(OrderQueryActivity.this, "获取订单类型失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OrderQueryActivity.this, "获取订单类型失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryInfo> call, Throwable t) {
                Toast.makeText(OrderQueryActivity.this, "获取订单类型失败3！", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void queryDropdown2() {
        spinnerValue2.add("全部");
        Map<String, String> params = new HashMap<>();
        params.put("colName", "order2");
        Call<CategoryInfo> call = RetrofitUtil.getInstance(API.URL).queryDropdown(params);
        call.enqueue(new Callback<CategoryInfo>() {
            @Override
            public void onResponse(Call<CategoryInfo> call, Response<CategoryInfo> response) {
                if (response.isSuccessful()) {
                    CategoryInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            spinnerValue2.add(info.getData().get(i).getValue() + "");
                            spinnerCode2.add(info.getData().get(i).getCode() + "");
                        }
                        arrayAdapter2 = new ArrayAdapter<String>(OrderQueryActivity.this, R.layout.spinner_item, spinnerValue2);
                        arrayAdapter2.setDropDownViewResource(R.layout.dropdown_stytle);
                        spinner2.setAdapter(arrayAdapter2);
                    } else {
                        Toast.makeText(OrderQueryActivity.this, "获取订单状态失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OrderQueryActivity.this, "获取订单状态失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryInfo> call, Throwable t) {
                Toast.makeText(OrderQueryActivity.this, "获取订单状态失败3！", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        query = (Button) findViewById(R.id.query);
        spinner2 = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        order_id = (EditText) findViewById(R.id.order_id);
        start_dataSelect = (LinearLayout) findViewById(R.id.start_dataSelect);
        start_dataTime = (TextView) findViewById(R.id.start_dataTime);
        end_dataSelect = (LinearLayout) findViewById(R.id.end_dataSelect);
        end_dataTime = (TextView) findViewById(R.id.end_dataTime);
        final Calendar c = Calendar.getInstance();
        start_dataSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new DatePickerDialog(OrderQueryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        start_dataTime.setText(DateFormat.format("yyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                if (!end_dataTime.getText().toString().equals("")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = sdf.parse(end_dataTime.getText().toString());
                        dialog.getDatePicker().setMaxDate(date.getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    dialog.getDatePicker().setMaxDate((new Date()).getTime());
                }
                dialog.show();
            }
        });
        end_dataSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new DatePickerDialog(OrderQueryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        end_dataTime.setText(DateFormat.format("yyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                if (!start_dataTime.getText().toString().equals("")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = sdf.parse(start_dataTime.getText().toString());
                        dialog.getDatePicker().setMinDate(date.getTime());
                        dialog.getDatePicker().setMaxDate((new Date()).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    dialog.getDatePicker().setMaxDate((new Date()).getTime());
                }
                dialog.show();
            }
        });
    }
}
