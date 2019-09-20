package com.Sunflower.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Sunflower.myapplication.Adapter.AdapterMovie;
import com.Sunflower.myapplication.Adapter.AdapterTv;
import com.Sunflower.myapplication.Model.MovieResponse;
import com.Sunflower.myapplication.Model.MovieResults;
import com.Sunflower.myapplication.R;
import com.Sunflower.myapplication.Server.Interface;
import com.Sunflower.myapplication.Server.RetroServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentMovie extends Fragment {

    private static final String STATE_RESULT = "state_result";

    RecyclerView rv;
    ProgressBar progressBar;
    String kode;
    AdapterMovie movieAdapter;
    AdapterTv tvAdapter;
    private String TOKEN = "d1fc8e1957ff243040727607da2fa44b";


    private List<MovieResults> mItems = new ArrayList<>();

    public FragmentMovie() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_movie, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progressBar);
        kode = getArguments().getString("type");
        getActivity().getActionBar();
        load_movie();
    }


    public void load_movie() {
        Interface api = RetroServer.getClient().create(Interface.class);
        Call<MovieResponse> call = null;

        if (kode.equals("1")) {
            call = api.GetMovieList(TOKEN, "en-US");
        }
        if (kode.equals("2")) {
            call = api.GetTVList(TOKEN, "en-US");
        }

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                try {
                    MovieResponse movieDBResponse = response.body();
                    mItems = movieDBResponse.getResults();
                    init_RecycleView();
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }

    private void init_RecycleView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (kode.equals("1")) {
            movieAdapter = new AdapterMovie(getContext(), mItems);
            rv.setAdapter(movieAdapter);
            movieAdapter.notifyDataSetChanged();
            getActivity().setTitle("Movie List");
        }
        if (kode.equals("2")) {
            tvAdapter = new AdapterTv(getContext(), mItems);
            rv.setAdapter(tvAdapter);
            tvAdapter.notifyDataSetChanged();
            getActivity().setTitle("TV Show List");
        }


    }


}