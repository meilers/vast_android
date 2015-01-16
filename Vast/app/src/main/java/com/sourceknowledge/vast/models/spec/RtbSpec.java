package com.sourceknowledge.vast.models.spec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by omegatai on 14-12-12.
 */
public class RtbSpec {

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
