package com.example.android.reminder.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.reminder.R;
import com.example.android.reminder.beans.Notification;
import com.example.android.reminder.db.DatabaseHelper;

public class AddNotificationFragment extends DialogFragment {

    EditText editTextTitle;
    EditText editTextDescription;
    EditText editTextDate;
    private OnDBChangedListener mCallback;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_add_notification, null);
        editTextTitle = (EditText) view.findViewById(R.id.editTxtAddTitle);
        editTextDescription = (EditText) view.findViewById(R.id.editTxtAddDesc);
        editTextDate = (EditText) view.findViewById(R.id.editTxtAddDate);
        builder.setView(view)
                .setTitle(R.string.add_notification_title)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addNewNotification();
                        }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        return builder.create();
    }

    public void showDatePickerDialog() {
        DialogFragment datePickerFragment = new DatePickerFragment(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editTextDate.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        };
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    public void addNewNotification(){
        try {
            DatabaseHelper db = new DatabaseHelper(getActivity());
            db.addNotification(new Notification(editTextTitle.getText().toString(),
                    editTextDescription.getText().toString(),
                    editTextDate.getText().toString()));
            db.close();
        }catch (Exception e){
            Toast toast = Toast.makeText(getActivity(),"Error:"+ e.toString(),Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public interface OnDBChangedListener {
        public void onDBNotificationChanged();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Log.v("TEST","onDismiss");
        mCallback.onDBNotificationChanged();
        super.onDismiss(dialog);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnDBChangedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnDBChangedListener");
        }
    }
}
