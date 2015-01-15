package com.sourceknowledge.vast.models.vast;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by omegatai on 15-01-14.
 */
@Root(name = "Linear", strict=false)
public class Linear {

    @Element(name="TrackingEvents", type=TrackingEvents.class)
    private TrackingEvents mTrackingEvents;

    @Element(name="VideoClicks", type=VideoClicks.class)
    private VideoClicks mVideoClicks;

}
