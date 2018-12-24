package com.example.lpiem.coderproprementprojet.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lpiem.coderproprementprojet.Models.Comic;
import com.example.lpiem.coderproprementprojet.Models.MyTaskParams;
import com.example.lpiem.coderproprementprojet.Models.ParsingFileTask;
import com.example.lpiem.coderproprementprojet.Presenters.MainPresenter;
import com.example.lpiem.coderproprementprojet.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
    }

    @Override
    public void displayComics(ArrayList comics) {
        //affichage des comics dans la table view
    }
}
