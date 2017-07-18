package com.ycsx.www.wms.activity;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.ShopViewPagerAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.OrderShop;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.GlideUtils;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyShopDetaisActivity extends BaseActivity {
    private TextView shop_name, shop_num, shop_price, shop_allPrice, shop_describ;
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private View mView;
    private List<ImageView> mDataList;
    private int diatance;
    private String[] image = null;
    private String shop_pictureUrl;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_my_shop_detais);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mLinearLayout = (LinearLayout) findViewById(R.id.layout_points);
        mView = findViewById(R.id.view_redpoint);
        shop_name = (TextView) findViewById(R.id.shop_name);
        shop_num = (TextView) findViewById(R.id.shop_num);
        shop_price = (TextView) findViewById(R.id.shop_price);
        shop_allPrice = (TextView) findViewById(R.id.shop_allPrice);
        shop_describ = (TextView) findViewById(R.id.shop_describe);
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

    private void initEvent() {
        /**
         * 当底部红色小圆点加载完成时测出两个小灰点的距离，便于计算后面小红点动态移动的距离
         */
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                diatance = mLinearLayout.getChildAt(1).getLeft() - mLinearLayout.getChildAt(0).getLeft();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //测出页面滚动时小红点移动的距离，并通过setLayoutParams(params)不断更新其位置
                position = position % mDataList.size();
                float leftMargin = diatance * (position + positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mView.getLayoutParams();
                params.leftMargin = Math.round(leftMargin);
                mView.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        Map<String, String> params = new HashMap<>();
        Log.e("uid", "===="+getIntent().getStringExtra("uid"));
        Log.e("pid", "===="+getIntent().getStringExtra("pid"));
        params.put("uid", getIntent().getStringExtra("uid"));
        params.put("pid", getIntent().getStringExtra("pid"));
        Call<OrderShop> call = RetrofitUtil.getInstance(API.URL).selectOneMyorde(params);
        call.enqueue(new Callback<OrderShop>() {
            @Override
            public void onResponse(Call<OrderShop> call, Response<OrderShop> response) {
                if (response.isSuccessful()) {
                    OrderShop info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            shop_name.setText(info.getData().get(i).getPname() + "");//商品名称
                            shop_num.setText(info.getData().get(i).getNum() + "");//商品数量
                            shop_price.setText(info.getData().get(i).getPrice() + "");//商品单价
                            shop_allPrice.setText(info.getData().get(i).getIocost() + "");//商品总价
                            shop_describ.setText(info.getData().get(i).getDescribee() + "");//商品描述
                            shop_pictureUrl = info.getData().get(i).getPictureUrl() + "";
                        }
                        Log.e("shop_pictureUrl", "11==" + shop_pictureUrl);
                        image = convertStrToArray(shop_pictureUrl);
                        mDataList = new ArrayList<ImageView>();
                        for (int i = 0; i < image.length; i++) {
                            ImageView img = new ImageView(MyShopDetaisActivity.this);
                            img.setScaleType(ImageView.ScaleType.FIT_XY);
                            //img.setBackgroundResource(images[i]);
                            GlideUtils.loadImage(MyShopDetaisActivity.this, image[i], img);
                            mDataList.add(img);
                            //添加底部灰点
                            View v = new View(MyShopDetaisActivity.this);
                            v.setBackgroundResource(R.drawable.gray_round);
                            //指定其大小
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(19, 19);
                            if (i != 0) {
                                params.leftMargin = 20;
                            }
                            v.setLayoutParams(params);
                            mLinearLayout.addView(v);
                        }
                        mViewPager.setAdapter(new ShopViewPagerAdapter(mDataList, MyShopDetaisActivity.this));
                        mViewPager.setCurrentItem(0);
                        if (image.length > 1) {
                            initEvent();
                        }
                    } else if (("10365").equals(info.getStatus())) {
                        Toast.makeText(MyShopDetaisActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MyShopDetaisActivity.this, "获取商品详情失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("code", "===="+response.code() );
                    Toast.makeText(MyShopDetaisActivity.this, "获取商品详情失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderShop> call, Throwable t) {
                Toast.makeText(MyShopDetaisActivity.this, "获取商品详情失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void back(View view) {
        finish();
    }
}
