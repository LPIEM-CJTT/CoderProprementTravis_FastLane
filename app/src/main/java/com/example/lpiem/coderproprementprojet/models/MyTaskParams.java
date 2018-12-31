package com.example.lpiem.coderproprementprojet.models;

import android.content.Context;

public  class MyTaskParams {

    Context context;
    String filePath;

    public MyTaskParams(Context context, String filePath) {
        this.context = context;
        this.filePath = filePath;
    }

    public Context getContext() {
        return context;
    }

    public String getFilePath() {
        return filePath;
    }


}
