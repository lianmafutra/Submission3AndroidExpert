package com.Sunflower.myapplication.Server;

import com.Sunflower.myapplication.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface {
    @GET("movie?api_key=d1fc8e1957ff243040727607da2fa44b&language=en-US")
    Call<MovieResponse> GetMovieList();
}
