package com.Rectivity.ActivityTypes;

/**
 * Created by leole on 3/11/18.
 */

public abstract class Activity {
    private float duration;
    private boolean indoor;
    abstract public boolean getIndoor();
    abstract  public float getDuration();
    abstract  public void setDuration(float dur);
}
