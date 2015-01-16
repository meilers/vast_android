package com.sourceknowledge.vast.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by omegatai on 15-01-15.
 */
public class Trailer implements Parcelable {

    @SerializedName("thumb")
    @Expose
    private String mThumb;

    @SerializedName("url")
    @Expose
    private String mUrl;


    @SerializedName("title")
    @Expose
    private String mTitle;

    public Trailer(String thumb, String url, String title) {
        mThumb = thumb;
        mUrl = url;
        mTitle = title;
    }



    public Trailer(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeInt(this.getThumb() != null ? 1:0);

        if( this.getThumb() != null )
            dest.writeString(this.getThumb());


        dest.writeInt(this.getUrl() != null ? 1:0);

        if( this.getUrl() != null )
            dest.writeString(this.getUrl());


        dest.writeInt(this.getTitle() != null ? 1:0);

        if( this.getTitle() != null )
            dest.writeString(this.getTitle());

    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setThumb(in.readString());

        if( in.readInt() == 1 )
            setUrl(in.readString());

        if( in.readInt() == 1 )
            setTitle(in.readString());
    }

    public static Creator<Trailer> CREATOR = new Creator<Trailer>() {

        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };






    public String getThumb() {
        return mThumb;
    }

    public void setThumb(String thumb) {
        mThumb = thumb;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
