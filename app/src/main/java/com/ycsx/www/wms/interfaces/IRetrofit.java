package com.ycsx.www.wms.interfaces;

import com.ycsx.www.wms.bean.AchiInfo;
import com.ycsx.www.wms.bean.CategoryInfo;
import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.bean.FlagCountInfo;
import com.ycsx.www.wms.bean.FlagInfo;
import com.ycsx.www.wms.bean.LoginInfo;
import com.ycsx.www.wms.bean.OrderDetailsInfo;
import com.ycsx.www.wms.bean.OrderInfo;
import com.ycsx.www.wms.bean.OrderShop;
import com.ycsx.www.wms.bean.OutStockInfo;
import com.ycsx.www.wms.bean.PermissionInfo;
import com.ycsx.www.wms.bean.RolesInfo;
import com.ycsx.www.wms.bean.ShopInfo;
import com.ycsx.www.wms.bean.SuperiorInfo;
import com.ycsx.www.wms.bean.Test;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ZZS_PC on 2017/5/23.
 */
public interface IRetrofit {
    @GET("wms/getUserInfo;username={username};userpwd={userpwd}")
    Call<LoginInfo> getUserInfo(@Path("username") String username, @Path("userpwd") String userpwd);

    @FormUrlEncoded
    @POST("wms1/getOrders")
    Call<ResponseBody> postTest(@FieldMap Map<String, String> params);//测试

    @GET("{name}")
    Call<List<Test>> getTest(@Path("name") String name);//测试

    @FormUrlEncoded
    @POST("wms/getPermissionInfo")
    Call<PermissionInfo> getPermissionInfo(@FieldMap Map<String, String> params);//获取权限

    @FormUrlEncoded
    @POST("wms/getUserInfo")
    Call<LoginInfo> getUserInfo(@FieldMap Map<String, String> params);//根据账号密码获取用户信息

    @FormUrlEncoded
    @POST("wms/modifyPwd")
    Call<Common> setUpdatePwd(@FieldMap Map<String, Object> params);//修改密码

    @FormUrlEncoded
    @POST("wms/updateToUsers")
    Call<Common> setPersonInfo(@FieldMap Map<String, Object> params);//设置个人信息

    @FormUrlEncoded
    @POST("wms/queryPull")
    Call<FlagInfo> getFlag(@FieldMap Map<String, String> params);//设置级别信息

    @FormUrlEncoded
    @POST("wms/queryRoles")
    Call<RolesInfo> getRoles(@FieldMap Map<String, String> params);//获取角色分组信息

    @FormUrlEncoded
    @POST("wms/queryFlagCount")
    Call<FlagCountInfo> getFlagCount(@FieldMap Map<String, String> params);//获取角色分组信息

    @FormUrlEncoded
    @POST("wms/getGoodsList")
    Call<ShopInfo> getGoodsList(@FieldMap Map<String, String> params);//获取全部商品信息

    @FormUrlEncoded
    @POST("wms/getLikeGoods")
    Call<ShopInfo> getLikeGoods(@FieldMap Map<String, String> params);//根据商品名获取商品信息

    @FormUrlEncoded
    @POST("wms/getGoodsListLikeValue")
    Call<ShopInfo> getGoodsListLikeValue(@FieldMap Map<String, String> params);//根据商品名、编号、条码获取商品信息

    @FormUrlEncoded
    @POST("wms/getGoodsByCategory")
    Call<ShopInfo> getGoodsByCategory(@FieldMap Map<String, String> params);//根据商品类别获取商品信息

    @FormUrlEncoded
    @POST("wms/queryDropdown")
    Call<CategoryInfo> queryDropdown(@FieldMap Map<String, String> params);//根据商品分类信息

    @FormUrlEncoded
    @POST("wms/getGoodsByGoodsStatus")
    Call<ShopInfo> getGoodsByGoodsStatus(@FieldMap Map<String, String> params);//获取入库记录信息

    @FormUrlEncoded
    @POST("wms/getinvenBypage")
    Call<OutStockInfo> getinvenBypage(@FieldMap Map<String, String> params);//获取出库记录信息

    @FormUrlEncoded
    @POST("wms/selectOrderh1")
    Call<OrderInfo> selectOrderh1(@FieldMap Map<String, String> params);//获取订单信息

    @FormUrlEncoded
    @POST("wms/getOrderinven")
    Call<OrderDetailsInfo> getOrderinven(@FieldMap Map<String, String> params);//获取订单详细信息

    @FormUrlEncoded
    @POST("wms/getDetial")
    Call<AchiInfo> getDetial(@FieldMap Map<String, String> params);//获取业绩信息

    @FormUrlEncoded
    @POST("wms/querySuperior")
    Call<SuperiorInfo> querySuperior(@FieldMap Map<String, String> params);//查询下级信息

    @FormUrlEncoded
    @POST("wms/addOrder")
    Call<Common> addOrder(@FieldMap Map<String, String> params);//提交订单

    @FormUrlEncoded
    @POST("wms/addMyorder")
    Call<Common> addMyorder(@FieldMap Map<String, String> params);//添加订单商品

    @FormUrlEncoded
    @POST("wms/selectMyorde")
    Call<OrderShop> selectMyorde(@FieldMap Map<String, String> params);//查询订单商品

    @FormUrlEncoded
    @POST("wms/deleteMyorde")
    Call<Common> deleteMyorde(@FieldMap Map<String, String> params);//删除订单商品
}
