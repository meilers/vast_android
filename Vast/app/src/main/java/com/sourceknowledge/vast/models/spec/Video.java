package com.sourceknowledge.vast.models.spec;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omegatai on 14-12-12.
 */
public class Video implements Parcelable {

    @SerializedName("h")
    @Expose
    private Integer mHeight;

    @SerializedName("w")
    @Expose
    private Integer mWidth;

    @SerializedName("mimes")
    @Expose
    private List<String> mMimes;

    public Video(Integer height, Integer width, List<String> mimes) {
        mHeight = height;
        mWidth = width;
        mMimes = mimes;
    }



    public Video() {
    }

    public Video(Parcel in) {
        readFromParcel(in);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {



        dest.writeInt(this.getHeight() != null ? 1:0);

        if( this.getHeight() != null )
            dest.writeInt(this.getHeight());


        dest.writeInt(this.getWidth() != null ? 1:0);

        if( this.getWidth() != null )
            dest.writeInt(this.getWidth());


        dest.writeInt(this.getMimes() != null ? 1:0);

        if( this.getMimes() != null )
            dest.writeStringList(this.getMimes());


    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setHeight(in.readInt());

        if( in.readInt() == 1 )
            setWidth(in.readInt());


        if( in.readInt() == 1 )
        {
            List<String> items = new ArrayList<>();
            in.readStringList(items);
            setMimes(items);
        }


    }

    public static Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {

        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };



    public Integer getHeight() {
        return mHeight;
    }

    public void setHeight(Integer height) {
        mHeight = height;
    }

    public Integer getWidth() {
        return mWidth;
    }

    public void setWidth(Integer width) {
        mWidth = width;
    }

    public List<String> getMimes() {
        return mMimes;
    }

    public void setMimes(List<String> mimes) {
        mMimes = mimes;
    }
}
