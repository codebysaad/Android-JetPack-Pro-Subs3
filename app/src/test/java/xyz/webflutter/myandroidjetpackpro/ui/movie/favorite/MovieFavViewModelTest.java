package xyz.webflutter.myandroidjetpackpro.ui.movie.favorite;

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
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieFavViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final Repository repository = mock(Repository.class);
    private MovieFavViewModel movieFavViewModel;

    @Before
    public void setUp(){
        movieFavViewModel = new MovieFavViewModel(repository);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getFavorite(){

        MutableLiveData<Resource<PagedList<MovieModels>>> dummyMovie = new MutableLiveData<>();
        PagedList<MovieModels> pagedList = mock(PagedList.class);

        dummyMovie.setValue(Resource.success(pagedList));

        when(repository.getFavoriteMovie()).thenReturn(dummyMovie);

        @SuppressWarnings("unchecked") Observer<Resource<PagedList<MovieModels>>> observer = mock(Observer.class);

        movieFavViewModel.getFavoritedMovies().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));

    }
}