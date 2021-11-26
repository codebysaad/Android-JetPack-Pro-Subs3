package xyz.webflutter.myandroidjetpackpro.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import xyz.webflutter.myandroidjetpackpro.FakeDummy;
import xyz.webflutter.myandroidjetpackpro.data.Repository;
import xyz.webflutter.myandroidjetpackpro.resource.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class TvShowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TvShowViewModel viewModel;
    private final Repository repository = mock(Repository.class);

    @Before
    public void setUp(){
        viewModel = new TvShowViewModel(repository);
    }

    @Test
    public void getTvShows() {
        Resource<List<TvShowModels>> dummyTvShow = Resource.success(FakeDummy.generateDummyTvShow());
        MutableLiveData<Resource<List<TvShowModels>>> tvShow = new MutableLiveData<>();
        tvShow.setValue(dummyTvShow);

        when(repository.getTvShows()).thenReturn(tvShow);
        @SuppressWarnings("unchecked") Observer<Resource<List<TvShowModels>>> observer = mock(Observer.class);
        viewModel.getTvShows().observeForever(observer);
        verify(observer).onChanged(dummyTvShow);
    }
}