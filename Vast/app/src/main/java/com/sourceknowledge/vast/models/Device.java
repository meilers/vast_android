package com.sourceknowledge.vast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by omegatai on 14-12-12.
 */
public class Device {

    @SerializedName("dnt")
    @Expose
    private Integer nDnt;

    @SerializedName("make")
    @Expose
    private String mMake;

    @SerializedName("model")
    @Expose
    private String mModel;


    @SerializedName("os")
    @Expose
    private String mOs;


    @SerializedName("osv")
    @Expose
    private String mOsv;


    @SerializedName("carrier")
    @Expose
    private String mCarrier;


    @SerializedName("connectiontype")
    @Expose
    private Integer mConnectionType;

    @SerializedName("ip")
    @Expose
    private String mIp;

    @SerializedName("ua")
    @Expose
    private String mUa;


    @SerializedName("geo")
    @Expose
    private DeviceGeo mDeviceGeo;

    public Device(Integer nDnt, String make, String model, String os, String osv, String carrier, Integer connectionType, String ip, String ua, DeviceGeo deviceGeo) {
        this.nDnt = nDnt;
        mMake = make;
        mModel = model;
        mOs = os;
        mOsv = osv;
        mCarrier = carrier;
        mConnectionType = connectionType;
        mIp = ip;
        mUa = ua;
        mDeviceGeo = deviceGeo;
    }

    public Integer getnDnt() {
        return nDnt;
    }

    public void setnDnt(Integer nDnt) {
        this.nDnt = nDnt;
    }

    public String getMake() {
        return mMake;
    }

    public void setMake(String make) {
        mMake = make;
    }

    public String getModel() {
        return mModel;
    }

    public void setModel(String model) {
        mModel = model;
    }

    public String getOs() {
        return mOs;
    }

    public void setOs(String os) {
        mOs = os;
    }

    public String getOsv() {
        return mOsv;
    }

    public void setOsv(String osv) {
        mOsv = osv;
    }

    public String getCarrier() {
        return mCarrier;
    }

    public void setCarrier(String carrier) {
        mCarrier = carrier;
    }

    public Integer getConnectionType() {
        return mConnectionType;
    }

    public void setConnectionType(Integer connectionType) {
        mConnectionType = connectionType;
    }

    public String getIp() {
        return mIp;
    }

    public void setIp(String ip) {
        mIp = ip;
    }

    public String getUa() {
        return mUa;
    }

    public void setUa(String ua) {
        mUa = ua;
    }

    public DeviceGeo getDeviceGeo() {
        return mDeviceGeo;
    }

    public void setDeviceGeo(DeviceGeo deviceGeo) {
        mDeviceGeo = deviceGeo;
    }
}
