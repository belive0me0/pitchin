package com.pitch.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class RequestFragment extends DialogFragment {
	private double mValue;
	public void setValue(double value) {
		mValue = value;
	}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Request Confirmation")
        .setMessage("Total amount of request money will be "+ String.format("%.2f", (6 * mValue))+". Do you want to proceed?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ContentActivity callingActivity = (ContentActivity) getActivity();
                callingActivity.onUserSelectRequestValue();            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
       
        return builder.create();
    }
    

    
}