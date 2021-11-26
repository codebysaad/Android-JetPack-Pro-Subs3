package xyz.webflutter.myandroidjetpackpro.data.local;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;

@androidx.room.Dao
public interface Dao {
    @WorkerThread
    @Query("SELECT * FROM movie")
    LiveData<List<MovieModels>> getMovies();

    @Transaction
    @Query("SELECT * FROM movie WHERE id = :id")
    LiveData<MovieModels> getMovieByid(String id);

    @WorkerThread
    @Query("SELECT * FROM movie where favorite = 1")
    LiveData<List<MovieModels>> getFavMovie();

    @WorkerThread
    @Query("SELECT * FROM movie where favorite = 1")
    DataSource.Factory<Integer, MovieModels> getFavMoviePage();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovie(List<MovieModels> list);

    @Update(onConflict = OnConflictStrategy.FAIL)
    void updateMovie(MovieModels entity);



    @WorkerThread
    @Query("SELECT * FROM tvshow")
    LiveData<List<TvShowModels>> getTvShows();

    @Transaction
    @Query("SELECT * FROM tvshow WHERE id = :id")
    LiveData<TvShowModels> getTvShowByid(String id);

    @WorkerThread
    @Query("SELECT * FROM tvshow where favorite = 1")
    LiveData<List<TvShowModels>> getFavTvShow();

    @WorkerThread
    @Query("SELECT * FROM tvshow where favorite = 1")
    DataSource.Factory<Integer, TvShowModels> getFavTvShowPage();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTvShow(List<TvShowModels> list);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateTvShow(TvShowModels entity);
}