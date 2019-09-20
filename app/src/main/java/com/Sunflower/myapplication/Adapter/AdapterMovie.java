package com.Sunflower.myapplication.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.Sunflower.myapplication.Model.MovieResponse;
import com.Sunflower.myapplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.HolderData> {

    private List<MovieResponse> mList;
    private Context ctx;

    public AdapterMovie(Context ctx, List<MovieResponse> mList) {
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
        final MovieResponse dm = mList.get(position);
        holder.txt_movie.setText(dm.getOriginalTitle());

        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends RecyclerView.ViewHolder {
        @BindView(R.id.img_movie)
        ImageView img_movie;
        @BindView(R.id.txt_title)
        TextView txt_movie;
        MovieResponse dm;

        public HolderData(View v) {
            super(v);
            ButterKnife.bind(this, itemView);

//            btn_website_link.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//
//                    }
//            });


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, DetailActivity.class);
                    intent.putExtra("title", dm.getTitle());
                    intent.putExtra("image", dm.getPosterPath());
                    ctx.startActivity(intent);
                }
            });
        }


    }


}

