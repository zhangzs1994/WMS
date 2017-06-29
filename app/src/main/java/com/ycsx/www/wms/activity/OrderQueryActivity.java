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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderQueryActivity extends BaseActivity {
    private Spinner spinner;
    private String[] spinnerChild = {"全 部", "未审核", "审核未通过", "审核已通过"};
    private ArrayAdapter<String> arrayAdapter;
    private LinearLayout start_dataSelect, end_dataSelect;
    private TextView start_dataTime, end_dataTime;
    private Button query;
    private DatePickerDialog dialog;
    private EditText order_id;
    private String status;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_order_query);
        initView();
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerChild);
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ("未审核".equals(spinnerChild[position])) {
                    status = "0";
                } else if ("审核未通过".equals(spinnerChild[position])) {
                    status = "2";
                } else if ("审核已通过".equals(spinnerChild[position])) {
                    status = "1";
                } else {
                    status = "";
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
                    intent.putExtra("starttime", start_dataTime.getText() + "");
                    intent.putExtra("endtime", end_dataTime.getText() + "");
                    startActivity(intent);
                }
            }
        });
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        query = (Button) findViewById(R.id.query);
        spinner = (Spinner) findViewById(R.id.spinner);
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
