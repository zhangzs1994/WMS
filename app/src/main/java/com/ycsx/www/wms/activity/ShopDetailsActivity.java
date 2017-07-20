package com.ycsx.www.wms.activity;

import android.support.v4.view.ViewPager;
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
import com.ycsx.www.wms.bean.ShopInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.GlideUtils;
import com.ycsx.www.wms.util.LoadingDialog;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopDetailsActivity extends BaseActivity {
    private TextView shop_name, shop_goodsNum, shop_barCode, shop_category, shop_instockTime,
            shop_stock, shop_price, shop_tradePrice, shop_measurementUnit, shop_spec, shop_manufactureTime,
            shop_qualityTime, shop_describ, shop_thePool;
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private View mView;
    private List<ImageView> mDataList;
    private int diatance;
    private String[] image = null;
    private int[] images = new int[]{R.drawable.major_image, R.drawable.major_image};
    private String shop_pictureUrl;
    private int currentItem;
    private LoadingDialog dialog;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_shop_details);
        initView();
        initData();
//        image = convertStrToArray(shop_pictureUrl);
//        Log.e("image", "" + image.length);
//        initImage();
//        initEvent();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mLinearLayout = (LinearLayout) findViewById(R.id.layout_points);
        mView = findViewById(R.id.view_redpoint);
        shop_name = (TextView) findViewById(R.id.shop_name);
        shop_goodsNum = (TextView) findViewById(R.id.shop_goodsNum);
        shop_barCode = (TextView) findViewById(R.id.shop_barCode);
        shop_category = (TextView) findViewById(R.id.shop_category);
        shop_instockTime = (TextView) findViewById(R.id.shop_instockTime);
        shop_stock = (TextView) findViewById(R.id.shop_stock);
        shop_price = (TextView) findViewById(R.id.shop_price);
        shop_tradePrice = (TextView) findViewById(R.id.shop_tradePrice);
        shop_spec = (TextView) findViewById(R.id.shop_spec);
        shop_measurementUnit = (TextView) findViewById(R.id.shop_measurementUnit);
        shop_manufactureTime = (TextView) findViewById(R.id.shop_manufactureTime);
        shop_qualityTime = (TextView) findViewById(R.id.shop_qualityTime);
        shop_describ = (TextView) findViewById(R.id.shop_describ);
        shop_thePool = (TextView) findViewById(R.id.shop_thePool);
        dialog = new LoadingDialog(this, R.style.CustomDialog);
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
//                currentItem = mViewPager.getCurrentItem();
//                Log.e("currentItem", "===" + currentItem);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        dialog.show();
        final Map<String, String> params = new HashMap<>();
        params.put("id", getIntent().getStringExtra("id"));
        Call<ShopInfo> call = RetrofitUtil.getInstance(API.URL).getGoods(params);
        call.enqueue(new Callback<ShopInfo>() {
            @Override
            public void onResponse(Call<ShopInfo> call, Response<ShopInfo> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    ShopInfo user = response.body();
                    if (("10200").equals(user.getStatus())) {
                        for (int i = 0; i < user.getData().size(); i++) {
                            shop_name.setText(user.getData().get(i).getName() + "");//商品名称
                            shop_goodsNum.setText(user.getData().get(i).getGoodsNum() + "");//商品编号
                            shop_barCode.setText(user.getData().get(i).getBarCode() + "");//商品条形码
                            shop_category.setText(user.getData().get(i).getCategory() + "");//商品类目
                            shop_instockTime.setText(user.getData().get(i).getInstockTime() + "");//入库时间
                            shop_stock.setText(user.getData().get(i).getNondefectiveNum() + "");//库存
                            shop_tradePrice.setText(user.getData().get(i).getTradePrice() + "");//批发价
                            shop_price.setText(user.getData().get(i).getRetailPrice() + "");//零售价
                            shop_spec.setText(user.getData().get(i).getSpec() + "");//规格
                            shop_measurementUnit.setText(user.getData().get(i).getMeasurementUnit() + "");//计量单位
                            shop_manufactureTime.setText(user.getData().get(i).getManufactureTime() + "");//生产日期
                            shop_qualityTime.setText(user.getData().get(i).getQualityTime() + "");//保质期
                            shop_describ.setText(user.getData().get(i).getDescrib() + "");//商品描述
                            shop_thePool.setText(user.getData().get(i).getThePool() + "");//所属分库
                            shop_pictureUrl = user.getData().get(i).getPictureUrl() + "";
                        }
                        image = convertStrToArray(shop_pictureUrl);
                        mDataList = new ArrayList<ImageView>();
                        for (int i = 0; i < image.length; i++) {
                            ImageView img = new ImageView(ShopDetailsActivity.this);
                            img.setScaleType(ImageView.ScaleType.FIT_XY);
                            //img.setBackgroundResource(images[i]);
                            GlideUtils.loadImage(ShopDetailsActivity.this, image[i], img);
                            mDataList.add(img);
                            //添加底部灰点
                            View v = new View(ShopDetailsActivity.this);
                            v.setBackgroundResource(R.drawable.gray_round);
                            //指定其大小
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(19, 19);
                            if (i != 0) {
                                params.leftMargin = 20;
                            }
                            v.setLayoutParams(params);
                            mLinearLayout.addView(v);
                        }
                        mViewPager.setAdapter(new ShopViewPagerAdapter(mDataList, ShopDetailsActivity.this));
                        mViewPager.setCurrentItem(0);
                        if (image.length > 1) {
                            initEvent();
                        }
                    } else if (("10365").equals(user.getStatus())) {
                        Toast.makeText(ShopDetailsActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ShopDetailsActivity.this, "获取商品详情失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ShopDetailsActivity.this, "获取商品详情失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShopInfo> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(ShopDetailsActivity.this, "获取商品详情失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void back(View view) {
        finish();
    }
}
