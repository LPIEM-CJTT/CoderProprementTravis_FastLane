package com.example.lpiem.coderproprementprojet.Models;

import android.content.Context;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Manager {

    public ArrayList getComics(Context context) throws ExecutionException, InterruptedException {

        MyTaskParams tasks = new MyTaskParams(context,"sample-ok.json");
        ParsingFileTask parsingFile =
                new ParsingFileTask();
        parsingFile.execute(tasks);

        return parsingFile.get();
    }
}
