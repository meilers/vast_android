package com.sourceknowledge.vast.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by omegatai on 15-01-16.
 */
public class AppUtil {


    public static String getAppId() {
        return ""; // TODO: get ID when published on Play Store
    }


    public static String getAppName(Context context) {
        int stringId = context.getApplicationInfo().labelRes;
        return context.getString(stringId);
    }


    public static String getAppVersion(Context c) {
        PackageInfo pi;
        try {
            pi = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
            return pi.versionName;
        } catch (final PackageManager.NameNotFoundException e) {
            return "na";
        }
    }

    public static String getPackageName(Context c) {
        return c.getPackageName();
    }
}
