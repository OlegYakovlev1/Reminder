package com.example.android.reminder.fragment;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.reminder.R;
import com.example.android.reminder.adapter.NotificationAdapter;
import com.example.android.reminder.beans.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends ListFragment {

    private static final int LAYOUT = R.layout.layout_notification_fragment;
    List<Notification> notificationList = getNotificationList();



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NotificationAdapter notificationAdapter = new NotificationAdapter(getActivity().getApplicationContext(),notificationList);
        setListAdapter(notificationAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(LAYOUT,null);
    }

    public List<Notification> getNotificationList() {
        List<Notification> notificationList = new ArrayList<Notification>();

        for (int i = 0; i < 10; i++) {
            Notification notification = new Notification("Title "+i, "Description "+i, "2015.10.18");
            notificationList.add(notification);
        }
        return notificationList;
    }
}
