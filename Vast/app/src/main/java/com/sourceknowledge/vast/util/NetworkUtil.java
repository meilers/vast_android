package com.sourceknowledge.vast.util;

/**
 * Created by omegatai on 14-12-28.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetworkUtil {

    public enum NetworkState {
        NOT_CONNECTED, WIFI, MOBILE
    }


    public static NetworkState getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo anInfo = cm.getActiveNetworkInfo();
        if (anInfo != null) {
            switch (anInfo.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    return NetworkState.WIFI;
                case ConnectivityManager.TYPE_MOBILE:
                    return NetworkState.MOBILE;
            }
        }
        return NetworkState.NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        NetworkState state = NetworkUtil.getConnectivityStatus(context);
        String status = null;
        if (state == NetworkState.WIFI) {
            status = "Wifi enabled";
        } else if (state == NetworkState.MOBILE) {
            status = "Mobile data enabled";
        } else if (state == NetworkState.NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }

    public static boolean isCurrentlyConnected(Context context) {
        return getConnectivityStatus(context) != NetworkState.NOT_CONNECTED;
    }


}
