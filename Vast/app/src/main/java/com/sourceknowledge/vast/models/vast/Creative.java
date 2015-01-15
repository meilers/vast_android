package com.sourceknowledge.vast.models.vast;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by omegatai on 15-01-14.
 */
@Root(name = "Creative", strict=false)
public class Creative {

    @Element(name="Linear", type=Linear.class)
    private Linear mLinear;
}
