package xyz.webflutter.myandroidjetpackpro.ui.tvshow.favorite;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import xyz.webflutter.myandroidjetpackpro.R;
import xyz.webflutter.myandroidjetpackpro.adapter.TvShowFavAdapter;
import xyz.webflutter.myandroidjetpackpro.utils.ViewModelFactory;

import static android.content.ContentValues.TAG;

public class TvShowFavFragment extends Fragment {

    private RecyclerView rvFavMovie;
    private ProgressBar progressBar;
    private TvShowFavAdapter adapter;

    public static TvShowFavFragment newInstance() {
        return new TvShowFavFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_fav_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavMovie = view.findViewById(R.id.rv_movie_fav);
        progressBar = view.findViewById(R.id.progress_movie_fav);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null){
            TvShowFavViewModel mViewModel = obtainViewModels(getActivity());
            adapter = new TvShowFavAdapter(getActivity());
            mViewModel.getFavoriteTvShow().observe(this, itemFavorite -> {
                if (itemFavorite != null) {
                    switch (itemFavorite.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            Log.e(TAG, "onActivityCreated: " + itemFavorite.data );
                            progressBar.setVisibility(View.GONE);
                                adapter.submitList(itemFavorite.data);
                                adapter.notifyDataSetChanged();
                            break;
                        case FAILED:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), R.string.some_mistake, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            rvFavMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavMovie.setHasFixedSize(true);
            rvFavMovie.setAdapter(adapter);
        }
    }
    @NonNull
    private static TvShowFavViewModel obtainViewModels(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TvShowFavViewModel.class);
    }
}
