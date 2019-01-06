package com.example.lpiem.coderproprementprojet.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.PictureDrawable;
import android.util.Log;

import com.example.lpiem.coderproprementprojet.R;
import com.example.lpiem.coderproprementprojet.dataSource.LoadPictureTask;
import com.example.lpiem.coderproprementprojet.models.Comic;
import com.example.lpiem.coderproprementprojet.models.MyTaskLoadPictureParams;
import com.example.lpiem.coderproprementprojet.models.MyTaskParams;
import com.example.lpiem.coderproprementprojet.dataSource.ParsingFileTask;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ComicManager {

    private String file = "sample-ok.json";
    //private String file = "sample-ko.json";

    public ArrayList<Comic> getComics(Context context) {

        MyTaskParams tasks = new MyTaskParams(context, file);
        ParsingFileTask parsingFile = new ParsingFileTask();
        parsingFile.execute(tasks);

        try {
            return parsingFile.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Drawable getComicPicture(String urlPicture) {
        try {
            URL url = new URL(urlPicture);
            LoadPictureTask loadPictureTask = new LoadPictureTask();
            MyTaskLoadPictureParams params = new MyTaskLoadPictureParams(url);
            loadPictureTask.execute(params);
            return loadPictureTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
