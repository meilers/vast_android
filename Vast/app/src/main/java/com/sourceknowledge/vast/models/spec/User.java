package com.sourceknowledge.vast.models.spec;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by omegatai on 14-12-12.
 */
public class User implements Parcelable {

    @SerializedName("id")
    @Expose
    private String mId;

    public User(String id) {
        mId = id;
    }



    public User() {
    }

    public User(Parcel in) {
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


    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setId(in.readString());



    }

    public static Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };



    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
