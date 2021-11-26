package xyz.webflutter.myandroidjetpackpro.ui.detail.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

import xyz.webflutter.myandroidjetpackpro.data.Repository;
import xyz.webflutter.myandroidjetpackpro.resource.Resource;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;

public class DetailTvViewModels extends ViewModel {
    private Repository repository;
    private MutableLiveData<String> id = new MutableLiveData<>();

    public DetailTvViewModels(Repository repository){
        this.repository = repository;
    }

    private LiveData<Resource<TvShowModels>> tvShow = Transformations.switchMap(id, tvShowId -> repository.getDetailTvShow(tvShowId));

    public void setTvShowId(String tvShowId){
        this.id.setValue(tvShowId);
    }

    LiveData<Resource<TvShowModels>> getTvShow(){
        return tvShow;
    }

    void setFavorite(){
        repository.setFavoriteTvShow(Objects.requireNonNull(tvShow.getValue()).data);
    }
}
