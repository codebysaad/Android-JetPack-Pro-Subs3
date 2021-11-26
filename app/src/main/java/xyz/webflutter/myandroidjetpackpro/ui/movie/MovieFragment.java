package xyz.webflutter.myandroidjetpackpro.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import xyz.webflutter.myandroidjetpackpro.R;
import xyz.webflutter.myandroidjetpackpro.adapter.MovieAdapter;
import xyz.webflutter.myandroidjetpackpro.utils.ViewModelFactory;

public class MovieFragment extends Fragment {

    private RecyclerView rvMovie;
    private ProgressBar progressBar;
    private List<MovieModels> models;
    private MovieAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movie, container, false);
        rvMovie = root.findViewById(R.id.rv_movie);
        progressBar = root.findViewById(R.id.progress_movie);
        return root;
    }

    public MovieFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null){
            MovieViewModel viewModel = obtainViewModel(getActivity());
            viewModel.getMovies().observe(this, movies -> {
                if (movies !=null){
                    switch (movies.status){
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case FAILED:
                            progressBar.setVisibility(View.GONE);
                            Snackbar.make(view, R.string.some_mistake, Snackbar.LENGTH_LONG);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            adapter.setMovies(movies.data);
                            adapter.notifyDataSetChanged();
                            break;
                    }
                }
            });
            adapter = new MovieAdapter(getActivity());
            adapter.setMovies(models);
            rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovie.setHasFixedSize(true);
            rvMovie.setAdapter(adapter);
        }
    }

    @NonNull
    private static MovieViewModel obtainViewModel(FragmentActivity activity) {

        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }
}