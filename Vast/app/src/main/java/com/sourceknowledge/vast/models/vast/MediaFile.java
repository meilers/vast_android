package com.sourceknowledge.vast.models.vast;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * Created by omegatai on 15-01-14.
 */
public class MediaFile implements Parcelable {

    @Attribute(name = "delivery", required=false)
    private String mDelivery;

    @Attribute(name = "bitrate", required=false)
    private String mBitrate;

    @Attribute(name = "width", required=false)
    private String mWidth;

    @Attribute(name = "height", required=false)
    private String mHeight;

    @Attribute(name = "type", required=false)
    private String mType;

    @Text(required=false)
    private String mValue;



    public MediaFile() {
    }

    public MediaFile(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeInt(this.getDelivery() != null ? 1:0);

        if( this.getDelivery() != null )
            dest.writeString(this.getDelivery());


        dest.writeInt(this.getBitrate() != null ? 1:0);

        if( this.getBitrate() != null )
            dest.writeString(this.getBitrate());


        dest.writeInt(this.getWidth() != null ? 1:0);

        if( this.getWidth() != null )
            dest.writeString(this.getWidth());


        dest.writeInt(this.getHeight() != null ? 1:0);

        if( this.getHeight() != null )
            dest.writeString(this.getHeight());


        dest.writeInt(this.getType() != null ? 1:0);

        if( this.getType() != null )
            dest.writeString(this.getType());


        dest.writeInt(this.getValue() != null ? 1:0);

        if( this.getValue() != null )
            dest.writeString(this.getValue());

    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setDelivery(in.readString());

        if( in.readInt() == 1 )
            setBitrate(in.readString());

        if( in.readInt() == 1 )
            setWidth(in.readString());

        if( in.readInt() == 1 )
            setHeight(in.readString());

        if( in.readInt() == 1 )
            setType(in.readString());


        if( in.readInt() == 1 )
            setValue(in.readString());
    }

    public static Parcelable.Creator<MediaFile> CREATOR = new Parcelable.Creator<MediaFile>() {

        @Override
        public MediaFile createFromParcel(Parcel in) {
            return new MediaFile(in);
        }

        @Override
        public MediaFile[] newArray(int size) {
            return new MediaFile[size];
        }
    };






    public String getDelivery() {
        return mDelivery;
    }

    public void setDelivery(String delivery) {
        mDelivery = delivery;
    }

    public String getBitrate() {
        return mBitrate;
    }

    public void setBitrate(String bitrate) {
        mBitrate = bitrate;
    }

    public String getWidth() {
        return mWidth;
    }

    public void setWidth(String width) {
        mWidth = width;
    }

    public String getHeight() {
        return mHeight;
    }

    public void setHeight(String height) {
        mHeight = height;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }
}
