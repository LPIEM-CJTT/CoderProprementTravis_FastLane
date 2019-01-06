package com.example.lpiem.coderproprementprojet.presenters;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.lpiem.coderproprementprojet.manager.ComicManager;
import com.example.lpiem.coderproprementprojet.models.Comic;

import java.util.concurrent.ExecutionException;

import io.reactivex.subjects.PublishSubject;

public class ComicDetailsPresenter {

    private Context context;
    private ComicManager manager;
    private Integer itemPosition;
    public PublishSubject<Comic> comic = PublishSubject.create();
    public PublishSubject<Drawable> image = PublishSubject.create();

    public ComicDetailsPresenter(Context context, Integer itemPosition) {
        this.context = context;
        this.itemPosition = itemPosition;
        this.manager = new ComicManager();
    }

    public void getComicDetails() {
        Comic getComic = manager.getComics(context).get(itemPosition);
        comic.onNext(getComic);
    }

    public void getImageComicDetail(String url){
        image.onNext(manager.getComicPicture(url));
    }
}
