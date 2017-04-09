package com.example.jonas.eduquest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;


public class InvalidUpstreamDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Could not reach provider!");
        builder.setMessage("Eduquest could not contact service provider. Please verify that the address is correct.")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //
                        }
                        });

            return builder.create();
        }
    }
