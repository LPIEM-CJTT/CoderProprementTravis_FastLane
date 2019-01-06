package com.example.lpiem.coderproprementprojet;
import com.example.lpiem.coderproprementprojet.models.Comic;
import com.example.lpiem.coderproprementprojet.models.Creator;
import com.example.lpiem.coderproprementprojet.models.MyTaskLoadPictureParams;
import com.example.lpiem.coderproprementprojet.views.ComicDetailsActivity;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CoderproprementprojetTest {

    @Test
    public void getTitleComic(){
        ArrayList<Creator> creators = new ArrayList<>();
        creators.add(new Creator("Tristan", "Writter"));
        Comic comic = new Comic(45, "ComicTest", 255, "A test comic lambda", "A45896", "2017-10-25T00:00:00-0400", 3.99, 4, "", creators);
        assertEquals(comic.getTitle(), "ComicTest");
    }

    @Test
    public void getCreatorNameComic(){
        ArrayList<Creator> creators = new ArrayList<>();
        creators.add(new Creator("Tristan", "Writter"));
        Comic comic = new Comic(45, "ComicTest", 255, "A test comic lambda", "A45896", "2017-10-25T00:00:00-0400", 3.99, 4, "", creators);
        assertEquals(comic.getCreators().get(comic.getCreators().size() - 1).getName(), "Tristan");
    }

    @Test
    public void getDateComic(){
        ArrayList<Creator> creators = new ArrayList<>();
        creators.add(new Creator("Tristan", "Writter"));
        Comic comic = new Comic(45, "ComicTest", 255, "A test comic lambda", "A45896", "2017-10-25T00:00:00-0400", 3.99, 4, "", creators);
        assertEquals(comic.getDate(), "2017-10-25T00:00:00-0400");
    }

    @Test
    public void parseDate(){
        ArrayList<Creator> creators = new ArrayList<>();
        creators.add(new Creator("Tristan", "Writter"));
        Comic comic = new Comic(45, "ComicTest", 255, "A test comic lambda", "A45896", "2017-10-25T00:00:00-0400", 3.99, 4, "", creators);
        ComicDetailsActivity comicDetailsActivity = new ComicDetailsActivity();
        assertEquals(comicDetailsActivity.formatComicDate(comic), "samedi 25 octobre 2017");
    }

    @Test
    public void getDescriptionComic(){
        ArrayList<Creator> creators = new ArrayList<>();
        creators.add(new Creator("Tristan", "Writter"));
        Comic comic = new Comic(45, "ComicTest", 255, "A test comic lambda", "A45896", "2017-10-25T00:00:00-0400", 3.99, 4, "", creators);
        assertEquals(comic.getDescription(), "A test comic lambda");
    }

    @Test
    public void getNumberPagesComic(){
        ArrayList<Creator> creators = new ArrayList<>();
        creators.add(new Creator("Tristan", "Writter"));
        Comic comic = new Comic(45, "ComicTest", 255, "A test comic lambda", "A45896", "2017-10-25T00:00:00-0400", 3.99, 4, "", creators);
        assertEquals(comic.getPageCount(), 4);
    }

    @Test
    public void getInformationsAboutComic(){
        ArrayList<Creator> creators = new ArrayList<>();
        creators.add(new Creator("Tristan", "Writter"));
        Comic comic = new Comic(45, "ComicTest", 255, "A test comic lambda", "A45896", "2017-10-25T00:00:00-0400", 3.99, 4, "", creators);
        ComicDetailsActivity comicDetailsActivity = new ComicDetailsActivity();
        assertEquals(comicDetailsActivity.getInformations(comic), "3.99, 4 - A45896");
    }

    @Test
    public void getUrlMyTaskLoadPictureParams() throws MalformedURLException {
        URL url = new URL("http://i.annihil.us/u/prod/marvel/i/mg/f/03/59e7b08528560.jpg");
        MyTaskLoadPictureParams myTaskLoadPictureParams = new MyTaskLoadPictureParams(url);
        assertEquals(myTaskLoadPictureParams.getUrlPicture(), new URL("http://i.annihil.us/u/prod/marvel/i/mg/f/03/59e7b08528560.jpg"));
    }
}
