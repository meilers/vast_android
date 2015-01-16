package com.sourceknowledge.vast.models.spec;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sourceknowledge.vast.models.vast.Linear;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omegatai on 14-12-12.
 */
public class Device implements Parcelable {

    @SerializedName("dnt")
    @Expose
    private Integer mDnt;

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
        this.mDnt = nDnt;
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


    public Device() {
    }

    public Device(Parcel in) {
        readFromParcel(in);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {




        dest.writeInt(this.getDnt() != null ? 1:0);

        if( this.getDnt() != null )
            dest.writeInt(this.getDnt());


        dest.writeInt(this.getMake() != null ? 1:0);

        if( this.getMake() != null )
            dest.writeString(this.getMake());


        dest.writeInt(this.getModel() != null ? 1:0);

        if( this.getModel() != null )
            dest.writeString(this.getModel());


        dest.writeInt(this.getOs() != null ? 1:0);

        if( this.getOs() != null )
            dest.writeString(this.getOs());



        dest.writeInt(this.getOsv() != null ? 1:0);

        if( this.getOsv() != null )
            dest.writeString(this.getOsv());


        dest.writeInt(this.getCarrier() != null ? 1:0);

        if( this.getCarrier() != null )
            dest.writeString(this.getCarrier());



        dest.writeInt(this.getConnectionType() != null ? 1:0);

        if( this.getConnectionType() != null )
            dest.writeInt(this.getConnectionType());


        dest.writeInt(this.getIp() != null ? 1:0);

        if( this.getIp() != null )
            dest.writeString(this.getIp());


        dest.writeInt(this.getDeviceGeo() != null ? 1:0);

        if( this.getDeviceGeo() != null )
            dest.writeParcelable(this.getDeviceGeo(), flags);
    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setDnt(in.readInt());

        if( in.readInt() == 1 )
            setMake(in.readString());

        if( in.readInt() == 1 )
            setModel(in.readString());

        if( in.readInt() == 1 )
            setOs(in.readString());

        if( in.readInt() == 1 )
            setOsv(in.readString());

        if( in.readInt() == 1 )
            setCarrier(in.readString());

        if( in.readInt() == 1 )
            setConnectionType(in.readInt());

        if( in.readInt() == 1 )
            setIp(in.readString());


        if( in.readInt() == 1 )
            setDeviceGeo((DeviceGeo) in.readParcelable(Linear.class.getClassLoader()));


    }

    public static Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>() {

        @Override
        public Device createFromParcel(Parcel in) {
            return new Device(in);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];
        }
    };


    public Integer getDnt() {
        return mDnt;
    }

    public void setDnt(Integer dnt) {
        mDnt = dnt;
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
