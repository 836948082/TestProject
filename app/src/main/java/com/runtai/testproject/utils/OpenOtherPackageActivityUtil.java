package com.runtai.testproject.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

/**
 * @作者：高炎鹏
 * @日期：2016/12/22时间10:11
 * @描述：打开第三方程序工具
 */
public class OpenOtherPackageActivityUtil {

    /**
     * 打开别的程序(默认Mainactivity)
     * @param packageName
     */
    public static void openApp(Context context, String packageName) {
        PackageInfo pi = null;
        try {
            pi = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(pi.packageName);

        List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
        ResolveInfo ri = apps.iterator().next();
        if (ri != null) {
            packageName = ri.activityInfo.packageName;
            String className = ri.activityInfo.name;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            context.startActivity(intent);
        }
    }

    /**
     * 打开别的程序(指定Activity)
     * @param packageName
     * 当报 java.lang.SecurityException: Permission Denial: starting Intent 类型错误时
     * (注意：指定打开的activity需要在Manifast加入action)
     *
     *  <activity android:name="ActivityX">
     *      <intent-filter>
     *          <action android:name="android.intent.action.MAIN" />
     *      </intent-filter>
     *  </activity>
     */
    public static void openApp(Context context, String packageName, String className){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        context.startActivity(intent);
    }

}
