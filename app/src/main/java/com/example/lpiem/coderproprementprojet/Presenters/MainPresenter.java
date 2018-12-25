package com.example.lpiem.coderproprementprojet.Presenters;

import android.content.Context;

import com.example.lpiem.coderproprementprojet.Models.Comic;
import com.example.lpiem.coderproprementprojet.Models.Manager;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import io.reactivex.subjects.PublishSubject;

public class MainPresenter implements Presenter {

    private Context context;
    private Manager manager;
    public PublishSubject<ArrayList<Comic>> comics = PublishSubject.create();

    public MainPresenter(Context context) {
        this.context = context;
        manager = new Manager();
    }

    @Override
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
