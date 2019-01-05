package com.example.lpiem.coderproprementprojet.views;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.util.Log;
import android.widget.Toast;

import com.example.lpiem.coderproprementprojet.Error.ErrorDisplayer;
import com.example.lpiem.coderproprementprojet.adapter.ComicsListAdapter;
import com.example.lpiem.coderproprementprojet.models.Comic;
import com.example.lpiem.coderproprementprojet.presenters.ComicListPresenter;
import com.example.lpiem.coderproprementprojet.R;
import java.util.ArrayList;

public class ComicListActivity extends AppCompatActivity {

    private ComicListPresenter presenter;
    private ArrayList<Comic> comicsList = new ArrayList<>();
    private ComicsListAdapter mAdapter;
    private ErrorDisplayer errorDisplayer;
    @BindView(R.id.rv_list_comic) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_list);
        ButterKnife.bind(this);

        presenter = new ComicListPresenter(this);

        errorDisplayer = new ErrorDisplayer(this);

        mAdapter = new ComicsListAdapter(comicsList, presenter.getManager());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.clickItemListener.subscribe( comicId ->
                openComicDetail(comicId)
        );

        presenter.comics.subscribe(
                list -> { displayComics(list); },
                error -> {
                    errorDisplayer.DisplayError(getString(R.string.toast_list_error),0);
                }
        );

        presenter.getComicList();
    }

    private void openComicDetail(Integer comicId) {
        Intent intent = new Intent(this, ComicDetailsActivity.class);
        intent.putExtra(Intent.EXTRA_UID, comicId);
        startActivity(intent);
    }

    private void displayComics(ArrayList<Comic> list) {
        comicsList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
}