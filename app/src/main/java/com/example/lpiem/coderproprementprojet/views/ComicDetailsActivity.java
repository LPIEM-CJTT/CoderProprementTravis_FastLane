package com.example.lpiem.coderproprementprojet.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import android.widget.Toolbar;
import com.example.lpiem.coderproprementprojet.R;
import com.example.lpiem.coderproprementprojet.error.ErrorDisplayer;
import com.example.lpiem.coderproprementprojet.models.Comic;
import com.example.lpiem.coderproprementprojet.presenters.ComicDetailsPresenter;

import java.net.MalformedURLException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
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
        setTitle(R.string.title_activity_detail);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Share:
                shareClick();
                return true;
            case android.R.id.home:
                finish();

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

        Calendar myCalendar = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDay());
        int dayOfWeek = myCalendar.get(Calendar.DAY_OF_WEEK);
        int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());

        String dayName = symbols.getWeekdays()[dayOfWeek];
        String monthName = symbols.getMonths()[date.getMonth()];

        return dayName + " " + day + " " + monthName + " " + year;
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
