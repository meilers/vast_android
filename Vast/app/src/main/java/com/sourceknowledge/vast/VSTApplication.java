package com.sourceknowledge.vast;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;



public class VSTApplication extends Application {

	private static Context sContext;
	private static Handler sHandler;


	@Override
	public void onCreate() {
		super.onCreate();


		sHandler = new Handler();
		sContext = getApplicationContext();

	}
	
	

	public static void runOnUiThread(Runnable runnable) {
		sHandler.post(runnable);
	}

	public static final Context getContext() {
		return sContext;
	}

	public static final ContentResolver getResolver() {  
		return sContext.getContentResolver();
	}

    public static SharedPreferences getSharedPreferences() {
		SharedPreferences prefs = sContext.getSharedPreferences(sContext.getPackageName(), Context.MODE_PRIVATE);
		return prefs;
	}

	public static String getResourceString(int resource) {
		return sContext.getResources().getString(resource);
	}

}
