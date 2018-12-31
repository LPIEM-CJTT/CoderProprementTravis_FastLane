package com.example.lpiem.coderproprementprojet.manager;

import android.content.Context;

import com.example.lpiem.coderproprementprojet.models.Comic;
import com.example.lpiem.coderproprementprojet.models.MyTaskParams;
import com.example.lpiem.coderproprementprojet.dataSource.ParsingFileTask;

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
}
