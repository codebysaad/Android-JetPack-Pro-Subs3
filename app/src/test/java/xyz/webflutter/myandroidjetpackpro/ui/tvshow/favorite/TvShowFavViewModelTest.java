package xyz.webflutter.myandroidjetpackpro.ui.tvshow.favorite;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import xyz.webflutter.myandroidjetpackpro.data.Repository;
import xyz.webflutter.myandroidjetpackpro.resource.Resource;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowFavViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final Repository repository = mock(Repository.class);
    private TvShowFavViewModel tvShowFavViewModel;

    @Before
    public void setUp(){
        tvShowFavViewModel = new TvShowFavViewModel(repository);
    }

    @Test
    public void getFavorite(){

        MutableLiveData<Resource<PagedList<TvShowModels>>> dummyMovie = new MutableLiveData<>();
        @SuppressWarnings("unchecked") PagedList<TvShowModels> pagedList = mock(PagedList.class);

        dummyMovie.setValue(Resource.success(pagedList));

        when(repository.getFavoriteTvShow()).thenReturn(dummyMovie);

        @SuppressWarnings("unchecked") Observer<Resource<PagedList<TvShowModels>>> observer = mock(Observer.class);

        tvShowFavViewModel.getFavoriteTvShow().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));

    }
}