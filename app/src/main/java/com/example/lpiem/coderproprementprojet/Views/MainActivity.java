package com.example.lpiem.coderproprementprojet.Views;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lpiem.coderproprementprojet.Adapter.ComicsListAdapter;
import com.example.lpiem.coderproprementprojet.Models.Comic;
import com.example.lpiem.coderproprementprojet.Presenters.MainPresenter;
import com.example.lpiem.coderproprementprojet.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;
    private ArrayList<Comic> comicsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ComicsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        presenter = new MainPresenter(this);

        mAdapter = new ComicsListAdapter(comicsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        presenter.comics.subscribe(
                this::displayComics
        );

        presenter.getComicList();
    }

    private void displayComics(ArrayList<Comic> list) {
        //comicsList.add(new Comic(3, "test", 3, "test", "test", "test", 3.0, 3, "test", comicsList));
        comicsList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
}
