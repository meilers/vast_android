package com.sourceknowledge.vast.managers;

/**
 * Created by omegatai on 14-11-25.
 */

import com.sourceknowledge.vast.listeners.VastListener;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Michael on 2014-04-02.
 */
public enum DebugManager {

    INSTANCE;

    private boolean mIsDebug;

    public boolean isDebug() {
        return mIsDebug;
    }

    public void setDebug(boolean isDebug) {
        mIsDebug = isDebug;
    }
}
