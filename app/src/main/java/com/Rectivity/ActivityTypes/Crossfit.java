package com.Rectivity.ActivityTypes;

/**
 * Created by Jon T on 3/18/2018.
 */

public class Crossfit extends Activity {
    private float duration;
    private boolean indoor = true;

    public Crossfit(float dur) {
        duration = dur;
    }
    @Override
    public boolean getIndoor() {
        return indoor;
    }

    @Override
    public float getDuration() {
        return duration;
    }

    @Override
    public void setDuration(float dur) {
        duration = dur;
    }
}
