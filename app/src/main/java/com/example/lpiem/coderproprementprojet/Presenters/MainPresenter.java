package com.example.lpiem.coderproprementprojet.Presenters;

import android.content.Context;

import com.example.lpiem.coderproprementprojet.Models.Manager;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainPresenter {

    private View mainView;
    private Manager manager;

    public MainPresenter(View mainView) {
        this.mainView = mainView;
        manager = new Manager();
    }

    public void getComicList() throws ExecutionException, InterruptedException {
        mainView.displayComics(manager.getComics((Context) mainView));
    }

    public interface View {

        void displayComics(ArrayList comics);
    }
}
