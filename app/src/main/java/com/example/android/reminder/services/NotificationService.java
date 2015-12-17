package com.example.android.reminder.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class NotificationService extends Service{
    public static final int INTERVAL = 1000*60; // 1 min
    private int firstRun;
    private int requestCode = 1234;
    private AlarmManager alarmManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        firstRun = (60 - Calendar.getInstance().get(Calendar.SECOND))*1000;
        startService();
    }

    private void startService(){
        Intent intent = new Intent(this, RepeatingAlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, 0);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + firstRun,
                INTERVAL,
                pendingIntent);
        Log.v("Test", "Calendar: "+Calendar.getInstance().get(Calendar.SECOND) + "First run: "+firstRun + " system clock: "+SystemClock.elapsedRealtime());

        Toast.makeText(this, "Service Started.", Toast.LENGTH_LONG).show();
        Log.v("Test", "AlarmManger started at " + new java.sql.Timestamp(System.currentTimeMillis()).toString());

    }

    @Override
    public void onDestroy() {

        if (alarmManager != null) {
            Intent intent = new Intent(this, RepeatingAlarmService.class);
            alarmManager.cancel(PendingIntent.getBroadcast(this, requestCode, intent, 0));
        }
        Toast.makeText(this, "Service Stopped!", Toast.LENGTH_LONG).show();
        Log.v("Test", "Service onDestroy(). Stop AlarmManager at " + new java.sql.Timestamp(System.currentTimeMillis()).toString());
        super.onDestroy();
    }
}
