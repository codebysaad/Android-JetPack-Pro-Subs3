package xyz.webflutter.myandroidjetpackpro.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import xyz.webflutter.myandroidjetpackpro.data.Repository;
import xyz.webflutter.myandroidjetpackpro.resource.Resource;

public class TvShowViewModel extends ViewModel {

    private final Repository repository;

    public TvShowViewModel(Repository repository){
        this.repository = repository;
    }

    LiveData<Resource<List<TvShowModels>>> getTvShows(){
        return repository.getTvShows();
    }
}