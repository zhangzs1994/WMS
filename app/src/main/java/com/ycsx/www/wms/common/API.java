package com.ycsx.www.wms.common;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/5/23.
 */
public class API {
    public static final String URL = "http://192.168.10.88:8080/YC_Service/";     //本地服务器地址
    //public static final String URL="http://222.187.226.86:8088/YC_Service/";     //服务器地址
    //public static final String URL1="http://192.168.10.3:8080/JobHunting/"; //测试地址
    //public static final String URL2="http://192.168.10.10:8080/JobHunting/"; //测试地址
    public static String authorizationCode = "YCWMS-20170522-XXX";  //验证码
    public static String downLoadUrl = null;   //APP下载地址
    public static String[] image = null;   //首页广告地址
    public static List images = null;   //首页广告地址
    public static int Version_no;//新版本号
    public static String versionName = null;//版本名称
    public static String versionInfo = null;//版本信息
    public static int forced;//是否强制更新，0：不强制；1：强制
}
