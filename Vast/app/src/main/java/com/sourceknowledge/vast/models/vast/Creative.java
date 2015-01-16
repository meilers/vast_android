package com.sourceknowledge.vast.models.vast;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by omegatai on 15-01-14.
 */
@Root(name = "Creative", strict=false)
public class Creative implements Parcelable {

    @Element(name="Linear", type=Linear.class, required=false)
    private Linear mLinear;


    public Creative() {
    }

    public Creative(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.getLinear() != null ? 1:0);

        if( this.getLinear() != null )
            dest.writeParcelable(this.getLinear(), flags);
    }


    public void readFromParcel(Parcel in) {


        if( in.readInt() == 1 )
            setLinear((Linear) in.readParcelable(Linear.class.getClassLoader()));
    }

    public static Parcelable.Creator<Creative> CREATOR = new Parcelable.Creator<Creative>() {

        @Override
        public Creative createFromParcel(Parcel in) {
            return new Creative(in);
        }

        @Override
        public Creative[] newArray(int size) {
            return new Creative[size];
        }
    };


    public Linear getLinear() {
        return mLinear;
    }

    public void setLinear(Linear linear) {
        mLinear = linear;
    }
}
