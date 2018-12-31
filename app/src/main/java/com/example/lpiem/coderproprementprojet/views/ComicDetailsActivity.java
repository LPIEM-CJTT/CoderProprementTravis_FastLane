package com.example.lpiem.coderproprementprojet.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.lpiem.coderproprementprojet.models.Comic;
import com.example.lpiem.coderproprementprojet.presenters.ComicDetailsPresenter;
import com.example.lpiem.coderproprementprojet.R;
import com.squareup.picasso.Picasso;

public class ComicDetailsActivity extends AppCompatActivity {

    private ComicDetailsPresenter presenter;
    private Integer itemPosition;
    @BindView(R.id.tv_title_details_comic) TextView title;
    @BindView(R.id.tv_description_details_comic) TextView description;
    @BindView(R.id.tv_date_details_comic) TextView date;
    @BindView(R.id.tv_price_page_diamond_details_comic) TextView informations;
    @BindView(R.id.tv_creators_details_comic) TextView creators;
    @BindView(R.id.iv_details_comic) ImageView imageComic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_details);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemPosition = getIntent().getIntExtra(Intent.EXTRA_UID, -1);

        presenter = new ComicDetailsPresenter(this, itemPosition);

        presenter.comic.subscribe(
                comic -> { displayComic(comic); },
                error -> {
                    Toast.makeText(this, getString(R.string.toast_comic_error), Toast.LENGTH_LONG).show();
                }
        );

        presenter.getComicDetails();

    }

    private void displayComic(Comic comic) {
        title.setText(comic.getTitle());
        description.setText(comic.getDescription());
        date.setText(comic.getDate());
        informations.setText(getInformations(comic));
        creators.setText(getCreators(comic));
        Picasso.get().load(comic.getImage()).into(imageComic);
    }

    private String getCreators(Comic comic) {
        String textCreators = "";
        for(int i = 0; i < comic.getCreators().size(); i++){
            textCreators += comic.getCreators().get(i).getRole() + " : " + comic.getCreators().get(i).getName() + "\n";
        }
        return textCreators;
    }

    private String getInformations(Comic comic) {
        return comic.getPrice() + ", " + comic.getPageCount() + " - " + comic.getDiamondCode();
    }
}
