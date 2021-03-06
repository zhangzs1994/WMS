package com.ycsx.www.wms.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.activity.AchievementActivity;
import com.ycsx.www.wms.activity.ClassifyMangerActivity;
import com.ycsx.www.wms.activity.OpLogActivity;
import com.ycsx.www.wms.activity.OrderMangerActivity;
import com.ycsx.www.wms.activity.StockMangerActivity;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

/**
 * Created by ZZS_PC on 2017/6/6.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {
    private LinearLayout layout_order, layout_achievement, layout_stock,
            layout_classify, layout_subTreasury, layout_opLog;
    private View view;
    private int[] image = new int[]{R.drawable.major_image, R.drawable.major_image,
            R.drawable.major_image, R.drawable.detail};
    private Intent intent;
    private Banner banner;
    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage, container, false);
        initView();
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置图片集合
        banner.setImages(API.images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        banner = (Banner) view.findViewById(R.id.banner);
        layout_order = (LinearLayout) view.findViewById(R.id.layout_order);
        layout_achievement = (LinearLayout) view.findViewById(R.id.layout_achievement);
        layout_stock = (LinearLayout) view.findViewById(R.id.layout_stock);
        layout_classify = (LinearLayout) view.findViewById(R.id.layout_classify);
        layout_subTreasury = (LinearLayout) view.findViewById(R.id.layout_subTreasury);
        layout_opLog = (LinearLayout) view.findViewById(R.id.layout_opLog);
        layout_order.setOnClickListener(this);
        layout_achievement.setOnClickListener(this);
        layout_stock.setOnClickListener(this);
        layout_classify.setOnClickListener(this);
        layout_subTreasury.setOnClickListener(this);
        layout_opLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        pref = getActivity().getSharedPreferences("login", getActivity().MODE_PRIVATE);
        switch (v.getId()) {
            case R.id.layout_order:
                if (pref.getString("menuNode", "").indexOf("401") < 0 &&
                        pref.getString("menuNode", "").indexOf("402") < 0 &&
                        pref.getString("menuNode", "").indexOf("403") < 0) {
                    Toast.makeText(getActivity(), "您的权限不足，如有疑问，请联系管理员！", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(getActivity(), OrderMangerActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.layout_achievement:
                if (pref.getString("menuNode", "").indexOf("501") < 0 &&
                        pref.getString("menuNode", "").indexOf("502") < 0 &&
                        pref.getString("menuNode", "").indexOf("503") < 0) {
                    Toast.makeText(getActivity(), "您的权限不足，如有疑问，请联系管理员！", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(getActivity(), AchievementActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.layout_stock:
                if (pref.getString("menuNode", "").indexOf("301") < 0 &&
                        pref.getString("menuNode", "").indexOf("302") < 0 &&
                        pref.getString("menuNode", "").indexOf("303") < 0) {
                    Toast.makeText(getActivity(), "您的权限不足，如有疑问，请联系管理员！", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(getActivity(), StockMangerActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.layout_classify:
                if (pref.getString("menuNode", "").indexOf("201") < 0 &&
                        pref.getString("menuNode", "").indexOf("202") < 0) {
                    Toast.makeText(getActivity(), "您的权限不足，如有疑问，请联系管理员！", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(getActivity(), ClassifyMangerActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.layout_subTreasury:
                if (pref.getString("menuNode", "").indexOf("701") < 0) {
                    Toast.makeText(getActivity(), "您的权限不足，如有疑问，请联系管理员！", Toast.LENGTH_SHORT).show();
                } else {
//                  intent = new Intent(getActivity(), SubTreasuryActivity.class);
//                  startActivity(intent);
                    Toast.makeText(getActivity(), "该功能暂未开放，敬请期待！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.layout_opLog:
                if (pref.getString("menuNode", "").indexOf("601") < 0 &&
                        pref.getString("menuNode", "").indexOf("602") < 0) {
                    Toast.makeText(getActivity(), "您的权限不足，如有疑问，请联系管理员！", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(getActivity(), OpLogActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
