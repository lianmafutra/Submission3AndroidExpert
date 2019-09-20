package com.Sunflower.myapplication.Server;

import com.Sunflower.myapplication.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Interface {
    @GET("movie/top_rated")
    Call<MovieResponse> GetMovieList(
            @Query("api_key") String apiKey,
            @Query("language") String lang);

    @GET("tv/top_rated")
    Call<MovieResponse> GetTVList(
            @Query("api_key") String apiKey,
            @Query("language") String lang);
}
