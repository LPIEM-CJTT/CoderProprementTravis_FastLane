package com.example.lpiem.coderproprementprojet.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.lpiem.coderproprementprojet.error.ErrorDisplayer;
import com.example.lpiem.coderproprementprojet.R;
import com.example.lpiem.coderproprementprojet.adapter.ComicsListAdapter;
import com.example.lpiem.coderproprementprojet.models.Comic;
import com.example.lpiem.coderproprementprojet.presenters.ComicListPresenter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

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
        setTitle(R.string.title_activity_list);

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
                list -> {
                    displayComics(list);
                },
                error -> {
                    errorDisplayer.DisplayError(getString(R.string.toast_list_error));
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
