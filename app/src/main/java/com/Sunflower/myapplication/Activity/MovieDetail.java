package com.Sunflower.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.Sunflower.myapplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetail extends AppCompatActivity {
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.tv_date)
    TextView date;
    @BindView(R.id.tv_desc)
    TextView desc;
    @BindView(R.id.img_poster)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        title.setText(bundle.getString("title"));
        date.setText(bundle.getString("date"));
        desc.setText(bundle.getString("desc"));
        String type =  bundle.getString("type");
        if (type.equals("1")){
            getActionBar().setTitle("Detail Movie");
        }
        if (type.equals("2")){
            getActionBar().setTitle("Detail TV Show");
        }
        String imagePath = "https://image.tmdb.org/t/p/w185" + bundle.getString("img");
        Glide.with(this)
                .load(imagePath)
                .apply(new RequestOptions()
                        .centerCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);

    }
}
