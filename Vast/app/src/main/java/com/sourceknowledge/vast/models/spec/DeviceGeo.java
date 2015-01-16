package com.sourceknowledge.vast.models.spec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by omegatai on 14-12-12.
 */
public class DeviceGeo {

    @SerializedName("lat")
    @Expose
    private Double mLat;

    @SerializedName("lon")
    @Expose
    private Double mLon;

    public DeviceGeo(Double lat, Double lon) {
        mLat = lat;
        mLon = lon;
    }

    public Double getLat() {
        return mLat;
    }

    public void setLat(Double lat) {
        mLat = lat;
    }

    public Double getLon() {
        return mLon;
    }

    public void setLon(Double lon) {
        mLon = lon;
    }
}
