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
@Root(name = "Linear", strict=false)
public class Linear implements Parcelable {

    @Element(name="Duration", type=String.class, required=false)
    private String mDuration;


    @Element(name="TrackingEvents", type=TrackingEvents.class, required=false)
    private TrackingEvents mTrackingEvents;

    @Element(name="VideoClicks", type=VideoClicks.class, required=false)
    private VideoClicks mVideoClicks;

    @Element(name="MediaFiles", type=MediaFiles.class, required=false)
    private MediaFiles mMediaFiles;

    public Linear() {
    }

    public Linear(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {



        dest.writeInt(this.getDuration() != null ? 1:0);

        if( this.getDuration() != null )
            dest.writeString(this.getDuration());


        dest.writeInt(this.getTrackingEvents() != null ? 1:0);

        if( this.getTrackingEvents() != null )
            dest.writeParcelable(this.getTrackingEvents(), flags);


        dest.writeInt(this.getVideoClicks() != null ? 1:0);

        if( this.getVideoClicks() != null )
            dest.writeParcelable(this.getVideoClicks(), flags);


        dest.writeInt(this.getMediaFiles() != null ? 1:0);

        if( this.getMediaFiles() != null )
            dest.writeParcelable(this.getMediaFiles(), flags);
    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setDuration(in.readString());

        if( in.readInt() == 1 )
            setTrackingEvents((TrackingEvents) in.readParcelable(TrackingEvents.class.getClassLoader()));

        if( in.readInt() == 1 )
            setVideoClicks((VideoClicks) in.readParcelable(VideoClicks.class.getClassLoader()));

        if( in.readInt() == 1 )
            setMediaFiles((MediaFiles) in.readParcelable(MediaFiles.class.getClassLoader()));
    }

    public static Parcelable.Creator<Linear> CREATOR = new Parcelable.Creator<Linear>() {

        @Override
        public Linear createFromParcel(Parcel in) {
            return new Linear(in);
        }

        @Override
        public Linear[] newArray(int size) {
            return new Linear[size];
        }
    };


    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public TrackingEvents getTrackingEvents() {
        return mTrackingEvents;
    }

    public void setTrackingEvents(TrackingEvents trackingEvents) {
        mTrackingEvents = trackingEvents;
    }

    public VideoClicks getVideoClicks() {
        return mVideoClicks;
    }

    public void setVideoClicks(VideoClicks videoClicks) {
        mVideoClicks = videoClicks;
    }

    public MediaFiles getMediaFiles() {
        return mMediaFiles;
    }

    public void setMediaFiles(MediaFiles mediaFiles) {
        mMediaFiles = mediaFiles;
    }
}
