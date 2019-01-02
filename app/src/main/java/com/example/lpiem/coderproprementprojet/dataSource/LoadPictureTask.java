package com.example.lpiem.coderproprementprojet.dataSource;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lpiem.coderproprementprojet.models.MyTaskLoadPictureParams;

import java.io.InputStream;
import java.net.URL;

public class LoadPictureTask extends AsyncTask<MyTaskLoadPictureParams, Void, Drawable> {

    @Override
    protected Drawable doInBackground(MyTaskLoadPictureParams... params) {
        URL urlDisplay = params[0].getUrlPicture();
        try {
            Object content = urlDisplay.getContent();
            InputStream inputStream = (InputStream)content;
            Drawable drawable = Drawable.createFromStream(inputStream, "src");
            Log.d("LOADPicture", "ICI Loading Picture " + urlDisplay + " " + drawable.toString());
            return drawable;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Drawable result) {
        super.onPostExecute(result);
    }
}
