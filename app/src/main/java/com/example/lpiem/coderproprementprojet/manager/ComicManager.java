package com.example.lpiem.coderproprementprojet.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

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

    public ArrayList<Comic> getComics(Context context) throws ExecutionException, InterruptedException {

        MyTaskParams tasks = new MyTaskParams(context,"sample-ok.json");
        ParsingFileTask parsingFile =
                new ParsingFileTask();
        parsingFile.execute(tasks);

        return parsingFile.get();
    }

    public Drawable getComicPicture(String urlPicture) throws ExecutionException, InterruptedException, MalformedURLException {
        URL url = new URL(urlPicture);
        Log.d("LOADPicture", "ICI Manager");
        LoadPictureTask loadPictureTask = new LoadPictureTask();
        MyTaskLoadPictureParams params = new MyTaskLoadPictureParams(url);
        loadPictureTask.execute(params);
        return loadPictureTask.get();
    }
}
