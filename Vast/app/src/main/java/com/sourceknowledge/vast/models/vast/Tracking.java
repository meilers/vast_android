package com.sourceknowledge.vast.models.vast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * Created by omegatai on 15-01-14.
 */
public class Tracking {

    @Attribute(name = "event")
    private String mEvent;

    @Text()
    private String mValue;
}
