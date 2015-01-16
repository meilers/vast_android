package com.sourceknowledge.vast.models.vast;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omegatai on 15-01-14.
 */
public class Tracking implements Parcelable {

    @Attribute(name = "event", required=false)
    private String mEvent;

    @Text(required=false)
    private String mValue;

    public Tracking() {
    }

    public Tracking(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeInt(this.getEvent() != null ? 1:0);

        if( this.getEvent() != null )
            dest.writeString(this.getEvent());


        dest.writeInt(this.getValue() != null ? 1:0);

        if( this.getValue() != null )
            dest.writeString(this.getValue());

    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setEvent(in.readString());

        if( in.readInt() == 1 )
            setValue(in.readString());
    }

    public static Creator<Tracking> CREATOR = new Creator<Tracking>() {

        @Override
        public Tracking createFromParcel(Parcel in) {
            return new Tracking(in);
        }

        @Override
        public Tracking[] newArray(int size) {
            return new Tracking[size];
        }
    };





    public String getEvent() {
        return mEvent;
    }

    public void setEvent(String event) {
        mEvent = event;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }
}
