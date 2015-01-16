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
@Root(name = "Wrapper", strict=false)
public class Wrapper implements Parcelable {

    @Element(name="AdSystem", type=String.class, required=false)
    private String mAdSystem;

    @Element(name="AdTitle", type=String.class, required=false)
    private String mAdTitle;

    @Element(name="VASTAdTagURI", data = true, required=false)
    private String mVASTAdTagURI;

    @ElementList(entry="Impression", type=String.class, inline=true, required=false)
    List<String> mImpressions;

    @Element(name="Creatives", type=Creatives.class, required=false)
    private Creatives mCreatives;


    public Wrapper() {
    }

    public Wrapper(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeInt(this.getAdSystem() != null ? 1:0);

        if( this.getAdSystem() != null )
            dest.writeString(this.getAdSystem());


        dest.writeInt(this.getAdTitle() != null ? 1:0);

        if( this.getAdTitle() != null )
            dest.writeString(this.getAdTitle());


        dest.writeInt(this.getVASTAdTagURI() != null ? 1:0);

        if( this.getVASTAdTagURI() != null )
            dest.writeString(this.getVASTAdTagURI());


        dest.writeInt(this.getImpressions() != null ? 1:0);

        if( this.getImpressions() != null )
            dest.writeStringList(this.getImpressions());


        dest.writeInt(this.getCreatives() != null ? 1:0);

        if( this.getCreatives() != null )
            dest.writeParcelable(this.getCreatives(), flags);
    }


    public void readFromParcel(Parcel in) {

        if( in.readInt() == 1 )
            setAdSystem(in.readString());

        if( in.readInt() == 1 )
            setAdTitle(in.readString());

        if( in.readInt() == 1 )
            setVASTAdTagURI(in.readString());

        if( in.readInt() == 1 )
        {
            List<String> items = new ArrayList<>();
            in.readStringList(items);
            setImpressions(items);
        }

        if( in.readInt() == 1 )
            setCreatives((Creatives)in.readParcelable(Creatives.class.getClassLoader()));
    }

    public static Creator<Wrapper> CREATOR = new Creator<Wrapper>() {

        @Override
        public Wrapper createFromParcel(Parcel in) {
            return new Wrapper(in);
        }

        @Override
        public Wrapper[] newArray(int size) {
            return new Wrapper[size];
        }
    };



    public String getAdSystem() {
        return mAdSystem;
    }

    public void setAdSystem(String adSystem) {
        mAdSystem = adSystem;
    }

    public String getAdTitle() {
        return mAdTitle;
    }

    public void setAdTitle(String adTitle) {
        mAdTitle = adTitle;
    }

    public String getVASTAdTagURI() {
        return mVASTAdTagURI;
    }

    public void setVASTAdTagURI(String VASTAdTagURI) {
        mVASTAdTagURI = VASTAdTagURI;
    }

    public List<String> getImpressions() {
        return mImpressions;
    }

    public void setImpressions(List<String> impressions) {
        mImpressions = impressions;
    }

    public Creatives getCreatives() {
        return mCreatives;
    }

    public void setCreatives(Creatives creatives) {
        mCreatives = creatives;
    }
}
