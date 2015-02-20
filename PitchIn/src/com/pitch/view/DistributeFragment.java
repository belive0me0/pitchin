package com.pitch.view;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DistributeFragment extends DialogFragment {
	private double mValue;
	public void setValue(double value) {
		mValue = value;
	}
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	final double eachAmount = mValue/6;
    	double roundDown = ((100*mValue) % 6) / 100;
    	double remainder = mValue - (6 * eachAmount);
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Request Confirmation")
        .setMessage("Your total amount is $"+String.format("%.2f", mValue)+". Each person will get " + String.format("%.2f", eachAmount) +" and the remainder is " + roundDown + ". And the remainder will goto Pitch.inc. Thanks for using our app.")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ContentActivity callingActivity = (ContentActivity) getActivity();
                callingActivity.onUserSelectDistributeValue(String.format("%.2f", eachAmount));            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
       
        return builder.create();
    }
    

    
}