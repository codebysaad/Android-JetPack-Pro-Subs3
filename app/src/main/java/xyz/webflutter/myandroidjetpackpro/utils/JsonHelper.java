package xyz.webflutter.myandroidjetpackpro.utils;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.webflutter.myandroidjetpackpro.data.remote.entity.MovieEntity;
import xyz.webflutter.myandroidjetpackpro.data.remote.entity.TvShowEntity;
import xyz.webflutter.myandroidjetpackpro.data.remote.response.MovieResponse;
import xyz.webflutter.myandroidjetpackpro.data.remote.response.TvShowResponse;

import static xyz.webflutter.myandroidjetpackpro.BuildConfig.BASE_URL;

public class JsonHelper {

    private static final JsonHelper jsonHelper = new JsonHelper();
    private static ApiServices apiServices;

    private JsonHelper(){
        Retrofit retrofit = createAdapter();
        apiServices = retrofit.create(ApiServices.class);
    }

    private Retrofit createAdapter(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static JsonHelper getInstance(){
        return jsonHelper;
    }

    public Call<MovieResponse> loadMovies(){
        return apiServices.getMovies();
    }

    public Call<TvShowResponse> loadTvShow(){
        return apiServices.getTvShows();
    }

    public Call<MovieEntity> getMovieById(String id){
        return apiServices.getMovieById(id);
    }

    public Call<TvShowEntity> getTvShowById(String id){
        return apiServices.getTvShowById(id);
    }
}
