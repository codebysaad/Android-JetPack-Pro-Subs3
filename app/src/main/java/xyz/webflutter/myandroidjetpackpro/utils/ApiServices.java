package xyz.webflutter.myandroidjetpackpro.utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.webflutter.myandroidjetpackpro.data.remote.entity.MovieEntity;
import xyz.webflutter.myandroidjetpackpro.data.remote.entity.TvShowEntity;
import xyz.webflutter.myandroidjetpackpro.data.remote.response.MovieResponse;
import xyz.webflutter.myandroidjetpackpro.data.remote.response.TvShowResponse;

import static xyz.webflutter.myandroidjetpackpro.BuildConfig.API_KEY;

interface ApiServices {
    @GET("discover/movie?api_key="+ API_KEY)
    Call<MovieResponse> getMovies();
    @GET("discover/tv?api_key="+API_KEY)
    Call<TvShowResponse> getTvShows();
    @GET("movie/{id}?api_key="+API_KEY)
    Call<MovieEntity> getMovieById(@Path("id") String id);
    @GET("tv/{id}?api_key="+API_KEY)
    Call<TvShowEntity> getTvShowById(@Path("id") String id);
}