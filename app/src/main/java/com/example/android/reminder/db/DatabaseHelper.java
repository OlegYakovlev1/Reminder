package com.example.android.reminder.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.reminder.beans.Notification;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "reminder.db";
    private static final String DATABASE_NOTIFICATIONS_TABLE = "notifications";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_DATE = "date";
    private static final String CREATE_NOTIFICATIONS_TABLE = "CREATE TABLE " + DATABASE_NOTIFICATIONS_TABLE + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
            + KEY_DESCRIPTION + " TEXT," + KEY_DATE + " TEXT" + ")";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTIFICATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NOTIFICATIONS_TABLE);
        onCreate(db);
    }

    public void addNotification(Notification notification){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, notification.getTitle());
        values.put(KEY_DESCRIPTION, notification.getDescription());
        values.put(KEY_DATE, notification.getDate());

        db.insert(DATABASE_NOTIFICATIONS_TABLE, null, values);
        db.close();
    }

    public List<Notification> getAllNotifications() {
        List<Notification> notificationList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DATABASE_NOTIFICATIONS_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Notification notification = new Notification();
                notification.setId(Integer.parseInt(cursor.getString(0)));
                notification.setTitle(cursor.getString(1));
                notification.setDescription(cursor.getString(2));
                notification.setDate(cursor.getString(3));
                notificationList.add(notification);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return notificationList;
    }
}
