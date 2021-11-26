package xyz.webflutter.myandroidjetpackpro.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.ArrayList;
import java.util.List;

import xyz.webflutter.myandroidjetpackpro.data.remote.RemoteRepository;
import xyz.webflutter.myandroidjetpackpro.data.remote.entity.MovieEntity;
import xyz.webflutter.myandroidjetpackpro.data.remote.entity.TvShowEntity;
import xyz.webflutter.myandroidjetpackpro.data.remote.response.ApiResponse;
import xyz.webflutter.myandroidjetpackpro.resource.Resource;
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;
import xyz.webflutter.myandroidjetpackpro.utils.MyAppExecutor;

public class Repository implements DataSource {
    private volatile static Repository INSTANCE = null;

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;
    private final MyAppExecutor myAppExecutor;

    private Repository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository,@NonNull MyAppExecutor myAppExecutor){
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.myAppExecutor = myAppExecutor;
    }

    public static Repository getInstance(LocalRepository localRepository, RemoteRepository remoteRepository, MyAppExecutor myAppExecutor){
        if (INSTANCE==null){
            synchronized (Repository.class){
                if (INSTANCE==null){
                    INSTANCE = new Repository(localRepository,remoteRepository,myAppExecutor);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieModels>>> getMovies() {
        return new NetworkResource<List<MovieModels>,List<MovieEntity>>(myAppExecutor){
            @Override
            protected LiveData<List<MovieModels>> loadFromDB() {
                return localRepository.getMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieModels> data) {
                return (data==null)||(data.size()==0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieEntity>>> createCall() {
                return remoteRepository.getMovies();
            }

            @Override
            protected void saveCallResult(List<MovieEntity> data) {
                List<MovieModels> movieEntities = new ArrayList<>();
                for (MovieEntity movieResponse:data){
                    movieEntities.add(new MovieModels(movieResponse));
                }
                localRepository.insertMovie(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TvShowModels>>> getTvShows() {
        return new NetworkResource<List<TvShowModels>,List<TvShowEntity>>(myAppExecutor){

            @Override
            protected LiveData<List<TvShowModels>> loadFromDB() {
                return localRepository.getTvShows();
            }

            @Override
            protected Boolean shouldFetch(List<TvShowModels> data) {
                return (data==null)||(data.size()==0);
            }

            @Override
            protected LiveData<ApiResponse<List<TvShowEntity>>> createCall() {
                return remoteRepository.getTvShows();
            }

            @Override
            protected void saveCallResult(List<TvShowEntity> data) {
                List<TvShowModels> tvShowEntities = new ArrayList<>();

                for (TvShowEntity tvShowResponse:data){
                    tvShowEntities.add(new TvShowModels(tvShowResponse));
                }

                localRepository.insertTvShow(tvShowEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieModels>> getMovieDetail(String id) {
        return new NetworkResource<MovieModels,MovieEntity>(myAppExecutor){

            @Override
            protected LiveData<MovieModels> loadFromDB() {
                return localRepository.getMovieById(id);
            }

            @Override
            protected Boolean shouldFetch(MovieModels data) {
                return data==null;
            }

            @Override
            protected LiveData<ApiResponse<MovieEntity>> createCall() {
                return remoteRepository.getMovie(id);
            }

            @Override
            protected void saveCallResult(MovieEntity data) {
                MovieModels movieModels = new MovieModels(data);
                List<MovieModels> movieModel = new ArrayList<>();
                movieModel.add(movieModels);
                localRepository.insertMovie(movieModel);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvShowModels>> getDetailTvShow(String id) {
        return new NetworkResource<TvShowModels,TvShowEntity>(myAppExecutor){

            @Override
            protected LiveData<TvShowModels> loadFromDB() {
                return localRepository.getTvShowById(id);
            }

            @Override
            protected Boolean shouldFetch(TvShowModels data) {
                return data==null;
            }

            @Override
            protected LiveData<ApiResponse<TvShowEntity>> createCall() {
                return remoteRepository.getTvShow(id);
            }

            @Override
            protected void saveCallResult(TvShowEntity data) {
                TvShowModels tvShowModels = new TvShowModels(data);
                List<TvShowModels> tvShowModel = new ArrayList<>();
                tvShowModel.add(tvShowModels);
                localRepository.insertTvShow(tvShowModel);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<MovieModels>>> getFavoriteMovie() {
        return new NetworkResource<PagedList<MovieModels>,List<MovieEntity>>(myAppExecutor){

            @Override
            protected LiveData<PagedList<MovieModels>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavoritedPaged(),10).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieModels> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieEntity>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieEntity> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvShowModels>>> getFavoriteTvShow() {
        return new NetworkResource<PagedList<TvShowModels>,List<TvShowEntity>>(myAppExecutor){

            @Override
            protected LiveData<PagedList<TvShowModels>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getBookmarkedTvPaged(),10).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvShowModels> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TvShowEntity>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TvShowEntity> data) {

            }
        }.asLiveData();
    }

    @Override
    public void setFavoriteMovie(MovieModels movieModels) {
        Runnable runnable = ()-> localRepository.setFavoriteMovie(movieModels);

        myAppExecutor.disk().execute(runnable);
    }

    @Override
    public void setFavoriteTvShow(TvShowModels tvShowModels) {
        Runnable runnable = ()-> localRepository.setFavoriteTvShow(tvShowModels);

        myAppExecutor.disk().execute(runnable);
    }
}
