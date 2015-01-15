package com.sourceknowledge.vast.models.vast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by omegatai on 15-01-12.
 */
@Root(name = "VAST")
public class Vast {

    @Attribute(name = "version")
    private String mVersion;

    @Attribute(name = "noNamespaceSchemaLocation")
    private String mNoNamespace;

    @Element(name="Ad", type=Ad.class)
    private Ad mAd;

}
