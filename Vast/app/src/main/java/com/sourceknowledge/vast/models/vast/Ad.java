package com.sourceknowledge.vast.models.vast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by omegatai on 15-01-14.
 */
@Root(name = "Ad", strict=false)
public class Ad {

    @Attribute(name = "id")
    private String mId;

    @Element(name="Wrapper", type=Wrapper.class, required=false)
    private Wrapper mWrapper;

    @Element(name="InLine", type=InLine.class, required=false)
    private InLine mInLine;
}
