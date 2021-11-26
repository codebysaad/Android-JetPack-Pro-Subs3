package xyz.webflutter.myandroidjetpackpro.ui.detail.movie;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import xyz.webflutter.myandroidjetpackpro.FakeDummy;
import xyz.webflutter.myandroidjetpackpro.data.Repository;
import xyz.webflutter.myandroidjetpackpro.resource.Resource;
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailMovieViewModelsTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private DetailMovieViewModels viewModels;
    private final Repository repository = mock(Repository.class);
    private final Resource<MovieModels> modelsResource = Resource.success(FakeDummy.generateDummyMovie().get(0));

    @Before
    public void setUp() {
        viewModels = new DetailMovieViewModels(repository);
        assert modelsResource.data != null;
        viewModels.setMovieId(modelsResource.data.getId());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getDetailMovie() {
        MutableLiveData<Resource<MovieModels>> entities = new MutableLiveData<>();
        entities.setValue(modelsResource);
        assert modelsResource.data != null;
        when(repository.getMovieDetail(modelsResource.data.getId())).thenReturn(entities);
        Observer<Resource<MovieModels>> observer = mock(Observer.class);
        viewModels.getMovie().observeForever(observer);
        verify(observer).onChanged(modelsResource);
    }
}