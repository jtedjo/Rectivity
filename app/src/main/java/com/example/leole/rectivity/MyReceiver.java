package com.example.leole.rectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Jon T on 3/11/2018.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    MainActivity main = (MainActivity) context;
        Log.i("Receiver long", intent.getStringExtra("long"));
//    main.updateLocation();
    }
}
