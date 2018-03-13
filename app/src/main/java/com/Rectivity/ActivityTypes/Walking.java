package com.Rectivity.ActivityTypes;

/**
 * Created by leole on 3/11/18.
 */

public class Walking extends Activity {

    private boolean  indoor = false;
    private float duration;
    public Walking(float dur) {
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
