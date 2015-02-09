package com.pitch.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class PayFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	final String[] list = new String[] {"James", "John", "Jimmy", "Joe", "June"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Who do you want to pay to?")
               .setItems(list, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       ContentActivity callingActivity = (ContentActivity) getActivity();
                       callingActivity.onUserSelectPayValue(list[which]);

               }
        });
        return builder.create();
    }

    
}