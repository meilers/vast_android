package com.sourceknowledge.vast.models.vast;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omegatai on 15-01-14.
 */
@Root(name = "Creatives", strict=false)
public class Creatives implements Parcelable {

    @Element(name="Creative", type=Creative.class, required=false)
    private Creative mCreative;

    public Creatives() {
    }

    public Creatives(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.getCreative() != null ? 1:0);

        if( this.getCreative() != null )
            dest.writeParcelable(this.getCreative(), flags);
    }


    public void readFromParcel(Parcel in) {


        if( in.readInt() == 1 )
            setCreative((Creative) in.readParcelable(Creative.class.getClassLoader()));
    }

    public static Parcelable.Creator<Creatives> CREATOR = new Parcelable.Creator<Creatives>() {

        @Override
        public Creatives createFromParcel(Parcel in) {
            return new Creatives(in);
        }

        @Override
        public Creatives[] newArray(int size) {
            return new Creatives[size];
        }
    };

    public Creative getCreative() {
        return mCreative;
    }

    public void setCreative(Creative creative) {
        mCreative = creative;
    }
}
