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
public enum VastManager {

    INSTANCE;

    private CopyOnWriteArrayList<VastListener> mVastListeners = new CopyOnWriteArrayList<VastListener>();
    private Vast mVast;

    public Vast getVast()
    {
        return mVast;
    }

    public void setVast(Vast vast) {

        mVast = vast;
        fireVastEvent();
    }

    public void addVastListener(VastListener l) {
        mVastListeners.add(l);
    }

    public void removeVastListener(VastListener l) {
        mVastListeners.remove(l);
    }

    public void fireVastEvent() {

        for (VastListener l : mVastListeners) {
            if (l != null)
                l.vastEventReceived();
        }
    }
}
