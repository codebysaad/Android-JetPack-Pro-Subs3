package xyz.webflutter.myandroidjetpackpro.data.remote;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.webflutter.myandroidjetpackpro.data.remote.entity.MovieEntity;
import xyz.webflutter.myandroidjetpackpro.data.remote.entity.TvShowEntity;
import xyz.webflutter.myandroidjetpackpro.data.remote.response.ApiResponse;
import xyz.webflutter.myandroidjetpackpro.data.remote.response.MovieResponse;
import xyz.webflutter.myandroidjetpackpro.data.remote.response.TvShowResponse;
import xyz.webflutter.myandroidjetpackpro.utils.ExpressoIdlingResource;
import xyz.webflutter.myandroidjetpackpro.utils.JsonHelper;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private final JsonHelper jsonHelper;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteRepository(JsonHelper jsonHelper){
        this.jsonHelper = jsonHelper;
    }
    public static RemoteRepository getInstance(JsonHelper jsonHelper){
        if (INSTANCE == null){
            INSTANCE = new RemoteRepository(jsonHelper);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MovieEntity>>> getMovies(){
        ExpressoIdlingResource.increment();

        MutableLiveData<ApiResponse<List<MovieEntity>>> movieResult = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> jsonHelper.loadMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                assert response.body() != null;
                movieResult.setValue(ApiResponse.success(response.body().getMovieEntities()));
                if (!ExpressoIdlingResource.getIdlingResource().isIdleNow())
                    ExpressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                ExpressoIdlingResource.decrement();
            }
        }),SERVICE_LATENCY_IN_MILLIS);

        return movieResult;
    }

    public LiveData<ApiResponse<List<TvShowEntity>>> getTvShows(){
        ExpressoIdlingResource.increment();

        MutableLiveData<ApiResponse<List<TvShowEntity>>> tvShowResult = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> jsonHelper.loadTvShow().enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                assert response.body() != null;
                tvShowResult.setValue(ApiResponse.success(response.body().getTvShowEntities()));
                if (!ExpressoIdlingResource.getIdlingResource().isIdleNow())
                    ExpressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                ExpressoIdlingResource.decrement();
            }
        }),SERVICE_LATENCY_IN_MILLIS);

        return tvShowResult;
    }

    public LiveData<ApiResponse<MovieEntity>> getMovie(String id){
        ExpressoIdlingResource.increment();

        MutableLiveData<ApiResponse<MovieEntity>> movieResult = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(()-> jsonHelper.getMovieById(id).enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(@NonNull Call<MovieEntity> call, @NonNull Response<MovieEntity> response) {
                movieResult.setValue(ApiResponse.success(response.body()));
                if (!ExpressoIdlingResource.getIdlingResource().isIdleNow())
                    ExpressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<MovieEntity> call, @NonNull Throwable t) {
                ExpressoIdlingResource.decrement();
            }
        }),SERVICE_LATENCY_IN_MILLIS);
        return movieResult;
    }

    public LiveData<ApiResponse<TvShowEntity>> getTvShow(String id){
        ExpressoIdlingResource.increment();

        MutableLiveData<ApiResponse<TvShowEntity>> tvShowResult = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(()-> jsonHelper.getTvShowById(id).enqueue(new Callback<TvShowEntity>() {
            @Override
            public void onResponse(@NonNull Call<TvShowEntity> call, @NonNull Response<TvShowEntity> response) {
                tvShowResult.setValue(ApiResponse.success(response.body()));
                if (!ExpressoIdlingResource.getIdlingResource().isIdleNow())
                    ExpressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<TvShowEntity> call, @NonNull Throwable t) {
                ExpressoIdlingResource.decrement();
            }
        }),SERVICE_LATENCY_IN_MILLIS);

        return tvShowResult;
    }
}
