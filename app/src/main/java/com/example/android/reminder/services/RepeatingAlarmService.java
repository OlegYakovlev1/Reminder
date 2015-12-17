package com.example.android.reminder.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class RepeatingAlarmService extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("Test","RepeatingAlarmService");
        Toast.makeText(context,"RepeatingAlarmService",Toast.LENGTH_SHORT).show();

    }
}
