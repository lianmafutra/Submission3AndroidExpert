package com.Sunflower.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.Sunflower.myapplication.Model.MovieResults;
import com.Sunflower.myapplication.Model.TvResults;
import com.Sunflower.myapplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetail extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV = "extra_tv";
    public static final String EXTRA_TYPE = "extra_type";

    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.tv_date)
    TextView date;
    @BindView(R.id.tv_desc)
    TextView desc;
    @BindView(R.id.img_poster)
    ImageView img;
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        MovieResults movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        TvResults tv = getIntent().getParcelableExtra(EXTRA_TV);
        String type = getIntent().getStringExtra(EXTRA_TYPE);
        if (type.equals("movie")){
            title.setText(movie.getTitle());
            date.setText(movie.getRelease_date());
            getSupportActionBar().setTitle("Movie Detail");
            desc.setText(movie.getOverview());
             imagePath = "https://image.tmdb.org/t/p/w185" + movie.getPhoto();
        }
        if (type.equals("tv")){
            title.setText(tv.getName());
            date.setText(tv.getFirst_air_date());
            getSupportActionBar().setTitle("Tv Show Detail");
            desc.setText(tv.getOverview());
             imagePath = "https://image.tmdb.org/t/p/w185" + tv.getPoster_path();
        }



        Glide.with(this)
                .load(imagePath)
                .apply(new RequestOptions()
                        .centerCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);

    }
}