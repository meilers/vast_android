package com.sourceknowledge.vast.models.vast;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by omegatai on 15-01-14.
 */
@Root(name = "Wrapper", strict=false)
public class Wrapper {

    @Element(name="AdSystem", type=String.class)
    private String mAdSystem;

    @Element(name="AdTitle", type=String.class)
    private String mAdTitle;

    @Element(name="VASTAdTagURI", data = true)
    private String mVASTAdTagURI;

    @ElementList(entry="Impression", type=String.class, inline=true)
    List<String> mImpressions;

    @Element(name="Creatives", type=Creatives.class)
    private Creatives mCreatives;
}
