package com.Sunflower.myapplication.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.Sunflower.myapplication.Activity.MovieDetail;
import com.Sunflower.myapplication.Model.MovieResults;
import com.Sunflower.myapplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.HolderData> {

    private List<MovieResults> mList;
    private Context ctx;

    public AdapterMovie(Context ctx, List<MovieResults> mList) {
        this.ctx = ctx;
        this.mList = mList;
    }


    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        HolderData holder = new HolderData(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(final HolderData holder, int position) {
        final MovieResults dm = mList.get(position);

        holder.txt_movie.setText(dm.getOriginalTitle());
        holder.txt_date.setText(dm.getReleaseDate());
        holder.txt_desc.setText(dm.getOverview());
        String imagePath = "https://image.tmdb.org/t/p/w185" + dm.getPosterPath();

        Glide.with(ctx)
                .load(imagePath)
                .apply(new RequestOptions()
                        .centerCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.img_movie);

        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends RecyclerView.ViewHolder {
        @BindView(R.id.img_movie)
        ImageView img_movie;
        @BindView(R.id.txt_title_movie)
        TextView txt_movie;
        @BindView(R.id.txt_release_date)
        TextView txt_date;
        @BindView(R.id.txt_desc_movie)
        TextView txt_desc;


        MovieResults dm;

        public HolderData(View v) {
            super(v);
            ButterKnife.bind(this, itemView);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, MovieDetail.class);
                    intent.putExtra("title", dm.getOriginalTitle());
                    intent.putExtra("date", dm.getReleaseDate());
                    intent.putExtra("desc", dm.getOverview());
                    intent.putExtra("img", dm.getPosterPath());
                    intent.putExtra("type","1");
                    ctx.startActivity(intent);
                }
            });
        }


    }


}

