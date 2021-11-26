package xyz.webflutter.myandroidjetpackpro.ui.movie;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MovieViewModel viewModel;
    private final Repository repository = mock(Repository.class);

    @Before
    public void setUp(){
        viewModel = new MovieViewModel(repository);
    }

    @Test
    public void getMovies() {
        Resource<List<MovieModels>> dummyMovie = Resource.success(FakeDummy.generateDummyMovie());
        MutableLiveData<Resource<List<MovieModels>>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);

        when(repository.getMovies()).thenReturn(movies);
        @SuppressWarnings("unchecked") Observer<Resource<List<MovieModels>>> observer = mock(Observer.class);
        viewModel.getMovies().observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }
}