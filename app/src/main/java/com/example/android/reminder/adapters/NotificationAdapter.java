package com.example.android.reminder.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.reminder.R;
import com.example.android.reminder.beans.Notification;

import java.util.List;

public class NotificationAdapter extends BaseAdapter{

    List<Notification> notificationList;
    Context context;
    LayoutInflater inflater;

    public NotificationAdapter(Context context, List<Notification> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Notification getItem(int position) {
        return notificationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         if(convertView == null){
             convertView = inflater.inflate(R.layout.layout_notification_item,parent,false);
         }

        TextView title = (TextView) convertView.findViewById(R.id.textTitle);
        TextView description = (TextView) convertView.findViewById(R.id.textDescription);
        TextView date = (TextView) convertView.findViewById(R.id.textDate);

        Notification notification = notificationList.get(position);

        title.setText(notification.getTitle());
        description.setText(notification.getDescription());
        date.setText(notification.getDate());

        return convertView;
    }


}
