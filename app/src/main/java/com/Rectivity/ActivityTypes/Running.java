package com.Rectivity.ActivityTypes;

/**
 * Created by leole on 3/11/18.
 */

public class Running extends Activity {

    private float duration;
    private boolean indoor = false;
    public Running(float dur) {
        duration = dur;
    }

    @Override
    public boolean getIndoor() {
        return false;
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
