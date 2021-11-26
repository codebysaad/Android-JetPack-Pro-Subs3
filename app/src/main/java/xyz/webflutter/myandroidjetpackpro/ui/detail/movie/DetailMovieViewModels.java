package xyz.webflutter.myandroidjetpackpro.ui.detail.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

import xyz.webflutter.myandroidjetpackpro.data.Repository;
import xyz.webflutter.myandroidjetpackpro.resource.Resource;
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;

public class DetailMovieViewModels extends ViewModel {
    private Repository repository;
    private MutableLiveData<String> id = new MutableLiveData<>();

    public DetailMovieViewModels(Repository repository){
        this.repository = repository;
    }

    private LiveData<Resource<MovieModels>> movie = Transformations.switchMap(id, mMovieId -> repository.getMovieDetail(mMovieId));

    public void setMovieId(String movieId){
        this.id.setValue(movieId);
    }

    LiveData<Resource<MovieModels>> getMovie(){
        return movie;
    }

    void setFavorite(){
        repository.setFavoriteMovie(Objects.requireNonNull(movie.getValue()).data);
    }
}