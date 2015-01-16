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
@Root(name = "MediaFiles", strict=false)
public class MediaFiles implements Parcelable {

    @ElementList(entry="MediaFile", type=MediaFile.class, inline=true, required=false)
    List<MediaFile> mMediaFiles;

    public MediaFiles() {
    }

    public MediaFiles(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.getMediaFiles() != null ? 1:0);

        if( this.getMediaFiles() != null )
            dest.writeTypedList(this.getMediaFiles());
    }


    public void readFromParcel(Parcel in) {


        if( in.readInt() == 1 )
        {
            List<MediaFile> items = new ArrayList<>();
            in.readTypedList(items, MediaFile.CREATOR);
            setMediaFiles(items);
        }
    }

    public static Parcelable.Creator<MediaFiles> CREATOR = new Parcelable.Creator<MediaFiles>() {

        @Override
        public MediaFiles createFromParcel(Parcel in) {
            return new MediaFiles(in);
        }

        @Override
        public MediaFiles[] newArray(int size) {
            return new MediaFiles[size];
        }
    };


    public List<MediaFile> getMediaFiles() {
        return mMediaFiles;
    }

    public void setMediaFiles(List<MediaFile> mediaFiles) {
        mMediaFiles = mediaFiles;
    }
}
