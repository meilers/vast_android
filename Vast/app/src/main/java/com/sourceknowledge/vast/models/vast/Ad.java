package com.sourceknowledge.vast.models.vast;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by omegatai on 15-01-14.
 */
@Root(name = "Ad", strict=false)
public class Ad implements Parcelable {

    @Attribute(name = "id")
    private String mId;

    @Element(name="Wrapper", type=Wrapper.class, required=false)
    private Wrapper mWrapper;

    @Element(name="InLine", type=InLine.class, required=false)
    private InLine mInLine;


    public Ad() {
    }

    public Ad(Parcel in) {
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



        dest.writeInt(this.getWrapper() != null ? 1:0);

        if( this.getWrapper() != null )
            dest.writeParcelable(this.getWrapper(), flags);



        dest.writeInt(this.getInLine() != null ? 1:0);

        if( this.getInLine() != null )
            dest.writeParcelable(this.getInLine(), flags);

    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setId(in.readString());

        if( in.readInt() == 1 )
            setWrapper((Wrapper) in.readParcelable(Ad.class.getClassLoader()));

        if( in.readInt() == 1 )
            setInLine((InLine) in.readParcelable(Ad.class.getClassLoader()));

    }

    public static Creator<Ad> CREATOR = new Creator<Ad>() {

        @Override
        public Ad createFromParcel(Parcel in) {
            return new Ad(in);
        }

        @Override
        public Ad[] newArray(int size) {
            return new Ad[size];
        }
    };

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Wrapper getWrapper() {
        return mWrapper;
    }

    public void setWrapper(Wrapper wrapper) {
        mWrapper = wrapper;
    }

    public InLine getInLine() {
        return mInLine;
    }

    public void setInLine(InLine inLine) {
        mInLine = inLine;
    }
}
