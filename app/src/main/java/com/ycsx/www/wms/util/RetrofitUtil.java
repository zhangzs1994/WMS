package com.ycsx.www.wms.util;

import com.ycsx.www.wms.interfaces.IRetrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZZS_PC on 2017/5/23.
 * 网络请求工具类
 */
public class RetrofitUtil {
    private static Retrofit retrofit = null;
    private static IRetrofit iRetrofit;

    public static IRetrofit getInstance(String url) {
        if (retrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    iRetrofit = retrofit.create(IRetrofit.class);
                }
            }
        }
        return iRetrofit;
    }
}
