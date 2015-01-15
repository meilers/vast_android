package com.sourceknowledge.vast.models.vast;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by omegatai on 15-01-14.
 */
@Root(name = "Creatives", strict=false)
public class Creatives {

    @Element(name="Creative", type=Creative.class)
    private Creative mCreative;
}
