package com.sourceknowledge.vast.models.spec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by omegatai on 14-12-12.
 */
public class App {

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
