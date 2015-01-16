package com.sourceknowledge.vast.models.vast;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omegatai on 15-01-12.
 */
@Root(name = "VAST", strict=false)
public class Vast implements Parcelable  {

    @Attribute(name = "version", required = false)
    private String mVersion;

    @Attribute(name = "noNamespaceSchemaLocation", required = false)
    private String mNoNamespaceSchemaLocation;

    @Element(name="Ad", type=Ad.class, required=false)
    private Ad mAd;

    public Vast() {
    }

    public Vast(Parcel in) {
        readFromParcel(in);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeInt(this.getVersion() != null ? 1:0);

        if( this.getVersion() != null )
            dest.writeString(this.getVersion());


        dest.writeInt(this.getNoNamespaceSchemaLocation() != null ? 1:0);

        if( this.getNoNamespaceSchemaLocation() != null )
            dest.writeString(this.getNoNamespaceSchemaLocation());



        dest.writeInt(this.getAd() != null ? 1:0);

        if( this.getAd() != null )
            dest.writeParcelable(this.getAd(), flags);





    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setVersion(in.readString());

        if( in.readInt() == 1 )
            setNoNamespaceSchemaLocation(in.readString());

        if( in.readInt() == 1 )
            setAd((Ad)in.readParcelable(Ad.class.getClassLoader()));

    }

    public static Creator<Vast> CREATOR = new Creator<Vast>() {

        @Override
        public Vast createFromParcel(Parcel in) {
            return new Vast(in);
        }

        @Override
        public Vast[] newArray(int size) {
            return new Vast[size];
        }
    };



    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

    public String getNoNamespaceSchemaLocation() {
        return mNoNamespaceSchemaLocation;
    }

    public void setNoNamespaceSchemaLocation(String noNamespaceSchemaLocation) {
        mNoNamespaceSchemaLocation = noNamespaceSchemaLocation;
    }

    public Ad getAd() {
        return mAd;
    }

    public void setAd(Ad ad) {
        mAd = ad;
    }
}
