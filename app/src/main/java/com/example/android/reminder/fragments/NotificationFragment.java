package com.example.android.reminder.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.reminder.R;
import com.example.android.reminder.adapters.NotificationAdapter;
import com.example.android.reminder.beans.Notification;
import com.example.android.reminder.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends ListFragment{

    private static final int LAYOUT = R.layout.layout_notification_fragment;
    List<Notification> notificationList;
    Context context;
    NotificationAdapter notificationAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        Log.v("TEST","onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(LAYOUT,null);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("TEST", "onActivityCreated");

    }

    @Override
    public void onResume() {
        super.onResume();
        notificationList = getNotificationList();
        notificationAdapter = new NotificationAdapter(context,notificationList);
        setListAdapter(notificationAdapter);
        Log.v("TEST", "onResume");
    }

    public List<Notification> getNotificationList() {
        List<Notification> notificationList;
        try {
            DatabaseHelper db = new DatabaseHelper(context);
            notificationList = db.getAllNotifications();
            db.close();
        }catch (Exception e){
            notificationList = new ArrayList<>();
            Toast toast = Toast.makeText(getActivity(),"Error:"+ e.toString(),Toast.LENGTH_SHORT);
            toast.show();
        }
        return notificationList;
    }

    public void onDBNotificationChanged(){
        notificationList = getNotificationList();
        notificationAdapter = new NotificationAdapter(context,notificationList);
        setListAdapter(notificationAdapter);
    }


}
