package xyz.webflutter.myandroidjetpackpro.ui.movie.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import xyz.webflutter.myandroidjetpackpro.data.Repository;
import xyz.webflutter.myandroidjetpackpro.resource.Resource;
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;

public class MovieFavViewModel extends ViewModel {
    private final Repository repository;

    public MovieFavViewModel(Repository repository) {
        this.repository = repository;
    }

    LiveData<Resource<PagedList<MovieModels>>> getFavoritedMovies(){
        return repository.getFavoriteMovie();
    }
}