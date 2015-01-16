package com.sourceknowledge.vast.models.spec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by omegatai on 14-12-12.
 */
public class Video {

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
