package com.example.lpiem.coderproprementprojet.presenters;

import android.content.Context;

import com.example.lpiem.coderproprementprojet.models.Comic;
import com.example.lpiem.coderproprementprojet.manager.ComicManager;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import io.reactivex.subjects.PublishSubject;

public class ComicListPresenter {

    private Context context;
    private ComicManager manager;
    public PublishSubject<ArrayList<Comic>> comics = PublishSubject.create();

    public ComicListPresenter(Context context) {
        this.context = context;
        manager = new ComicManager();
    }

    public void getComicList() {
        try {
            comics.onNext(manager.getComics(context));
        } catch (ExecutionException e) {
            comics.onError(e);
        } catch (InterruptedException e) {
            comics.onError(e);
        }
    }
}
