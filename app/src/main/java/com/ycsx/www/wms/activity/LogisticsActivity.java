package com.ycsx.www.wms.activity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class LogisticsActivity extends BaseActivity {
    private WebView webView;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_logistics);
        webView= (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDefaultFontSize(16);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setDefaultTextEncodingName("UTF -8");
        webView.loadUrl("https://m.kuaidi100.com/index_all.html");
    }

    public void back(View view) {
        finish();
    }
}
