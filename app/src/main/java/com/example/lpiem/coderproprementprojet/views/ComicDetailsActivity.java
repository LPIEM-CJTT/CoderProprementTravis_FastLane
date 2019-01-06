package com.example.lpiem.coderproprementprojet.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.lpiem.coderproprementprojet.error.ErrorDisplayer;
import com.example.lpiem.coderproprementprojet.models.Comic;
import com.example.lpiem.coderproprementprojet.presenters.ComicDetailsPresenter;
import com.example.lpiem.coderproprementprojet.R;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ComicDetailsActivity extends AppCompatActivity {

    private ComicDetailsPresenter presenter;
    private Integer itemPosition;
    private ErrorDisplayer errorDisplayer;
    @BindView(R.id.tv_title_details_comic)
    TextView title;
    @BindView(R.id.tv_description_details_comic)
    TextView description;
    @BindView(R.id.tv_date_details_comic)
    TextView date;
    @BindView(R.id.tv_price_page_diamond_details_comic)
    TextView informations;
    @BindView(R.id.tv_creators_details_comic)
    TextView creators;
    @BindView(R.id.iv_details_comic)
    ImageView imageComic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_details);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        itemPosition = getIntent().getIntExtra(Intent.EXTRA_UID, -1);

        errorDisplayer = new ErrorDisplayer(this);

        presenter = new ComicDetailsPresenter(this, itemPosition);

        presenter.comic.subscribe(
                comic -> {
                    displayComic(comic);
                },
                error -> {
                    errorDisplayer.DisplayError(getString(R.string.toast_comic_error));
                }
        );

        presenter.image.subscribe(image -> {
                    if (image != null) {
                        imageComic.setImageDrawable(image);
                    } else {
                        imageComic.setImageResource(R.drawable.placeholder);
                    }
                }
        );

        presenter.getComicDetails();
    }

    private void displayComic(Comic comic) {
        title.setText(comic.getTitle());
        description.setText(comic.getDescription());
        date.setText(formatComicDate(comic));
        informations.setText(getInformations(comic));
        creators.setText(getCreators(comic));
        presenter.getImageComicDetail(comic.getImage());
    }

    private String getCreators(Comic comic) {
        String textCreators = "";
        for (int i = 0; i < comic.getCreators().size(); i++) {
            textCreators += comic.getCreators().get(i).getRole() + " : " + comic.getCreators().get(i).getName() + "\n";
        }
        return textCreators;
    }

    private String getInformations(Comic comic) {
        return comic.getPrice() + ", " + comic.getPageCount() + " - " + comic.getDiamondCode();
    }

    private String formatComicDate(Comic comic) {
        SimpleDateFormat simpleDateFormatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date date = new Date();
        try {
            date = simpleDateFormatInput.parse(comic.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat simpleDateFormatOutput = new SimpleDateFormat("dd-MM-yyyy' at 'HH:mm:ss");
        return simpleDateFormatOutput.format(date);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
