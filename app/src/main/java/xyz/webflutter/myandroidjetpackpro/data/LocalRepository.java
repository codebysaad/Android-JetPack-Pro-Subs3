package xyz.webflutter.myandroidjetpackpro.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import java.util.List;

import xyz.webflutter.myandroidjetpackpro.data.local.Dao;
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;

import static android.content.ContentValues.TAG;

public class LocalRepository {
    private final Dao dao;

    private LocalRepository(Dao dao) {
        this.dao = dao;
    }

    private static LocalRepository INSTANCE;

    public static LocalRepository getInstance(Dao dao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(dao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieModels>> getMovies() {
        return dao.getMovies();
    }


    public LiveData<MovieModels> getMovieById(String id){
        return dao.getMovieByid(id);
    }

    public void setFavoriteMovie(MovieModels entity) {
        entity.favorited();
        dao.updateMovie(entity);
        Log.e(TAG, "movieFavorited: "+entity.getOverview() );
    }

    public void insertMovie(List<MovieModels> entities) {
        dao.insertMovie(entities);
    }

    public androidx.paging.DataSource.Factory<Integer, MovieModels> getFavoritedPaged() {
        return dao.getFavMoviePage();
    }

    public LiveData<List<TvShowModels>> getTvShows() {
        return dao.getTvShows();
    }

    public LiveData<TvShowModels> getTvShowById(final String id) {
        return dao.getTvShowByid(id);
    }

    public void setFavoriteTvShow(TvShowModels entity) {
        entity.favorited();
        dao.updateTvShow(entity);
    }

    public DataSource.Factory<Integer, TvShowModels> getFavTvPage() {
        return dao.getFavTvShowPage();
    }

    public void insertTvShow(List<TvShowModels> entities) {
        dao.insertTvShow(entities);
    }
    public DataSource.Factory<Integer, TvShowModels> getBookmarkedTvPaged() {
        return dao.getFavTvShowPage();
    }
}