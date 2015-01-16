package com.sourceknowledge.vast.models.vast;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omegatai on 15-01-14.
 */

@Root(name = "VideoClicks", strict=false)
public class VideoClicks implements Parcelable {

    @Element(name="ClickThrough", type=String.class, required=false)
    private String mClickThrough;

    @ElementList(entry="ClickTracking", type=String.class, inline=true, data=true, required = false)
    List<String> mClickTrackings;


    public VideoClicks() {
    }

    public VideoClicks(Parcel in) {
        readFromParcel(in);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.getClickThrough() != null ? 1:0);

        if( this.getClickThrough() != null )
            dest.writeString(this.getClickThrough());


        dest.writeInt(this.getClickTrackings() != null ? 1:0);

        if( this.getClickTrackings() != null )
            dest.writeStringList(this.getClickTrackings());
    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setClickThrough(in.readString());

        if( in.readInt() == 1 )
        {
            List<String> items = new ArrayList<>();
            in.readStringList(items);
            setClickTrackings(items);
        }
    }

    public static Parcelable.Creator<VideoClicks> CREATOR = new Parcelable.Creator<VideoClicks>() {

        @Override
        public VideoClicks createFromParcel(Parcel in) {
            return new VideoClicks(in);
        }

        @Override
        public VideoClicks[] newArray(int size) {
            return new VideoClicks[size];
        }
    };

    public String getClickThrough() {
        return mClickThrough;
    }

    public void setClickThrough(String clickThrough) {
        mClickThrough = clickThrough;
    }

    public List<String> getClickTrackings() {
        return mClickTrackings;
    }

    public void setClickTrackings(List<String> clickTrackings) {
        mClickTrackings = clickTrackings;
    }
}
