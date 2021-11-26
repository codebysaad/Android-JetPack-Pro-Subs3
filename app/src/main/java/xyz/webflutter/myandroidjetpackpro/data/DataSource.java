package xyz.webflutter.myandroidjetpackpro.data;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import java.util.List;

import xyz.webflutter.myandroidjetpackpro.resource.Resource;
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;

interface DataSource {
    LiveData<Resource<List<MovieModels>>> getMovies();
    LiveData<Resource<MovieModels>> getMovieDetail(String id);
    void setFavoriteMovie(MovieModels entity);
    LiveData<Resource<PagedList<MovieModels>>> getFavoriteMovie();

    LiveData<Resource<List<TvShowModels>>> getTvShows();
    LiveData<Resource<TvShowModels>> getDetailTvShow(String id);
    void setFavoriteTvShow(TvShowModels entity);
    LiveData<Resource<PagedList<TvShowModels>>> getFavoriteTvShow();
}
