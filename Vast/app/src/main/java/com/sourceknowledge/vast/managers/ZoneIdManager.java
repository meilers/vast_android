package com.sourceknowledge.vast.managers;

/**
 * Created by omegatai on 14-11-25.
 */

import com.sourceknowledge.vast.listeners.VastListener;
import com.sourceknowledge.vast.models.vast.Vast;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Michael on 2014-04-02.
 */
public enum ZoneIdManager {

    INSTANCE;

    private String mZoneId;

    public String getZoneId() {
        return mZoneId;
    }

    public void setZoneId(String zoneId) {
        mZoneId = zoneId;
    }
}
