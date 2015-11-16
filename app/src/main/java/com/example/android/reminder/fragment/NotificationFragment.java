package com.example.android.reminder.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.reminder.R;
import com.example.android.reminder.adapter.NotificationAdapter;
import com.example.android.reminder.beans.Notification;
import com.example.android.reminder.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends ListFragment {

    private static final int LAYOUT = R.layout.layout_notification_fragment;
    List<Notification> notificationList;
    Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        notificationList = getNotificationList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(LAYOUT,null);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NotificationAdapter notificationAdapter = new NotificationAdapter(context,getNotificationList());
        setListAdapter(notificationAdapter);
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
}
