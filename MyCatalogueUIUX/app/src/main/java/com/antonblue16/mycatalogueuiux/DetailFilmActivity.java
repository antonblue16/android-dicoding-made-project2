package com.antonblue16.mycatalogueuiux;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailFilmActivity extends AppCompatActivity
{
    public static String EXTRA_NAME = "extraName";
    public static String EXTRA_LANGUAGE = "extraLanguage";
    public static String EXTRA_AVERAGE = "extraAverage";
    public static String EXTRA_SYNOPSIS = "extraSynopsis";
    public static String EXTRA_IMAGE = "extraImage";
    public static String EXTRA_DATETIME = "extraDateTime";

    public TextView tvDetailNameFilm, tvDetailLanguageFilm, tvDetailAverageFilm, tvDetailSynopsisFilm, tvDetailDateTime;
    public ImageView ivDetailImageFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        tvDetailNameFilm = findViewById(R.id.tvDetailNameFilm);
        tvDetailLanguageFilm = findViewById(R.id.tvDetailLanguageFilm);
        tvDetailAverageFilm = findViewById(R.id.tvDetailAverageFilm);
        tvDetailSynopsisFilm = findViewById(R.id.tvDetailSynopsisFilm);
        ivDetailImageFilm = findViewById(R.id.ivDetailImageFilm);
        tvDetailDateTime = findViewById(R.id.tvDetailDateTime);

        String name = getIntent().getStringExtra(EXTRA_NAME);
        String language = getIntent().getStringExtra(EXTRA_LANGUAGE);
        String average = getIntent().getStringExtra(EXTRA_AVERAGE);
        String synopsis = getIntent().getStringExtra(EXTRA_SYNOPSIS);
        String image = getIntent().getStringExtra(EXTRA_IMAGE);
        String dateTime = getIntent().getStringExtra(EXTRA_DATETIME);

        tvDetailNameFilm.setText(name);
        tvDetailLanguageFilm.setText(language);
        tvDetailAverageFilm.setText(average);
        tvDetailDateTime.setText(dateTime);
        tvDetailSynopsisFilm.setText(synopsis);
        Picasso.with(getApplicationContext()).load("http://image.tmdb.org/t/p/w500/"+image)
                .into(ivDetailImageFilm);
    }
}
