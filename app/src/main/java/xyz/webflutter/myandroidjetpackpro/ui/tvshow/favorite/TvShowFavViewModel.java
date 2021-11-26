package xyz.webflutter.myandroidjetpackpro.ui.tvshow.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import xyz.webflutter.myandroidjetpackpro.data.Repository;
import xyz.webflutter.myandroidjetpackpro.resource.Resource;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;

public class TvShowFavViewModel extends ViewModel {
    private final Repository repository;

    public TvShowFavViewModel(Repository repository) {
        this.repository = repository;
    }

    LiveData<Resource<PagedList<TvShowModels>>> getFavoriteTvShow() {
        return repository.getFavoriteTvShow();
    }
}
