package com.example.intervaltimer.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CreateIntervalBroadcastReciever extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "Create Interval recieved!", Toast.LENGTH_SHORT);
    }

}
