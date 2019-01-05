package com.example.lpiem.coderproprementprojet.Error;

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

    /**

     *
     * @param  message  The message that will be displayed
     * @param  type 0 for Toast | 1 for snackbar

     */
    public void DisplayError (String message, int type){
        switch (type){
            case 0 :
                this.ShowToast(message);

            case 1 :
               this.ShowSnackbar(message);

        }
    }

    private void ShowToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    private void ShowSnackbar(String message){
        View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        View v = rootView.findViewById(R.id.iv_details_comic);
        Snackbar.make(v,message, Snackbar.LENGTH_SHORT).show();
    }
}
