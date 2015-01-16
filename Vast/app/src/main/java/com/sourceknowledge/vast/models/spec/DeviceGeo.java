package com.sourceknowledge.vast.models.spec;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sourceknowledge.vast.models.vast.Linear;

/**
 * Created by omegatai on 14-12-12.
 */
public class DeviceGeo implements Parcelable {

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



    public DeviceGeo() {
    }

    public DeviceGeo(Parcel in) {
        readFromParcel(in);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {




        dest.writeInt(this.getLat() != null ? 1:0);

        if( this.getLat() != null )
            dest.writeDouble(this.getLat());


        dest.writeInt(this.getLon() != null ? 1:0);

        if( this.getLon() != null )
            dest.writeDouble(this.getLon());

    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setLat(in.readDouble());

        if( in.readInt() == 1 )
            setLon(in.readDouble());


    }

    public static Parcelable.Creator<DeviceGeo> CREATOR = new Parcelable.Creator<DeviceGeo>() {

        @Override
        public DeviceGeo createFromParcel(Parcel in) {
            return new DeviceGeo(in);
        }

        @Override
        public DeviceGeo[] newArray(int size) {
            return new DeviceGeo[size];
        }
    };





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
