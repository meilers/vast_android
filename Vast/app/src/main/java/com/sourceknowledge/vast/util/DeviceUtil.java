package com.sourceknowledge.vast.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

/**
 * Created by omegatai on 15-01-16.
 */
public class DeviceUtil {

    // If “0”, then do not track Is set to false, if “1”,
    // then do no track is set to true in browser.
    public static Integer getDnt()
    {
        return 0;
    }

    public static String getMake()
    {
        return Build.MANUFACTURER;
    }

    public static String getModel()
    {
        return Build.MODEL;
    }

    public static String getOs()
    {
        return "Android";
    }

    public static String getOsVersion()
    {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return sdkVersion + " (" + release + ")";
    }

    public static String getIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

        // Convert little-endian to big-endian if needed
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            ipAddressString = null;
        }

        return ipAddressString;
    }

    /*
    0 Unknown
    1 Ethernet
    2 Wifi
    3 Cellular data – Unknown Generation
    4 Cellular data – 2G
    5 Cellular data – 3G
    6 Cellular data – 4G
    */
    public static int getConnectionType(Context c)
    {
        NetworkUtil.NetworkState state =  NetworkUtil.getConnectivityStatus(c);

        if( state.equals(NetworkUtil.NetworkState.WIFI))
        {
            return 2;
        }
        else if( state.equals(NetworkUtil.NetworkState.MOBILE))
        {
            String networkClass = CarrierUtil.getNetworkClass(c);

            if( networkClass.equals("2G"))
                return 4;
            else if( networkClass.equals("3G"))
                return 5;
            else if( networkClass.equals("4G"))
                return 6;

            return 3;
        }
        else
            return 0;
    }


    // You may uncomment next line if using Android Annotations library, otherwise just be sure to run it in on the UI thread
    // @UiThread
    public static String getUserAgent(Context context) {
        if (Build.VERSION.SDK_INT >= 17) {
            return NewApiWrapper.getDefaultUserAgent(context);
        }

        try {
            Constructor<WebSettings> constructor = WebSettings.class.getDeclaredConstructor(Context.class, WebView.class);
            constructor.setAccessible(true);
            try {
                WebSettings settings = constructor.newInstance(context, null);
                return settings.getUserAgentString();
            } finally {
                constructor.setAccessible(false);
            }
        } catch (Exception e) {
            return new WebView(context).getSettings().getUserAgentString();
        }
    }

    @TargetApi(17)
    static class NewApiWrapper {
        static String getDefaultUserAgent(Context context) {
            return WebSettings.getDefaultUserAgent(context);
        }
    }


    public static String getName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}
