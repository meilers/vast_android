package com.sourceknowledge.vast.rest;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sourceknowledge.vast.VSTConstants;

import java.util.HashMap;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Michael on 2014-03-11.
 */
public enum VastApiClientManager {

    INSTANCE;

    private String mBaseUrl = VSTConstants.ADS_URL;

    private RestAdapter mRestAdapter;
    private Map<String, Object> mClients = new HashMap<String, Object>();


    private VastApiClientManager() {
    }


    @SuppressWarnings("unchecked")
    public <T> T getClient(Context context, Class<T> clazz) {
        if (mRestAdapter == null) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

            mRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(getBaseUrl())
                    .setConverter(new GsonConverter(gson))
                    .build();
        }
        T client = null;
        if ((client = (T) mClients.get(clazz.getCanonicalName())) != null) {
            return client;
        }
        client = mRestAdapter.create(clazz);
        mClients.put(clazz.getCanonicalName(), client);
        return client;
    }

    public void setRestAdapter(RestAdapter restAdapter) {
        mRestAdapter = restAdapter;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }


}
