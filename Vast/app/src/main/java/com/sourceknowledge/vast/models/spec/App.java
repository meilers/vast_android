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
public class App implements Parcelable {

    @SerializedName("id")
    @Expose
    private String mId;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("bundle")
    @Expose
    private String mBundle;

    @SerializedName("content")
    @Expose
    private AppContent mContent;


    public App(String id, String name, String bundle, AppContent content) {
        mId = id;
        mName = name;
        mBundle = bundle;
        mContent = content;
    }


    public App() {
    }

    public App(Parcel in) {
        readFromParcel(in);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {



        dest.writeInt(this.getId() != null ? 1:0);

        if( this.getId() != null )
            dest.writeString(this.getId());


        dest.writeInt(this.getName() != null ? 1:0);

        if( this.getName() != null )
            dest.writeString(this.getName());


        dest.writeInt(this.getBundle() != null ? 1:0);

        if( this.getBundle() != null )
            dest.writeString(this.getBundle());




        dest.writeInt(this.getContent() != null ? 1:0);

        if( this.getContent() != null )
            dest.writeParcelable(this.getContent(), flags);


    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setId(in.readString());

        if( in.readInt() == 1 )
            setName(in.readString());

        if( in.readInt() == 1 )
            setBundle(in.readString());


        if( in.readInt() == 1 )
            setContent((AppContent) in.readParcelable(AppContent.class.getClassLoader()));


    }

    public static Creator<App> CREATOR = new Creator<App>() {

        @Override
        public App createFromParcel(Parcel in) {
            return new App(in);
        }

        @Override
        public App[] newArray(int size) {
            return new App[size];
        }
    };




    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getBundle() {
        return mBundle;
    }

    public AppContent getContent() {
        return mContent;
    }

    public void setId(String id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setBundle(String bundle) {
        mBundle = bundle;
    }

    public void setContent(AppContent content) {
        mContent = content;
    }
}
