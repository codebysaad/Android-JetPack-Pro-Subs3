package xyz.webflutter.myandroidjetpackpro.ui.detail.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Objects;

import xyz.webflutter.myandroidjetpackpro.FakeDummy;
import xyz.webflutter.myandroidjetpackpro.data.Repository;
import xyz.webflutter.myandroidjetpackpro.resource.Resource;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailTvViewModelsTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private DetailTvViewModels viewModels;
    private final Repository repository = mock(Repository.class);
    private final Resource<TvShowModels> models = Resource.success(FakeDummy.generateDummyTvShow().get(0));

    @Before
    public void setUp() {
        viewModels = new DetailTvViewModels(repository);
        viewModels.setTvShowId(Objects.requireNonNull(models.data).getId());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getDetailTvShow() {
        MutableLiveData<Resource<TvShowModels>> entities = new MutableLiveData<>();
        entities.setValue(models);
        assert models.data != null;
        when(repository.getDetailTvShow(Objects.requireNonNull(models.data).getId())).thenReturn(entities);
        Observer<Resource<TvShowModels>> observer = mock(Observer.class);
        viewModels.getTvShow().observeForever(observer);
        verify(observer).onChanged(models);
    }
}