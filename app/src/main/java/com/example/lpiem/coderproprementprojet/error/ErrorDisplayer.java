package com.example.lpiem.coderproprementprojet.error;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.lpiem.coderproprementprojet.R;
import com.google.android.material.snackbar.Snackbar;

public class ErrorDisplayer {

    private Context context;

    public ErrorDisplayer(Context ctx) {
        context = ctx;
    }


    public void DisplayError (String message) {
                //this.ShowToast(message);
                this.ShowSnackbar(message);
    }

    private void ShowToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    private void ShowSnackbar(String message){
        View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        View v = rootView.findViewById(R.id.main_layout_comic_list);
       Snackbar.make(v,message, Snackbar.LENGTH_LONG).show();
   }
}
