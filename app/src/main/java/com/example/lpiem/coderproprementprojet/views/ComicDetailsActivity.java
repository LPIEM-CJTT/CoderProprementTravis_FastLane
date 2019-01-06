package com.example.lpiem.coderproprementprojet.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.lpiem.coderproprementprojet.Error.ErrorDisplayer;
import com.example.lpiem.coderproprementprojet.R;
import com.example.lpiem.coderproprementprojet.models.Comic;
import com.example.lpiem.coderproprementprojet.presenters.ComicDetailsPresenter;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
public class ComicDetailsActivity extends AppCompatActivity {

    private ComicDetailsPresenter presenter;
    private Comic shareComic;
    private Integer itemPosition;
    private ErrorDisplayer errorDisplayer;
    @BindView(R.id.tv_title_details_comic) TextView title;
    @BindView(R.id.tv_description_details_comic) TextView description;
    @BindView(R.id.tv_date_details_comic) TextView date;
    @BindView(R.id.tv_price_page_diamond_details_comic) TextView informations;
    @BindView(R.id.tv_creators_details_comic) TextView creators;
    @BindView(R.id.iv_details_comic) ImageView imageComic;
    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CheckResult")
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
                comic -> { displayComic(comic); },
                error -> {
                    errorDisplayer.DisplayError(getString(R.string.toast_comic_error));
                }
        );

        presenter.getComicDetails();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Share:
                shareClick();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }



    private void displayComic(Comic comic) {
        shareComic = comic;
        title.setText(comic.getTitle());
        description.setText(comic.getDescription());
        date.setText(formatComicDate(comic));
        informations.setText(getInformations(comic));
        creators.setText(getCreators(comic));
        try {
            imageComic.setImageDrawable(presenter.getManager().getComicPicture(comic.getImage()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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

    private String formatComicDate(Comic comic) {
        SimpleDateFormat simpleDateFormatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date date = new Date();
        try {
            date = simpleDateFormatInput.parse(comic.getDate());
            Log.d("DateFormatter", "ICI");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat simpleDateFormatOutput = new SimpleDateFormat("dd-MM-yyyy' at 'HH:mm:ss");
        Log.d("DateFormatter", "New Simple DateFormatter");
        return simpleDateFormatOutput.format(date);
    }



    private String shareTextFormater (){
        String[] extraParams = new String[4];
        extraParams[0] = getString(R.string.hey_look_at_this) + "\n";
        extraParams[1] = shareComic.getTitle() + "\n";
        extraParams[2] = formatComicDate(shareComic)+"\n";
        extraParams[3] = String.valueOf(shareComic.getPrice()) + " $";
String tmp = "";
        for(int i = 0; i< extraParams.length;i++){
            tmp = tmp + extraParams[i];
        }
        return  tmp;
    }

    private void shareClick()
    {


        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareTextFormater());

        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share)));
    }
}
