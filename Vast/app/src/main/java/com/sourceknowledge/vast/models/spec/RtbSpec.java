package com.sourceknowledge.vast.models.spec;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sourceknowledge.vast.models.vast.InLine;
import com.sourceknowledge.vast.models.vast.Tracking;
import com.sourceknowledge.vast.models.vast.Wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omegatai on 14-12-12.
 */
public class RtbSpec implements Parcelable {

    @SerializedName("imp")
    @Expose
    private List<Video> mImp;

    @SerializedName("app")
    @Expose
    private App mApp;


    @SerializedName("device")
    @Expose
    private Device mDevice;

    @SerializedName("user")
    @Expose
    private User mUser;

    public RtbSpec(List<Video> imp, App app, Device device, User user) {
        mImp = imp;
        mApp = app;
        mDevice = device;
        mUser = user;
    }

    public RtbSpec() {
    }

    public RtbSpec(Parcel in) {
        readFromParcel(in);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {



        dest.writeInt(this.getImp() != null ? 1:0);

        if( this.getImp() != null )
            dest.writeTypedList(this.getImp());



        dest.writeInt(this.getApp() != null ? 1:0);

        if( this.getApp() != null )
            dest.writeParcelable(this.getApp(), flags);


        dest.writeInt(this.getDevice() != null ? 1:0);

        if( this.getDevice() != null )
            dest.writeParcelable(this.getDevice(), flags);


        dest.writeInt(this.getUser() != null ? 1:0);

        if( this.getUser() != null )
            dest.writeParcelable(this.getUser(), flags);


    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
        {
            List<Video> items = new ArrayList<>();
            in.readTypedList(items, Video.CREATOR);
            setImp(items);
        }

        if( in.readInt() == 1 )
            setApp((App) in.readParcelable(App.class.getClassLoader()));

        if( in.readInt() == 1 )
            setDevice((Device) in.readParcelable(Device.class.getClassLoader()));

        if( in.readInt() == 1 )
            setUser((User) in.readParcelable(User.class.getClassLoader()));


    }

    public static Creator<RtbSpec> CREATOR = new Creator<RtbSpec>() {

        @Override
        public RtbSpec createFromParcel(Parcel in) {
            return new RtbSpec(in);
        }

        @Override
        public RtbSpec[] newArray(int size) {
            return new RtbSpec[size];
        }
    };



    public List<Video> getImp() {
        return mImp;
    }

    public void setImp(List<Video> imp) {
        mImp = imp;
    }

    public App getApp() {
        return mApp;
    }

    public void setApp(App app) {
        mApp = app;
    }

    public Device getDevice() {
        return mDevice;
    }

    public void setDevice(Device device) {
        mDevice = device;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
