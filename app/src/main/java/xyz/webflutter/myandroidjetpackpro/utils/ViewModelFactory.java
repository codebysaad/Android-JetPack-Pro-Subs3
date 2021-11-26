package xyz.webflutter.myandroidjetpackpro.utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import xyz.webflutter.myandroidjetpackpro.data.Repository;
import xyz.webflutter.myandroidjetpackpro.di.Injection;
import xyz.webflutter.myandroidjetpackpro.ui.detail.movie.DetailMovieViewModels;
import xyz.webflutter.myandroidjetpackpro.ui.detail.tvshow.DetailTvViewModels;
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieViewModel;
import xyz.webflutter.myandroidjetpackpro.ui.movie.favorite.MovieFavViewModel;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowViewModel;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.favorite.TvShowFavViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;
    private final Repository repository;

    private ViewModelFactory(Repository repository){
        this.repository = repository;
    }
    public static ViewModelFactory getInstance(Application application){
        if (INSTANCE == null){
            synchronized (ViewModelFactory.class){
                if (INSTANCE == null){
                    INSTANCE = new ViewModelFactory(Injection.repository(application));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        if (modelClass.isAssignableFrom(MovieViewModel.class)){
            return (T) new MovieViewModel(repository);
        } else if (modelClass.isAssignableFrom(TvShowViewModel.class)){
            return (T) new TvShowViewModel(repository);
        } else if (modelClass.isAssignableFrom(DetailMovieViewModels.class)) {
            return (T) new DetailMovieViewModels(repository);
        } else if (modelClass.isAssignableFrom(DetailTvViewModels.class)){
            return (T) new DetailTvViewModels(repository);
        } else if (modelClass.isAssignableFrom(MovieFavViewModel.class)){
            return (T) new MovieFavViewModel(repository);
        } else if (modelClass.isAssignableFrom(TvShowFavViewModel.class)){
            return (T) new TvShowFavViewModel(repository);
        }
        throw new IllegalArgumentException("Can't Find ViewModel Class: " + modelClass.getSimpleName());
    }
}
