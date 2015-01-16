package com.sourceknowledge.vast.models.spec;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by omegatai on 14-12-12.
 */
public class AppContent implements Parcelable {


    @SerializedName("id")
    @Expose
    private String mId;

    @SerializedName("title")
    @Expose
    private String mTitle;

    public AppContent(String id, String title) {
        mId = id;
        mTitle = title;
    }




    public AppContent() {
    }

    public AppContent(Parcel in) {
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


        dest.writeInt(this.getTitle() != null ? 1:0);

        if( this.getTitle() != null )
            dest.writeString(this.getTitle());




    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setId(in.readString());

        if( in.readInt() == 1 )
            setTitle(in.readString());


    }

    public static Parcelable.Creator<AppContent> CREATOR = new Parcelable.Creator<AppContent>() {

        @Override
        public AppContent createFromParcel(Parcel in) {
            return new AppContent(in);
        }

        @Override
        public AppContent[] newArray(int size) {
            return new AppContent[size];
        }
    };



    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
