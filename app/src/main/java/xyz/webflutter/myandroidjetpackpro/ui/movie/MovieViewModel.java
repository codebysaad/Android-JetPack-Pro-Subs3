package xyz.webflutter.myandroidjetpackpro.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import xyz.webflutter.myandroidjetpackpro.data.Repository;
import xyz.webflutter.myandroidjetpackpro.resource.Resource;

public class MovieViewModel extends ViewModel {

    private final Repository repository;

    public MovieViewModel(Repository repository){
        this.repository = repository;
    }

    LiveData<Resource<List<MovieModels>>> getMovies(){
        return repository.getMovies();
    }
}