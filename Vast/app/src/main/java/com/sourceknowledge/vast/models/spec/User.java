package com.sourceknowledge.vast.models.spec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by omegatai on 14-12-12.
 */
public class User {

    @SerializedName("id")
    @Expose
    private String mId;

    public User(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
