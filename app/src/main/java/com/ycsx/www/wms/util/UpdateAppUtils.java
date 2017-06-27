package com.ycsx.www.wms.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.service.UpdateAppService;

/**
 * 类描述：版本更新工具类
 * 这里使用DownLoadManager来做下载，在UpdateAppService类中
 * 在UpdateAppUtils检测是否要下载
 * 逻辑关系说明：
 * 1、请求数据  拿到后台的apk的版本号、版本名、更新内容、下载地址（可能还会包括是否强制更新的标志位）
 * 2、和本地的apk版本号对比时候需要更新
 * 3、这里用户可能会忽略此版本，这时候我要存储一个版本号，用户忽略的版本号
 * 4、在步骤2以后   如果需要更新，先判断步骤3里边存储的版本号，比较大小，
 * 如果用户忽略更新这个版本，那就不更新了；如果用户没有忽略过此版本，继续往下
 * 5、这时候给用户一个提示，展示内容是有新版本是否更新，更新内容
 * 5.1  这里可能涉及到一个强制更新，如果是强制更新的，那就要求，用户没有更新，就退出程序，不是强制更新的，可以忽略此版本
 * 6、启动下载服务
 * 7、下载完成之后的自动安装
 * 使用说明：
 * 在外部直接调用UpdateApp()方法
 */
public class UpdateAppUtils extends BaseActivity{
    public static int versionCode = 0;//当前版本号
    public static String versionName = "";//当前版本名称
    private static Context mContext;

    public UpdateAppUtils() {
    }

    /**
     * @param mContext       上下文
     * @param newVersionCode 服务器的版本号
     * @param newVersionName 服务器的版本名称
     * @param content        更新了的内容
     * @param downUrl        下载地址
     * @param IsUpdate       是否强制更新
     * @param IsToast        是否提示用户当前已经是最新版本
     */
    public static void UpdateApp(Context mContext, int newVersionCode, String newVersionName,
                                 String content, String downUrl, boolean IsUpdate, boolean IsToast) {
        UpdateAppUtils.mContext = mContext;

        //首先拿到当前的版本号和版本名
        try {
            UpdateAppUtils.mContext = mContext;
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            versionName = pi.versionName;
            versionCode = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (versionCode < newVersionCode) {//第2步骤
            showDownLoadDialog(newVersionName, newVersionCode, content, downUrl, IsUpdate);
        } else {
            if (IsToast) {
                Toast.makeText(mContext, "当前已是最新版本", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * 下载对话框
     *
     * @param versionname 要下载的版本名
     * @param versionCode 要下载的版本号
     * @param desc        更新说明
     * @param downloadurl 下载地址
     * @param isUpdate    是否强制更新
     */
    private static void showDownLoadDialog(String versionname, final int versionCode, String desc,
                                           final String downloadurl, final boolean isUpdate) {
        AlertDialog dialog = new AlertDialog.Builder(mContext).setCancelable(false)
                .setTitle("更新到 " + versionname).setMessage(desc)
                .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {//第6步骤，下载
                        Toast.makeText(mContext, "正在下载...", Toast.LENGTH_SHORT).show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                API.downLoadUrl = downloadurl;
                                //启动服务
                                Intent service = new Intent(mContext, UpdateAppService.class);
                                mContext.startService(service);
                            }
                        }).start();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //这里涉及到下载的强制更新，是不是强制更新   强制更新，点取消按钮，退出程序
                        if (isUpdate) {
                            Toast.makeText(mContext, "此版本需要更新，程序即将退出", Toast.LENGTH_SHORT).show();
                            mHandler.sendEmptyMessageDelayed(0, 1000 * 3);
                        } else {
                            dialog.dismiss();
                        }
                    }
                })
                .create();
        dialog.show();
    }


    static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            exitApp();
        }
    };

    /**
     * 退出程序
     */
    public static void exitApp() {
        removeAllActivity();
    }

}
