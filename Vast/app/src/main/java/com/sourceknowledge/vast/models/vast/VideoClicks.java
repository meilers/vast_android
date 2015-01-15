package com.sourceknowledge.vast.models.vast;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by omegatai on 15-01-14.
 */

@Root(name = "VideoClicks", strict=false)
public class VideoClicks {

    @ElementList(entry="ClickTracking", type=String.class, inline=true, data=true)
    List<String> mClickTrackings;
}
