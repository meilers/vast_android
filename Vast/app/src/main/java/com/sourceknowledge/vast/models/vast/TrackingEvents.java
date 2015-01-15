package com.sourceknowledge.vast.models.vast;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by omegatai on 15-01-14.
 */
@Root(name = "TrackingEvents", strict=false)
public class TrackingEvents {

    @ElementList(entry="Tracking", type=Tracking.class, inline=true, data=true)
    List<Tracking> mTrackings;
}
