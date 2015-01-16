package com.sourceknowledge.vast.models.vast;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omegatai on 15-01-14.
 */
@Root(name = "TrackingEvents", strict=false)
public class TrackingEvents implements Parcelable {

    @ElementList(entry="Tracking", type=Tracking.class, inline=true, data=true, required=false)
    List<Tracking> mTrackings;

    public TrackingEvents() {
    }

    public TrackingEvents(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.getTrackings() != null ? 1:0);

        if( this.getTrackings() != null )
            dest.writeTypedList(this.getTrackings());
    }


    public void readFromParcel(Parcel in) {


        if( in.readInt() == 1 )
        {
            List<Tracking> items = new ArrayList<>();
            in.readTypedList(items, Tracking.CREATOR);
            setTrackings(items);
        }
    }

    public static Parcelable.Creator<TrackingEvents> CREATOR = new Parcelable.Creator<TrackingEvents>() {

        @Override
        public TrackingEvents createFromParcel(Parcel in) {
            return new TrackingEvents(in);
        }

        @Override
        public TrackingEvents[] newArray(int size) {
            return new TrackingEvents[size];
        }
    };


    public List<Tracking> getTrackings() {
        return mTrackings;
    }

    public void setTrackings(List<Tracking> trackings) {
        mTrackings = trackings;
    }
}
